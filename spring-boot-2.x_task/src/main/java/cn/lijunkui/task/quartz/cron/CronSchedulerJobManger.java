package cn.lijunkui.task.quartz.cron;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class CronSchedulerJobManger {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    
    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleJob(scheduler);
       // scheduleJob2(scheduler);   
    }  
    
    private void scheduleJob(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(CronJob.class) .withIdentity("job1", "group1")
        		.usingJobData("serviceCode","Live lesson reminder1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);   
    }  
      
    private void scheduleJob2(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(CronJob2.class) .withIdentity("job2", "group2")
        		.usingJobData("serviceCode","Live lesson reminder2").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);  
    } 
}
