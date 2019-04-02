package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.User;
import cn.ceh.dao.Teacoursedao;

/**
 * Servlet implementation class TeachooseServlet
 */
public class TeachooseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeachooseServlet() {
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
		String tid=user.user_id;
		String name=user.user_name;
		System.out.println(state);
		System.out.println(cid);
		System.out.println(tid);
		
		Teacoursedao dao=new Teacoursedao();
		if(state.equals("true"))
		{
			if(!dao.checktime(cid, tid))
			{
				if(dao.update(cid, tid, state)&&dao.changename(state, cid, name))
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
			if(dao.update(cid, tid, state)&&dao.changename(state, cid, name))
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
