package com.naver.myhome.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome.domain.Member;

@Repository
public class MemberDAO {

	private SqlSessionTemplate sqlSession;

	@Autowired
	public MemberDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Member isId(String id) {
		return sqlSession.selectOne("Members.idcheck", id);
	}

	public int insert(Member m) {
		return sqlSession.insert("Members.insert", m);
	}

	public int updateProcess(Member m) {
		return sqlSession.insert("Members.update", m);
	}

	public int getSearchListcount(Map<String, String> map) {
		return sqlSession.selectOne("Members.searchCount", map);
	}
	
	public List<Member> getSearchList(Map<String, Object> map) {
		return sqlSession.selectList("Members.list", map);
	}

	public void delete(String id) {
		sqlSession.delete("Members.delete", id);
	}

}