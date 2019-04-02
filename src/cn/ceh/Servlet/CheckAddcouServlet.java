package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ceh.dao.Coursedao;

/**
 * Servlet implementation class CheckAddcouServlet
 */
public class CheckAddcouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAddcouServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		String cplace=request.getParameter("cplace");
		String ctime=request.getParameter("ctime");
		String teacher=request.getParameter("teacher");
		String term=request.getParameter("term");
		System.out.println(cplace);
		System.out.println(ctime);
		System.out.println(teacher);
		System.out.println(term);
		Coursedao dao=new Coursedao();
		if(teacher!=null&&teacher.length()>0)
		{
			if(!dao.cheaktime(ctime,teacher,term))
			{
				if (!dao.cheakaddress(ctime,cplace,term)) {	
					System.out.println("suc");
					response.getWriter().print("suc");
				}
				else
				{
					System.out.println("place");
					response.getWriter().print("place");
				}
			}
			else
			{
				System.out.println("time");
				response.getWriter().print("time");
			}
		}
		else
		{
			System.out.println("aa");
			if (!dao.cheakaddress(ctime,cplace,term)) {	
				System.out.println("suc");
				response.getWriter().print("suc");
			}
			else
			{
				System.out.println("place");
				response.getWriter().print("place");
			}
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
