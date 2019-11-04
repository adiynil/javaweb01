package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import cn.edu.lingnan.dto.Student;
import cn.edu.lingnan.util.DataAccess;

public class StudentDAO {
	//ƥ����Ϣ
	public boolean findStudentByNameAndPwd
		(String _name , String _password){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("select * from student where sname = ?"
					+ " and password = ? and status = 1 ");
			prep.setString(1, _name);
			prep.setString(2, _password);
			rs = prep.executeQuery();
			if (rs.next())
				flag = true;
		} catch (SQLException e) {
			System.out.println("sql������...");
		} finally {
			DataAccess.closeConnection(conn, prep, rs);
		}
		return flag;
	}
	
	//����������Ϣ
	public Vector<Student> findAllStudent() {
		Vector<Student> v = new Vector<Student>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("select * from student where status = 1");
			rs = prep.executeQuery();
			while (rs.next()) {
				Student s = new Student();
				s.setSid(rs.getString("sid"));
				s.setSname(rs.getString("sname"));
				s.setPassword(rs.getString("password"));
				s.setSuperuser(rs.getInt("superuser"));
				s.setStatus(rs.getInt("status"));
				v.add(s);
			}
		} catch (SQLException e) {
			System.out.println("sql������...");
		} finally {
			DataAccess.closeConnection(conn, prep, rs);
		}
		return v;
	}
	
	//����
	public boolean insertInfoToStudent(Student _s) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("insert into student values(?,?,?,?,?)");
			prep.setString(1, _s.getSid());
			prep.setString(2, _s.getSname());
			prep.setString(3, _s.getPassword());
			prep.setInt(4,_s.getSuperuser());
			prep.setInt(5, _s.getStatus());
			prep.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.out.println("sql������...");
		} finally {
			DataAccess.closeConnection(conn, prep);
		}
		return flag;
	}
	//ɾ��
	@SuppressWarnings("resource")
	public boolean deleteStu(String _sid) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep1 = null;
		PreparedStatement prep2 = null;
		PreparedStatement prep3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			conn = DataAccess.getConnection();			
			//�����Ƿ�����Ҫɾ���Ŀγ�
			//�����ſ�ֻ��Ҫɾ����ѧ��ѡ����ѧ��ɾ������ȻҲҪ������û����ѡ�Ŀ�ɾ��
			prep1  = conn.prepareStatement
					("select * from score where sid = ? and status = 1");  
			prep1.setString(1, _sid);
			rs1 = prep1.executeQuery();
			HashSet<String> h = new HashSet<String>();//�ù�ϣ�������洢��Ҫɾ����cid
			while(rs1.next()) {
				prep2 = conn.prepareStatement
						("select count(*) as num from score where cid = ? and status = 1");
				prep2.setString(1, rs1.getString("cid"));
				rs2 = prep2.executeQuery();
				while(rs2.next()) {
					if(Integer.parseInt(rs2.getString("num"))==1) {
						h.add(rs1.getString("cid"));
					}
				}
			}
			//������������������װ��һ������������������ʱ�򷽱�ع�
			//Ĭ����һ�����һ������
			conn.setAutoCommit(false);
			//ɾ��������
			prep3 = conn.prepareStatement
					("update score set status = 0 where sid = ?");
			prep3.setString(1, _sid);
			prep3.executeUpdate();
			//ɾ��ѧ����
			prep3  = conn.prepareStatement
					("update student set status = 0 where sid = ?");  
			prep3.setString(1, _sid);
			prep3.executeUpdate();
			//ɾ���γ̱�
			Iterator<String> it = h.iterator();//�õ���������ϣ��
			while(it.hasNext()) {
				prep3  = conn.prepareStatement
						("update course set status = 0 where cid = ?");  
				prep3.setString(1, it.next());
				prep3.executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(true);
			flag = true;
		} catch (SQLException e) {
			System.out.println("sql������...���Իع�...");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("�ع�ʧ�ܣ�");
			}
			System.out.println("�ع��ɹ���");
		} finally {
			DataAccess.closeConnection(conn,prep1,prep2,prep3,rs1,rs2);
		}
		return flag;
	}
	//����
	public boolean updateStu(Student _s) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("update student set sname=?,password=?,superuser=? where sid=? and status = 1");
			prep.setString(1, _s.getSname());
			prep.setString(2, _s.getPassword());
			prep.setInt(3,_s.getSuperuser());
			prep.setString(4, _s.getSid());
			prep.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.out.println("sql������...");
		} finally {
			DataAccess.closeConnection(conn, prep);
		}
		return flag;
	}
	//�޸�����
	@SuppressWarnings("resource")
	public boolean changePassword
		(String _sid , String _password , String _newpassword) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("select * from student where sid = ?"
					+ " and password = ? and status = 1 ");
			prep.setString(1, _sid);
			prep.setString(2, _password);
			rs = prep.executeQuery();
			if (rs.next()) {
				prep = conn.prepareStatement
						("update student set password = ? where sid = ?");
				prep.setString(1, _newpassword);
				prep.setString(2, _sid);
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println("sql������...");
		} finally {
			DataAccess.closeConnection(conn, prep, rs);
		}
		return flag;
	}
	
	
}













