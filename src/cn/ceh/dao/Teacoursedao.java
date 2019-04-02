package cn.ceh.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.ceh.bean.*;
import utilhelper.C3P0Util;

public class Teacoursedao {

	DataSource ds = C3P0Util.getDataSource();
	QueryRunner qr = new QueryRunner(ds);

	/**
	 * 添加tc表记录
	 * 
	 * @param course_id
	 * @param teacher_id
	 * @return
	 */
	public boolean add(String course_id, String teacher_id) {

		String sql = "insert into tc(teacher_id,course_id) values(?,?) ";

		try {
			int num = qr.update(sql, teacher_id, course_id);
			if (num > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * 根据课程id删除课程
	 * 
	 * @param id
	 * @return
	 */
	public boolean Cdelete(String id) {
		String sql = "delete from tc where course_id=?";
		try {
			qr.update(sql, id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 根据教师id删除记录
	 * 
	 * @param id
	 * @return
	 */
	public boolean Tdelete(String id) {
		String sql = "delete from tc where teacher_id=?";
		try {
			qr.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 根据教师id查找该教师学院已审核课程
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Course> getteacourse(String id, String term) {
		String sql = "select * from course where course_state = '已审核' and course_term LIKE CONCAT('%', ?, '%') and course_academy in (select teacher_academy from teacher where teacher_id=?)";
		try {
			List<Course> query = qr.query(sql, new BeanListHandler<Course>(Course.class), term, id);
			if (query != null) {
				return (ArrayList<Course>) query;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 教师选课检查时间是否冲突 冲突返回true
	 * 
	 * @param cid
	 * @param tid
	 * @return
	 */
	public boolean checktime(String cid, String tid) {

		String sql = "SELECT * from course WHERE course_id =? AND course_time in(SELECT course_time from course WHERE course_id in(select course_id from tc WHERE teacher_id=?))";
		try {
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class), cid, tid);
			if (list != null && list.size() > 0) {
				System.out.println("true");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
	/**
	 * 改变课程的授课教师
	 * @param state
	 * @param cid
	 * @param name
	 * @return
	 */
	public boolean changename(String state,String cid, String name)
	{
		if (state.equals("false")) // 退选
		{
			String sql="update course set course_teacher ='' where course_id=?";
			try {
				int update = qr.update(sql, cid);
				if(update>0)
				{
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (state.equals("true")) // 选课
		{
			String sql="update course set course_teacher =? where course_id=?";
			try {
				int update = qr.update(sql,name,cid);
				if(update>0)
				{
					System.out.println("aaa");
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * 教师选课与退选
	 * @return
	 * @throws Exception
	 */
	public boolean update(String cid, String tid, String state) {
		String sql2 ="";
		if (state.equals("false")) // 退选
		{
			sql2 = "delete from tc where teacher_id=? and course_id=?";
		} else if (state.equals("true")) // 选课
		{
			sql2 = "insert into tc(teacher_id,course_id) values(?,?)";
		}
		int update2=0;
		try {
			update2 = qr.update(sql2, tid, cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (update2 > 0) {
			return true;
		}
		return false;
	}
	@Test
	public void test() {
		Teacoursedao dao=new Teacoursedao();
		boolean b = dao.update("3aeba6b2-1f", "222637313","true");
		System.out.println(b);
	}
}
