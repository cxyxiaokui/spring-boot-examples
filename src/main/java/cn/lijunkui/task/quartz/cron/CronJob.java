package cn.lijunkui.task.quartz.cron;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.lijunkui.task.own.SchedulerTask;
import cn.lijunkui.task.quartz.cron.service.LiveReminderService;

public class CronJob implements Job{
	private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	private LiveReminderService  liveReminderService;//直播课提醒
	private String serviceCode;//业务code Live lesson reminder

	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
    @Override  
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	log.info("quartz cron The time is now {}", dateFormat.format(new Date()));
    	System.out.println("CronJob"+serviceCode);
    	liveReminderService.sendmessage();
    } 
}
