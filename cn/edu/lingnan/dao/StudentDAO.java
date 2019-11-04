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
	//匹配信息
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
			System.out.println("sql语句出错...");
		} finally {
			DataAccess.closeConnection(conn, prep, rs);
		}
		return flag;
	}
	
	//查找所有信息
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
			System.out.println("sql语句出错...");
		} finally {
			DataAccess.closeConnection(conn, prep, rs);
		}
		return v;
	}
	
	//插入
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
			System.out.println("sql语句出错...");
		} finally {
			DataAccess.closeConnection(conn, prep);
		}
		return flag;
	}
	//删除
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
			//查找是否有需要删除的课程
			//当这门课只有要删除的学生选，把学生删除了自然也要把这门没有人选的课删除
			prep1  = conn.prepareStatement
					("select * from score where sid = ? and status = 1");  
			prep1.setString(1, _sid);
			rs1 = prep1.executeQuery();
			HashSet<String> h = new HashSet<String>();//用哈希数组来存储需要删除的cid
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
			//连续三条语句操作，封装成一个事务操作，语句出错的时候方便回滚
			//默认是一条语句一个事务
			conn.setAutoCommit(false);
			//删除分数表
			prep3 = conn.prepareStatement
					("update score set status = 0 where sid = ?");
			prep3.setString(1, _sid);
			prep3.executeUpdate();
			//删除学生表
			prep3  = conn.prepareStatement
					("update student set status = 0 where sid = ?");  
			prep3.setString(1, _sid);
			prep3.executeUpdate();
			//删除课程表
			Iterator<String> it = h.iterator();//用迭代器读哈希表
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
			System.out.println("sql语句出错...尝试回滚...");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("回滚失败！");
			}
			System.out.println("回滚成功！");
		} finally {
			DataAccess.closeConnection(conn,prep1,prep2,prep3,rs1,rs2);
		}
		return flag;
	}
	//更新
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
			System.out.println("sql语句出错...");
		} finally {
			DataAccess.closeConnection(conn, prep);
		}
		return flag;
	}
	//修改密码
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
			System.out.println("sql语句出错...");
		} finally {
			DataAccess.closeConnection(conn, prep, rs);
		}
		return flag;
	}
	
	
}













