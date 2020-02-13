package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static {
		try {
			
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패!!!");
		}
	}
	
	public static Connection getConnection() {
		// 실제적으로 OracleDriver가 사용되는 부분 // DB접속
					
		try {
			
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe",  "PC03", "java");
		
		}catch(SQLException e) {
			System.out.println("DB 연결 실패!!!");
			e.printStackTrace();
			return null;
		}
		
		
	}

}
