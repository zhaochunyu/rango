package com.yeepay.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

public class HelloServiceTest { 
public static void main(String[] args) throws Exception { 
String url = "http://localhost:8080/rango/hello"; 
HessianProxyFactory factory = new HessianProxyFactory(); 
HelloInterf hello = (HelloInterf) factory.create(HelloInterf.class, url); 
System.out.println("椿椿：客户端请求服务器 ");
System.out.println(hello.setHelloStr("椿椿：客户端请求服务器 "));
} 
}