package cn.ceh.bean;

public class Office {
	public String id;
	public String academy_name;
	public String academy_office;
	public Office(String id, String academy_name, String academy_office) {
		super();
		this.id = id;
		this.academy_name = academy_name;
		this.academy_office = academy_office;
	}
	public Office() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAcademy_name() {
		return academy_name;
	}
	public void setAcademy_name(String academy_name) {
		this.academy_name = academy_name;
	}
	public String getAcademy_office() {
		return academy_office;
	}
	public void setAcademy_office(String academy_office) {
		this.academy_office = academy_office;
	}
	
}
