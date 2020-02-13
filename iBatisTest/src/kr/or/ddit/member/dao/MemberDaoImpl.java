package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;


public class MemberDaoImpl implements IMemberDao {
	
	private static MemberDaoImpl dao;
	
	private SqlMapClient smc;//16 sqlmapclient 선언
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberDaoImpl() {
		
		//16선언
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd ;
//		iBatis를 이용하여 DB자료를 처리하는 작업 순서
			// 1. iBatis의 환경설정 파일을 읽어와 실행시킨다.
			try {
				rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				//	1-1. xml문서 읽어오기
				// 설정파일의 인코딩 설정
				
				//	1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 계속할 객체 생성
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				rd.close(); // Reader 객체 닫기
			}catch(IOException e) {
				e.printStackTrace();
			}		
	}
			
	public static MemberDaoImpl getInstance() {
		if(dao==null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	/**
	  자원 반납용 메서드
	 */
	private void disConnect() {
		// 사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			
			Object obj = smc.insert("member.insertMember",mv);
			
			if(obj ==null) { // 성공
				cnt =1;
			}
			
		}catch(SQLException e)	{
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean getMember(String memId) {
		
		boolean chk= false;
		
		try {
			//16 getMember. resultClass를 int로 했기때문에,
			int cnt = (int) smc.queryForObject("member.getMember",memId);
					
					
			if(cnt > 0) {
				chk = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			chk = false;
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		//16 getMemberAlll
		try {
				memList = smc.queryForList("member.getMemberAll");
							
		}catch(SQLException e) {
			System.out.println("회원 자료 가져오기 실패");
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			
			cnt = smc.update("member.updateMember",mv);
			
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			
			cnt = smc.delete("member.deleteMember",memId);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<>();
		
		try { 
			
			memList = smc.queryForList("member.getSearchMember",mv);
			
			
		}catch(SQLException e) {
			memList = null;
			e.printStackTrace();
		}finally {
			disConnect();
		}
					
		return memList;
	}

}
