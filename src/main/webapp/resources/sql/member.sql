drop table member cascade constraints purge;
--1. index.jsp���� �����մϴ�.
--2. ������ ���� admin, ��� 1234�� ����ϴ�.
--3. ����� ������ 3�� ����ϴ�.

create table member3(
   id         VARCHAR2(12),
   password      VARCHAR2(60),
   name         VARCHAR2(15),   --�ѱ� 5���ڱ��� ����
   age         NUMBER(2),
   gender         VARCHAR2(3),
   email         VARCHAR2(30),
   auth		VARCHAR2(50) NOT NULL, -- ȸ���� role(����)�� �����ϴ� ������ �⺻���� 'ROLE_MEMBER' �Դϴ�.
   PRIMARY KEY(id)
);