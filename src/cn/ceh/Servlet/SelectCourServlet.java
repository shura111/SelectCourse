package cn.ceh.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ceh.bean.Course;
import cn.ceh.bean.User;
import cn.ceh.dao.Coursedao;
import cn.ceh.dao.Stucoursedao;

/**
 * Servlet implementation class SelectCourServlet
 */
public class SelectCourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCourServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession ses = request.getSession();
		
		
		if(ses.getAttribute("stuopen") != null)
		{
			System.out.println((String)ses.getAttribute("teaopen"));
			User user=new User();
			user=(User)ses.getAttribute("SesUser");
			int grade=Integer.parseInt((user.user_id).toString().substring(0, 2));			//获得学生年级
			int year =Integer.parseInt(new SimpleDateFormat("yy").format(new Date()));	    //获取当前年份
			int month=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));		//获取当前月份
			System.out.println("grade="+grade+"  "+"year="+year+"  "+"month="+month);
			grade=year-grade;		//判断学生是大几的
			String term="";
			if(month>=1&&month<=3)
			{
				switch(grade) {
					case 1:term="大一下学期";break;
					case 2:term="大二下学期";break;
					case 3:term="大三下学期";break;
					case 4:term="大四下学期";break;
					default: term="";break;
				}
			}
			else if(month>=6&&month<=7)
			{
				switch(grade) {
					case 1:term="大一上学期";break;
					case 2:term="大二上学期";break;
					case 3:term="大三上学期";break;
					case 4:term="大四上学期";break;
					default: term="";break;
				}
			}
			System.out.println(term);
			Coursedao dao=new Coursedao();
			Stucoursedao sdao=new Stucoursedao();
			ArrayList<Course> list2 = sdao.getstucourse(user.user_id, term);	//学生下学期已选课程列表
			ArrayList<Course> list = dao.getcour(term);
			if(list!=null&&list.size()>0)
			{
				ses.setAttribute("courselist", list);
				ses.setAttribute("list2", list2);
				response.sendRedirect("/Webtest/page/course/selectCList.jsp");
			}
			else
			{
				ses.setAttribute("cou", "zero");
				response.sendRedirect("/Webtest/page/course/selectCList.jsp");
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
