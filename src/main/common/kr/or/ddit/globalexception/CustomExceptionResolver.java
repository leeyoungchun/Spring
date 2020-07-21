package kr.or.ddit.globalexception;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		// 클라이언트 서버 대상 요청시 서버 내 경량화된 쓰레드 생성(응답 전송시 GC)
		String currentThread = Thread.currentThread().getName();
		// 익셉션 발생 메서드와 해당 메서드를 포함하는 클래스명 추출 
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		String exceptionOccuredClazz = handlerMethod.getBean().getClass().getName();
		String exceptionOccuredMethod = handlerMethod.getMethod().getClass().getName();
		int lineNumber = ex.getStackTrace()[0].getLineNumber();
		String exceptionType = ex.getClass().getName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd kk:mm:ss");
		
		System.out.println("에러 발생 : " + currentThread 
									+ " | 클래스 : " + exceptionOccuredClazz 
									+ " | 메서드" + exceptionOccuredMethod 
									+ " | 라인 : " + lineNumber 
									+ " | 익셉션 타입 : " + exceptionType 
									+ " | 발생시간 : " + sdf.format(new Date()));
		return super.resolveException(request, response, handler, ex);
	}

}
