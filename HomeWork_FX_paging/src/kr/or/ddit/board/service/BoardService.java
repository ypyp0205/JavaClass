package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.board.vo.BoVo;

public interface BoardService {

	public List<BoVo> mainViewList();
	
	public boolean dialogwrite(BoVo bv);
	
	public boolean viewsCount(BoVo bv);
	
	public boolean saveUp(BoVo vo);
	
	public List<BoVo> listSearch(HashMap<String, String> combotext);
}
