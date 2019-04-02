package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Student;
import cn.ceh.bean.User;
import cn.ceh.dao.Studentdao;
import cn.ceh.dao.Userdao;
import idhelper.OrderNoUtil;

/**
 * Servlet implementation class AddStudentServlet
 */
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();

		String student_id = OrderNoUtil.getStudentNo();
		String student_name = request.getParameter("sname");
		String student_academy = request.getParameter("sacademy");
		String student_sex = request.getParameter("ssex");
		String student_professional = request.getParameter("sprofessional");
		String student_class = request.getParameter("sclass");
		System.out.println(
				student_id + student_name + student_academy + student_professional + student_class + student_sex);
		Student stu = new Student(student_id, student_name, student_academy, student_professional, student_class,
				student_sex);
		Studentdao studao = new Studentdao();
		Userdao userdao = new Userdao();
		User user = new User(student_id, student_name, "123", "Ñ§Éú");
		try {
			if (studao.addstudent(stu) && userdao.adduser(user)) {
				ses.setAttribute("stu", "addsuc");
				response.sendRedirect("SelectStudentServlet");
			} else {
				response.sendRedirect("/Webtest/page/404.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
