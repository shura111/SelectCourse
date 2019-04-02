package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Teacher;
import cn.ceh.bean.User;
import cn.ceh.dao.Teacherdao;
import cn.ceh.dao.Userdao;

/**
 * Servlet implementation class UpdateTeacherServlet
 */
public class UpdateTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();

		String teacher_id = request.getParameter("id");
		String teacher_name=request.getParameter("tname");
		int teacher_age=Integer.parseInt(request.getParameter("tage"));
		String teacher_office=request.getParameter("toffice");
		String teacher_academy=request.getParameter("tacademy");
		String teacher_sex=request.getParameter("tsex");
		
		Teacher tea=new Teacher(teacher_id,teacher_name,teacher_age,teacher_office,teacher_academy,teacher_sex);
		Teacherdao tdao=new Teacherdao();
		Userdao userdao = new Userdao();
		User user = new User(teacher_id, teacher_name, "123", "ΩÃ ¶");
		try {
			if(tdao.update(tea)&&userdao.update(user))
			{
				ses.setAttribute("tea", "updatesuc");
				response.sendRedirect("SelectTeacherServlet");
			}else {
				response.sendRedirect("/Webtest/page/404.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
