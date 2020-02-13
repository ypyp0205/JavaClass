package Service;

import java.util.List;
import java.util.Map;

import Dao.ProdInfoDao;
import Dao.ProdInfoDaoImpl;
import Vo.LprodVO;
import Vo.ProdVO;



public class ProdInfoServiceImpl implements ProdInfoService {
	
	private static ProdInfoServiceImpl instance;
	private ProdInfoServiceImpl() {}
	
	public static ProdInfoService getInstance() {
		if (instance == null) {
			instance = new ProdInfoServiceImpl();
		}
		return instance;
	}
	
	ProdInfoDao dao = ProdInfoDaoImpl.getInstance();
	@Override
	public List<ProdVO> getSearchData(Map<String, String> map) {
		return dao.getSearchData(map);
	}

	@Override
	public List<LprodVO> getLpName() {
	System.out.println(43344);
		return dao.getLpName();
	}

	@Override
	public List<ProdVO> getPlist(String lpName) {
		return dao.getPlist(lpName);
	}

}
