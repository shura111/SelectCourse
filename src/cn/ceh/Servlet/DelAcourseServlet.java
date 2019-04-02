package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import cn.ceh.dao.Coursedao;
import cn.ceh.dao.Teacoursedao;

/**
 * Servlet implementation class delServlet
 */
public class DelAcourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelAcourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession ses = request.getSession();	
		
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		System.out.println(id);
		
		Coursedao dao=new Coursedao();
		Teacoursedao Tdao=new Teacoursedao();
		if(dao.delete(id)&&Tdao.Cdelete(id))
		{
			if(flag.equals("1"))
			{
				ses.setAttribute("acadd", "delsuc");
				response.sendRedirect("SelectAcourseServlet");
			}
			else if(flag.equals("2"))
			{
				ses.setAttribute("cou", "delsuc");
				response.sendRedirect("SelectAllcourseServlet");
			}
		}
	}

}
