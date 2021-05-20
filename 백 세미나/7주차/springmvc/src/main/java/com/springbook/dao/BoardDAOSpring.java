package com.springbook.dao;

import java.util.List;


import com.springbook.domain.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//DAO(Data Access Object)
@Repository
public class BoardDAOSpring {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// SQL 명령어들
	private final String BOARD_INSERT1 = "SET @mymax := (SELECT MAX(seq) FROM board)";
	private final String BOARD_INSERT2 =
		"INSERT INTO board (seq, title, writer, content, cnt, regDate) VALUES (ifnull(@mymax, 0)+1,?,?,?,0,sysdate())";
	private final String BOARD_UPDATE = "UPDATE board SET title=?, content=? WHERE seq=?";
	private final String BOARD_DELETE = "DELETE FROM board WHERE seq=?";
	private final String BOARD_GET = "SELECT * FROM board WHERE seq=?";
	// 조회 시 조회수 업데이트를 위한 sql문. 실행하려면 트랜잭션 read-only 조건을 없애야함.
//	private final String BOARD_CNT = "UPDATE board SET cnt=cnt+1 WHERE seq=?";
	private final String BOARD_LIST = "SELECT * FROM board ORDER BY seq DESC";

	// CRUD 기능의 메소드 구현
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT1);
		jdbcTemplate.update(BOARD_INSERT2, vo.getTitle(), vo.getWriter(), vo.getContent().trim());
	}

	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent().trim(), vo.getSeq());
	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}

	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
		Object[] args = { vo.getSeq() };
		// 조회 시 조회수 업데이트를 위한 sql문. 실행하려면 트랜잭션 read-only 조건을 없애야함.
//		jdbcTemplate.update(BOARD_CNT, vo.getSeq());

		// 검색결과를 매핑할 RowMapper 객체를 지정해야함.
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
}