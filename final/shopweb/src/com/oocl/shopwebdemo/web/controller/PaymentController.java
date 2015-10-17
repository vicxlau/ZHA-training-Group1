package com.oocl.shopwebdemo.web.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oocl.shopwebdemo.model.Cart;
import com.oocl.shopwebdemo.util.*;

public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_CUSTOMER_CONFIRM_PAYMENT = ConfigReader.getSystemValue("URL_CUSTOMER_CONFIRM_PAYMENT");
	private static final String CONTENT_TYPE = "text/html";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * get方法临时用来处理内网中页面与页面之前的跳转,后面学了Spring MVC替换
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		request.getRequestDispatcher("/WEB-INF/" + url).forward(request,
				response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch(request.getParameter("status")){
			case "payment":
				paymentPOST(request,response);
				break;
			case "pay":
				payment(request,response);
				break;
			case "success":
				updateSuccessStatus(request,response);
				break;
		}
	}


	private void updateSuccessStatus(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String keyValue = ConfigReader.getSystemValue("payment-key");
		// 获取所有的明文
		String r0_Cmd = formatString(request.getParameter("r0_Cmd")); 
		String p1_MerId = request.getParameter("p1_MerId");
		String r1_Code = formatString(request.getParameter("r1_Code"));
		String r2_TrxId = formatString(request.getParameter("r2_TrxId"));
		String r3_Amt = formatString(request.getParameter("r3_Amt"));
		String r4_Cur = formatString(request.getParameter("r4_Cur"));
		String r5_Pid = new String(formatString(
				request.getParameter("r5_Pid")).getBytes("iso-8859-1"),
				"UTF-8");
		String r6_Order = formatString(request.getParameter("r6_Order"));
		String r7_Uid = formatString(request.getParameter("r7_Uid"));
		String r8_MP = new String(formatString(
				request.getParameter("r8_MP")).getBytes("iso-8859-1"),
				"UTF-8");
		String r9_BType = formatString(request.getParameter("r9_BType"));
		// 对明文进行数据追加
		String hmac = formatString(request.getParameter("hmac"));
		StringBuffer infoBuffer = new StringBuffer();
		infoBuffer.append(p1_MerId);
		infoBuffer.append(r0_Cmd);
		infoBuffer.append(r1_Code);
		infoBuffer.append(r2_TrxId);
		infoBuffer.append(r3_Amt);
		infoBuffer.append(r4_Cur);
		infoBuffer.append(r5_Pid);
		infoBuffer.append(r6_Order);
		infoBuffer.append(r7_Uid);
		infoBuffer.append(r8_MP);
		infoBuffer.append(r9_BType);
		// 对返回的明文进行加密
		String md5 = DigestUtil.hmacSign(infoBuffer.toString(), keyValue);
		// 判断加密的密文与传过来的数据签名是否相等
		boolean isOK = md5.equals(hmac);
		if (isOK) {
			if (r1_Code.equals("1")) { 
				//把 支付成功的订单状态改成已支付,并个给用户显示支付成功信息
				//调用邮件服务接口,短信发送服务
				out.println("订单编号为:" + r6_Order + "支付金额为:" + r3_Amt);
			}
		} else {
			out.println("fail !!!!");
		}
	}

	private void paymentPOST(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 加密的密钥,由支付中介提供
		Cart pCart = (Cart) request.getSession().getAttribute("previousCart");
		String keyValue = ConfigReader.getSystemValue("payment-key");
		// 1: 给参数赋值
		String p0_Cmd = formatString("Buy");
		String p1_MerId = formatString("10000940764");
		String p2_Order = formatString(pCart.getId().toString());
		String p3_Amt = formatString(pCart.getTotal().toString());
		String p4_Cur = formatString("CNY");
		String p5_Pid = ""; // null 
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://www.163.com";
		String p9_SAF = "0";
		String pa_MP = "";
		String pd_FrpId = formatString(request.getParameter("pd_FrpId"));
		pd_FrpId = pd_FrpId.toUpperCase();
		String pr_NeedResponse = "0";
		
		String hmac = formatString("");
		
		//  解决数据安全性问题:  把明文加密--->密文    然后把明文和密文都交给易宝 
		// 易宝拿到数据后,把传过来的明文加密, 和传过来密文比较,
		// 如果相等数据没有被篡改 (商家与易宝加密时都用的是相同key)
		
		// 把明文数据追加到StringBuffer
		StringBuffer infoBuffer = new StringBuffer();
		infoBuffer.append(p0_Cmd);
		infoBuffer.append(p1_MerId);
		infoBuffer.append(p2_Order);
		infoBuffer.append(p3_Amt);
		infoBuffer.append(p4_Cur);
		infoBuffer.append(p5_Pid);
		infoBuffer.append(p6_Pcat);
		infoBuffer.append(p7_Pdesc);
		infoBuffer.append(p8_Url);
		infoBuffer.append(p9_SAF);
		infoBuffer.append(pa_MP);
		infoBuffer.append(pd_FrpId);
		infoBuffer.append(pr_NeedResponse);
		// 加密后的密文存储到了hmac中
		hmac = DigestUtil.hmacSign(infoBuffer.toString(), keyValue);
		// 把明文和密文都存储到request.setAttribute中
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);
		System.out.println("hmac-->" + hmac);
		
		
		String data = 
				"p0_Cmd=" + URLEncoder.encode( p0_Cmd)+
				"&p1_MerId=" + URLEncoder.encode( p1_MerId)+
				"&p2_Order=" + URLEncoder.encode( p2_Order)+
				"&p3_Amt=" + URLEncoder.encode( p3_Amt)+
				"&p4_Cur=" + URLEncoder.encode( p4_Cur)+
				"&p5_Pid=" + URLEncoder.encode( p5_Pid)+
				"&p6_Pcat=" + URLEncoder.encode( p6_Pcat)+
				"&p7_Pdesc=" + URLEncoder.encode( p7_Pdesc)+
				"&p8_Url=" + URLEncoder.encode( p8_Url)+
				"&p9_SAF=" + URLEncoder.encode( p9_SAF)+
				"&pa_MP=" + URLEncoder.encode( pa_MP)+
				"&pd_FrpId=" + URLEncoder.encode( pd_FrpId)+
				"&pr_NeedResponse=" + URLEncoder.encode( pr_NeedResponse)+
				"&hmac=" + URLEncoder.encode( hmac); 
						
		
		URL url = new URL(ConfigReader.getSystemValue("yeepay-URL"));
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

        //write parameters
        writer.write(data);

        writer.flush();

        // Get the response
        StringBuffer answer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            answer.append(line);
        }
        writer.close();
        reader.close();
		
		
		
		
		//

//		String content1 = 
//				"p0_Cmd=" + URLEncoder.encode( p0_Cmd)+
//				"&p1_MerId=" + URLEncoder.encode( p1_MerId)+
//				"&p2_Order=" + URLEncoder.encode( p2_Order)+
//				"&p3_Amt=" + URLEncoder.encode( p3_Amt)+
//				"&p4_Cur=" + URLEncoder.encode( p4_Cur)+
//				"&p5_Pid=" + URLEncoder.encode( p5_Pid)+
//				"&p6_Pcat=" + URLEncoder.encode( p6_Pcat)+
//				"&p7_Pdesc=" + URLEncoder.encode( p7_Pdesc)+
//				"&p8_Url=" + URLEncoder.encode( p8_Url)+
//				"&p9_SAF=" + URLEncoder.encode( p9_SAF)+
//				"&pa_MP=" + URLEncoder.encode( pa_MP)+
//				"&pd_FrpId=" + URLEncoder.encode( pd_FrpId)+
//				"&pr_NeedResponse=" + URLEncoder.encode( pr_NeedResponse)+
//				"&hmac=" + URLEncoder.encode( hmac); 
//						
//
//		URL url1 = new URL(Locale.getSystemValue("yeepay-URL"));
//		HttpURLConnection connection = (HttpURLConnection)url1.openConnection();                
//		connection.setDoOutput(true);
//		connection.setRequestMethod("POST");
////		connection.setRequestProperty("Content-Length", "" + sb.length());
//
//		OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
//		outputWriter.write(content1);
//		outputWriter.flush();
//		outputWriter.close();
		//
		
//		response.setContentType(CONTENT_TYPE);
//		URL	url;
//		URLConnection    urlConn;
//		DataOutputStream cgiInput;
//		// URL of target page script.
//		url = new URL(Locale.getSystemValue("yeepay-URL"));
//		urlConn = url.openConnection();
//
//		urlConn.setDoInput(true);
//		urlConn.setDoOutput(true);
//		urlConn.setUseCaches(false);
//		urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//
//		// Send POST output.
//		cgiInput = new DataOutputStream(urlConn.getOutputStream());
//
//		String content = 
//				"p0_Cmd=" + URLEncoder.encode( p0_Cmd)+
//				"&p1_MerId=" + URLEncoder.encode( p1_MerId)+
//				"&p2_Order=" + URLEncoder.encode( p2_Order)+
//				"&p3_Amt=" + URLEncoder.encode( p3_Amt)+
//				"&p4_Cur=" + URLEncoder.encode( p4_Cur)+
//				"&p5_Pid=" + URLEncoder.encode( p5_Pid)+
//				"&p6_Pcat=" + URLEncoder.encode( p6_Pcat)+
//				"&p7_Pdesc=" + URLEncoder.encode( p7_Pdesc)+
//				"&p8_Url=" + URLEncoder.encode( p8_Url)+
//				"&p9_SAF=" + URLEncoder.encode( p9_SAF)+
//				"&pa_MP=" + URLEncoder.encode( pa_MP)+
//				"&pd_FrpId=" + URLEncoder.encode( pd_FrpId)+
//				"&pr_NeedResponse=" + URLEncoder.encode( pr_NeedResponse)+
//				"&hmac=" + URLEncoder.encode( hmac); 
//						
//		cgiInput.writeBytes(content);
//		cgiInput.flush();
//		cgiInput.close();
//
//		// reads the CGI response and print it inside the servlet content
//		BufferedReader cgiOutput = 
//		    new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
//		PrintWriter    servletOutput = response.getWriter();        
//		System.out.println(url + " \n" + content);
//		String line = null;
//		while (null != (line = cgiOutput.readLine())){
//		    servletOutput.println(line);
//		}
//		cgiOutput.close();
//		servletOutput.close();
		
//		request.getRequestDispatcher(URL_CUSTOMER_CONFIRM_PAYMENT).forward(request,
//				response);
	}
	
	private void payment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 加密的密钥,由支付中介提供
		Cart pCart = (Cart) request.getSession().getAttribute("previousCart");
		String keyValue = ConfigReader.getSystemValue("payment-key");
		// 1: 给参数赋值
		String p0_Cmd = formatString("Buy");
		String p1_MerId = formatString("10000940764");
		String p2_Order = formatString(pCart.getId().toString());
		String p3_Amt = formatString(pCart.getTotal().toString());
		String p4_Cur = formatString("CNY");
		String p5_Pid = ""; // null 
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://www.163.com";
		String p9_SAF = "0";
		String pa_MP = "";
		String pd_FrpId = formatString(request.getParameter("pd_FrpId"));
		pd_FrpId = pd_FrpId.toUpperCase();
		String pr_NeedResponse = "0";
		
		String hmac = formatString("");
		
		//  解决数据安全性问题:  把明文加密--->密文    然后把明文和密文都交给易宝 
		// 易宝拿到数据后,把传过来的明文加密, 和传过来密文比较,
		// 如果相等数据没有被篡改 (商家与易宝加密时都用的是相同key)
		
		// 把明文数据追加到StringBuffer
		StringBuffer infoBuffer = new StringBuffer();
		infoBuffer.append(p0_Cmd);
		infoBuffer.append(p1_MerId);
		infoBuffer.append(p2_Order);
		infoBuffer.append(p3_Amt);
		infoBuffer.append(p4_Cur);
		infoBuffer.append(p5_Pid);
		infoBuffer.append(p6_Pcat);
		infoBuffer.append(p7_Pdesc);
		infoBuffer.append(p8_Url);
		infoBuffer.append(p9_SAF);
		infoBuffer.append(pa_MP);
		infoBuffer.append(pd_FrpId);
		infoBuffer.append(pr_NeedResponse);
		// 加密后的密文存储到了hmac中
		hmac = DigestUtil.hmacSign(infoBuffer.toString(), keyValue);
		// 把明文和密文都存储到request.setAttribute中
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);
		System.out.println("hmac-->" + hmac);
		request.getRequestDispatcher(URL_CUSTOMER_CONFIRM_PAYMENT).forward(request,
				response);
	}

	private String formatString(String text) {
		if (text == null) {
			return "";
		}
		return text;
	}
}
