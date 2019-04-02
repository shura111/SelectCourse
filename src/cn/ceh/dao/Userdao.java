package cn.ceh.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import cn.ceh.bean.User;
import utilhelper.C3P0Util;

public class Userdao {
	DataSource ds = C3P0Util.getDataSource();	
	QueryRunner qr = new QueryRunner(ds);
	
	//����û�
	public  Boolean adduser(User user) throws Exception 
	{
		String sql="insert into users(user_id,user_name,user_password,user_type) values(?,?,?,?)";
		try {
			qr.update(sql,user.user_id,user.getUser_name(),
					user.getUser_password(),user.getUser_type());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// �޸��û���Ϣ
	public boolean update(User user) {

		String sql = "update users set user_name=?,user_password=?,user_type=? where user_id=?";
		try {
			qr.update(sql, user.user_name,user.user_password,user.user_type,user.user_id);
			} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// ɾ���û�
	public boolean delete(String id) {
		String sql = "delete from users where user_id=?";
		try {
		    qr.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// �����ݿ��в�ѯ�û�����
	public User findname(String id) throws Exception {

		String sql = "select * from users where user_id=?";
		User u =qr.query(sql, new BeanHandler<User>(User.class), id);
		if (u != null) {
			return u;
		} else {
			return null;
		}

	}
	//�޸�����
	public boolean update(String id,String pwd) throws Exception
	{
		String sql="update  users set user_password=? where user_id=?";
		try {
			qr.update(sql, pwd,id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	//�����ݿ��в�ѯ�Ƿ��и��û�
	public Boolean find(User user) throws Exception {
		
		String sql = "select * from users where user_id=?";
		List<User> u = qr.query( sql, new BeanListHandler<User>(User.class),user.getUser_id());
		
		if (u!=null&&u.size()>0) {
			return true;
		} else {
			return false;
		}

	}
	
	//�����ݿ��м���û���¼
	public Boolean check(User user) throws Exception {
		
		String sql = "select * from users where user_id=? and user_password=? and user_type=?";
		
		List<User> u = qr.query(sql,new BeanListHandler<User>(User.class), user.getUser_id(),user.getUser_password(),user.getUser_type());
		if (u!=null&&u.size()>0) {
			return true;
		} else {
			return false;
		}

	}
	
	//���������û�
	public ArrayList<User> getAlluser() throws SQLException
	{
		try
		{
			String sql="select * from users";
			List<User> list = qr.query(sql,new BeanListHandler<User>(User.class));
			
			return (ArrayList<User>) list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Test
	public void test1() throws Exception
	{
		User user=new User();
		Userdao dao=new Userdao();
		user=dao.findname("Tea201901061601888");
		System.out.println(user.getUser_name());
	}

}
