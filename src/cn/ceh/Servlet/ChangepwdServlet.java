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
 * Servlet implementation class changepwdServlet
 */
public class ChangepwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangepwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession ses= request.getSession();
		User user=(User) ses.getAttribute("SesUser");
		String pwd=user.getUser_password();
		String id=user.getUser_id();
		
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		if (pwd.equals(oldpassword)) {
			System.out.println("aaa");
			if (newpassword != null && newpassword.length() > 0) {
				Userdao dao = new Userdao();
				try {
					if (dao.update(id,newpassword)) {
						ses.setAttribute("pwd", "suc");
						response.sendRedirect("/Webtest/page/user/changePwd.jsp");
					} else {
						ses.setAttribute("pwd", "fail");
						response.sendRedirect("/Webtest/page/user/changePwd.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
