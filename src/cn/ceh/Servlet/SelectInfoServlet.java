package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Student;
import cn.ceh.bean.Teacher;
import cn.ceh.bean.User;
import cn.ceh.dao.Studentdao;
import cn.ceh.dao.Teacherdao;

/**
 * Servlet implementation class SelectInfoServlet
 */
public class SelectInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession ses= request.getSession();
		User user=(User) ses.getAttribute("SesUser");
		if(user.user_type.equals("教师"))
		{
			Teacherdao dao=new Teacherdao();
			Teacher teacher = dao.find(user.user_id);
			ses.setAttribute("Info", teacher);
			response.sendRedirect("/Webtest/page/user/userInfo.jsp");
		}
		else if(user.user_type.equals("学生"))
		{
			Studentdao dao=new Studentdao();
			Student student = dao.find(user.user_id);
			ses.setAttribute("Info", student);
			response.sendRedirect("/Webtest/page/user/userInfo.jsp");
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
