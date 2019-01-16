package cn.lijunkui.task.quartz.simple;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.lijunkui.task.own.SchedulerTask;
import cn.lijunkui.task.quartz.simple.service.OrderService;

public class SimpleJob  extends QuartzJobBean{
	private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	private OrderService  orderService;//订单service
	private String serviceCode;//业务code
	

	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println(serviceCode);
		 log.info("quartz simple The time is now {}", dateFormat.format(new Date()));
		orderService.delete();
	}

}
