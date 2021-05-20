package com.springbook.controller;

import java.util.HashMap;
import java.util.Map;

import com.springbook.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.domain.BoardVO;

@Controller
@SessionAttributes("board") // 수정 시 유용.
// model에 "board"라는 이름으로 저장되는 데이터가 있다면
// 그 데이터를 세션(HttpSession)에도 자동 저장하란 뜻
public class BoardController {

	@Autowired // DI 의존성 주입: 컨테이너가 직접 객체 사이의 의존관계를 처리하는 것
	private BoardService boardService;

	// 글 등록
	// Q. 클라이언트가 입력한 값을 어떻게 받을까?
	// 매개변수로 VO 객체는? 커맨드객체!
	// 스프링 컨테이너가 VO 객체 생성 후
	// VO 객체의 setter 메소드를 활용하여 setter injection 후
	// 해당 메소드에 객체 던져줌
	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(BoardVO vo) {
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do";
	}

	// 글 수정 - 신기하고도 어려운 세계...
	// 수정에 필요한 데이터는 메소드의 매개변수로 받는다.
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		// 1. 글 상세 조회를 통해 model에 "board"로 저장
		// 2. @SessionAttributes를 통해 세션에 "board"로 저장
		// 3. @ModelAttribute("board") 설정을 해석하면,
		// 세션에 "board"가 있다면 세션에서 꺼내서! 매개변수 vo에 할당하고
		// 4. 사용자가 입력한 수정 정보도 vo에 할당

		// 이렇게 안하면 새롭게 입력하지 못하는 정보 (작성자)는 null 로 수정
		boardService.updateBoard(vo);
		return "redirect:getBoardList.do";
		// + transaction 로그도 확인 가능
	}

	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}

	// 글 상세 조회
	// 몇번째 글인지 인자로, model에 board라는 변수명으로 검색결과 저장
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "getBoard.jsp";
	}

	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		// Model: 뷰에 전달할 객체 정보 저장
		model.addAttribute("boardList", boardService.getBoardList(vo));
		return "getBoardList.jsp";
	}

	// 검색 조건 목록 설정
	// 막고 실행해보기 -> 빈 선택값
	// @ModelAttribute가 설정된 메소드는 @RequestMapping보다 먼저 호출된다.
	// @ModelAttribute 메소드 실행 결과 리턴된 객체는 자동으로 model에 저장됨. 따라서 view에서 사용가능
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
}
