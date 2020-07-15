package kr.or.ddit.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.FreeboardVO;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// /SpringToddler/user/member/memberList.do
// /SpringToddler/user/member/memberView.do
// /SpringToddler/user/member/memberForm.do
@Controller
@RequestMapping("/user/member/")
public class MemberController {
	@Autowired
	private IMemberService service;
	
	
	@RequestMapping("memberList")
	public Model memberList(String search_keycode, String search_keyword
							, Map<String, String> params, Model model
							){
//		Map<String, String> params = new HashMap<String, String>();
		params.put("search_keycode", search_keycode);
		params.put("search_keyword", search_keyword);
		
		List<MemberVO> memberList = this.service.memberList(params);
		// memberList => view resolver => memberList.jsp 전달 -포워딩
		/* Model model = new ExtendedModelMap();*/
		model.addAttribute("memberList",memberList);
		return model;
	}
	
	@RequestMapping("memberView")
	public ModelMap memberView(String mem_id, Map<String,String> params
								,ModelMap modelmap){
		params.put("mem_id", mem_id);
		
		MemberVO memberInfo = this.service.memberInfo(params);
		
		//ModelMap modelmap = new ModelMap();
		modelmap.addAttribute("memberInfo",memberInfo);
		return modelmap;
	}
	
	@RequestMapping("updateMemberInfo")
	private String updateMember(MemberVO memberInfo){
		this.service.updateMemberInfo(memberInfo);
		return "redirect:/user/member/memberList.do";
	}
	// /user/member/deleteMemberInfo.do?user_id=a001
	@RequestMapping("deleteMemberInfo")
	public String deleteMember(@RequestParam(required=false, defaultValue="널 대체값") String mem_id, Map<String,String> params) throws Exception{
		params.put("mem_id", mem_id);
		this.service.deleteMemberInfo(params);
		String message = URLEncoder.encode("탈퇴처리 되었습니다.","utf-8");
		return "redirect:/user/join/loginForm.do?message="+message;
	}
}
