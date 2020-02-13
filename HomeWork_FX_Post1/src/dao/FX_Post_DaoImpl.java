
package dao;

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

import vo.FX_Post_VO;

public class FX_Post_DaoImpl implements FX_Post_Dao {

	private static FX_Post_DaoImpl instance;
	private SqlMapClient smc;
	
	private FX_Post_DaoImpl(){
		try {
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
	
	public static FX_Post_Dao getInstance() {
		if (instance == null) {
			instance = new FX_Post_DaoImpl();
		}
		return instance;
	}

//	@Override
//	public List<PostnumVO> getSearch(Map<String, String> map) {
//		List<PostnumVO> list = null;
//		try {
//			list = smc.queryForList("Postnum.search", map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	@Override
	public List<FX_Post_VO> getSearchdong(String dong) {
		
		List<FX_Post_VO> list = new ArrayList<FX_Post_VO>();
		try {
			list = smc.queryForList("FX_Post.searchdong", dong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FX_Post_VO> getSearchzipcode(String zipcode) {
		
		List<FX_Post_VO> list = new ArrayList<FX_Post_VO>();
		try {
			list = smc.queryForList("FX_Post.searchzipcode", zipcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	
	
	

}
