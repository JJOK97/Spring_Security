package com.naver.myhome.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome.domain.Board;

@Repository
public class BoardDAO {

	private SqlSessionTemplate sqlSession;

	@Autowired
	public BoardDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int getListCount() {
		return sqlSession.selectOne("Boards.count");
	}

	public List<Board> getBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("Boards.list", map);
	}

	public void insertBoard(Board board) {
		sqlSession.insert("Boards.insert", board);
	}

	public int setReadCountUpdate(int num) {
		return sqlSession.update("Boards.readCountUpdate", num);
	}

	public Board getDetail(int num) {
		return sqlSession.selectOne("Boards.detail", num);
	}

	public Board isBoardWriter(Map<String, Object> map) {
		return sqlSession.selectOne("Boards.boardwriter", map);
	}

	public int boardModify(Board modifyboard) {
		return sqlSession.update("Boards.modify", modifyboard);
	}

	public int boardReplyUpdate(Board board) {
		return sqlSession.update("Boards.reply_update", board);
	}
	
	public int boardReply(Board board) {
		return sqlSession.insert("Boards.reply_insert", board);
	}

	public int boardDelete(Board board) {
		return sqlSession.delete("Boards.delete", board);
	}

	public List<String> getDeleteFileList() {
		return sqlSession.selectList("Boards.getDeleteFileList");
	}

	public void DeleteFileList(String filename) {
		sqlSession.delete("Boards.deleteFileList", filename);
	}
	
}