package cn.ceh.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.ceh.bean.Academy;
import utilhelper.C3P0Util;

public class Academydao {
	DataSource ds = C3P0Util.getDataSource();	
	QueryRunner qr = new QueryRunner(ds);
	
	public List<Academy> findacademy() {
		
		String sql="SELECT DISTINCT academy FROM academy";
		try {
			List<Academy> query = qr.query(sql, new BeanListHandler<Academy>(Academy.class));
			if(query!=null&&query.size()>0)
			{
				return query;
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
public List<Academy> findprofessional(String academy) {
		
		String sql="SELECT DISTINCT professional FROM academy where academy=?";
		try {
			List<Academy> query = qr.query(sql, new BeanListHandler<Academy>(Academy.class),academy);
			if(query!=null&&query.size()>0)
			{
				System.out.println(query);
				return query;
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
public List<Academy> findcla(String professional) {
	
	String sql="SELECT DISTINCT cla FROM academy where professional=?";
	try {
		List<Academy> query = qr.query(sql, new BeanListHandler<Academy>(Academy.class),professional);
		if(query!=null&&query.size()>0)
		{
			return query;
		}
		else
		{
			return null;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}
