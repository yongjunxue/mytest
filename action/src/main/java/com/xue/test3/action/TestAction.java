package com.xue.test3.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xue.test3.service.TestInterface;

@Controller
@RequestMapping("/testAction")
public class TestAction {
	@Autowired
	private TestInterface testInterface;
	
	@RequestMapping("/test")
//	@ResponseBody
	public String receiveTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unused")
		String result=testInterface.getResult();
		System.out.println("test");
		return "main";
	}
	
	@RequestMapping("/test1")
//	@ResponseBody
	public String test1(Model model) throws Exception {
		String result=testInterface.getResult();
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/spring/spring-mvc.xml"});
//		TestInterface o = (TestInterface)context.getBean("testInterface");
		model.addAttribute("result",result);  
		return "jsp/mygoods";
	}
	
}
