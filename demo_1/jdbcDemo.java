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
			Class.forName("com.mysql.jdbc.Driver");//ע����������
			conn = DriverManager.getConnection//��ȡ���ݿ�����
					("jdbc:mysql://localhost:3306/school","root","root");
			stat = conn.createStatement();//����sql������
			rs = stat.executeQuery("select * from student");
			while (rs.next()) {
				System.out.println(rs.getString("sid"));
			}
			
			//DriverManager.getConnection(url, usepasswordr, )
					
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} 
		}
		
	}
}
