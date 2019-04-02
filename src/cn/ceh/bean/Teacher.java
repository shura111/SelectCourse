package cn.ceh.bean;

public class Teacher {
	
	public String teacher_id;	//教师编号
	public String teacher_name;	//教师姓名
	public int teacher_age;	//教师年龄
	public String teacher_office;	//办公地址
	public String teacher_academy;	//所属学院
	public String teacher_sex;		//教师性别
	
	
	public Teacher() {
		super();
	}
	public Teacher(String teacher_id, String teacher_name, int teacher_age, String teacher_office,
			String teacher_academy, String teacher_sex) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.teacher_age = teacher_age;
		this.teacher_office = teacher_office;
		this.teacher_academy = teacher_academy;
		this.teacher_sex = teacher_sex;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public int getTeacher_age() {
		return teacher_age;
	}
	public void setTeacher_age(int teacher_age) {
		this.teacher_age = teacher_age;
	}
	public String getTeacher_office() {
		return teacher_office;
	}
	public void setTeacher_office(String teacher_office) {
		this.teacher_office = teacher_office;
	}
	public String getTeacher_academy() {
		return teacher_academy;
	}
	public void setTeacher_academy(String teacher_academy) {
		this.teacher_academy = teacher_academy;
	}
	public String getTeacher_sex() {
		return teacher_sex;
	}
	public void setTeacher_sex(String teacher_sex) {
		this.teacher_sex = teacher_sex;
	}
	
	
}
