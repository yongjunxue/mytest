package com.xue.test3;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ZhuCe {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/spring/*.xml"});
        context.start();
        System.in.read(); // 按任意键退出
	}
}
