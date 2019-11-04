package cn.edu.lingnan.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.lingnan.dto.Course;
import cn.edu.lingnan.util.DataAccess;

public class CourseDAO {
	/***
	 * 一般管理员只能软删除课程表
	 */
	//插入
	public boolean addCourse(Course _c) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("insert into course values(?,?,?)");
			prep.setString(1, _c.getCid());
			prep.setString(2, _c.getCname());
			prep.setInt(3, _c.getStatus());
			prep.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.out.println("sql语句出错...");
		} finally {
			DataAccess.closeConnection(conn, prep);
		}
		return flag;
	}
	//查询
	public Vector<Course> findAllCourse() {
		Vector<Course> v = new Vector<Course>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("select * from course where status = 1");
			rs = prep.executeQuery();
			while (rs.next()) {
				Course s = new Course();
				s.setCid(rs.getString("cid"));
				s.setCname(rs.getString("cname"));
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
	//更新
	public boolean updateCourse(Course _c) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("update course set cname=? where cid=?");
			prep.setString(1, _c.getCname());
			prep.setString(2, _c.getCid());
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
	public boolean deleteCourse(String _cid) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DataAccess.getConnection();			
			conn.setAutoCommit(false);
			//软删除分数表
			prep = conn.prepareStatement
					("update score set status = 0 where cid = ?");
			prep.setString(1, _cid);
			prep.executeUpdate();
			//软删除课程表
			prep  = conn.prepareStatement
					("update course set status = 0 where cid = ?");  
			prep.setString(1, _cid);
			prep.executeUpdate();
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
			DataAccess.closeConnection(conn,prep);
		}
		return flag;
	}

}
