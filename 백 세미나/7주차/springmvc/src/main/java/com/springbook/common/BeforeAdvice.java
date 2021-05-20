package com.springbook.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect // Aspect는 Pointcut과 Advice의 결합이다.
public class BeforeAdvice {
	// allPointCut()으로 지정한 메소드가 호출될 때, beforeLog() 메소드가 Before 형태로 동작하도록 설정
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp){
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();

		System.out.println("[사전 처리] " + method +
			"() 메소드 ARGS 정보 : " + args[0].toString());
	}
}
