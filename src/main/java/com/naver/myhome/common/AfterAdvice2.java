package com.naver.myhome.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
 JoinPoint �������̽��� �޼���
 	1. Signature getSignature() : ȣ��Ǵ� �޼��忡 ���� ������ ���մϴ�.
 	2. Object getTarget() : ȣ���� ����Ͻ� �޼��带 �����ϴ� ����Ͻ� ��ü�� ���մϴ�.
 	3. getClass().getName() : Ŭ���� �̸��� ���մϴ�.
 	4. poreceeding.getSignature().getName() : ȣ��Ǵ� �޼��� �̸��� ���մϴ�.
 */

// �������� ó���� ������ AfterAdvice Ŭ������ afterLog()�޼���� �����մϴ�.
// Advice : Ⱦ�� ���ɿ� �ش��ϴ� ���� ����� �ǹ��ϸ� ������ Ŭ������ �޼���� �ۼ��˴ϴ�.
// ��� ��ü�� �޼ҵ带 �����ϴ� ���߿� �ͼ����� �߻��ߴ����� ���ο� ������� �޼��� ���� �� ���� ����� �����մϴ�.

//@Service
//@Aspect
public class AfterAdvice2 {
	private static final Logger logger = LoggerFactory.getLogger(AfterAdvice2.class);
	
	@After("execution(* com.naver.myhome..*Impl.get*(..))")
	public void afterLog(JoinPoint proceeding) {
		/*
		 ��� ����
		[BeforeAdvice] : ����Ͻ� ���� ���� �� �����Դϴ�.
		[BeforeAdvice] : com.naver.myhome.service.BoardServcieImpl�� getBoardList() ȣ���մϴ�.
		 */
		
		logger.info("=========================================================================");
		logger.info("[AfterAdvice2] : ����Ͻ� ���� ���� �� �����Դϴ�.");
		logger.info("[AfterAdvice2] :  "
				+ proceeding.getTarget().getClass().getName()
						+ "�� " + proceeding.getSignature().getName() + "() ȣ�� �� �Դϴ�.");
		logger.info("=========================================================================");
	}

}
