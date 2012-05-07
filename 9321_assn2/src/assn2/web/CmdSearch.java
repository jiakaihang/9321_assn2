/**
 * 
 */
package assn2.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assn2.beans.HotelBean;
import assn2.beans.UserBean;
import assn2.daosImpl.HotelDAOImpl;
import assn2.daosImpl.RoomTypeDAOImpl;
import assn2.daosImpl.SearchDAOImpl;
import assn2.daosImpl.UserDAOImpl;
import assn2.exceptions.UserLoginFailedException;

/**
 * @author ASUS
 *
 */
public class CmdSearch implements Command {

	/* (non-Javadoc)
	 * @see assn2.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String city = request.getParameter("city");
			String roomtype = request.getParameter("roomtype");
			String rawin = request.getParameterValues("checkindate")[0];
			String[] ri = rawin.split("/");
			int month = Integer.parseInt(ri[0]);
			int day = Integer.parseInt(ri[1]);
			int year = Integer.parseInt(ri[2]);
			String rawout = request.getParameterValues("checkoutdate")[0];
			String[] ro = rawout.split("/");
			int month2 = Integer.parseInt(ro[0]);
			int day2 = Integer.parseInt(ro[1]);
			int year2 = Integer.parseInt(ro[2]);
			
			if (rawin == null || rawout == null) {
				return "/home.jsp";
			}//
			Date d1 = new Date(year,month,day);
			Date d2 = new Date(year2,month2,day2);
			if(d1.after(d2)){  //indate > outdate
				return "/home.jsp";
			}
			DateFormat  df = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date date = null;
			java.util.Date date2 = null;
			try {
				date = df.parse(rawin);
				date2 = df.parse(rawout);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 
			Timestamp checkindate = new Timestamp(date.getTime());
			Timestamp checkoutdate = new Timestamp(date2.getTime());
			//perform search
			SearchDAOImpl sd = new SearchDAOImpl();
			int numberofclash = sd.overlapnumber(city, roomtype, checkindate, checkoutdate);
			int roomtypeid = 0;
			int hotelid = 0;
			HotelDAOImpl hdao = new HotelDAOImpl();
			List<HotelBean> hbs = hdao.getHotelByCity(city);
			HotelBean thathotel = hbs.get(0);
			hotelid = thathotel.getHotlid(); //TODO:assume 1 city 1 hotel,Hence only pick up one hotel
			RoomTypeDAOImpl rtdao = new RoomTypeDAOImpl();
			roomtypeid = rtdao.getroomtypeid(hotelid, roomtype);
			int amount = rtdao.getamount(roomtypeid); //room type id
			int numberofroomleft = amount - numberofclash;
			//
			double priceofone = rtdao.getprice(roomtypeid);
		
			HttpSession session = request.getSession();
			//show next page
			session.setAttribute("priceofone", priceofone);
			session.setAttribute("numberofroomleft", numberofroomleft);
			session.setAttribute("thathotel", thathotel);
			session.setAttribute("roomtypeid", roomtypeid);
			
			return "/searchresult.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/home.jsp";
		}
	}
}