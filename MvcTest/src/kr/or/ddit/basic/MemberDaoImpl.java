package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	MemberVO mv = new MemberVO();

	@Override
	public int insertMember(MemberVO MV) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember " + " (mem_id, mem_name, mem_tel, mem_addr)" + " values(?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_id());
			pstmt.setString(2, mv.getMem_name());
			pstmt.setString(3, mv.getMem_tel());
			pstmt.setString(4, mv.getMem_addr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 자원반납
		}

		return cnt;
	}

	@Override
	public boolean getMember(String memId) {

		boolean chk = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from mymember " + " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

			if (cnt > 0) {
				chk = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			chk = false;
		} finally {
			disConnect();
		}
		return chk;

	}

	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			conn = DBUtil.getConnection();

			String sql = "select * from mymember";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				MemberVO mv = new MemberVO();
				mv.setMem_id(rs.getString("mem_id"));
				mv.setMem_name(rs.getString("mem_name"));
				mv.setMem_tel(rs.getString("mem_tel"));
				mv.setMem_addr(rs.getString("mem_addr"));

				memList.add(mv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();

			String sql = "update mymember " + "set mem_name = ?, " + "   mem_tel = ?," + "   mem_addr = ?"
					+ "where mem_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_name());
			pstmt.setString(2, mv.getMem_tel());
			pstmt.setString(3, mv.getMem_addr());
			pstmt.setString(4, mv.getMem_id());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 자원 반납
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from mymember where mem_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 자원반납
		}
		return 0;
	}

	private void disConnect() {
		// 사용했던 자원 반납
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ee) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException ee) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ee) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ee) {
			}

	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {

		List<MemberVO> memList = new ArrayList<>();

		try {
			conn = DBUtil3.getConnection();

			String sql = "select * from mymember where 1=1 ";
			if (mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				sql += " and mem_id = ? ";
			}
			if (mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				sql += " and mem_name = ? ";
			}
			if (mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if (mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' ";
			}

			pstmt = conn.prepareStatement(sql);

			int index = 1;

			if (mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				pstmt.setString(index++, mv.getMem_id());
			}
			if (mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				pstmt.setString(index++, mv.getMem_name());
			}
			if (mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				pstmt.setString(index++, mv.getMem_tel());
			}
			if (mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				pstmt.setString(index++, mv.getMem_addr());
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO memVO = new MemberVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_addr(rs.getString("mem_addr"));

				memList.add(memVO);
			}
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}finally {
			disConnect(); // 자원 반납
		}
		
		return memList;
	}


}
