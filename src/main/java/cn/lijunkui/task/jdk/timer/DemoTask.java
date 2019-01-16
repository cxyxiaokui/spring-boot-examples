package cn.lijunkui.task.jdk.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lijunkui.task.own.SchedulerTask;

public class DemoTask  extends TimerTask{
	private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Override
	public void run() {
		try {
			Thread.sleep(4*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 log.info("DemoTask The time is now {}", dateFormat.format(new Date()));
	}
}
