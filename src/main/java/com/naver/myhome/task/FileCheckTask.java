package com.naver.myhome.task;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.naver.myhome.service.BoardService;

@Service
public class FileCheckTask {

	private static final Logger logger = LoggerFactory.getLogger(FileCheckTask.class);

	@Value("#{savefolder['savefoldername']}")
	private String saveFolder;

	@Autowired
	private BoardService boardService;

	public FileCheckTask(BoardService boardService) {
		this.boardService = boardService;
	}

	// 스케줄러를 이용해서 주기적으로 메서드를 실행하기 위해 설정합니다.
	// 1000 : 밀리세컨드 단위입니다. (1초)

//	@Scheduled(fixedDelay = 1000)
//	public void test() throws Exception {
//		logger.info("test");
//	}

	@Scheduled(cron = "0 17 * * * *")
	public void checkFiles() throws Exception {

		logger.info("checkFiles");

		List<String> deleteFileList = boardService.getDeleteFileList();

		// for(String filename : deletefileList) {
		for (int i = 0; i < deleteFileList.size(); i++) {
			String filename = deleteFileList.get(i);
			File file = new File(saveFolder + filename);
			if (file.exists()) {
				if (file.delete()) {
					logger.info(file.getPath() + " 삭제 되었습니다.");
					boardService.deleteFileList(filename);
				}
			} else {
				logger.info(file.getPath() + " 파일이 존재하지 않습니다.");
			}
		}
	}
}
