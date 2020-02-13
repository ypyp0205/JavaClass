package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil;

public class BoardDaoImpl implements IBoardDao {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**
	 * 자원반납 메서드
	 */
	private void disconnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}

	
	@Override
	public List<BoardVO> getAllBoardList() {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoard_no(rs.getInt("board_no"));
				bv.setBoard_title(rs.getString("board_title"));
				bv.setBoard_writer(rs.getString("board_writer"));
				bv.setBoard_date(rs.getDate("board_date"));
				bv.setBoard_content(rs.getString("board_content"));
				
				boardList.add(bv);
			}
			
		}catch(SQLException e) {
			System.out.println("게시글 목록 가져오기 실패 !!!");
			e.printStackTrace();
		}finally {disconnect();}
		
		return boardList;
	}

	
	@Override
	public boolean getBoard(int board_no) {

		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
			if(cnt > 0) { chk = true;}
		}catch(SQLException e) {e.printStackTrace(); chk = false;}
		finally {disconnect();}
		
		return chk;
	}

	
	@Override
	public int insertBoard(BoardVO bv) {
		
		int check = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) "
					+ "values ( board_seq.nextVal , ? , ? , sysdate , ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			
			check = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("게시글 작성 실패!!!");
			e.printStackTrace();
		}finally {disconnect();}
		
		return check;
	}

	
	@Override
	public int updateBoard(BoardVO bv) {
		
		int check = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update jdbc_board set "
						+ " board_title = ?, "
						+ " board_writer = ?, "
						+ " board_date = sysdate, "
						+ " board_content = ? "
						+ " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			pstmt.setInt(4, bv.getBoard_no());
			
			check = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("게시글 수정 실패!!!");
			e.printStackTrace();
		}finally{disconnect();}
		
		return check;
	}

	
	@Override
	public int deletBoard(int board_no) {
		
		int check = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			check = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("게시글 삭제 실패!!!");
			e.printStackTrace();
		}finally {disconnect();}
		
		return check;
	}

	
	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board where 1=1";
			
			if(bv.getBoard_no()!=0) {
				sql += " and board_no = ?";						}
			if(bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
				sql += " and board_title = ?";						}
			if(bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
				sql += " and board_writer = ?";						}
			if(bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
				sql += " and board_content Like '%'|| ? ||'%'";		}
			
			pstmt =  conn.prepareStatement(sql);
			int index = 1;
			
			if(bv.getBoard_no()!=0) {
				pstmt.setInt(index++, bv.getBoard_no());		}
			if(bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
				pstmt.setString(index++, bv.getBoard_title());		}
			if(bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
				pstmt.setString(index++, bv.getBoard_writer());		}
			if(bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
				pstmt.setString(index++, bv.getBoard_content());		}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bv2 = new BoardVO();
				bv2.setBoard_no(rs.getInt("Board_no"));
				bv2.setBoard_title(rs.getString("Board_title"));
				bv2.setBoard_writer(rs.getString("Board_writer"));
				bv2.setBoard_date(rs.getDate("Board_date"));
				bv2.setBoard_content(rs.getString("Board_content"));
				
				boardList.add(bv2);
			}
			
		}catch(SQLException e) {
			boardList = null;
			e.printStackTrace();
		}finally {disconnect();}
		
		return boardList;
	}

}
