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

	// �����ٷ��� �̿��ؼ� �ֱ������� �޼��带 �����ϱ� ���� �����մϴ�.
	// 1000 : �и������� �����Դϴ�. (1��)

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
					logger.info(file.getPath() + " ���� �Ǿ����ϴ�.");
					boardService.deleteFileList(filename);
				}
			} else {
				logger.info(file.getPath() + " ������ �������� �ʽ��ϴ�.");
			}
		}
	}
}
