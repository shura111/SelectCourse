package cn.ceh.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.ceh.bean.Course;
import cn.ceh.bean.Student;
import utilhelper.C3P0Util;

/**
 * �����ˣ� ceh ����ʱ�䣺2019��1��11�� ����8:44:34
 */
public class Stucoursedao {
	DataSource ds = C3P0Util.getDataSource();
	QueryRunner qr = new QueryRunner(ds);

	// ����ѧ��idɾ����¼
	public boolean Sdelete(String id) {
		String sql = "delete from sc where student_id=?";
		try {
			qr.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * �����ܵ�ҳ��
	 * 
	 * @return
	 */
	public int getstucoursePage(String id) {
		int recordCount = 0, t1 = 0, t2 = 0;
		try {
			String sql = "SELECT * from (SELECT *from course WHERE course_id IN(SELECT course_id from sc WHERE sc.student_id=?)) as t";
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class), id);
			recordCount = list.size();
			t1 = recordCount % 5;
			t2 = recordCount / 5;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (t1 != 0) {
			t2 = t2 + 1;
		}
		return t2;
	}

	/**
	 * ѧ����ȡ��ѡ�γ��嵥 ��ѯָ��ҳ������
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Course> getstucourse(int pageNo, String id) throws Exception {

		int pageSize = 5;
		int page = (pageNo - 1) * pageSize;
		String sql = "SELECT * from course WHERE course_id IN(SELECT course_id from sc WHERE sc.student_id=?) order by course_id limit ?,?";
		List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class), id, page, pageSize);
		return (ArrayList<Course>) list;
	}

	/**
	 * ѧ����ȡ��ѧ����ѡ�γ��嵥 
	 * @return
	 * @throws Exception
	 */
	
	public ArrayList<Course> getstucourse(String id,String term)
	{
		String sql="SELECT * from course WHERE course_term=? and course_id IN(SELECT course_id from sc WHERE sc.student_id=?) order by course_id";
		try {
			List<Course> query = qr.query(sql, new BeanListHandler<Course>(Course.class), term,id);
			return (ArrayList<Course>) query;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ѧ��ѡ������ѡ
	 * @return
	 * @throws Exception
	 */
	public boolean update(String sid,String cid,String state)
	{
		String sql="";
		if(state.equals("false"))	//��ѡ
		{
			sql="delete from sc where student_id=? and course_id=?";
		}
		else if(state.equals("true"))	//ѡ��
		{
			sql="insert into sc(student_id,course_id) values(?,?)";
		}
		try {
			int update = qr.update(sql, sid, cid);
			if(update>0)
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * ѧ��ѡ�μ��ʱ���Ƿ��ͻ ��ͻ����true
	 * 
	 * @param cid
	 * @param sid
	 * @return
	 */
	public boolean checktime(String cid, String sid) {

		String sql = "SELECT * from course WHERE course_id =? AND course_time in(SELECT course_time from course WHERE course_id in(select course_id from sc WHERE student_id=?))";
		try {
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class), cid, sid);
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
	 * 
	 * ѧ������ѡ�γ̵�ģ����ѯ
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Course> getstucourse(int pageNo,String id,String word) {
		int pageSize = 5;
		int page = (pageNo - 1) * pageSize;
		String sql = "SELECT * FROM (SELECT *from course WHERE course_id IN(SELECT course_id from sc WHERE sc.student_id=?)) as t WHERE CONCAT(course_id,course_name,course_time,course_place,course_term,course_credit,course_teacher,course_sum,course_academy,course_type,course_state) LIKE CONCAT('%', ?, '%') order by course_id limit ?,?";
		try {
			List<Course> query = qr.query(sql, new BeanListHandler<Course>(Course.class), id, word, page, pageSize);
			if (query != null && query.size() > 0) {
				return (ArrayList<Course>) query;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getstucoursePage(String id, String word) {
		int recordCount = 0, t1 = 0, t2 = 0;
		try {
			String sql = "SELECT * from (SELECT * FROM (SELECT *from course WHERE course_id IN(SELECT course_id from sc WHERE sc.student_id=?)) as t WHERE CONCAT(course_id,course_name,course_time,course_place,course_term,course_credit,course_teacher,course_sum,course_academy,course_type,course_state) LIKE CONCAT('%', ?, '%')) as t";
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class), id,word);
			recordCount = list.size();
			t1 = recordCount % 5;
			t2 = recordCount / 5;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (t1 != 0) {
			t2 = t2 + 1;
		}
		return t2;
	}

	/**
	 * �����ܵ�ҳ��
	 * 
	 * @return
	 */
	public int getPage(String id) {
		int recordCount = 0, t1 = 0, t2 = 0;
		try {
			String sql = "SELECT count(*) from course,sc,student WHERE course.course_id=? AND course.course_id=sc.course_id AND sc.student_id=student.student_id";
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class), id);
			recordCount = list.size();
			t1 = recordCount % 5;
			t2 = recordCount / 5;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (t1 != 0) {
			t2 = t2 + 1;
		}
		return t2;
	}

	/**
	 * ���ݿγ�id��ѧ���嵥 ��ѯָ��ҳ������
	 * 
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Student> listStudent(int pageNo, String id) throws Exception {

		int pageSize = 5;
		int page = (pageNo - 1) * pageSize;
		String sql = "SELECT student.student_id,student_name,student_academy,student_class,student_professional,student_sex from course,sc,student WHERE course.course_id=? AND course.course_id=sc.course_id AND sc.student_id=student.student_id order by student.student_id limit ?,?";

		List<Student> list = qr.query(sql, new BeanListHandler<Student>(Student.class), id, page, pageSize);

		return (ArrayList<Student>) list;
	}

}
