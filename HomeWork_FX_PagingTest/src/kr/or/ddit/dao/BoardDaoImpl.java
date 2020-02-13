package kr.or.ddit.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements BoardDao {
	private SqlMapClient smc;
	private static BoardDaoImpl bDao;
	
	private BoardDaoImpl() {

		try {
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
		smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		rd.close();
		
	   }catch(IOException e) {
		   
	   }
		
	}
	
	public static BoardDaoImpl getInstance () {
		
		if(bDao == null) {
			bDao = new BoardDaoImpl();

		}
		return bDao;
  }

	@Override
	public int insertContent(BoardVO bv) {
		int cnt = 0;
		
		try {
		Object obj = smc.insert("board.insertContent", bv); 
		
		if(obj == null) {
			
			cnt = 1;
			
		 }
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return cnt;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BoardVO> displayBoardAll() {
		
		
		List <BoardVO> bv = new ArrayList<BoardVO>();
		try {
			
			bv = smc.queryForList("board.displayBoardAll");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return bv;
	}

	@Override
	public int updateBoard(Map<String, String> bmap) {
		int cnt = 0;
		
		try {
		cnt = smc.update("board.updateBoard",bmap);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(String memId) {
		int cnt = 0;
		try {
			cnt = smc.delete("board.deleteBoard",memId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		List<BoardVO> bdist = new ArrayList<BoardVO>();
				
		try {
			bdist =smc.queryForList("board.searchBoard",bv);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bdist;
	}
	
	
}
