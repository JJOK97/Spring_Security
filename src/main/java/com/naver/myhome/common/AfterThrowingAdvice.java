package com.naver.myhome.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* 
 * JoinPoint 인터페이스의 메서드
  1. Signature getSignature() : 호출되는 메서드에 대한 정보를 구합니다.
  2. Object getTarget() : 호출한 비즈니스 메서드를 포함하는 비즈니스 객체를 구합니다.
  3. getClass().getName(): 클래스 이름을 구합니다.
  4. proceeding.getSignature().getName() : 호출되는 메서드 이름을 구합니다.
 */

/*
 * Advice : 횡단 관심에 해당하는 공통 기능을 의미하며 독립된 클래스의 메서드로 작성됩니다.
 * AfterReturningAdvice : 타겟 메소드가 성공적으로 결과값을 반환 후에 어드바이스 기능을 수행합니다.
 * 
 * BoardServiceImpl.java에서 getBoardList()안에
 * double i = 1/0;
 *       추가합니다.
 */
public class AfterThrowingAdvice {

   private static final Logger logger = LoggerFactory.getLogger(AfterThrowingAdvice.class);

   public void afterThrowingLog(Throwable exp) {

      logger.info("=========================================================================");
      logger.info("[AfterThrowing] : 비지니스 로직 수행 중 오류가 발생하면 동작입니다.");
      logger.info("ex : " + exp.toString());
      logger.info("=========================================================================");
   }
}