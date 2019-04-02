package cn.ceh.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Course;
import cn.ceh.bean.User;
import cn.ceh.dao.Coursedao;
import cn.ceh.dao.Teacoursedao;
import idhelper.OrderNoUtil;

/**
 * Servlet implementation class appServlet
 */
public class AcaddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcaddServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();	
		
		User user=(User) ses.getAttribute("SesUser");
		String teacher_id=user.getUser_id();
		String course_id=OrderNoUtil.getCourseNo();
		String cname=request.getParameter("cname");
		String ctime=request.getParameter("ctime");
		String cplace=request.getParameter("cplace");
		String teacher=request.getParameter("teacher");
		String college=request.getParameter("college");
		String ctype=request.getParameter("ctype");
		double credit=Double.parseDouble(request.getParameter("credit"));
		String state=request.getParameter("state");
		String term=request.getParameter("term");
		int count=Integer.parseInt(request.getParameter("count"));			
		
		Course ac=new Course(course_id,cname,ctime,cplace,credit,teacher,count,college,ctype,state,term);
		Coursedao dao=new Coursedao();
		String flag=request.getParameter("flag");
		System.out.println(flag);
		if(flag.equals("1"))
		{
			Teacoursedao Tdao=new Teacoursedao();
			try {
					if (dao.addcourse(ac)&&Tdao.add(course_id, teacher_id)) {
						ses.setAttribute("acadd", "addsuc");
						response.sendRedirect("SelectAcourseServlet");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		else if(flag.equals("2"))
		{
			try {
				if (dao.addcourse(ac)) {
					ses.setAttribute("cou", "addsuc");
					response.sendRedirect("SelectAllcourseServlet");
				}
			} catch (Exception e) {
						e.printStackTrace();
			}
			
		}
		
	}

}
