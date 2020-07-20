package kr.or.ddit.join.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.utiles.CryptoGenerator;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;


// /SpringToddler/user/join/loginForm.do
// /SpringToddler/user/join/loginCheck.do
// /SpringToddler/user/join/loginOut.do
@Controller
@RequestMapping("/user/join/")
public class JoinController {
   
   @Autowired
   private MessageSourceAccessor accessor;
   @Autowired
   private IMemberService service;
   
   @RequestMapping("loginForm")
   public void loginForm(HttpServletRequest request){
      // 반환값 : join/loginForm
      // InternalResourceViewResolver 취득
      //    prefix (/WEB-INF/views/user/)
      //   suffix (.jsp)
      // /WEB-INF/views/user/join/loginForm.jsp 포워딩 처리
   //   return "user/join/loginForm";
      //RedirectAttribute를 활용해 전송되는 값 취득
      Map<String, ?> paramMap = RequestContextUtils.getInputFlashMap(request);
      if(paramMap != null){
         String message = (String) paramMap.get("message");
         System.out.println("RedirectAttribute 전달된 취득값:"+message);
      }
   }
   
   //SpringToddler/user/join/loginCheck.do
   //POST 전송방식 : mem_id=a001&mem_pass=asdfasdf
   @RequestMapping(value="loginCheck", method=RequestMethod.POST, params={"mem_id=b001"})
   public String loginCheck(String mem_id, String mem_pass, 
                        HttpServletRequest request, 
                        HttpSession session, 
                        HttpServletResponse response,
                        CryptoGenerator crypto)throws Exception{
	   
	  mem_id = crypto.decryptRSA(session, mem_id);
	  mem_pass = crypto.decryptRSA(session, mem_pass);
      Map<String, String> params = new HashMap<String, String>();
      params.put("mem_id", mem_id);
      params.put("mem_pass",mem_pass);
      
      MemberVO memberInfo = this.service.memberInfo(params);
   
   
   if(memberInfo == null){
      //리다이렉트(컨텍스트 루트|패스 생략)
      String message =  this.accessor.getMessage("fail.common.join",Locale.KOREA);
      message = URLEncoder.encode(message,"UTF-8");
      return "redirect:/user/freeboard/freeboardList.do?message="+message;
   }else{
      session.setAttribute("LOGIN_MEMBERINFO",memberInfo);
      // 포워드(컨텍스트 루트|패스 생략)
      return "forward:/user/freeboard/freeboardList.do";
   }
   }
   
   @RequestMapping("logout")
   public String logout(HttpSession session) throws Exception{
      session.invalidate();
      String message = this.accessor.getMessage("success.common.logout",Locale.KOREA);
      message = URLEncoder.encode(message,"UTF-8");
      return "redirect:/user/freeboard/freeboardList.do?message="+message;
   }
}
