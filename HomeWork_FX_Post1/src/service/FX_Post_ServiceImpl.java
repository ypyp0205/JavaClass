package service;

import java.util.List;
import java.util.Map;

import dao.FX_Post_Dao;
import dao.FX_Post_DaoImpl;
import vo.FX_Post_VO;


public class FX_Post_ServiceImpl implements FX_Post_Service {

	private static FX_Post_ServiceImpl instance;
	private FX_Post_ServiceImpl() {}
	
	public static FX_Post_Service getInstance() {
		if (instance == null) {
			instance = new FX_Post_ServiceImpl();
		}
		return instance;
	}
	
	FX_Post_Dao  PostnumDao = FX_Post_DaoImpl.getInstance();
//	@Override
//	public List<PostnumVO> getSearch(Map<String, String> map) {
//		return PostnumDao.getSearch(map);
//	}

	@Override
	public List<FX_Post_VO> getSearchdong(String dong) {
		
		return PostnumDao.getSearchdong(dong);
	}

	@Override
	public List<FX_Post_VO> getSearchzipcode(String zipcode) {
		
		return PostnumDao.getSearchzipcode(zipcode);
	}


	

}
