package com.yeepay.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SockerServertest {
	public void createSocketClient(String host, int port,String name) throws Exception {
		
		String requestMsg = "我叫："+name;
		System.out.println(requestMsg);
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			//利用Server的IP和端口创建socket对象
			socket = new Socket(host, port);
			
			//创建writer对象，并向Server发送请求
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8")));
			pw.println(requestMsg);
			pw.flush();
			
			//接收Server相应信息，并做进一步处理
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("OK--> "+ br.readLine());
			
		} 
		catch (UnknownHostException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("The Server : "+host+" is Unknown !");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

		public static void main(String[] args) throws Exception {
			SockerServertest client = new SockerServertest();
			String name="赵椿玉";
		client.createSocketClient("127.0.0.1", 4800,name);
		}
}
