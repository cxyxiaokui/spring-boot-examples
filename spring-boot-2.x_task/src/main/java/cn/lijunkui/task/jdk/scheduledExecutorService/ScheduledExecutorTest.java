package cn.lijunkui.task.jdk.scheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

	private ScheduledExecutorTask task;
	public ScheduledExecutorTest(ScheduledExecutorTask task) {
		this.task = task;
	}
	public void run() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(); 
		
		 //当任务时间小于设置时间间隔则以设置的时间间隔为准
		//当任务执行时间大于设置的间隔时间时，真正间隔的时间由任务执行时间为准！
		// service.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);//不延迟立即执行每3执行一次
	    service.scheduleWithFixedDelay(task,0, 3, TimeUnit.SECONDS); //时间间隔是设置的间隔时间+执行任务的时间
	   // service.schedule(task, 3, TimeUnit.SECONDS);//只执行一次时间间隔是设置的间隔时间+执行任务的时间
	}
}
