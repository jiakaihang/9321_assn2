<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.lang.String" %> <!-- solve all problem -->
<%@page import="java.sql.*"%>
<%@ page import="assn2.beans.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search result</title>
</head>
<body>
	<form action="addBooking" method="post">
		<p>Rate: 1 (AUD ${priceofone})</p>
		<p>Only ${numberofroomleft} room left</p>
	   	<table>
	   		<tr>
	   			<td>Name</td>
	   			<td>${thathotel.name}</td>
	   		</tr>
	   		<tr>
	   			<td>City</td>
	   			<td>${thathotel.city}</td>
	   		</tr>
	   		<tr>
	   			<td>Phone number</td>
	   			<td>${thathotel.phoneno }</td>
	   		</tr>
	   		<tr>
	   			<td>Address</td>
	   			<td>${thathotel.address }</td>
	   		</tr>
	   	</table>
		<input type="submit" value="Add to booking">
		<input type="hidden" name="roomtypeid" value="${roomtypeid}">
	</form>
</body>
</html>