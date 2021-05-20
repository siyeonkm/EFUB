package com.springbook.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
	@Pointcut("execution(* com.springbook.service..*Impl.*(..))")
	public void allPointcut() {
	}

	@Pointcut("execution(* com.springbook.service..*Impl.get*(..))")
	public void getPointcut() {
	}
}