package cn.edu.lingnan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DataAccess {
	
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String xmlPath = "database.conf.xml";
	private static String xsdPath = "database.conf.xsd";

	//��ȡ���ݿ�����
	public static Connection getConnection(){
		Connection conn = null;
		if (XmlValidator.validate(xmlPath, xsdPath)) {
			HashMap<String, String> hm = XmlParser.parser(xmlPath);
			driver = hm.get("driver");
			url = hm.get("url");
			user = hm.get("user");
			password = hm.get("password");
		}
		try {
			Class.forName(driver);// ע����������
			conn = DriverManager.getConnection
			(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ����ӵ�jar������...");
		} catch (SQLException e) {
			System.out.println("���ݿ����Ӳ�������sql������...");
		}
		return conn;
	}
	
	//�ر����ݿ�����
	public static void closeConnection
		(Connection conn,PreparedStatement prep,ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (prep != null)
				prep.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("�ر����ݿ����...");
		} 
	}
	//����
	public static void closeConnection
	(Connection conn,PreparedStatement prep) {
	try {
		if (prep != null)
			prep.close();
		if (conn != null)
			conn.close();
	} catch (SQLException e) {
		System.out.println("�ر����ݿ����...");
	} 
}

	//����
	public static void closeConnection(Connection conn, Statement prep, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (prep != null)
				prep.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("�ر����ݿ����...");
		} 
	}
	//����
	public static void closeConnection(Connection conn, PreparedStatement prep1, PreparedStatement prep2,
			PreparedStatement prep3, ResultSet rs1, ResultSet rs2) {
		try {
			if (rs1 != null)
				rs1.close();
			if (rs2 != null)
				rs2.close();
			if (prep1 != null)
				prep1.close();
			if (prep2 != null)
				prep2.close();
			if (prep3 != null)
				prep3.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("�ر����ݿ����...");
		} 
	}
	

}
