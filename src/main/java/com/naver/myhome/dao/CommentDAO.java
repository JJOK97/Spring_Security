package com.naver.myhome.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome.domain.Comment;

@Repository
public class CommentDAO {
	
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public CommentDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int getListCount(int board_num) {
		return sqlSession.selectOne("Comments.count", board_num);
	}
	
	public List<Comment> getCommentList(Map<String, Integer> map) {
		return sqlSession.selectList("Comments.list", map);
		
	}

	public int getCommentsInsert(Comment c) {
		return sqlSession.insert("Comments.insert", c);
	}

	public Comment getComment(int num) {
		return sqlSession.selectOne("Comments.select", num);
	}

	public int commentsDelete(Comment c) {
		return sqlSession.delete("Comments.delete", c);
	}
	
	public int getCommentsUpdate(Comment co) {
		return sqlSession.update("Comments.update", co);
	}

}
