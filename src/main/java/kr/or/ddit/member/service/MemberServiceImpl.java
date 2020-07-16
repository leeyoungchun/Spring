package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

// 설정파일 : <bean name = "iMemberServiceImpl"
//			class="kr.or.ddit.member.service.IMemberServiceImpl"/>
@Service
public class MemberServiceImpl implements IMemberService {
	@Autowired
	private IMemberDao dao;

	@Override
	public MemberVO memberInfo(Map<String, String> params) throws Exception {
		MemberVO memberInfo = null;

		return memberInfo = dao.memberInfo(params);
	}

	@Override
	public List<MemberVO> memberList(Map<String, String> params)
			throws Exception {
		List<MemberVO> memberList = null;

		return memberList = dao.memberList(params);

	}

	@Override
	public void deleteMemberInfo(Map<String, String> params) throws Exception {

		dao.deleteMemberInfo(params);

	}

	@Override
	public void updateMemberInfo(MemberVO memberInfo) throws Exception {

		dao.updateMemberInfo(memberInfo);

	}

	@Override
	public void insertMemberInfo(MemberVO memberInfo) throws Exception {

		dao.insertMemberInfo(memberInfo);

	}

	@Override
	public String totalCount(Map<String, String> params) throws Exception {
		String totalCount = null;

		return totalCount = dao.totalCount(params);

	}
}
