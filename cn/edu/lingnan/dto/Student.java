package cn.edu.lingnan.dto;

public class Student {
	private String sid;
	private String sname;
	private String password;
	private int superuser = 2;//Ĭ��Ϊһ���û�Ȩ��
	private int status = 1;//Ĭ��status=1��ʾ�����û��ɲ鿴
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSuperuser() {
		return superuser;
	}
	public void setSuperuser(int superuser) {
		this.superuser = superuser;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
