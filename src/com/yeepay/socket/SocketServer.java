package com.yeepay.socket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class SocketServer implements ServletContextListener {
	private SocketThread socketThread;
	public void contextDestroyed(ServletContextEvent e) {
	if (socketThread != null && socketThread.isInterrupted()) {
	socketThread.closeServerSocket();
	socketThread.interrupt();
	}
	}
	public void contextInitialized(ServletContextEvent e) {
	ServletContext servletContext = e.getServletContext();
	  String str=(String)servletContext.getAttribute("socketThreadIsRun");
	   if(null==str)
	   {
	if ( socketThread == null) {
	socketThread = new SocketThread(null, servletContext);
	socketThread.start(); // servlet上下文初始化时启动socket服务端线程
	System.out.println("socketThread");
	}
	}
	}
	}