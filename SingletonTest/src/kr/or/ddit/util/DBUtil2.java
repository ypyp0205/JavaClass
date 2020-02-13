package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil2 {
	static Properties prop;
	
	static {
		
	// 읽어온 정보를 저장할 Properties객체 생성
			Properties prop = new Properties();
			
			// 읽어올 파일명을 이용한 File 객체 생성
			File file = new File("res/db.properties");
			
			try {
				// 파일 읽기를 수행할 FileInputStream 객체 생성
				FileInputStream fin = new FileInputStream(file);
				
				// Properties객체로 파일 내용 읽기
				prop.load(fin); //파일 내용을 읽어와 key와 value값으로 분류한 후 Properties 객체에 담아준다.
				
				Class.forName(prop.getProperty("driver"));
				
			}catch(IOException e) {
				System.out.println("파일이 없거나 입출력 오류다.");
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패!!!");
				e.printStackTrace();
			}
	}
	
			public static Connection getConnection() {
				// 실제적으로 OracleDriver가 사용되는 부분 // DB접속
							
				try {
					return DriverManager.getConnection(
													prop.getProperty("url"),
													prop.getProperty("user"),
													prop.getProperty("pass"));
				}catch(SQLException e) {
					System.out.println("DB 연결 실패!!!");
					e.printStackTrace();
					return null;
				}
			}
		}
			
			
