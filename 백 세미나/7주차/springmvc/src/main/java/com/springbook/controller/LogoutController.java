package com.springbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	// HttpSession을 매개변수로 선언하면
	// 스프링 컨테이너가 로그아웃을 요청한 브라우저와 매핑된 세션 객체를 찾아서 넘겨준다.
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate(); // 매개변수로 받은 세션 객체를 강제 종료
		return "login.jsp";
	}

}
