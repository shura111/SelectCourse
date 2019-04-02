package cn.ceh.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Student;
import cn.ceh.dao.Studentdao;

/**
 * Servlet implementation class SelectStudentServlet
 */
public class SelectStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStudentServlet() {
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
			Studentdao Sc=new Studentdao();
			ArrayList<Student> lists=new ArrayList<Student>();
			String pageno=request.getParameter("pageNos");
			int recordCount=1;
			if(pageno != null){
			pageNo=Integer.parseInt(pageno);
			}
			else{
				pageNo=1;
			}
			try {
				lists=Sc.findall(word, pageNo);
				recordCount=Sc.getfindPage(word);
				if(lists!=null)
				{
					ses.setAttribute("word", word);
					ses.setAttribute("pageNo", pageNo);
					ses.setAttribute("recordCount", recordCount);
					ses.setAttribute("Studentlists", lists);
					response.sendRedirect("/Webtest/page/student/studentList.jsp");
				}
				else {
					ses.setAttribute("tea", "false");
					response.sendRedirect("/Webtest/page/student/studentList.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			int pageNo = 1;
			Studentdao Sc=new Studentdao();
			ArrayList<Student> lists=new ArrayList<Student>();
			String pageno=request.getParameter("pageNos");
			int recordCount=1;
			if(pageno != null){
			pageNo=Integer.parseInt(pageno);
			}
			else{
				pageNo=1;
			}
			try {
				lists=Sc.listStudent(pageNo);
				recordCount=Sc.getPage();
				if(lists!=null)
				{
					ses.setAttribute("pageNo", pageNo);
					ses.setAttribute("recordCount", recordCount);
					ses.setAttribute("Studentlists", lists);
					response.sendRedirect("/Webtest/page/student/studentList.jsp");
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
