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
 * Servlet implementation class UpdateCourseServlet
 */
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCourseServlet() {
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
		String term = request.getParameter("term");
		
		
		String time = request.getParameter("time"); // δ�޸�ǰʱ��
		String place = request.getParameter("place"); // δ�޸�ǰ�ص�
		String cterm=request.getParameter("cterm");	//δ�޸�ǰѧ��
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
		ac.setcourse_term(term);
		
		Coursedao dao = new Coursedao();
		if ((actime.equals(time)&&(cterm.equals(term))) && (!acplace.equals(place))) // ʱ��δ�޸� �ص��޸�
		{
			if (!dao.cheakaddress(actime, teacher,term)) {
				try {
					if (dao.update(ac)) {
						ses.setAttribute("cou", "updatesuc");
						response.sendRedirect("SelectAllcourseServlet");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ses.setAttribute("cou", "place");
				response.sendRedirect("/Webtest/page/course/courseUpdate.jsp?aid=" + acid);
			}
		}

		else if ((!actime.equals(time)||(!cterm.equals(term))) && (acplace.equals(place))) // ʱ���޸� �ص�δ�޸�
		{
			if (!dao.cheaktime(actime, teacher, term)) {
				try {

					if (dao.update(ac)) {
						ses.setAttribute("cou", "updatesuc");
						response.sendRedirect("SelectAllcourseServlet");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ses.setAttribute("cou", "time");
				response.sendRedirect("/Webtest/page/course/courseUpdate.jsp?aid=" + acid);
			}
		}

		else if ((actime.equals(time)&&(cterm.equals(term))) && (acplace.equals(place))) // ʱ��δ�޸� �ص�δ�޸�
		{
			try {

				if (dao.update(ac)) {
					ses.setAttribute("cou", "updatesuc");
					response.sendRedirect("SelectAllcourseServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		else if (!dao.cheaktime(actime, teacher, term)) // ʱ��ص㶼�޸�
		{
			if (!dao.cheakaddress(actime, acplace,term)) {
				try {
					if (dao.update(ac)) {
						ses.setAttribute("cou", "updatesuc");
						response.sendRedirect("SelectAllcourseServlet");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ses.setAttribute("cou", "place");
				response.sendRedirect("/Webtest/page/course/courseUpdate.jsp?aid=" + acid);
			}

		}
		
		else {
			ses.setAttribute("cou", "time");
			response.sendRedirect("/Webtest/page/course/courseUpdate.jsp?aid=" + acid);
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
