package cn.oocl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.oocl.model.ApplicationStat;
import cn.oocl.service.StatisticService;
import cn.oocl.service.imple.StatisticServiceImpl;


// @WebServlet("/AccountServlet") xml配置
public class StatisticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatisticService statisticService = new StatisticServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * get方法临时用来处理内网中页面与页面之前的跳转,后面学了Spring MVC替换
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
//		String url = request.getParameter("url");
//		request.getRequestDispatcher("/WEB-INF/"+url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<ApplicationStat> stat = new ArrayList<ApplicationStat>();
//		// plz get data from DB
//		stat.add(new ApplicationStat("clothes",12));
//		stat.add(new ApplicationStat("electronic",30));
//		stat.add(new ApplicationStat("food",50));
		String season = request.getParameter("name");
//		if(season!=null){
//			System.out.println("season " + season );
//		}
		if (season.equals("spring")){
			String from = "01-MAR-2014";
			String to = "31-MAY-2014";
			List<ApplicationStat> stat = statisticService.query(from, to);
			request.setAttribute("graphData",stat);
			System.out.println(stat.size());
			request.setAttribute("graphTitle","Statistic About Shopweb [SPRING] 2014");
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);			
		}
		else if (season.equals("summer")){
			String from = "01-JUN-2014";
			String to = "31-AUG-2014";
			List<ApplicationStat> stat = statisticService.query(from, to);
			System.out.println(stat.size());
			request.setAttribute("graphData",stat);
			request.setAttribute("graphTitle","Statistic About Shopweb [SUMMER] 2014");
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);			
		}
		else if (season.equals("autumn")){
			String from = "01-SEP-2014";
			String to = "30-NOV-2014";
			List<ApplicationStat> stat = statisticService.query(from, to);
			System.out.println(stat.size());
			request.setAttribute("graphData",stat);
			request.setAttribute("graphTitle","Statistic About Shopweb [AUTUMN] 2014");
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);			
		}
		else if (season.equals("winter")){
			String from = "01-DEC-2014";
			String to = "28-FEB-2015";
			List<ApplicationStat> stat = statisticService.query(from, to);
			System.out.println(stat.size());
			request.setAttribute("graphData",stat);
			request.setAttribute("graphTitle","Statistic About Shopweb [WINTER] 2014-2015");
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);			
		}
		else{
			String from = "01-MAR-2014";
			String to = "28-FEB-2015";
			List<ApplicationStat> stat = statisticService.query(from, to);
			request.setAttribute("graphData",stat);
			request.setAttribute("graphTitle","Statistic About Shopweb 2014-2015");
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}
	}

}
