package kr.or.ddit.service;


import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.BoardDaoImpl;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements BoardService{

	private static BoardServiceImpl Bsi;
	private BoardDaoImpl bdo;
	private BoardServiceImpl() {
	
	bdo = BoardDaoImpl.getInstance();
		
	}
	public static BoardServiceImpl getInstance () {
		
		if(Bsi == null) {
			
			Bsi = new BoardServiceImpl();
		}
		return Bsi;
	}
	@Override
	public int insertContent(BoardVO bv) {
		
		return bdo.insertContent(bv);
	}


	@Override
	public List<BoardVO> displayBoardAll() {
		
		return bdo.displayBoardAll();
	}

	@Override
	public int updateBoard(Map<String, String> bmap) {
		
		return bdo.updateBoard(bmap);
	}

	@Override
	public int deleteBoard(String memId) {
		
		return bdo.deleteBoard(memId);
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		return bdo.searchBoard(bv);
	}

}
