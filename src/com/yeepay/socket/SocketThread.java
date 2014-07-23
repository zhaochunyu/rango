package com.yeepay.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;

public class SocketThread  extends Thread {
	private ServletContext servletContext;
	private ServerSocket serverSocket;
	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
	this.servletContext = servletContext;
	// 从web.xml中context-param节点获取socket端口
	String port = this.servletContext.getInitParameter("socketPort");
	if (serverSocket == null) {
	try {
	this.serverSocket = new ServerSocket(Integer.parseInt(port));
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	}
	public void run() {
	while (!this.isInterrupted()) { // 线程未中断执行循环
	try {
	Socket socket = serverSocket.accept();
	if (socket != null)
	new ProcessSocketData(socket, this.servletContext).start();
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	}
	public void closeServerSocket() {
	try {
	if (serverSocket != null && !serverSocket.isClosed())
	serverSocket.close();
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	}