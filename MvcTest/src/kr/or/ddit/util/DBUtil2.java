package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 	db.properties파일의 내용으로 DB정보를 설정하는 방법
 	방법1) properties객체 이용하기
 */
public class DBUtil2 {
	static Properties prop;
	static {

		prop = new Properties();

		// 읽어올 파일명을 이용한 File객체 생성
		File file = new File("res/db.properties");

		try {
			// 파일 읽기를 수행할 FileInputStream객체 생성
			FileInputStream fin = new FileInputStream(file);

			// Properties객체로 파일 내용 읽기
			prop.load(fin); // 파일 내용을 읽어와 key와 value값으로 분류한 후 properties객체에 담아준다.

			Class.forName(prop.getProperty("driver"));
		} catch (IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"),
												prop.getProperty("user"),
												prop.getProperty("pass"));

		} catch (SQLException e) {
			System.out.println("DB연결 실패!!!");
			e.printStackTrace();
			return null;
		}

	}
}