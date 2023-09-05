package com.naver.myhome.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome.aop.LogAdvice;
import com.naver.myhome.dao.CommentDAO;
import com.naver.myhome.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentDAO dao;
	private LogAdvice log;
	
	@Autowired
	public CommentServiceImpl(CommentDAO dao, LogAdvice log) {
		this.dao = dao;
		this.log = log;
	}

	@Override
	public int getListCount(int board_num) {
		log.beforeLog();
		return dao.getListCount(board_num);
	}

	@Override
	public List<Comment> getCommentList(int board_num, int page) {
		log.beforeLog();
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		int startrow = 1;
		int endrow = page*3;
		
		map.put("board_num", board_num);
		map.put("start", startrow);
		map.put("end", endrow);
	
		return dao.getCommentList(map);
	}

	@Override
	public int commentsInsert(Comment c) {
		return dao.getCommentsInsert(c);
	}

	@Override
	public int commentsDelete(int num) {
		 int result = 0;
		 Comment c = dao.getComment(num);
		 
		 if(c != null) {
			result = dao.commentsDelete(c);
		 }
		 
	     return result;
	}

	@Override
	public int commentsUpdate(Comment co) {
		return dao.getCommentsUpdate(co);
	}
	
	

}
