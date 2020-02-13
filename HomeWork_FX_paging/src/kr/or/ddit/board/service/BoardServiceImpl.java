package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.vo.BoVo;

public class BoardServiceImpl implements BoardService{

	private static BoardServiceImpl instance;

	BoardDaoImpl boaserv = BoardDaoImpl.getInstance();
	
	private BoardServiceImpl(){
		
	}
	
	public static BoardServiceImpl getInstance() {
		if(instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}

	@Override
	public List<BoVo> mainViewList() {
		return boaserv.mainViewselect();
	}
	@Override
	public boolean dialogwrite(BoVo bv) {
		return boaserv.dialogwrite(bv);
		
	}
	@Override
	public boolean viewsCount(BoVo bv) {
		return boaserv.viewsInsert(bv);
	}

	public boolean saveUp(BoVo vo) {
		return boaserv.updaterepair(vo);
		// TODO Auto-generated method stub
		
	}

	public boolean drop(BoVo vo) {
		return boaserv.deleteRow(vo);
	}

	public List<BoVo> listSearch(HashMap<String, String> combotext) {
		return boaserv.iWantSearch(combotext);
	}
	
	
}
