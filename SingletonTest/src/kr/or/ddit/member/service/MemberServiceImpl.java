package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	// 사용할 Dao의 객체변수를 선언한다.
	private IMemberDao memDao;
	
	// 자기자신 참조변수
	private static MemberServiceImpl service;
	
	public MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		// TODO Auto-generated method stub
		return memDao.insertMember(mv);
	}

	@Override
	public boolean getMember(String memId) {
		// TODO Auto-generated method stub
		return memDao.getMember(memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return memDao.getAllMemberList();
	}

	@Override
	public int updateMember(MemberVO mv) {
		// TODO Auto-generated method stub
		return memDao.updateMember(mv);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return memDao.deleteMember(memId);
	}



	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		// TODO Auto-generated method stub
		return memDao.getSearchMember(mv);
	}

}
