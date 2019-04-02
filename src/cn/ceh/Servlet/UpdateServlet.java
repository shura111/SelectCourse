package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Course;
import cn.ceh.dao.Coursedao;

/**
 * Servlet implementation class updateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();

		String acid = request.getParameter("id");
		String acname = request.getParameter("acname");
		String actime = request.getParameter("actime");
		String acplace = request.getParameter("acplace");
		String teacher = request.getParameter("teacher");
		String college = request.getParameter("college");
		String actype = request.getParameter("actype");
		double credit = Double.parseDouble(request.getParameter("credit"));
		String state = request.getParameter("state");
		int count = Integer.parseInt(request.getParameter("count"));
		String cterm = request.getParameter("cterm");
		
		String time = request.getParameter("time"); // 未修改前时间
		String place = request.getParameter("place"); // 未修改前地点
		String term=request.getParameter("term");	//未修改前学期
		Course ac = new Course();
		ac.setcourse_id(acid);
		ac.setcourse_name(acname);
		ac.setcourse_time(actime);
		ac.setcourse_place(acplace);
		ac.setcourse_teacher(teacher);
		ac.setcourse_academy(college);
		ac.setcourse_type(actype);
		ac.setcourse_credit(credit);
		ac.setcourse_state(state);
		ac.setcourse_sum(count);
		ac.setcourse_term(cterm);

		Coursedao dao = new Coursedao();
		if ((actime.equals(time)&&(cterm.equals(term))) && (!acplace.equals(place))) // 时间未修改 地点修改
		{
			if (!dao.cheakaddress(actime, teacher,cterm)) {
				try {

					if (dao.update(ac)) {
						ses.setAttribute("acadd", "updatesuc");
						response.sendRedirect("SelectAcourseServlet");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ses.setAttribute("acadd", "place");
				response.sendRedirect("/Webtest/page/acourse/acourseUpdate.jsp?aid=" + acid);
			}
		}

		else if ((!actime.equals(time)||(!cterm.equals(term))) && (acplace.equals(place))) // 时间修改 地点未修改
		{
			if (!dao.cheaktime(actime, teacher, cterm)) {
				try {

					if (dao.update(ac)) {
						ses.setAttribute("acadd", "updatesuc");
						response.sendRedirect("SelectAcourseServlet");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ses.setAttribute("acadd", "time");
				response.sendRedirect("/Webtest/page/acourse/acourseUpdate.jsp?aid=" + acid);
			}
		}

		else if ((actime.equals(time)&&(cterm.equals(term))) && (acplace.equals(place))) // 时间未修改 地点未修改
		{
			try {

				if (dao.update(ac)) {
					ses.setAttribute("acadd", "updatesuc");
					response.sendRedirect("SelectAcourseServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		else if (!dao.cheaktime(actime, teacher, cterm)) // 时间地点都修改
		{
			if (!dao.cheakaddress(actime, acplace,cterm)) {
				try {
					if (dao.update(ac)) {
						ses.setAttribute("acadd", "updatesuc");
						response.sendRedirect("SelectAcourseServlet");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ses.setAttribute("acadd", "place");
				response.sendRedirect("/Webtest/page/acourse/acourseUpdate.jsp?aid=" + acid);
			}
		}
		
		else {
			ses.setAttribute("acadd", "time");
			response.sendRedirect("/Webtest/page/acourse/acourseUpdate.jsp?aid=" + acid);
		}
	}
}
