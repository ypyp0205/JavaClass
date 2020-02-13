package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.vo.BoVo;

public class BoardDaoImpl implements BoardDao{
	private Charset charset;
	private Reader rd;
	private SqlMapClient smc;
	private static BoardDaoImpl instance;
	
	private BoardDaoImpl() {
		try {
			charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			smc =SqlMapClientBuilder.buildSqlMapClient(rd);
				rd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static BoardDaoImpl getInstance() {
		if(instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}
	@Override
	public List<BoVo> mainViewselect() {
		List<BoVo> list = new ArrayList<>();
		try {
			list=smc.queryForList("board.all");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean dialogwrite(BoVo bv) {
		boolean flag=false;
		try {
			Object obj =smc.insert("board.write",bv);
			
			if(obj == null) {
				flag=true;
				System.out.println(bv.getNames()+"님 게시글이 등록되었습니다.");
			}else {
				flag=false;
				System.out.println(bv.getNames()+"님 게시글이 미등록처리 됬습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean viewsInsert(BoVo bv) {
		boolean flag= false;
			try {
				Object obj=smc.insert("board.viewCount",bv);
				if(obj == null) {
					flag=true;
					System.out.println("조회수 +1 증가");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
		return flag;
	}

	public boolean updaterepair(BoVo vo) {
		boolean flag= false;
		
			try {
				int cnt =smc.update("board.repair",vo);
				if(cnt > 0) {
					flag=true;
					System.out.println("update 작업 성공");
				}else {
					System.out.println("udate 작업 실패");
				}
				System.out.println("--------------------------------------------------------");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return flag;
	}

	public boolean deleteRow(BoVo vo) {
		boolean flag = false;
		try {
			int cnt =smc.delete("board.delete",vo);
			if(cnt > 0) {
				flag=true;
				System.out.println("delete 작업 성공");
			}else {
				System.out.println("delete 작업 실패");
			}
			System.out.println("--------------------------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public List<BoVo> iWantSearch(HashMap<String, String> combotext) {
		List<BoVo> list = new ArrayList<>();
		try {
			list=smc.queryForList("board.iWantSearch",combotext);
			System.out.println(list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
