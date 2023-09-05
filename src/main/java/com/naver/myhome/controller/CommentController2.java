package com.naver.myhome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.naver.myhome.domain.Comment;
import com.naver.myhome.service.CommentService;

@RestController
@RequestMapping(value = "/comment")
public class CommentController2 {

	private CommentService commentService;

	@Autowired
	public CommentController2(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@ResponseBody
	@PostMapping(value="/list")
	public Map<String, Object> CommentList(int board_num, int page) {
		List<Comment> list = commentService.getCommentList(board_num, page);
		int listcount = commentService.getListCount(board_num);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list",list);
		map.put("listcount",listcount);
		return map;
	}
	
	@ResponseBody
	@PostMapping(value="/add")
	public int CommentAdd(Comment co) {
		return commentService.commentsInsert(co);
	}
	
	@ResponseBody
	@PostMapping(value="/update")
	public int CommentUpdate(Comment co) {
		return commentService.commentsUpdate(co);
	}
	
	@ResponseBody
	@PostMapping(value="/delete")
	public int CommentDelete(int num) {
		return commentService.commentsDelete(num);
	}

}
