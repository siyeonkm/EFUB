package com.springbook.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect // Aspect는 Pointcut과 Advice의 결합이다.
public class LogAdvice {
	// allPointCut()으로 지정한 메소드가 호출될 때, printLog() 메소드가 Before advice로 동작하도록 설정
	@Before("PointcutCommon.allPointcut()")
	public void printLog(){
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
