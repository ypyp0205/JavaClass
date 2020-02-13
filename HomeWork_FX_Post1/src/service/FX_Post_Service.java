package service;

import java.util.List;
import java.util.Map;

import vo.FX_Post_VO;

public interface FX_Post_Service {

//	List<PostnumVO> getSearch(Map<String, String> map);
 
	List<FX_Post_VO> getSearchdong(String dong);
	List<FX_Post_VO> getSearchzipcode(String zipcode);

	
	
}
