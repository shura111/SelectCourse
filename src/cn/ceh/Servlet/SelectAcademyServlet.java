package cn.ceh.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ceh.bean.Academy;
import cn.ceh.dao.Academydao;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class SelectAcademyServlet
 */
public class SelectAcademyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAcademyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
	
		
		System.out.println("½øÈë");
		List<Academy> pros= new ArrayList<Academy>();
		Academydao dao=new Academydao();
		pros=dao.findacademy();
		System.out.println(pros);
		JSONArray jsonArray = JSONArray.fromObject(pros);
		System.out.println(jsonArray);
		response.getWriter().print(jsonArray.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
