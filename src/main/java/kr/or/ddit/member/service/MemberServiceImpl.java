package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
