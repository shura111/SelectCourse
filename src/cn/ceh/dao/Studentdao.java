package cn.ceh.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.ceh.bean.Student;
import utilhelper.C3P0Util;


public class Studentdao {

	DataSource ds = C3P0Util.getDataSource();
	QueryRunner qr = new QueryRunner(ds);

	// 添加学生
	public Boolean addstudent(Student student) throws Exception {
		String sql = "insert into student(student_id,student_name,student_academy,student_professional,student_class,student_sex) values(?,?,?,?,?,?)";

		try {
			int num = qr.update(sql, student.getStudent_id(), student.getStudent_name(), student.getStudent_academy(),
					student.getStudent_professional(), student.getStudent_class(), student.getStudent_sex());
			if (num > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 删除学生
	public boolean delete(String id) {
		String sql = "delete from student where student_id=?";
		try {
			 int num = qr.update(sql,id);
			if (num > 0) {
				return true;
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}

	// 修改学生信息
	public boolean update(Student student) {

		String sql = "update student set student_name=?,student_academy=?,student_professional=?,student_class=?,student_sex=? where student_id=?";
		try {

			int update = qr.update(sql, student.getStudent_name(), student.getStudent_academy(),
					student.getStudent_professional(), student.getStudent_class(), student.getStudent_sex(),
					student.getStudent_id());
			if (update > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 根据id查找数据库中的学生
	public Student find(String id) {
		String sql = "select * from Student where student_id=?";
		try {
			Student query = qr.query(sql, new BeanHandler<Student>(Student.class), id);
			if (query != null) {
				return query;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 关键字模糊查询
	public ArrayList<Student> findall(String word, int pageNo) {
		int pageSize = 5;
		int page = (pageNo - 1) * pageSize;
		String sql = "SELECT * FROM student WHERE CONCAT(student_id,student_name,student_academy,student_professional,student_class,student_sex) LIKE CONCAT('%', ?, '%') order by student_id limit ?,?";
		try {
			List<Student> query = qr.query(sql, new BeanListHandler<Student>(Student.class), word, page, pageSize);
			if (query != null && query.size() > 0) {
				return (ArrayList<Student>) query;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getfindPage(String word) {
		int recordCount = 0, t1 = 0, t2 = 0;
		try {
			String sql = "SELECT * from (SELECT * FROM student WHERE CONCAT(student_id,student_name,student_academy,student_professional,student_class,student_sex) LIKE CONCAT('%', ?, '%')) as t";
			List<Student> list = qr.query(sql, new BeanListHandler<Student>(Student.class),word);
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
	 * 计算学生表总的页数
	 * 
	 * @return
	 */
	public int getPage() {
		int recordCount = 0, t1 = 0, t2 = 0;
		try {
			String sql = "SELECT * from student";
			List<Student> list = qr.query(sql, new BeanListHandler<Student>(Student.class));
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
	 * 
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Student> listStudent(int pageNo) throws Exception {

		int pageSize = 5;
		int page = (pageNo - 1) * pageSize;
		String sql = "SELECT student_id,student_name,student_academy,student_class,student_professional,student_sex from student order by student_id limit ?,?";

		List<Student> list = qr.query(sql, new BeanListHandler<Student>(Student.class), page, pageSize);

		return (ArrayList<Student>) list;
	}

}
