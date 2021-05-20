package com.springbook.common;

import com.springbook.domain.UserVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect // Aspect는 Pointcut과 Advice의 결합이다.
public class AfterReturningAdvice {
	// getPointcut()으로 지정한 메소드가 호출될 때, afterLog() 메소드가 AfterReturning 형태로 동작하도록 설정
	// After Returning 어드바이스는 비즈니스 메소드 수행 결과를 받아내기 위해 바인드 변수를 지정
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		if (returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			if (user.getRole().equals("Admin")) {
				System.out.println(user.getName() + " 로그인(Admin)");
			}
		}
		System.out.println("[사후 처리] " + method + "() 메소드 리턴값 : " + returnObj.toString());
	}
}