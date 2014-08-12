<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 
  <body>
  	<form id="htmlmoco" name="htmlmoco" action="/rangodata" method="post">
	  	<table bgcolor="lightblue" align="center" width="550" >
	  		<tr><td></td></tr>
	  		<tr><td>
	  			<table border="0">
					<tr><td colspan="3" align="center">html</td></tr>
					<tr><td align="right">端口</td><td><input type="text" id="port" name="port" onblur="checkUser()" style="width:150; height:20"/> </td><td></td></tr>
					<tr><td align="right">URL</td><td><input type="text" id="URL" name="URL"  onblur="checkPassword()" style="width:150; height:20"/> </td><td></td></tr>
					<tr><td align="right">返回</td><td><input type="text" name="return" style="width:150; height:20"/></td><td></td></tr>
					<tr><td colspan="3"><div align="center"><input type="submit" name="submit" onclick="doCheckSubmit()"/>提交</div></td></tr>
	  			</table>
	  		</td></tr>
	  	</table>
	  </form>	
  </body>
</html>