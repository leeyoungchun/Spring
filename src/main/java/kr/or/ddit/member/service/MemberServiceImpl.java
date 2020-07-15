package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	// 싱글톤 패턴
	private static IMemberService service;
	private IMemberDao dao;

	public static IMemberService getInstance() {
		if (service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}

	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}

	@Override
	public MemberVO memberInfo(Map<String, String> params) {
		MemberVO memberInfo = null;
		try {
			memberInfo = dao.memberInfo(params);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return memberInfo;
	}
	
	
	@Override
	public List<MemberVO> memberList(Map<String, String> params) {
		List<MemberVO> memberList = null;
		try{
			memberList = dao.memberList(params);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return memberList;
	}
	
	@Override
	public void deleteMemberInfo(Map<String, String> params) {
		
		try{
			dao.deleteMemberInfo(params);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateMemberInfo(MemberVO memberInfo) {
		try{
			dao.updateMemberInfo(memberInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void insertMemberInfo(MemberVO memberInfo) {
		try{
			dao.insertMemberInfo(memberInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String totalCount(Map<String, String> params) {
		String totalCount = null;
		try {
			totalCount = dao.totalCount(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
}
