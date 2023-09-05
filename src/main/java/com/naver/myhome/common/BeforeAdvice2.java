package com.naver.myhome.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
 ȣ��Ǵ� ����Ͻ� �޼����� ������ JoinPoint �������̽��� �� �� �ֽ��ϴ�.

 JoinPoint �������̽��� �޼���
 	1. Signature getSignature() : ȣ��Ǵ� �޼��忡 ���� ������ ���մϴ�.
 	2. Object getTarget() : ȣ���� ����Ͻ� �޼��带 �����ϴ� ����Ͻ� ��ü�� ���մϴ�.
 	3. getClass().getName() : Ŭ���� �̸��� ���մϴ�.
 	4. poreceeding.getSignature().getName() : ȣ��Ǵ� �޼��� �̸��� ���մϴ�.
 */

// �������� ó���� ������ BeforeAdvice Ŭ������ beforLog()�޼���� �����մϴ�.
// Advice : Ⱦ�� ���ɿ� �ش��ϴ� ���� ����� �ǹ��ϸ� ������ Ŭ������ �޼���� �ۼ��˴ϴ�.
// Advice Ŭ������ ������ ���� ���Ͽ��� <bean>���� ����ϰų� @Service annotation�� ����մϴ�.

//@Service
//@Aspect // @Aspect�� ������ Ŭ�������� Pointcut�� Advice�� �����ϴ� ������ �־�� �մϴ�.
public class BeforeAdvice2 {
	private static final Logger logger = LoggerFactory.getLogger(BeforeAdvice2.class);
	
	@Before("execution(* com.naver.myhome..*Impl.get*(..))")
	public void beforeLog(JoinPoint proceeding) {
		
		logger.info("=========================================================================");
		logger.info("[BeforeAdvice2] : ����Ͻ� ���� ���� �� �����Դϴ�.");
		logger.info("[BeforeAdvice2] :  "
				+ proceeding.getTarget().getClass().getName()
						+ "�� " + proceeding.getSignature().getName() + "() ȣ���մϴ�.");
		logger.info("=========================================================================");
	}

}
