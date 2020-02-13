
	package kr.or.ddit.basic;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.HashMap;
	
	import java.util.Map;
	import java.util.Scanner;
	

	public class JDBC {
		Scanner s = new Scanner(System.in);
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql = null;
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String userId = "yp0205";
		String password = "java";
		
		Map<String,String> map = new HashMap<>();
		
		public static void main(String[] args) {
			new JDBC().Start();

		}

		public void Start() {
			System.out.println("****************");
			System.out.println("호텔 문을 열었습니다");
			System.out.println("****************");

			try {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, userId, password);
				sql = " select count(*) from all_tables where table_name = 'HOTEL' ";
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				
				rs.next();
					
						if(rs.getInt("count(*)") == 0){
							
							sql = "CREATE TABLE HOTEL (room_nm varchar(20), cnt_nm varchar(20) )";
							pstmt = con.prepareStatement(sql);	
							pstmt.executeQuery();
						}
						
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
				
			} finally {

				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e2) {
					}
				if (stmt != null)
					try {
						stmt.close();
					} catch (SQLException e2) {
					}
				if (con != null)
					try {
						con.close();
					} catch (SQLException e2) {
				}
			}
			while (true) {

				System.out.println("*********************************");
				System.out.println("어떤 업무를 보시겠습니까?");
				System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
				System.out.println("*********************************");
				
				int input = Integer.parseInt(s.nextLine());

				switch (input) {

				case 1:
					checkin();
					break;

				case 2:
					 checkout();
					break;
				case 3:
					 roomstat();
					break;
				case 4:
					System.out.println("호텔문을 닫았습니다");

					return;
				}
			}
		}

		private void roomstat() {
			System.out.println("*****************************************");
			System.out.println("번호 \t\t 호실 \t\t 투숙");
			System.out.println("*****************************************");
			
				int cnt = 0;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, userId, password);
					stmt = con.createStatement();
					sql = "select * from hotel ";
					rs = stmt.executeQuery(sql);

					while(rs.next()) {
						cnt++;

						System.out.println(" " + cnt + "번 " + "\t \t" + rs.getInt("room_nm") +"호실"+ "\t\t" +rs.getString("cnt_nm")+" 고객님");
						System.out.println("==========================================");
						
					}

				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();

				}finally {

					if(rs != null) try{rs.close();}catch (SQLException e2) {}
					if(stmt != null) try{stmt.close();}catch (SQLException e2) {}
					if(con != null) try{con.close();}catch (SQLException e2) {}
						
					}
				}		
			
		private void checkout() {
			System.out.println("*********************************");
			System.out.println("어느 방에서 체크아웃 하시겠습니까?");
			System.out.println("*********************************");
			System.out.println("호실을 입력해 주세요");
			String room = s.nextLine();
			int a = 0;
			
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, userId, password);
					sql = "select * from HOTEL ";
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						
						if(rs.getString("room_nm").equals(room)) {
							
							sql = "Delete hotel Where room_nm = " + room;
							pstmt = con.prepareStatement(sql);
							pstmt.executeUpdate();
							a = 1;
							System.out.println(room + "호실의 체크아웃을 완료했습니다.");
							break;
						}
						
					}
					if(a == 0) {
					
						System.out.println("정보가 없습니다 다시 확인해 주세요");
					}

				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();
				} finally {

					if (pstmt != null)
						try {
							pstmt.close();
						} catch (SQLException e2) {
						}
					if (con != null)
						try {
							con.close();
						} catch (SQLException e2) {
					}
				}
		  }

		private void checkin() {
			System.out.println("*******************************");
			System.out.println("어느 방에 체크인 하시겠습니까?");
			System.out.println("*******************************");
			System.out.println("이름을 입력해 주세요");
			String name = s.nextLine();
			System.out.println("호실을 입력해 주세요");
			String room = s.nextLine();
			int b = 0;

				try {
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, userId, password);
				
					stmt = con.createStatement();
					sql = " select * from HOTEL";
					rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						
						if(rs.getString("room_nm").equals(room)) {
							System.out.println("이미 등록된 방입니다 다시 확인해 주세요");
							b = 1;
							break;
							
						}
					}if(b == 0) {

							sql = "insert into  hotel" + " (room_nm ,cnt_nm) " + " values(?,?)" ;
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, room);
							pstmt.setString(2, name);			
							pstmt.executeUpdate();
							System.out.println(name +" 고객님 " +room+" 호실 체크인 완료했습니다. ");

					}

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			} finally {

				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e2) {
					}
				if (con != null)
					try {
						con.close();
					} catch (SQLException e2) {
				}
			}
		}
	}


