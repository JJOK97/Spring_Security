package com.naver.myhome.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogAdvice {

		private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
				
		//LogAdvice Ŭ���� ���� �޼��带 aop���� Advice
		public void beforeLog() {
			logger.info("[LogAdvice :  ����޼��� �Դϴ�.]");
		}
}
