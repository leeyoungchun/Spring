package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component("logPrintClazz")
public class LogPrintAspectClazz {
	@Loggable
	public static Logger logger;
	
	//메서드(joinpoint), 메서드들(pointcut) 대상 호출전(advice) 주입될 공통 코드(aspect)의 실행(weaving)
	public void theWholeJoinpointCallBefore(JoinPoint joinPoint){
		// 해당 메서드(joinpoint)가 포함된 빈 클래스 정보
		String beanClazzName = joinPoint.getTarget().getClass().getName();
		String beanMethodName  = joinPoint.getSignature().getName();
		
		logger.debug("target : {} | joinpoint : {}가 호출되었습니다.", beanClazzName, beanMethodName);
	}
	
	//메서드(joinpoint), 메서드들(pointcut) 대상 종료후(advice) 주입될 공통 코드(aspect)의 실행(weaving)
	public void theWholeJoinpointCallAfter(JoinPoint joinPoint){
		// 해당 메서드(joinpoint)가 포함된 빈 클래스 정보
		String beanClazzName = joinPoint.getTarget().getClass().getName();
		String beanMethodName  = joinPoint.getSignature().getName();
		
		logger.debug("target : {} | joinpoint : {}가 종료되었습니다.", beanClazzName, beanMethodName);
	}

	//메서드(joinpoint), 메서드들(pointcut) 대상 호출 및 익셉션 발생 후(advice) 주입될 공통 코드(aspect)의 실행(weaving)
	public void theWholeJoinpointCallThrowing(JoinPoint joinPoint, Exception ex){
		// 해당 메서드(joinpoint)가 포함된 빈 클래스 정보
		String beanClazzName = joinPoint.getTarget().getClass().getName();
		String beanMethodName  = joinPoint.getSignature().getName();
		
		logger.debug("target : {} | joinpoint : {}가 호출 후 {} 익셉션이 발생되었습니다.", beanClazzName, beanMethodName, ex.getMessage());
	}
	
	//메서드(joinpoint), 메서드들(pointcut) 대상 호출 전과 종료 후(advice) 주입될 공통 코드(aspect)의 실행(weaving)
		public Object theWholeJoinpointCallAround(ProceedingJoinPoint joinPoint) throws Throwable{
			// 해당 메서드(joinpoint)가 포함된 빈 클래스 정보
			String beanClazzName = joinPoint.getTarget().getClass().getName();
			String beanMethodName  = joinPoint.getSignature().getName();
			
			logger.debug("target : {} | joinpoint : {}가 호출되고.....", beanClazzName, beanMethodName);
			// 상단 코드 : 해당 메서드(joinpoint) 호출 전 실행
			Object methodReturnValue = joinPoint.proceed();
			// 하단 코드 : 해당 메서드(joinpoint) 종료 후 실행
			logger.debug("target : {} | joinpoint : {}가 종료됩니다......", beanClazzName, beanMethodName);
		
			return methodReturnValue;
		}
	
	
	
}
