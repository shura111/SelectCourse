package cn.ceh.bean;

public class Academy {
	public String id;	//学生id
	public String academy;	//所属学院
	public String professional;		//专业
	public String cla;		//班级
	public String office;	//办公室
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getprofessional() {
		return professional;
	}
	public void setprofessional(String professional) {
		this.professional = professional;
	}
	public String getCla() {
		return cla;
	}
	public void setCla(String cla) {
		this.cla = cla;
	}
}
