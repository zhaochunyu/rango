package com.yeepay.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletContext;

public class ProcessSocketData extends Thread {
	private Socket socket;
	private ServletContext servletContext;
	public ProcessSocketData() {
	super();
	}
	public ProcessSocketData(Socket socket, ServletContext servletContext) {
	this.socket = socket;
	this.servletContext = servletContext;
	}
	public void run() {
		  try {
			   //将输入的字节流转化为高层流
				if (socket != null){					
					//接收Client发来的请求消息
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					// 从流中读取消息内容
					String request = br.readLine(); 
					request=	request.substring(3);
					// 对请求做处理后，生成响应response
					PrintWriter pw = new PrintWriter(socket.getOutputStream());
					pw.println("我是服务器 你好 : " + request);
					pw.flush(); // 刷新缓冲区
			
				}
				} catch (IOException e) {
			   e.printStackTrace();
			  }
	}
	}