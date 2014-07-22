package com.yeepay.ftp;

public class FtpState {
	 final static int FS_WAIT_LOGIN = 0;    //等待输入用户名状态
	    final static int FS_WAIT_PASS = 1;    //等待输入密码状态
	    final static int FS_LOGIN = 2;        //已经登陆状态	    
	    final static int FTYPE_ASCII = 0;
	    final static int FTYPE_IMAGE  = 1;
	    final static int FMODE_STREAM = 0;
	    final static int FMODE_COMPRESSED = 1;
	    final static int FSTRU_FILE = 0;
	    final static int FSTRU_PAGE = 1;
}
