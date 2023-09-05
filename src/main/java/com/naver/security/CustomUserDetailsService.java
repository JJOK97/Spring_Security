package com.naver.security;

import java.util.ArrayList;
import java.util.Collection;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naver.myhome.domain.Member;

/*
 1. UserDetailsService �������̽��� DB���� ���� ������ �ҷ����� loadUserByUsername()�� �����մϴ�.
     �̸� �����ϴ� Ŭ������ DB���� ������ ������ �����ͼ� UserDetails Ÿ������ ������ �ִ� �۾��� �մϴ�.
     
 2. UserDetails�� �� �����̽��� Security���� ������� ������ ��� ���� ���̽��Դϴ�.
 
 3. UserDetails �������̽��� ������ Ŭ������ ������ ������� ������ ����ڰ� ���� ������ ������ ó���ؼ� ��ȯ�ϰ� �˴ϴ�.
       ��) UserDetails user = new User(username,users.getPassword(),roles);
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("username�� �α��ν� �Է��� �� : " + username);
		Member users = sqlSession.selectOne("Members.idcheck", username);

		if (users == null) {
			logger.info("username" + username + "not found");
			throw new UsernameNotFoundException("username " + username + " not found");
		}
		
		// GrantedAuthority : ���� ��ü�� �ο��� ������ ��Ÿ���� ���� �������̽��� �̸� ������ ����ü�� �����ڿ� ������ ���ڿ���
		// SimpleGrantedAuthority : GrantedAuthority�� ����ü �Դϴ�.
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

		roles.add(new SimpleGrantedAuthority(users.getAuth()));

		UserDetails user = new User(username, users.getPassword(), roles);
		return user;
	}
	

	// 1. ������ �޼��带 �߰��մϴ�.

	// 2 ������ �޼��带 �������̵� �� �� �Ű����� username�� �ش��ϴ� �ο츦 ������ ���̽��� ���� ���� �� Member�� �����մϴ�.
	
	
}