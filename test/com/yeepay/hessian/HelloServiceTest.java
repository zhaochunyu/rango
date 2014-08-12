package com.yeepay.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

public class HelloServiceTest { 
public static void main(String[] args) throws Exception { 
String url = "http://localhost:8080/rangoMVC/hessian/HelloInterf"; 
HessianProxyFactory factory = new HessianProxyFactory(); 
HelloInterf hello = (HelloInterf) factory.create(HelloInterf.class, url); 
System.out.println("123：客户端请求服务器 ");
System.out.println(hello.setHelloStr("123：客户端请求服务器 "));
} 
}