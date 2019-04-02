package cn.ceh.bean;

public class Course {
	 public String course_id;  //课程id
	 public String course_name;	//课程名称
	 public String course_time;	//上课时间
	 public String course_place;	//上课地点
	 public double course_credit;	//课程学分
	 public String course_teacher;	//授课教师
	 public int course_sum;	//总容量
	 public String course_academy;	//所属学院
	 public String course_type;	//课程分类
	 public String course_state;	//申请状态
	 public String course_term; 	//上课学期
	 
	 
	 
	public Course() {
		super();
	}
	public Course(String course_id, String course_name, String course_time, String course_place,
			double course_credit, String course_teacher, int course_sum, String course_academy, String course_type,
			String course_state, String course_term) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_time = course_time;
		this.course_place = course_place;
		this.course_credit = course_credit;
		this.course_teacher = course_teacher;
		this.course_sum = course_sum;
		this.course_academy = course_academy;
		this.course_type = course_type;
		this.course_state = course_state;
		this.course_term = course_term;
	}
	public String getcourse_term() {
		return course_term;
	}
	public void setcourse_term(String course_term) {
		this.course_term = course_term;
	}
	public String getcourse_id() {
		return course_id;
	}
	public void setcourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getcourse_name() {
		return course_name;
	}
	public void setcourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getcourse_time() {
		return course_time;
	}
	public void setcourse_time(String course_time) {
		this.course_time = course_time;
	}
	public String getcourse_place() {
		return course_place;
	}
	public void setcourse_place(String course_place) {
		this.course_place = course_place;
	}
	public double getcourse_credit() {
		return course_credit;
	}
	public void setcourse_credit(double course_credit) {
		this.course_credit = course_credit;
	}
	public String getcourse_teacher() {
		return course_teacher;
	}
	public void setcourse_teacher(String course_teacher) {
		this.course_teacher = course_teacher;
	}
	public int getcourse_sum() {
		return course_sum;
	}
	public void setcourse_sum(int course_sum) {
		this.course_sum = course_sum;
	}
	public String getcourse_academy() {
		return course_academy;
	}
	public void setcourse_academy(String course_academy) {
		this.course_academy = course_academy;
	}
	public String getcourse_type() {
		return course_type;
	}
	public void setcourse_type(String course_type) {
		this.course_type = course_type;
	}
	public String getcourse_state() {
		return course_state;
	}
	public void setcourse_state(String course_state) {
		this.course_state = course_state;
	}
	 
	 
}
