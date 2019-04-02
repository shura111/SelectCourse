package cn.ceh.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Course;
import cn.ceh.bean.User;
import cn.ceh.dao.Coursedao;

/**
 * Servlet implementation class SelectCourseServlet
 */
public class SelectCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession ses = request.getSession();
		
		Coursedao dao=new Coursedao();
		User user=(User) ses.getAttribute("SesUser");
		if(user==null)
		{
			ses.setAttribute("sessiondestory", "yes");
			response.sendRedirect("/Webtest/page/login/relay.jsp");
		}	
		else
		{
			ArrayList<Course> list = new ArrayList<>();
			list=dao.getcourse(user.getUser_id());
			ses.setAttribute("courselist", list);
			response.sendRedirect("/Webtest/page/sc/courseList.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
