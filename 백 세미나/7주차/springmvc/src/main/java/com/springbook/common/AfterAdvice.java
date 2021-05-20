package com.springbook.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect // Aspect는 Pointcut과 Advice의 결합이다.
public class AfterAdvice {
	// allPointCut()으로 지정한 메소드가 호출될 때, finallyLog() 메소드가 After 형태로 동작하도록 설정
	@After("PointcutCommon.allPointcut()")
	public void finallyLog() {
		System.out.println("[사후 처리] 비즈니스 로직 수행 후 무조건 동작");
	}
}
