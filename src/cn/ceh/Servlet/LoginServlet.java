package cn.ceh.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.User;
import cn.ceh.dao.*;

/**
 * Servlet implementation class loginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String user_id=request.getParameter("userid");
		String password=request.getParameter("password");
		String user_type=request.getParameter("user_type");
		System.out.println(user_id);
		HttpSession ses = request.getSession();				
		User user=new User();
		user.setUser_id(user_id);
		user.setUser_password(password);
		user.setUser_type(user_type);
		Userdao dao=new Userdao();		
		try {
			if(dao.check(user))
			{
				user=dao.findname(user_id);
				ses.setAttribute("SesUser", user);
				response.sendRedirect("index.jsp");
			}
			else
			{
				ses.setAttribute("error", "yes");
				response.sendRedirect("/Webtest/page/login/login.jsp");
				//System.out.println("sss");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
