package cn.ceh.Servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.User;
import cn.ceh.dao.Userdao;

/**
 * Servlet implementation class registerServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		HttpSession ses= request.getSession();
		
		if(password.equals(password2))
		{
			//System.out.println(username);
			User user=new User();
			user.setUser_name(username);
			user.setUser_password(password);
			
			Userdao dao=new Userdao();
			
			try {
				if(dao.find(user))
				{
					
					//out.print("用户已存在! 请重新输入!");
					//System.out.println("用户已存在! 请重新输入!");
					//request.setAttribute("register", "no");
					//request.getRequestDispatcher("/page/login/register.jsp").forward(request, response);
					ses.setAttribute("register", "no");
					response.sendRedirect("/Webtest/page/login/register.jsp");
				}
				else 
				{
					try {
						if(dao.adduser(user))
						{
							//ses.setAttribute("register", "yes");
							ses.setAttribute("register", "suc");
							response.sendRedirect("/Webtest/page/login/login.jsp");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
