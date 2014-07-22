package com.yeepay.hessian;

	public class HelloImpl implements HelloInterf { 
		private String helloStr="" ; 
		public String getHelloStr() { 
		return helloStr; 
		} 
		public String setHelloStr(String helloStr) { 
		this.helloStr = helloStr; 
		System.out.println("服务器：接收到："+helloStr);
		helloStr=helloStr+"已经处理,  这是服务器返回";
		return helloStr; 
		} 
		public String seeHello() {			
		return helloStr; 
		} 
		} 
