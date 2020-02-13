package Dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import Vo.LprodVO;
import Vo.ProdVO;



public class ProdInfoDaoImpl implements ProdInfoDao {
	private static ProdInfoDaoImpl instance;
	private SqlMapClient smc;
	private ProdInfoDaoImpl() {
		try {
			System.out.println(12);
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	public static ProdInfoDao getInstance() {
		if (instance == null) {
			instance = new ProdInfoDaoImpl();
		}
		return instance;
	}
	

	@Override
	public List<ProdVO> getSearchData(Map<String, String> map) {
		List<ProdVO> list = null;
		try {
			list = smc.queryForList("prodInfo.searchData",map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<LprodVO> getLpName() {
		List<LprodVO> list = null;
		try {
			list = smc.queryForList("prodInfo.getLpname");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProdVO> getPlist(String lpName) {
		List<ProdVO> list = null;
		try {
			list = smc.queryForList("prodInfo.getPname", lpName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
