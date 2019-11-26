package cn.lijunkui.task.quartz.simple;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SimpleJobConfig {
	@Bean
	public JobDetail simpleJobDetail() {
		
		return JobBuilder.newJob(SimpleJob.class).withIdentity("myJob").storeDurably()
				 	.usingJobData("serviceCode","delete overdue orders")
	                .build();
	}
	@Bean
	public Trigger simpleJobTrigger() {
		//定义每三秒执行一次
		SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever();
		//定义触发器
		return TriggerBuilder.newTrigger().forJob(simpleJobDetail()).withIdentity("myJobTrigger").withSchedule(simpleScheduleBuilder).build();
	}
}
