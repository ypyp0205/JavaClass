package kr.or.ddit.board.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.board.vo.BoVo;

public interface BoardDao {

	public List<BoVo> mainViewselect();

	public boolean dialogwrite(BoVo bv);
	
	public boolean viewsInsert(BoVo bv);
	
	public boolean updaterepair(BoVo vo);
	
	public List<BoVo> iWantSearch(HashMap<String, String> combotext);
}
