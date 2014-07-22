package com.yeepay.ftp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class FtpConsole  extends Thread   
{   
    BufferedReader cin; //   
    String conCmd;      //命令   
    String conParam;    //参数   
///   
    public FtpConsole()   
    {   
        System.out.println("ftp 服务器启动!");   
        cin = new BufferedReader(new InputStreamReader(System.in));   
    }   
///   
    public void run()   
    {   
        boolean ok = false;   
        String input = "";   
        while(!ok)   
        {   
            System.out.print("->");   
            try   
            {   
                input = cin.readLine();    
            }   
            catch(Exception e)   
            {   
                e.printStackTrace();   
            }   
            switch(parseInput(input))       //命令号   
            {   
                case 1:   
                    consoleQUIT();          //退出   
                    break;   
                case 8:   
                    ok = consoleLISTUSER(); //列出所有注册用户及其工作目录   
                    break;   
                case 0:   
                    ok = consoleLIST();     //列出活动,及其IP   
                    break;   
                case 2:   
                    ok = consoleADDUSER();  //增加一个注册用户   
                    break;   
                case 3:   
                    ok = consoleDELUSER();  //删除一个注册用户   
                    break;   
                 case 7:   
                    ok = consoleHELP();     //显示帮助信息   
                    break;   
                 case -1:   
                    ok = consoleERR();      //错误命令   
                    break;   
            }   
        }   
    }   
   
    //退出   
    int consoleQUIT()                          
    {   
        System.exit(0);   
        return 0;   
    }   
       
    //列出所有注册用户及其工作目录   
    boolean consoleLISTUSER()                  
    {   
        System.out.println("用户名   \t\t 工作目录");   
        for(int i = 0 ; i<FtpServer.usersInfo.size();i++)   
        {   
            System.out.println(((UserInfo)FtpServer.usersInfo.get(i)).user+" \t\t\t "+((UserInfo)FtpServer.usersInfo.get(i)).workDir);   
        }   
        return false;   
    }   
   
    //列出活动用户,及其IP   
    boolean consoleLIST()                      
    {   
        int i = 0;   
        for(i=0;i<FtpServer.users.size();i++)   
        {   
            System.out.println((i+1)+":"+((FtpHandler)(FtpServer.users.get(i))).user    
            + " 来自于 "    
            +((FtpHandler)(FtpServer.users.get(i))).ctrlSocket.getInetAddress().toString());   
        }   
   
        return false;   
    }   
       
    //判断是否已经注册了   
    boolean validateUserName(String s)         
    {   
        for(int i = 0 ; i<FtpServer.usersInfo.size();i++)   
        {   
            if(((UserInfo)FtpServer.usersInfo.get(i)).user.equals(s))   
                return false;      
        }   
        return true;   
    }   
   
    //增加一个注册用户   
    boolean consoleADDUSER()                   
    {   
        System.out.print("请键入用户 :");   
        try   
        {   
            cin = new BufferedReader(new InputStreamReader(System.in));   
            UserInfo tempUserInfo = new UserInfo();   
            String line = cin.readLine();   
            if(line != "")   
            {   
                if(!validateUserName(line))//已存在这个用户   
                {   
                    System.out.println("用户名 "+line+" 已注册!");   
                    return false;   
                }   
            }   
            else   
            {   
                System.out.println("用户名不能为空 !");   
                return false;   
            }   
               
            tempUserInfo.user = line;   
            System.out.print("请键入密码 :");   
            line = cin.readLine();   
            if(line != "")   
                tempUserInfo.password = line;   
            else   
            {   
                System.out.println("密码不能为空 !");   
                return false;   
            }   
            System.out.print("输入用户主目录 : ");   
            line = cin.readLine();   
            if(line != "")   
            {   
                File f = new File(line);   
                if(!f.exists())   
                    f.mkdirs();   
                tempUserInfo.workDir = line;   
            }   
            else   
            {   
                System.out.println("主目录不能为空 !");   
                return false;   
            }   
            FtpServer.usersInfo.add(tempUserInfo);   
            saveUserInfo();   
        }   
        catch(Exception e)   
        {   
            e.printStackTrace();   
            return false;   
        }   
        return false;   
    }   
   
    //   
    void saveUserInfo()                    
    {   
        String s = "";   
        try   
        {   
            BufferedWriter fout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("user.cfg")));   
            for(int i = 0; i < FtpServer.usersInfo.size();i++)   
            {   
                s = ((UserInfo)FtpServer.usersInfo.get(i)).user+"|"+((UserInfo)FtpServer.usersInfo.get(i)).password+"|"+((UserInfo)FtpServer.usersInfo.get(i)).workDir+"|";   
                fout.write(s);   
                fout.newLine();   
            }   
            fout.close();   
        }   
        catch(Exception e)   
        {   
            e.printStackTrace();   
        }   
    }     
   
    //删除一个注册用户   
    boolean consoleDELUSER()                   
    {   
        String s = "";   
        if(conParam.equals(""))   
        {   
            System.out.println("用法:deluser 用户名");   
            return false;   
        }   
        for(int i=0;i<FtpServer.usersInfo.size();i++)   
        {   
            s = ((UserInfo)FtpServer.usersInfo.get(i)).user;   
            if(s.equals(conParam))   
            {   
                System.out.println("用户注册信息 "+conParam+" 已删除");   
                FtpServer.usersInfo.remove(i);   
                saveUserInfo();   
                return false;   
            }   
        }   
        System.out.println("用户 "+conParam+" 不存在");                     
        return false;   
   
    }   
   
    ///   
    boolean consoleHELP()   
    {   
        if(conParam.equals(""))   
        {   
            System.out.println("adduser :增加一个注册用户");   
            System.out.println("deluser <username> :删除一个注册用户");   
            System.out.println("quit  :退出");   
            System.out.println("list  :列出活动用户,及其IP");   
            System.out.println("listuser : 列出所有注册用户及其工作目录");   
            System.out.println("help :显示 帮助信息");   
        }   
        else if(conParam.equals("adduser"))   
            System.out.println("adduser :增加一个注册用户");   
        else if(conParam.equals("deluser"))   
            System.out.println("deluser <username> :删除一个注册用户");   
        else if(conParam.equals("quit"))   
            System.out.println("quit  :退出");   
        else if(conParam.equals("list"))   
            System.out.println("list  :列出活动用户,及其IP");   
        else if(conParam.equals("listuser"))   
            System.out.println("listuser : 列出所有注册用户及其工作目录");   
        else if(conParam.equals("help"))   
            System.out.println("help :显示 帮助信息");   
        else   
            return false;   
        return false;   
           
    }    
    boolean consoleERR()   
    {   
        System.out.println("错误命令!");   
        return false;   
    }    
    int parseInput(String s)   
    {   
        String upperCmd;   
        int p = 0;   
        conCmd = "";   
        conParam = "";   
        p = s.indexOf(" ");              
        if(p == -1)   
            conCmd = s;   
        else    
            conCmd = s.substring(0,p);//返回子串,0 ~ p-1 */   
           
        if(p >= s.length() || p ==-1)   
            conParam = "";   
        else   
            conParam = s.substring(p+1,s.length());   
               
        upperCmd = conCmd.toUpperCase();//小写->大写   
           
        if(upperCmd.equals("LIST"))   
            return 0;   
        else if(upperCmd.equals("QUIT")||upperCmd.equals("EXIT"))   
            return 1;   
        else if(upperCmd.equals("ADDUSER"))   
            return 2;   
        else if(upperCmd.equals("DELUSER"))   
            return 3;   
        else if(upperCmd.equals("EDITUSER"))   
            return 4;   
        else if(upperCmd.equals("ADDDIR"))   
            return 5;   
        else if(upperCmd.equals("REMOVEDIR"))   
            return 6;   
        else if(upperCmd.equals("HELP") ||upperCmd.equals("?"))   
            return 7;   
        else if(upperCmd.equals("LISTUSER"))   
            return 8;                          
        return -1;   
    }   

}
