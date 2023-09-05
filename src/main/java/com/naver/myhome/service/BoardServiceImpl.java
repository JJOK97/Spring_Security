package com.naver.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.myhome.aop.LogAdvice;
import com.naver.myhome.dao.BoardDAO;
import com.naver.myhome.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {
   
   private BoardDAO dao;
   
   @Autowired
   public BoardServiceImpl(BoardDAO dao, LogAdvice log) {
	   this.dao = dao;
   }

   @Override
   public int getListCount() {
      return dao.getListCount();
   }

   @Override
   public List<Board> getBoardList(int page, int limit) {
//	  double i = 1/0;
	  
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      
      int startrow = (page - 1) * limit + 1;
      int endrow = startrow + limit - 1;
      map.put("start", startrow);
      map.put("end", endrow);
      return dao.getBoardList(map);
   }

   @Override
   public Board getDetail(int num) {
	  
      return dao.getDetail(num);
   }
   
   @Override
   public int boardReplyUpdate(Board board) {
	   
	   return dao.boardReplyUpdate(board);
   }
   
   @Override
   @Transactional
   public int boardReply(Board board) {
	   
//	  double i = 1/0;
	   
	  boardReplyUpdate(board);
	  
	  board.setBOARD_RE_LEV(board.getBOARD_RE_LEV()+1);
	  board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ()+1);
      return dao.boardReply(board);
   }
   
   @Override
   public int boardModify(Board modifyboard) {
      return dao.boardModify(modifyboard);
   }

   @Override
   public int boardDelete(int num) {
	   
	   int result = 0;
	   Board board = dao.getDetail(num);
	   if(board != null) {
		   result = dao.boardDelete(board);
	   }
      return result;
   }

   @Override
   public int setReadCountUpdate(int num) {
	   
      return dao.setReadCountUpdate(num);
   }

   @Override
   public boolean isBoardWriter(int num, String pass) {
	  
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("num", num);
      map.put("pass", pass);
      Board result = dao.isBoardWriter(map);
      
      if(result == null)
         return false;
      else
         return true;
   }
   
   @Override
   public void insertBoard(Board board) {
	   
      dao.insertBoard(board);
   }

   @Override
   public List<String> getDeleteFileList() {
	   
	  return dao.getDeleteFileList();
   }

   @Override
   public void deleteFileList(String filename) {
	   
	   dao.DeleteFileList(filename);
   }
   
}