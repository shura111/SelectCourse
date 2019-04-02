package cn.ceh.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Course;
import cn.ceh.bean.User;
import cn.ceh.dao.Teacoursedao;

/**
 * Servlet implementation class SelectTcourseServlet
 */
public class SelectTcourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectTcourseServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession ses = request.getSession();
		
		if(ses.getAttribute("teaopen") != null)
		{
			System.out.println((String)ses.getAttribute("teaopen"));
			int month=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));		//获取当前月份
			System.out.println(month);
			String term="";
			if(month>=1&&month<=3) {
				term="下学期";
			}
			else if(month>=6&&month<=7) {
				term="上学期";
			}
			System.out.println(term);
			User user=(User) ses.getAttribute("SesUser");
			Teacoursedao dao=new Teacoursedao();
			ArrayList<Course> list = dao.getteacourse(user.user_id,term);
			for(Course o:list)
			{
				System.out.println(o.course_id);
			}
			if(list!=null&&list.size()>0)
			{
				ses.setAttribute("list", list);
				response.sendRedirect("/Webtest/page/course/selectTList.jsp");
			}
			else
			{
				ses.setAttribute("cou", "zero");
				response.sendRedirect("/Webtest/page/course/selectTList.jsp");
			}
		}
		else {
			response.sendRedirect("/Webtest/page/noselect.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
