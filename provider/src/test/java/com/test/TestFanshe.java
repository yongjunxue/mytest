package com.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestFanshe extends Test1<User>{
	public static void test1(){
		List<TestInner> li=new ArrayList<TestInner>();
		Class clazz=li.getClass();
		System.out.println(clazz.getClassLoader());
		Type  ts=clazz.getGenericSuperclass();
		Type[] params = ((ParameterizedType) ts).getActualTypeArguments();
		System.out.println(params[0]);
		System.out.println();
		for(Type t : params){
			System.out.println(t);
		}
	}
	public static void test2(){
		TestFanshe li=new TestFanshe();
		Class clazz=li.getClass();
		System.out.println(clazz.getClassLoader());
		Type  ts=clazz.getGenericSuperclass();
		Type[] params = ((ParameterizedType) ts).getActualTypeArguments();
		for(Type t : params){
			System.out.println(t);
		}
	}
	
	public static void main(String[] args) {
//		test1();
		test2();
		
//		List<TestFanshe> lst = new ArrayList<TestFanshe>(){};
//		Type genType = lst.getClass().getClass().getGenericSuperclass();
//		 
//		Class templatClazz = null;
//		 
//		if(ParameterizedType.class.isInstance(genType))
//		{
//		  ParameterizedType parameterizedType = (ParameterizedType) genType;
//		 templatClazz = (Class) parameterizedType.getActualTypeArguments()[0];
//		}
//		System.out.println(templatClazz);
	}
}
class Test1<T extends Human>{
	
}
class Human{
	
}
class User extends Human{
	
}



