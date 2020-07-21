package kr.or.ddit.utiles;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 클라이언트 요청(인터셉터 선언 순서대로 동작)
// 		1. preHandle() 콜백 (default return true)
//		2. 컨트롤러 클래스 내 클라이언트의 해당 요청을 처리하는 컨트롤러 메서드 콜백 
//		3. postHandle() 콜백
//		4. afterCompletion() 콜백
//		5. 응답 컨텐츠가 클라이언트 대상 전송
// 클라이언트 비동기식(ajax) 요청(인터셉터 선언 순서대로 동작)
// 		1. preHandle() 콜백 (default return true)
//		2. 컨트롤러 클래스 내 클라이언트의 해당 요청을 처리하는 컨트롤러 메서드 콜백 
//		3. 응답 컨텐츠가 클라이언트 대상 전송


public class EncodingInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("postHandler() 종료 후 컨트롤러 메서드의 반환값이 ViewResolver에 "
									+ "전달로 응답 컨텐츠가 HttpServletResponse의 출력버퍼에 저장된 직후 콜백");
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("비동기식 통신(ajax) 요청시 postHandle() 콜백 메서드와 afterCompletion() 콜백 메서드가 무시되고 콜백");
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("컨트롤러 클래스 내 클라이언트의 해당 요청을 처리하는 컨트롤러 메서드 종료 후 콜백");
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("컨트롤러 클래스 내 클라이언트의 해당 요청을 처리하는 컨트롤러 메서드 호출 전 콜백");
		
		String encodingType = request.getCharacterEncoding();
		
		if(!StringUtils.isNotEmpty(encodingType)){
			encodingType = "UTF-8";
		}
		
		request.setCharacterEncoding(encodingType);
		
		//return false : 컨트롤러 메서드 호출 무시
		//return true : 컨트롤러 메서드 호출(default) 
		return true;
	}

	
	
	
	
	
/*	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		// 클라이언트 전송하는 쿼리스트링 전송방식 : 
		//			GET : server.xml <connector URIEncoding="UTF-8">
		//			POST : jsp 내 스크립트릿 영역 request.setCharacterEncoding("UTF-8")
		// 클라이언트의 쿼리스트링 전송시 특정 인코딩 처리 요구를 요청 헤더에 포함 가능.
		// 			자바스크립트 : Accept-Charset : UTF-8
		String encodingType = servletRequest.getCharacterEncoding();
		
		// http://commons.apache.org
		// java.lang.String 클래스의 확장 API 라이브러리
		if(!StringUtils.isNotEmpty(encodingType)){
			encodingType = "UTF-8";
		}
		
		servletRequest.setCharacterEncoding(encodingType);
		chain.doFilter(servletRequest, servletResponse);
	}*/


}
