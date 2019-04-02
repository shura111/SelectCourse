package cn.ceh.bean;

public class Student {
	public String student_id;	//学生id
	public String student_name;		//学生姓名
	public String student_academy;	//所属学院
	public String student_professional;		//专业
	public String student_class;		//班级
	public String student_sex;		//性别
	
	public Student() {
		super();
	}
	public Student(String student_id, String student_name, String student_academy, String student_professional,
			String student_class, String student_sex) {
		super();
		this.student_id = student_id;
		this.student_name = student_name;
		this.student_academy = student_academy;
		this.student_professional = student_professional;
		this.student_class = student_class;
		this.student_sex = student_sex;
		
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_academy() {
		return student_academy;
	}
	public void setStudent_academy(String student_academy) {
		this.student_academy = student_academy;
	}
	public String getStudent_professional() {
		return student_professional;
	}
	public void setStudent_professional(String student_professional) {
		this.student_professional = student_professional;
	}
	public String getStudent_class() {
		return student_class;
	}
	public void setStudent_class(String student_class) {
		this.student_class = student_class;
	}
	public String getStudent_sex() {
		return student_sex;
	}
	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}
	
}
