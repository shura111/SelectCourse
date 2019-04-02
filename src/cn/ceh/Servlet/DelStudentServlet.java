package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.dao.*;


/**
 * Servlet implementation class DelStudentServlet
 */
public class DelStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		HttpSession ses = request.getSession();	
		
		String id=request.getParameter("id");
		Studentdao dao=new Studentdao();
		Stucoursedao Sdao=new Stucoursedao();
		Userdao udao=new Userdao();
		System.out.println(id);
		if(dao.delete(id)&&Sdao.Sdelete(id)&&udao.delete(id))
		{
			ses.setAttribute("stu", "delsuc");
			response.sendRedirect("SelectStudentServlet");
		}
		else
		{
			response.sendRedirect("/Webtest/page/404.jsp");
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
