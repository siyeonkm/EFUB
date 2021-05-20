package com.springbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.springbook.domain.BoardVO;
import org.springframework.jdbc.core.RowMapper;

// BoardDAOSpring 중 get* 함수를 실행할 때 검색 결과를 자바 객체와 매핑하기 위한 RowMapper 객체이다.
public class BoardRowMapper implements RowMapper<BoardVO> {
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		return board;
	}
}