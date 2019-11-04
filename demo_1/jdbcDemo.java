package demo_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcDemo {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//注册驱动程序
			conn = DriverManager.getConnection//获取数据库连接
					("jdbc:mysql://localhost:3306/school","root","root");
			stat = conn.createStatement();//创建sql语句对象
			rs = stat.executeQuery("select * from student");
			while (rs.next()) {
				System.out.println(rs.getString("sid"));
			}
			
			//DriverManager.getConnection(url, usepasswordr, )
					
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} 
		}
		
	}
}
