<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="javax.*"%>
<%@ page import="assn2.beans.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<jsp:useBean id="user" class="assn2.beans.UserBean" scope="session" />
<jsp:useBean id="booking" class="assn2.beans.BookingBean" scope="session" />
<jsp:useBean id="listBooking" class="java.util.ArrayList" scope="session" />




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>home</title>
	<link rel="stylesheet" href="css/jquery.ui.all.css">
	<script src="js/jquery-1.7.2.js"></script>
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker.js"></script>
	<link rel="stylesheet" href="css/demos.css">
	<script>
	$(function() {
		$( "#datepickerfrom" ).datepicker();
		$( "#datepickerto" ).datepicker();
	});
	</script>
	<STYLE TYPE="text/css">
	<!--
		TD{font-family: Arial; font-size: 10pt;}
		TH{font-weight: bold; font-size: 12pt}
	--->
	</STYLE>
</head>
<body>
	<%UserBean usr = (UserBean) request.getSession().getAttribute("user");
		String name = usr.getFname()+" "+usr.getLname();
		out.println("<h2>Hello, "+name+"</h2>");%>
	<center>
	<form action="dispatcher?operation=delBooking"  method="post">
	 
	<%	BookingBean emptyBean = new BookingBean();
		BookingBean	b = (BookingBean) request.getSession().getAttribute("booking");
		
		if(!b.equals(emptyBean)){%>
			<table align="left" border="1">
				<tr><th>Booking ID</th><td>${booking.bookingid}</td></tr>
				<tr><th>User ID</th><td>${booking.userid}</td></tr>
				<tr><th>Booking Date</th><td>${booking.bookingdate}</td></tr>
				<tr><th>Total Price</th><td>${booking.totalprice}</td></tr>				
			</table>
			<br/><br/>
			<table align="left" border="1">
				<%int j = 1;%>
				<c:forEach var="record" items="${listBooking}">
					<c:choose>
						<c:when test="${record.extrabed=='1'}"><c:set var="extrabed" value="Yes"/></c:when>
						<c:when test="${record.extrabed=='0'}"><c:set var="extrabed" value="No"/></c:when>
					</c:choose>
					<tr><th>Record <%=j%></th><td>ID</td><td>${record.recordid}</td></tr>
					<tr><td>			</td><td>Hotel ID</td><td>${record.hotelid}</td></tr>
					<tr><td>			</td><td>Room Type ID</td><td>${record.roomtypeid}</td></tr>
					<tr><td>			</td><td>Extrabed</td><td>${extrabed}</td></tr>
					<tr><td>			</td><td>Checkin Date</td><td>${record.checkindate}</td></tr>
					<tr><td>			</td><td>Checkout Date</td><td>${record.checkoutdate}</td></tr>
					<%j++;%>
				</c:forEach>
			</table>
	
		<%}else{out.println("<h2>You have no booking currently, Start searching by fill in following details!</h2>");}%>
	</form>
	
	
	<form action="dispatcher?operation=search"  method="post">
		<select name="city">
			<option value="Sydney">Sydney</option>
			<option value="Brisbane">Brisbane</option>
			<option value="Melbourne">Melbourne</option>
			<option value="Adelaide">Adelaide</option>
			<option value="Hobart">Hobart</option>
		</select>
		<select name="roomtype">
			<option value="single">Single Room</option>
			<option value="twin">Twin Bed</option>
			<option value="quene">Queen</option>
			<option value="executive">Executive</option>
			<option value="suite">Suite</option>
		</select>
		<div class="demo">
			<p>Check-in Date: <input name="checkindate" type="text" id="datepickerfrom"></p>
			<p>Check-out Date: <input name="checkoutdate" type="text" id="datepickerto"></p>
		</div>
		<input type="submit" value="Search">
	</form>
	</center>
</body>
</html>






