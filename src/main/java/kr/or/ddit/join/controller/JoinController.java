package kr.or.ddit.join.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// /SpringToddler/user/join/loginForm.do
// /SpringToddler/user/join/loginCheck.do
// /SpringToddler/user/join/logOut.do

@Controller
@RequestMapping("/user/join/")
public class JoinController {
@Autowired
private IMemberService service;
	
	@RequestMapping("loginForm")
	public void loginForm(){
		// 반환값 : join/loginForm
		// InternalResourceViewResolver 취득
		// prefix(/WEB-INF/views/user/)
		// suffix(.jsp)
		// /WEB-INF/views/user/join/loginForm.jsp 포워딩 처리
		//return "user/join/loginForm";
	}
	
	//POST 전송방식 : mem_id=a001&mem_pass=asdfasdf
	@RequestMapping("loginCheck")
	public String loginCheck(String mem_id, String mem_pass, HttpServletRequest request
						 	 , HttpServletResponse response, HttpSession session) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		MemberVO memberInfo = this.service.memberInfo(params);
		
		if(memberInfo == null){
			// 리다이렉트(컨텍스트루트|패스 생략)
			String message = URLEncoder.encode("회원이 아닙니다.","utf-8");
			return "redirect:/user/join/loginForm.do?message="+message;
		}else{
			// 포워드(컨텍스트루트|패스 생략)
			session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
			return "forward:/user/member/memberList.do";
		}
	}
	
	@RequestMapping("logOut")
	public String logOut(HttpSession session) throws Exception{
		String message = URLEncoder.encode("로그아웃 되었습니다.","utf-8");
		session.invalidate();
		return "redirect:/user/join/loginForm.do?message="+message;
	}
}
