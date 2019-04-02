package idhelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;


public class OrderNoUtil {

	public static String getOrderNo() {
		String orderNo = "";
		String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
		String sdf = new SimpleDateFormat("yyMMddHHMMSS").format(new Date());
		orderNo = trandNo.toString().substring(0, 4);
		orderNo = orderNo + sdf;
		return orderNo;
	}
	public static String getTeacherNo() {
		String orderNo = "" ;
		String sdf = new SimpleDateFormat("HHmmssSS").format(new Date());
		orderNo = sdf;
		return orderNo ;
	}
	public static String getStudentNo() {
		String orderNo = "17" ;
		String sdf = new SimpleDateFormat("MMddmmssSS").format(new Date());
		orderNo =orderNo + sdf;
		return orderNo ;
	}
	public static String getCourseNo() {
		String orderNo = "" ;
		UUID uuid = UUID.randomUUID();
		orderNo=uuid.toString().substring(0, 11);
		return orderNo ;
	}
	public static String getAdminNo() {
		String orderNo = "" ;
		String trandNo = "Adm";
		String s = String.valueOf((Math.random() * 9 + 1) * 1000000);
		String sdf = new SimpleDateFormat("yyMMddHHmmssSS").format(new Date());
		orderNo=s.toString().substring(0, 3);
		orderNo = trandNo+sdf+orderNo;
		return orderNo ;
	}
	
	@Test
	public void test() {
		for(int i=0;i<1000;i++)
			System.out.println(OrderNoUtil.getStudentNo());
	}
}

