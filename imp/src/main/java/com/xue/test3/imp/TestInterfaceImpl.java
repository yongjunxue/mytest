package com.xue.test3.imp;

import org.springframework.stereotype.Service;

import com.xue.test3.service.TestInterface;


@Service
public class TestInterfaceImpl implements TestInterface{
	public TestInterfaceImpl(){
		System.out.println("TestInterfaceImpl初始化");
	}
	@Override
	public String getResult() {
		return "result";
	}

}
