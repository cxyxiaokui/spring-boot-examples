package cn.lijunkui.task.own;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class SchedulerTask {

	private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//固定的时间执行 也就是 5秒执行一次
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTimeFixedRate() {
    	try {
			Thread.sleep(6*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        log.info("reportCurrentTimeFixedRate The time is now {}", dateFormat.format(new Date()));
    }
    
    @Scheduled(initialDelay=0, fixedDelay=5000)
    public void reportCurrentTimeInitialDelay() {
    	try {
			Thread.sleep(6*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        log.info("reportCurrentTimeInitialDelay fixedRate The time is now {}", dateFormat.format(new Date()));
    }
}
