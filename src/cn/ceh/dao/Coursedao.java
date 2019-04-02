package cn.ceh.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.ceh.bean.Course;
import cn.ceh.bean.Stucourse;
import utilhelper.C3P0Util;

public class Coursedao {

	DataSource ds = C3P0Util.getDataSource();	
	QueryRunner qr = new QueryRunner(ds);
	
	/**
	 * ��ӿγ�
	 * @param course
	 * @return
	 * @throws Exception
	 */
	public  Boolean addcourse(Course course) throws Exception 
	{
		String sql="insert into course(course_id,course_name,course_time,course_place,course_term,course_credit,course_teacher,course_sum,course_academy,course_type,course_state) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			qr.update(sql, course.getcourse_id(),course.getcourse_name(),course.getcourse_time(),course.getcourse_place(),
					course.getcourse_term(),course.getcourse_credit(),course.getcourse_teacher(),course.getcourse_sum(),
					course.getcourse_academy(),course.getcourse_type(),course.getcourse_state());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	/**
	 * ����ʦ�γ�ʱ���Ƿ��ͻ
	 * @param time
	 * @param teacher
	 * @param term
	 * @return
	 */
	public boolean cheaktime(String time,String teacher,String term)
	{
		String sql="select * from course where course_time=? and course_teacher=? and course_term=?";
		try {
			List<Course> list = qr.query(sql,new BeanListHandler<Course>(Course.class),time,teacher,term);
			
			if (list!=null&&list.size()>0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	/**
	 * ���γ̵ص��Ƿ��ͻ
	 * @param time
	 * @param address
	 * @param term
	 * @return
	 */
	public boolean cheakaddress(String time, String address, String term) {
		String sql = "select * from course where course_time=? and course_place=? and course_term=?";
		try {
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class), time, address, term);
			if (list != null && list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * ɾ���γ�
	 * @param id
	 * @return
	 */
	public boolean delete(String id) 
	{
		String sql="delete from course where course_id=?";
		try {
			int num = qr.update(sql, id);
			if(num>0)
			{
				return true;
			}
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	/**
	 * �޸Ŀγ�
	 * @param course
	 * @return
	 */
	public boolean update(Course course)
	{

		String sql="update course set course_name=?,course_time=?,course_place=?,course_term=?,course_credit=?,course_teacher=?,course_sum=?,course_academy=?,course_type=?,course_state=? where course_id=?";
		try {
			qr.update(sql, course.getcourse_name(),course.getcourse_time(),course.getcourse_place(),course.getcourse_term(),
					course.getcourse_credit(),course.getcourse_teacher(),course.getcourse_sum(),course.getcourse_academy(),
					course.getcourse_type(),course.course_state,course.getcourse_id());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * �޸Ŀγ�״̬
	 * @param state
	 * @param id
	 * @return
	 */
	public boolean updatestate(String state,String id) {
		String sql="update course set course_state=? where course_id=?";
		try {
			int update = qr.update(sql, state,id);
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
	 * ���ݿγ�id�������ݿ��еĿγ�
	 * @param id
	 * @return
	 */
	public Course find(String id)
	{
		String sql = "select * from course where course_id=?";
		try {
			Course query = qr.query(sql, new BeanHandler<Course>(Course.class), id);
			if(query!=null)
			{
				return query;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ���ݽ�ʦid������������γ�
	 * @param id
	 * @return
	 */
	public ArrayList<Course> getAllacourse(String id) {
		List<Course> list = new ArrayList<Course>();
		String sql = "SELECT *from course WHERE course_state =? and course_id IN(SELECT course_id from tc WHERE tc.teacher_id=?)";
		try {
			list = qr.query(sql, new BeanListHandler<Course>(Course.class), "������", id);
			if(list!=null)
			{
				//System.out.println("list!=null");
				return (ArrayList<Course>) list;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ����ѧ�ڷ�����������ˣ����ڿν�ʦ�Ŀγ�
	 * @param term
	 * @return ArrayList<Course>
	 */
	public ArrayList<Course> getcour(String term)
	{
		try
		{
			String sql="SELECT * from course WHERE course_state =? and course_term=? and course_id IN(SELECT course_id from tc)";
			String sql2="select * FROM sc where course_id=?";
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class),"�����", term);
			for(Course o: list)
			{
				List<Stucourse> list2 = qr.query(sql2, new BeanListHandler<Stucourse>(Stucourse.class),o.course_id);
				o.course_sum=o.course_sum-list2.size();
			}
			return (ArrayList<Course>) list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * ��ʦ��ȡ�ڿ��嵥
	 * @param id
	 * @return
	 */
	public ArrayList<Course> getcourse(String id)
	{
		try
		{
			String sql="SELECT *from course WHERE course_state =? and course_id IN(SELECT course_id from tc WHERE tc.teacher_id=?)";
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class),"�����",id);
			return (ArrayList<Course>) list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * ����Ա������пγ�
	 * @return
	 */
	public ArrayList<Course> getAllcourse()  
	{
		String sql="select * from course";
		try {
			List<Course> query = qr.query(sql, new BeanListHandler<Course>(Course.class));
			return (ArrayList<Course>) query;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * �ؼ���ģ����ѯ
	 * @param word
	 * @param pageNo
	 * @return
	 */
	public ArrayList<Course> findall(String word,int pageNo){
		int pageSize = 5;
		int page = (pageNo - 1) * pageSize;
		String sql="SELECT * FROM course WHERE CONCAT(course_id,course_name,course_time,course_place,course_term,course_credit,course_teacher,course_sum,course_academy,course_type,course_state) LIKE CONCAT('%', ?, '%') order by course_id limit ?,?";
		try {
			List<Course> query = qr.query(sql, new BeanListHandler<Course>(Course.class), word,page,pageSize);
			if(query!=null&&query.size()>0)
			{
				return (ArrayList<Course>) query;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ùؼ���ģ����ѯ�������ҳ��
	 * @param word
	 * @return
	 */
	public int getfindPage(String word) {
		int recordCount = 0, t1 = 0, t2 = 0;
		try {
			String sql = "SELECT * from (SELECT * FROM course WHERE CONCAT(course_id,course_name,course_time,course_place,course_term,course_credit,course_teacher,course_sum,course_academy,course_type,course_state) LIKE CONCAT('%', ?, '%')) as t";
			List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class),word);
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
	* @return
	*/
	public int getPage() {
		int recordCount = 0, t1 = 0, t2 = 0;
		try {
			String sql = "SELECT * from course";
			List<Course> query = qr.query(sql, new BeanListHandler<Course>(Course.class));
			recordCount = query.size();
			System.out.println("recordCount="+recordCount);
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
	* ��ѯָ��ҳ������
	* @param pageNo
	* @return
	 * @throws Exception 
	*/
	public ArrayList<Course> listCourse(int pageNo) throws Exception {

		int pageSize = 5;
		int page = (pageNo - 1) * pageSize;
		String sql = "SELECT * from course order by course_id limit ?,?";
		List<Course> list = qr.query(sql, new BeanListHandler<Course>(Course.class),page,pageSize);
		return (ArrayList<Course>) list;
	}
	@Test
	public void test() {
		Coursedao dao=new Coursedao();
		int page = dao.getPage();
		System.out.println(page);
	}
	
}