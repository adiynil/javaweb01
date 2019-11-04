package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.lingnan.dto.Score;
import cn.edu.lingnan.util.DataAccess;

public class ScoreDAO {
	/***
	 * 一般管理员只能软删除成绩表
	 */
	//插入
	public boolean addScore(Score _s) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("insert into score values(?,?,?,?)");
			prep.setString(1, _s.getSid());
			prep.setString(2, _s.getCid());
			prep.setInt(3, _s.getScore());
			prep.setInt(4, _s.getStatus());
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
	public Vector<Score> findAllScore() {
		Vector<Score> v = new Vector<Score>();
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement("select * from score where status = 1");
			rs = prep.executeQuery();
			while (rs.next()) {
				Score s = new Score();
				s.setCid(rs.getString("cid"));
				s.setSid(rs.getString("sid"));
				s.setScore(rs.getInt("score"));
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
	public boolean updateScore(Score _c) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DataAccess.getConnection();
			prep = conn.prepareStatement
					("update score set score=? where cid=? and sid=?");
			prep.setInt(1, _c.getScore());
			prep.setString(2, _c.getCid());
			prep.setString(3, _c.getSid());
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
	public boolean deleteScore(String _cid , String _sid) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DataAccess.getConnection();			
			prep = conn.prepareStatement
					("update score set status = 0 where cid=? and sid=?");
			prep.setString(1, _cid);
			prep.setString(2, _sid);
			prep.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.out.println("sql语句出错...");
		} finally {
			DataAccess.closeConnection(conn,prep);
		}
		return flag;
	}

}
