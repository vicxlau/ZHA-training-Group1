package cn.oocl.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// @WebServlet("/AccountServlet") xml配置
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * get方法临时用来处理内网中页面与页面之前的跳转,后面学了Spring MVC替换
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		request.getRequestDispatcher("/WEB-INF/"+url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1: 获取UI页面的数据
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
//		Account account=new Account();
//		account.setName(name);
//		account.setPass(pass);
//		boolean isOk=AccountService.login(account);
//		if()  // 跳转到页面
		// 2: 根据需求调用service
		// 3: 跳转到目标页面
		if(name.equals("admin") && pass.equals("admin")){
			// 登录成功,跳转到后台首页(登录的个人数据要存储到session中)
			request.getSession().setAttribute("name", name);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
			dispatcher.forward(request, response);
			// 访问WEB-INF文件夹的页面只能用forward方式
			// response.sendRedirect(request.getContextPath() + "/WEB-INF/index.jsp");
		}else{
			// 登录失败,重新跳到登录页面,并且显示错误消息
			request.setAttribute("error", "登录失败!");
			// 重定向,让客户在去访问 login.jsp, 如果当前的Servlet与目标页面Login有数据共享则采用forward方式
			// response.sendRedirect(request.getContextPath() + "/login.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}

	}

}
