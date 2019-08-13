package cn.lijunkui.task.quartz.cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.lijunkui.task.quartz.cron.service.LiveReminderService;

public class CronJob2 implements Job{
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
    	System.out.println("CronJob2"+serviceCode);
    	liveReminderService.sendmessage();
    } 
}
