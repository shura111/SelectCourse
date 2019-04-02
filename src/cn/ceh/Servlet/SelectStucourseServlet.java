package cn.ceh.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Course;
import cn.ceh.bean.User;
import cn.ceh.dao.Stucoursedao;

/**
 * Servlet implementation class SelectStucourseServlet
 */
public class SelectStucourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStucourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();
		
		if(ses.getAttribute("out") != null)
		{
			String word=request.getParameter("word");
			User user=(User) ses.getAttribute("SesUser");
			String id=user.user_id;
			if(word!=null)
			{
				int pageNo = 1;
				Stucoursedao Sc=new Stucoursedao();
				ArrayList<Course> lists=new ArrayList<Course>();
				String pageno=request.getParameter("pageNos");
				int recordCount=1;
				if(pageno != null){
				pageNo=Integer.parseInt(pageno);
				}
				try {
					lists=Sc.getstucourse(pageNo,id,word);
					recordCount=Sc.getstucoursePage(id,word);
					if(lists!=null)
					{
						ses.setAttribute("word", word);
						ses.setAttribute("pageNo", pageNo);
						ses.setAttribute("recordCount", recordCount);
						ses.setAttribute("courselist", lists);
						response.sendRedirect("/Webtest/page/course/stucourseList.jsp");
					}
					else {
						ses.setAttribute("cou", "false");
						response.sendRedirect("/Webtest/page/course/stucourseList.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
			{
				int pageNo = 1;
				Stucoursedao Sc=new Stucoursedao();
				ArrayList<Course> lists=new ArrayList<Course>();
				String pageno=request.getParameter("pageNos");
				int recordCount=1;
				if(pageno != null){
				pageNo=Integer.parseInt(pageno);
				}
				try {
					lists=Sc.getstucourse(pageNo, id);
					recordCount=Sc.getstucoursePage(id);
					ses.setAttribute("pageNo", pageNo);
					ses.setAttribute("recordCount", recordCount);
					ses.setAttribute("courselist", lists);
					response.sendRedirect("/Webtest/page/course/stucourseList.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else {
			response.sendRedirect("/Webtest/page/noselect.jsp");
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
