package com.springbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.domain.UserVO;
import com.springbook.dao.UserDAO;

@Controller
public class LoginController {

	// 스프링에서는 @RequestMapping을 이용하여 HandlerMapping 설정을 대체한다.
	// 클라이언트의 GET "/login.do" 요청에 대해서 loginView 메소드가 실행되도록 한다.
	// loginView 메소드는 로그인 화면으로 이동할 때 실행됨.
	// 사용자가 입력할 값이 아무것도 없는 상태인데 매개변수로 UserVO 객체를 받아들이도록 설정
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {

		System.out.println("로그인 화면으로 이동...");
		vo.setId("test");
		vo.setPassword("test123"); // 마치 캐시처럼 id, password 정보가 자동으로 설정됨.
		// Command 객체란 : Controller 메소드 매개변수로 받은 VO(value object) 객체

		return "login.jsp"; // 포워딩. 브라우저의 url 변경 X
		// 리턴 값이 String이면 View의 이름으로 사용된다.
		// View 는 webapp이 루트 경로
	}

	// 로그인 버튼을 누르면 클라이언트는 POST "/login.do" 요청을 하게 되고
	// login 메소드가 실행된다.
	// Controller 메소드 매개변수로 다양한 Servlet API를 사용할 수 있다.
	// HttpSession도 그 중 하나.
	// session이란? 브라우저당 서버 메모리에 하나씩 유지되는 객체
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
		// 아래와 같이 service 없이 바로 repository 메소드를 실행하면 안됨
		UserVO user = userDAO.getUser(vo);
		if (user != null) {
			// 로그인 성공 시 사용자의 이름을 세션에 userName 이란 변수명으로 저장해
			// getBoardList.jsp 파일에서 출력하고 있다.
			session.setAttribute("userName", user.getName());
			return "redirect:getBoardList.do"; // 리다이렉트
			// controller가 view 경로를 리턴하면 기본적으로 포워딩 방식으로 작동함.
		} else
			return "login.jsp";
	}
}
