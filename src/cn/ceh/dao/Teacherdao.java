package cn.ceh.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.ceh.bean.Office;
import cn.ceh.bean.Teacher;
import utilhelper.C3P0Util;


public class Teacherdao {
	DataSource ds = C3P0Util.getDataSource();	
	QueryRunner qr = new QueryRunner(ds);
	
	
	/**
	 * 添加教师
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public  Boolean addTea(Teacher teacher) throws Exception 
	{
		String sql="insert into teacher(teacher_id,teacher_name,teacher_age,teacher_office,teacher_academy,teacher_sex) values(?,?,?,?,?,?)";
		
		try {
			int num = qr.update(sql, teacher.teacher_id,teacher.teacher_name,teacher.teacher_age,teacher.teacher_office,teacher.teacher_academy,teacher.teacher_sex);
			if(num>0)
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
		/**
		 * 删除教师信息
		 * @param id
		 * @return
		 */
		public boolean delete(String id) 
		{
			String sql="delete from teacher where teacher_id=?";
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
		 * 修改教师信息
		 * @param teacher
		 * @return
		 */
		public boolean update(Teacher teacher)
		{

			String sql="update teacher set teacher_name=?,teacher_age=?,teacher_office=?,teacher_academy=?,teacher_sex=?where teacher_id=?";
			try {
				int update = qr.update(sql, teacher.teacher_name,teacher.teacher_age,teacher.teacher_office,teacher.teacher_academy,teacher.teacher_sex,teacher.teacher_id);
				if(update>0)
				{
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return false;
		}
		
		/**
		 * 根据id找出教师
		 * @param id
		 * @return
		 */
		public Teacher find(String id)
		{
			String sql = "select * from teacher where teacher_id=?";
			try {
				Teacher query = qr.query(sql, new BeanHandler<Teacher>(Teacher.class), id);
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
		 * 根据学院找出办公室
		 * @param academy_name
		 * @return
		 */
		public List<Office> findoffice(String academy_name)
		{
			String sql="select academy_office from office where academy_name=?";
			try {
				List<Office> query = qr.query(sql, new BeanListHandler<Office>(Office.class), academy_name);
				if(query!=null&&query.size()>0)
				{
					System.out.println(query);
					return query;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("null");
			return null;
			
		}
		
		
		/**
		 * 关键字模糊查询
		 * @param word
		 * @param pageNo
		 * @return
		 */
		public ArrayList<Teacher> findall(String word,int pageNo){
			int pageSize = 5;
			int page = (pageNo - 1) * pageSize;
			String sql="SELECT * FROM teacher WHERE CONCAT(teacher_id,teacher_name,teacher_age,teacher_office,teacher_academy,teacher_sex) LIKE CONCAT('%', ?, '%') order by teacher_id limit ?,?";
			try {
				List<Teacher> query = qr.query(sql, new BeanListHandler<Teacher>(Teacher.class), word,page,pageSize);
				if(query!=null&&query.size()>0)
				{
					return (ArrayList<Teacher>) query;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		public int getfindPage(String word) {
			int recordCount = 0, t1 = 0, t2 = 0;
			try {
				String sql = "SELECT * from (SELECT * FROM teacher WHERE CONCAT(teacher_id,teacher_name,teacher_age,teacher_office,teacher_academy,teacher_sex) LIKE CONCAT('%', ?, '%')) as t";
				List<Teacher> list = qr.query(sql, new BeanListHandler<Teacher>(Teacher.class), word);
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
		* 计算总的页数
		* @return
		*/
		public int getPage() {
			int recordCount = 0, t1 = 0, t2 = 0;
			try {
				String sql = "SELECT * from teacher";
				List<Teacher> list = qr.query(sql, new BeanListHandler<Teacher>(Teacher.class));
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
		* 查询指定页的数据
		* @param pageNo
		* @return
		 * @throws Exception 
		*/
		public ArrayList<Teacher> listTeacher(int pageNo) throws Exception {

			int pageSize = 5;
			int page = (pageNo - 1) * pageSize;
			String sql = "SELECT teacher_id,teacher_name,teacher_age,teacher_office,teacher_academy,teacher_sex from teacher order by teacher_id limit ?,?";
			
			List<Teacher> list = qr.query(sql, new BeanListHandler<Teacher>(Teacher.class),page,pageSize);
			return (ArrayList<Teacher>) list;
		}
		@Test
		public void test() {
			Teacherdao dao=new Teacherdao();
			ArrayList<Teacher> list = dao.findall("jim",1);
			System.out.println(list);
			for(Teacher o:list)
			{
				System.out.println(o.teacher_id);
			}
		}

}
