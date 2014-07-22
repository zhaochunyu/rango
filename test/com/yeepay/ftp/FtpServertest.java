package com.yeepay.ftp;

public class FtpServertest {
	public static void main(String[] args) {
		  String initDir; 
		if (args.length != 0) {
			initDir = args[0];
		} else {
			initDir = "/home/chunchun/ftptest";
		}
		FtpServer ftpServer = new FtpServer();

	}
}
