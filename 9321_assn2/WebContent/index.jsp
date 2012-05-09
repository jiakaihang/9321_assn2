<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to eBooking.com</title>
<style type="text/css">
div.container {
	width:650px;
	margin-top:100px;
	margin-left:100px;
	border:1px solid gray;
	line-height:150%;
}
div.header, div.footer {
	padding:0.5em;
	color:white;
	background-color:gray;
	clear:left;
}
h1.header {
	padding:0;
	margin:0;
	font:italic bold Georgia, serif;
}
div.left {
	float:left;
	width:160px;
	margin:0;
	padding:1em;
}
div.content {
	margin-left:240px;
	border-left:1px solid gray;
	padding:1em;
}
</style>
</head>
<body">

<div class="container">
  <div class="header">
    <h1 class="header">Welcome to e-Hotel System</h1>
  </div>
  <div class="left">
    <p>
    <h2>Please login to start your Hotel Booking</h2>
    </p>
  </div>
  <p>
  <div class="content">
    <form action="dispatcher?operation=login" method="post">
      <table>
        <tr>
          <td>Username:</td>
          <td><input name="username" type="text" style="width:150px;"></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td><input name="password" type="password" style="width:150px;"></td>
        </tr>
        <tr>
          <td>Login As:</td>
          <td><select name="loginType">
              <option value="user" selected="selected">User</option>
              <option value="manager">Manager</option>
              <option value="owner">Owner</option>
            </select></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input type="submit" value="Login"style="width:100px;height:30px;font-family:sans-serif;
			font-weight:bold;font-style:italic;"></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="footer">Copyright 2012-2012 by M&H</div>
</div>
</body></html>