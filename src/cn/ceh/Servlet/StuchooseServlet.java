package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.User;
import cn.ceh.dao.Stucoursedao;

/**
 * Servlet implementation class UpdateStucourseServlet
 */
public class StuchooseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuchooseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();
		
		String state=request.getParameter("state");
		String cid=request.getParameter("id");
		User user=(User) ses.getAttribute("SesUser");
		String sid=user.user_id;
		System.out.println(state);
		System.out.println(cid);
		System.out.println(sid);
		
		Stucoursedao dao=new Stucoursedao();
		if(state.equals("true"))
		{
			if(!dao.checktime(cid, sid))
			{
				if(dao.update(sid, cid, state))
				{
					response.getWriter().print("1");
				}
			}
			else
			{
				response.getWriter().print("2");	//Ê±¼ä³åÍ»
			}
		}
		else
		{
			if(dao.update(cid, sid, state))
			{
				response.getWriter().print("1");
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
