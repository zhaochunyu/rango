package com.yeepay.ftp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class FTPSocketServer implements ServletContextListener {
	private FtpServer FtpServer;
	public void contextDestroyed(ServletContextEvent e) {
	if (FtpServer != null && FtpServer.isInterrupted()) {
		FtpServer.interrupt();
	}
	}
	public void contextInitialized(ServletContextEvent e) {
	ServletContext servletContext = e.getServletContext();
	  String str=(String)servletContext.getAttribute("socketThreadIsRun");
	   if(null==str)
	   {
	if ( FtpServer == null) {
		FtpServer.start(); // servlet上下文初始化时启动socket服务端线程
	System.out.println("socketThread");
	}
	}
	}
	}