package cn.lijunkui.task.quartz.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class CronSchedulerRunner implements CommandLineRunner {
	@Autowired
	public CronSchedulerJobManger manger;
	@Override
	public void run(String... args) throws Exception {
		manger.scheduleJobs();
	}

}
