<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to eBooking.com</title>
</head>
<body>
<center>
<h1>Welcome to Hotel Management System</h1>
<h2>Please login to start your Hotel Booking</h2>
<p>
<form action="dispatcher?operation=login" method="post"> 
<table>
	<tr><td>Username:</td><td><input name="username" type="text"></td></tr>
	<tr><td>Password:</td><td><input name="password" type="password"></td></tr>
	<tr><td>Login As:</td>
		<td><select name="loginType">
			<option value="user" selected="selected">User</option>
			<option value="manager">Manager</option>
			<option value="owner">Owner</option>
		</select></td>
	</tr><p>
	<tr><td colspan="2" align="center"><input type="submit" value="Login"
			style="width:100px;height:30px;font-family:sans-serif;
			font-weight:bold;font-style:italic;"></TD></TR>
</table>
</form>
</center>
</body>
</html>