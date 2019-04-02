package cn.ceh.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 管理员开启选课
 */
public class OpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenServlet() {
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
		String id=request.getParameter("id");
		if(id.equals("tea"))
		{
			if(state.equals("true")) 	//开启教师选课
			{
				ses.setAttribute("teaopen", "yes");
				if(ses.getAttribute("teaopen") != null)
				{
					response.getWriter().print("2");
				}
			}
			if(state.equals("false"))
			{
				ses.removeAttribute("teaopen");
				if(ses.getAttribute("teaopen") == null)
				{
					response.getWriter().print("3");
				}
			}
		}
		else if(id.equals("stu"))
		{
			if(state.equals("true"))	//开启学生选课
			{
				ses.setAttribute("stuopen", "yes");
				if(ses.getAttribute("stuopen") != null)
				{
					response.getWriter().print("2");
				}
			}
			if(state.equals("false"))
			{
				ses.removeAttribute("stuopen");
				if(ses.getAttribute("stuopen") == null)
				{
					response.getWriter().print("3");
				}
			}
		}
		else if(id.equals("out"))
		{
			if(state.equals("true")) 	//公布选课结果
			{
				ses.setAttribute("out", "yes");
				if(ses.getAttribute("out") != null)
				{
					response.getWriter().print("2");
				}
			}
			if(state.equals("false"))
			{
				ses.removeAttribute("out");
				if(ses.getAttribute("out") == null)
				{
					response.getWriter().print("3");
				}
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
