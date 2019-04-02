package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ceh.dao.Coursedao;

/**
 * Servlet implementation class UpdateStateServlet
 */
public class UpdateStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String state=request.getParameter("state");
		String id=request.getParameter("id");
		System.out.println(state);
		System.out.println(id);
		Coursedao dao=new Coursedao();
		if(state.equals("true"))
		{
			System.out.println("true");
			boolean b = dao.updatestate("“—…Û∫À", id);
			if(b)
			{
				response.getWriter().print("1");
			}
			else
			{
				response.getWriter().print("2");
			}
			
		}
		if(state.equals("false"))
		{
			boolean b = dao.updatestate("…Í«Î÷–", id);
			if(b)
			{
				response.getWriter().print("1");
			}
			else
			{
				response.getWriter().print("2");
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
