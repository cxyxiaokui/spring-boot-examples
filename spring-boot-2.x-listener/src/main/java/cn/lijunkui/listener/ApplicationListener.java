package cn.lijunkui.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ApplicationListener implements ServletContextListener{
	private Logger log = LoggerFactory.getLogger(ApplicationListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("ApplicationListener 监听器启动...");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("ApplicationListener 监听器销毁...");
	}
}
