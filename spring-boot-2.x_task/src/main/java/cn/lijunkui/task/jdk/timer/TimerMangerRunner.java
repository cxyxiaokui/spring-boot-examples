package cn.lijunkui.task.jdk.timer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.lijunkui.task.jdk.scheduledExecutorService.ScheduledExecutorTask;
import cn.lijunkui.task.jdk.scheduledExecutorService.ScheduledExecutorTest;
/**
 * TimerManger 的启动类
 * @author lijunkui
 */
//@Component
@Order(value = 1)
public class TimerMangerRunner implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//TimerManger manger = new TimerManger(new DemoTask());
		//manger.run();
		ScheduledExecutorTest test = new ScheduledExecutorTest(new ScheduledExecutorTask());
		test.run();
	}
}
