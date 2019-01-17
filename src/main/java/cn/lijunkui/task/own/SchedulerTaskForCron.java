package cn.lijunkui.task.own;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class SchedulerTaskForCron {
	private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	/**
	 * 每天的14:00 执行一次
	 */
	@Scheduled(cron="0 0 14 * * ?")
	private void cron() {
		 log.info("cron The time is now {}", dateFormat.format(new Date()));
	}
	
	/**
	 * 每5秒执行一次
	 */
	@Scheduled(cron="0/5 * * * * ?")
	private void cron2() {
		try {
			Thread.sleep(6*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("cron2 The time is now {}", dateFormat.format(new Date()));
	}
}
