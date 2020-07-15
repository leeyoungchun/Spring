package kr.or.ddit.join.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// /SpringToddler/user/join/loginForm.do
// /SpringToddler/user/join/loginCheck.do
// /SpringToddler/user/join/logOut.do

@Controller
@RequestMapping("/user/join/")
public class JoinController {

	@RequestMapping("loginForm")
	public String loginForm(){
		// 반환값 : join/loginForm
		// InternalResourceViewResolver 취득
		// prefix(/WEB-INF/views/user/)
		// suffix(.jsp)
		// /WEB-INF/views/user/join/loginForm.jsp 포워딩 처리
		return "join/loginForm";
	}
}
