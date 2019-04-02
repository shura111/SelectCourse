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
	 * ���tc���¼
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
	 * ���ݿγ�idɾ���γ�
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
	 * ���ݽ�ʦidɾ����¼
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
	 * ���ݽ�ʦid���Ҹý�ʦѧԺ����˿γ�
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Course> getteacourse(String id, String term) {
		String sql = "select * from course where course_state = '�����' and course_term LIKE CONCAT('%', ?, '%') and course_academy in (select teacher_academy from teacher where teacher_id=?)";
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
	 * ��ʦѡ�μ��ʱ���Ƿ��ͻ ��ͻ����true
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
	 * �ı�γ̵��ڿν�ʦ
	 * @param state
	 * @param cid
	 * @param name
	 * @return
	 */
	public boolean changename(String state,String cid, String name)
	{
		if (state.equals("false")) // ��ѡ
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
		else if (state.equals("true")) // ѡ��
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
	 * ��ʦѡ������ѡ
	 * @return
	 * @throws Exception
	 */
	public boolean update(String cid, String tid, String state) {
		String sql2 ="";
		if (state.equals("false")) // ��ѡ
		{
			sql2 = "delete from tc where teacher_id=? and course_id=?";
		} else if (state.equals("true")) // ѡ��
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
