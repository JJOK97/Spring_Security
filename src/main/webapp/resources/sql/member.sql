drop table member cascade constraints purge;
--1. index.jsp에서 시작합니다.
--2. 관리자 계정 admin, 비번 1234를 만듭니다.
--3. 사용자 계정을 3개 만듭니다.

create table member3(
   id         VARCHAR2(12),
   password      VARCHAR2(60),
   name         VARCHAR2(15),   --한글 5글자까지 가능
   age         NUMBER(2),
   gender         VARCHAR2(3),
   email         VARCHAR2(30),
   auth		VARCHAR2(50) NOT NULL, -- 회원의 role(권한)을 저장하는 곳으로 기본값은 'ROLE_MEMBER' 입니다.
   PRIMARY KEY(id)
);