package com.springbook.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect // Aspect는 Pointcut과 Advice의 결합이다.
public class AroundAdvice {
	// allPointCut()으로 지정한 메소드가 호출될 때, aroundLog() 메소드가 Around 형태로 동작하도록 설정
	// Around 어드바이스만 ProceedingJoinPoint 객체를 매개변수로 받아 proceed() 메소드를 실행한다.
	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object obj = pjp.proceed();
		stopWatch.stop();
		System.out.println(method + "() 메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");
		return obj;
	}
}