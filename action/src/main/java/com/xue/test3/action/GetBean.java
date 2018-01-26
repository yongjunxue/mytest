package com.xue.test3.action;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xue.test3.service.TestInterface;

public class GetBean {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/spring/spring-mvc.xml"});
		TestInterface o = (TestInterface)context.getBean("testInterface");
		System.out.println("get:"+o.getResult());
	}
}
