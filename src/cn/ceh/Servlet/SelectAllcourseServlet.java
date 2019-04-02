package cn.ceh.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Course;
import cn.ceh.dao.Coursedao;

/**
 * Servlet implementation class SelectAllcourseServlet
 */
public class SelectAllcourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAllcourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();
		String word=request.getParameter("word");
		System.out.println("word="+word);
		if(word!=null)
		{
			int pageNo = 1;
	 	 	Coursedao dao=new Coursedao();
			ArrayList<Course> list = new ArrayList<>();
			String pageno=request.getParameter("pageNos");
			int recordCount=1;
			if(pageno != null){
			pageNo=Integer.parseInt(pageno);
			}
			else{
				pageNo=1;
			}
			list=dao.findall(word,pageNo);
			recordCount=dao.getfindPage(word);
			
			if(list==null)
			{
				ses.setAttribute("cou", "false");
				response.sendRedirect("/Webtest/page/course/courseList.jsp");
			}
			else {
				System.out.println("pageNo="+pageNo);
				System.out.println("recordCount="+recordCount);
				ses.setAttribute("word", word);
				ses.setAttribute("pageNo", pageNo);
				ses.setAttribute("recordCount", recordCount);
				ses.setAttribute("courselist", list);
				response.sendRedirect("/Webtest/page/course/courseList.jsp");
			}
		}
		else
		{
			int pageNo = 1;
	 	 	Coursedao dao=new Coursedao();
			ArrayList<Course> list = new ArrayList<>();
			String pageno=request.getParameter("pageNos");
			int recordCount=1;
			if(pageno != null){
			pageNo=Integer.parseInt(pageno);
			}
			else{
				pageNo=1;
			}
			try {
				list=dao.listCourse(pageNo);
				recordCount=dao.getPage();
				System.out.println("pageNo="+pageNo);
				System.out.println("recordCount="+recordCount);
				ses.setAttribute("pageNo", pageNo);
				ses.setAttribute("recordCount", recordCount);
				ses.setAttribute("courselist", list);
				response.sendRedirect("/Webtest/page/course/courseList.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
