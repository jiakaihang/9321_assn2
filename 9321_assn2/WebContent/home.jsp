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
		$( "#conditionfrom" ).datepicker();
		$( "#conditionto" ).datepicker();
	});
	</script>
	<STYLE TYPE="text/css">
	  TD {
		  font-family: Arial;
		  font-size: 10pt;
	  }
	  TH {
		  font-weight: bold;
		  font-size: 12pt
	  }
	  #mytable {
		  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
		  width:80%;
		  border-collapse:collapse;
	  }
	  #mytable td, #customers th {
		  font-size:1em;
		  border:1px solid #98bf21;
		  padding:3px 7px 2px 7px;
	  }
	  #mytable th {
		  font-size:1.1em;
		  text-align:left;
		  padding-top:5px;
		  padding-bottom:4px;
		  background-color:#A7C942;
		  color:#ffffff;
	  }
	  #mytable tr.alt td {
		  color:#000000;
		  background-color:#EAF2D3;
	  }
		div.container {
			width:90%;
			margin-left:10px;
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
			margin-left:180px;
			border-left:1px solid gray;
			padding:1em;
		}
	</STYLE>
	</head>
	<body>
    <div class="container">

<%UserBean usr = (UserBean) request.getSession().getAttribute("user");
		String name = usr.getFname()+" "+usr.getLname();
		out.println("<h2>Hello, "+name+"</h2>");%>
<center>
      <!--Interface for Customer-->
      <%if(usr.getSecurity_level().equalsIgnoreCase("user")){%>
      <div class="header">
      	<h1 class="header">Welcome to Hotel Customer Interface</h1>
      </div>
      
          <div class="left">
<form action="dispatcher?operation=search"  method="post">
      <select name="city">
    <option value="Sydney">Sydney</option>
    <option value="Brisbane">Brisbane</option>
    <option value="Melbourne">Melbourne</option>
    <option value="Adelaide">Adelaide</option>
    <option value="Hobart">Hobart</option>
  </select><p/>
      <select name="roomtype">
    <option value="single">Single Room</option>
    <option value="twin">Twin Bed</option>
    <option value="quene">Queen</option>
    <option value="executive">Executive</option>
    <option value="suite">Suite</option>
  </select>
      <div class="demo">
    <p>Check-in Date:
          <input name="checkindate" type="text" id="datepickerfrom" style="width:100px;">
        </p>
    <p>Check-out Date:
          <input name="checkoutdate" type="text" id="datepickerto" style="width:100px;">
        </p>
  </div>
      <input type="submit" value="Search">
    </form>
    </div>
      
      <form action="dispatcher?operation=delBooking"  method="post">
      <div class="content">
    <%	BookingBean emptyBean = new BookingBean();
			BookingBean	b = (BookingBean) request.getSession().getAttribute("booking");
			
			if(!b.equals(emptyBean)){%>
    <table width="456" id="mytable">
      <tr>
        <th bgcolor="#CC9966" align="left">Booking ID</th>
        <th bgcolor="#CC9966" align="left">User ID</th>
        <th bgcolor="#CC9966" align="left">Booking Date</th>
        <th bgcolor="#CC9966" align="left">Total Price</th>
      </tr>
      <tr>
        <td>${booking.bookingid}</td>
        <td>${booking.userid}</td>
        <td>${booking.bookingdate}</td>
        <td>${booking.totalprice}</td>
      </tr>

        </table>
    <br/>
    <br/>
    <table width="456" id="mytable">
          <%int j = 1;%>
          <c:forEach var="record" items="${listBooking}">
        <c:choose>
              <c:when test="${record.extrabed=='1'}">
            <c:set var="extrabed" value="Yes"/>
          </c:when>
              <c:when test="${record.extrabed=='0'}">
            <c:set var="extrabed" value="No"/>
          </c:when>
            </c:choose>
        <tr>
              <th width="88" bgcolor="#CC9966">Record <%=j%></th>
              <td width="108">ID</td>
              <td width="238">${record.recordid}</td>
            </tr>
        <tr>
              <td></td>
              <td>Hotel ID</td>
              <td>${record.hotelid}</td>
            </tr>
        <tr>
              <td></td>
              <td>Room Type ID</td>
              <td>${record.roomtypeid}</td>
            </tr>
        <tr>
              <td></td>
              <td>Extrabed</td>
              <td>${extrabed}</td>
            </tr>
        <tr>
              <td></td>
              <td>Checkin Date</td>
              <td>${record.checkindate}</td>
            </tr>
        <tr>
              <td></td>
              <td>Checkout Date</td>
              <td>${record.checkoutdate}</td>
            </tr>
        <%j++;%>
      </c:forEach>
        </table>
    <%}else{out.println("<h2>You have no booking currently, Start searching by fill in following details!</h2>");}%>
    </div>
  </form>
      
      <!--Interface for Manager!-->
      <%} else if(usr.getSecurity_level().equalsIgnoreCase("manager")) {%>
      <form action="dispatcher?operation=assignRoom" method="post">
    <div class="header">
    <h1 class="header">Welcome to Hotel
          <c:out value="${hotel.name}"/>
          's Manager Interface </h1>
    </div>
    
    <br/>
    <br/>

    <div class="content">
    <table width="744" align="left" id="mytable">
          <c:set var="count" value="${0}"/>
          <tr>
        <th align="left">Room Type Status: </th>
      </tr>
          <tr bgcolor="#66FFFF">
        <th width="203" >Type</th>
        <th width="127">Price</th>
        <th width="234">Discount Rate</th>
        <th width="160">Room Left</th>
      </tr>
          <c:forEach var="roomtype" items="${roomtypelist}">
        <tr>
              <td align="left">${roomtype.type}</td>
              <td>${roomtype.price}</td>
              <td>${roomtype.discountrate}</td>
              <td>${roomLeftList[count]}</td>
            </tr>
        <c:set var="count" value="${count+1}"/>
      </c:forEach>
        </table>
    <p>&nbsp;
        </p>
        <table width="744" align="left" id="mytable">
          <tr>
            <th align="left">Room Status:</th>
          </tr>
          <tr bgcolor="#66FFFF">
            <th width="125" height="45">Room ID</th>
            <th width="182">Room Type ID</th>
            <th width="137">Type</th>
            <th width="129">Room No.</th>
            <th width="147">Condition</th>
          </tr>
          <c:forEach var="roomtype" items="${roomtypelist}">
            <c:forEach var="room" items="${rooms}">
              <c:choose>
                <c:when test="${roomtype.roomtypeid == room.roomtypeid}">
                  <tr>
                    <td>${room.roomid }</td>
                    <td>${roomtype.roomtypeid }</td>
                    <td>${roomtype.type}</td>
                    <td>${room.roomno}</td>
                    <td>${room.condition}</td>
                  </tr>
                </c:when>
              </c:choose>
            </c:forEach>
          </c:forEach>
        </table>
    <br/>
    <br/>
    <p>&nbsp;
        </p>
        <table width="784" align="left" id="mytable">
          <tr>
            <th>Booking Status:</th>
          </tr>
          <tr bgcolor="#66FFFF">
            <th width="118">Booking ID</th>
            <th width="139">Room Type ID</th>
            <th width="86">Extra Bed</th>
            <th width="89">Price</th>
            <th width="157">Check-In Date</th>
            <th width="158">Check-Out Date</th>
          </tr>
          <c:forEach var="record" items="${bookinglist}">
            <c:choose>
              <c:when test="${record.extrabed=='1'}">
                <c:set var="extrabed" value="Yes"/>
              </c:when>
              <c:when test="${record.extrabed=='0'}">
                <c:set var="extrabed" value="No"/>
              </c:when>
            </c:choose>
            <tr>
              <td>${record.bookingid}</td>
              <td>${record.roomtypeid}</td>
              <td>${extrabed}</td>
              <td>${record.price}</td>
              <td>${record.checkindate}</td>
              <td>${record.checkoutdate}</td>
            </tr>
          </c:forEach>
        </table>
    <p>&nbsp;
        </p>
        <table align="left" id="mytable">
          <tr align="left">
            <th>Checkin with Booking ID:</th>
            <td><input name="bookingid" type="text"></td>
          </tr>
          <tr align="left">
            <th>Assigned Room(s):</th>
            <td><input name="assignedrooms" type="text"></td>
          </tr>
          <tr align="left">
            <td></td>
            <td><input type="submit" value="Checkin" style="width:100px;height:30px;
					font-family:sans-serif; font-weight:bold;font-style:italic;"></td>
          </tr>
        </table>
        </div>
  </form>
      
      <!--Interface for Owner!-->
      <%} else {%>
      <div class="header">
      <h2 class="header">Welcome to Hotel Owner Interface</h2>
      </div>

       <div class="content">
      <form action="dispatcher?operation=setCondition" method="post">
    <table width="906" align="left" id="mytable">
          <c:set var="c" value="${0}"/>
          <tr>
        <th>Occupancy Status</th>
      </tr>
          <tr bgcolor="#CC6600">
        <th width="141">Hotel ID</th>
        <th width="100">Name</th>
        <th width="151">Available No.</th>
        <th width="177">Maintainence No.</th>
        <th width="145">Occupied No.</th>
      </tr>
          <c:forEach var="hotel" items="${hotellist}">
        <tr>
              <td>${hotel.hotlid}</td>
              <td>${hotel.name}</td>
              <td>${availlist[c]}</td>
              <td>${maintainlist[c]}</td>
              <td>${occupiedlist[c]}</td>
            </tr>
        <c:set var="c" value="${c+1}"/>
      </c:forEach>
          <tr>
        <td>ID:
              <select name="setCondition_hotelid">
            <c:forEach var="h" items="${hotellist}">
                  <option value="${h.hotlid}">${h.hotlid}</option>
                </c:forEach>
          </select></td>
        <td></td>
        <td></td>
        <td>Condition
              <select name="setCondition_cond">
            <option value="available" selected="selected">available</option>
            <option value="maintainence">maintainence</option>
            <option value="repair">repair</option>
          </select></td>
        <td align="center"><input type="submit" value="Confirm!"></td>
      </tr>
        </table>
  </form>
      <p>&nbsp;
      <p>
 	 <form action="dispatcher?operation=setDiscount" method="post">
    <table align="left" id="mytable">
      <tr>
        <th>Discount Status</th>
      </tr>
      <tr bgcolor="#CC6600">
        <th width="102">Hotel ID</th>
        <th width="65">Name</th>
        <th width="130">Type</th>
        <th width="120">Price</th>
        <th width="160">Discount Rate</th>
        <th width="203">From</th>
        <th width="204">To</th>
      </tr>
      <c:set var="c" value="${0}"/>
      <c:forEach var="hh" items="${hotellist}">
        <c:forEach var="x" begin="1" end="5" step="1">
          <tr>
            <td>${hh.hotlid}</td>
            <td>${hh.name}</td>
            <td>${roomtypelist[c].type}</td>
            <td>${roomtypelist[c].price}</td>
            <td>${roomtypelist[c].discountrate}</td>
            <td>${roomtypelist[c].discountfrom}</td>
            <td>${roomtypelist[c].discountto}</td>
          </tr>
          <c:set var="c" value="${c+1}"/>
        </c:forEach>
      </c:forEach>
      <tr>
        <td>ID:
          <select name="setDiscount_hotelid">
            <c:forEach var="h" items="${hotellist}">
              <option value="${h.hotlid}">${h.hotlid}</option>
            </c:forEach>
          </select></td>
        <td></td>
        <td>Type:
          <select name="setDiscount_type">
            <option value="single" selected="selected">single</option>
            <option value="twin">twin</option>
            <option value="queen">queen</option>
            <option value="executive">executive</option>
            <option value="suite">suite</option>
          </select></td>
        <td></td>
        <td>Rate:
          <select name="setDiscount_rate">
            <option value="100" selected="selected">100%</option>
            <option value="90">90%</option>
            <option value="80">80%</option>
            <option value="70">70%</option>
            <option value="60">60%</option>
            <option value="50">50%</option>
            <option value="40">40%</option>
            <option value="30">30%</option>
            <option value="20">20%</option>
            <option value="10">10%</option>
          </select></td>
        <td><div class="demo"> From:
            <input name="setDiscount_from" type="text" id="conditionfrom" style="width:100px;">
          </div></td>
        <td><div class="demo"> To:
            <input name="setDiscount_to" type="text" id="conditionto" style="width:100px;">
          </div></td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td align="right"><input type="submit" value="Confirm!"></td>
      </tr>
    </table>
  </form>
  </div>
      <%} %>
    </center>
    </div>
</body>
</html>
