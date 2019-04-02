package cn.ceh.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Teacher;
import cn.ceh.dao.Teacherdao;

/**
 * Servlet implementation class SelectTeacherServlet
 */
public class SelectTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession ses = request.getSession();
		String word=request.getParameter("word");
		System.out.println("word="+word);
		if(word!=null)
		{
			int pageNo = 1;
			Teacherdao dao=new Teacherdao();
			ArrayList<Teacher> lists=new ArrayList<Teacher>();
			String pageno=request.getParameter("pageNos");
			int recordCount=1;
			if(pageno != null){
			pageNo=Integer.parseInt(pageno);
			}
			else{
				pageNo=1;
			}
			try {
				lists=dao.findall(word, pageNo);
				recordCount=dao.getfindPage(word);
				if(lists!=null)
				{
					//System.out.println(pageNo);
					ses.setAttribute("word", word);
					ses.setAttribute("pageNo", pageNo);
					ses.setAttribute("recordCount", recordCount);
					ses.setAttribute("Teacherlists", lists);
					response.sendRedirect("/Webtest/page/teacher/teacherList.jsp");
				}
				else {
						ses.setAttribute("tea", "false");
						response.sendRedirect("/Webtest/page/teacher/teacherList.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			int pageNo = 1;
			Teacherdao dao=new Teacherdao();
			ArrayList<Teacher> lists=new ArrayList<Teacher>();
			String pageno=request.getParameter("pageNos");
			int recordCount=1;
			if(pageno != null){
			pageNo=Integer.parseInt(pageno);
			}
			else{
				pageNo=1;
			}
			try {
				lists=dao.listTeacher(pageNo);
				recordCount=dao.getPage();
				if(lists!=null)
				{
					//System.out.println(pageNo);
					ses.setAttribute("pageNo", pageNo);
					ses.setAttribute("recordCount", recordCount);
					ses.setAttribute("Teacherlists", lists);
					response.sendRedirect("/Webtest/page/teacher/teacherList.jsp");
				}
				else {
					response.sendRedirect("/Webtest/page/404.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
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
