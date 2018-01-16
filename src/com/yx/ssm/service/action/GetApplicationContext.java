package com.yx.ssm.service.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetApplicationContext {
	private ApplicationContext applicationContext;
	public GetApplicationContext() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-service.xml");
	}
	
	public Object excute(Class<?> clazz) {
		String interfaceName = (new StringBuilder()).append(Character.toLowerCase(
				clazz.getSimpleName().charAt(0))).append(clazz.getSimpleName().substring(1)).toString();
		Object obj = applicationContext.getBean(interfaceName);
		return obj;
	}
}
