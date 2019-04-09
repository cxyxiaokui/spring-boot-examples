package cn.lijunkui.task.quartz.simple.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
	public void delete() {
		try {
			Thread.sleep(6*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("delete data sucess....");
	}
}
