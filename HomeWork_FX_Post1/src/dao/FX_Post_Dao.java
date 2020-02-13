package dao;

import java.util.List;

import vo.FX_Post_VO;


public interface FX_Post_Dao {
	
//	List<PostnumVO> getSearch(Map<String, String> map);
	List<FX_Post_VO> getSearchdong(String dong);
	
	List<FX_Post_VO> getSearchzipcode(String zipcode);
	
	
}
