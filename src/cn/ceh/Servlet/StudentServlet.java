package cn.ceh.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Student;
import cn.ceh.dao.Stucoursedao;



/**
 * 教师查看授课学生名单
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		HttpSession ses = request.getSession();
		String id=request.getParameter("id");
		//System.out.println(id);
 	 	int pageNo = 1;
		Stucoursedao Sc=new Stucoursedao();
		ArrayList<Student> lists=new ArrayList<Student>();
		String pageno=request.getParameter("pageNos");
		int recordCount=1;
		if(pageno != null){
		pageNo=Integer.parseInt(pageno);
		try {
			lists=Sc.listStudent(pageNo,id);
			recordCount=Sc.getPage(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		else
		{
			pageNo=1;
			try {
				lists=Sc.listStudent(pageNo,id);
				recordCount=Sc.getPage(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		ses.setAttribute("id", id);
		ses.setAttribute("pageNo", pageNo);
		ses.setAttribute("recordCount", recordCount);
		ses.setAttribute("Studentlists", lists);
		response.sendRedirect("/Webtest/page/sc/studentList.jsp");
	}

}