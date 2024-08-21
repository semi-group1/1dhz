package semi.service;

import semi.model.dao.MemberDao;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
//	
//	public boolean selectOneUser(String email, String password) {
//		return memberDao.selectOneUser(email, password);
//	}

}
