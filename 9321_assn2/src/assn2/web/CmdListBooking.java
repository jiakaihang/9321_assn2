/**
 * 
 */
package assn2.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assn2.beans.*;
import assn2.daosImpl.*;
import assn2.exceptions.DataAccessException;
 
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Kaihang
 *
 */
public class CmdListBooking implements Command {

	final static long ONE_DAY = 1*24*60*60*1000; //1 day in milliseconds

	/* (non-Javadoc)
	 * @see assn2.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		int id = user.getUserid();
		BookingBean booking = null;
		
		if(user.getSecurity_level().equalsIgnoreCase("user")){
			/**try to get bookingbean of a customer, if not null, get records of the booking**/
			try{
				booking = getBooking(id);
			}catch(DataAccessException e){
				e.printStackTrace();
			}
			if(booking!=null){
				try{
					List<RecordBean> list = getRecord(booking.getBookingid());
					//listBooking = List<RecordBean>
					session.setAttribute("listBooking", list);
					session.setAttribute("booking", booking);
					System.out.println("booking in the session is: "+booking);
				}catch(DataAccessException e){
					e.printStackTrace();
				}
			}
		}
		else if(user.getSecurity_level().equalsIgnoreCase("manager")){
			/**try to get the hotel the manager is managing and a list of roomtype status**/
			HotelBean hotel = null;
			List<RoomTypeBean> roomTypeList = new ArrayList();
			List<Integer> roomLeftList = new ArrayList();
			SearchDAOImpl sDao = new SearchDAOImpl();
			List<RoomBean> rooms = new ArrayList();
			List<RecordBean> recordList = new ArrayList();
			try{
				hotel = getHotel(id); //get hotel by manager id
				rooms = getRooms(hotel.getHotlid());
				roomTypeList = getRoomTypeList(hotel.getHotlid());

				Timestamp now = new Timestamp(System.currentTimeMillis());
				Timestamp tomorrow = new Timestamp(System.currentTimeMillis()+ONE_DAY);
				for(RoomTypeBean rt:roomTypeList){
					int occupied = sDao.overlapnumber(hotel.getCity(), rt.getType(), now, tomorrow);
					roomLeftList.add(rt.getAmount()- occupied);
				}
				recordList = new RecordDAOImpl().getAllByHotel(hotel.getHotlid());
			}catch(DataAccessException e){
				e.printStackTrace();
			}
			session.setAttribute("hotel", hotel);
			session.setAttribute("rooms", rooms);
			session.setAttribute("bookinglist", recordList);
			session.setAttribute("roomtypelist", roomTypeList);
			session.setAttribute("roomLeftList", roomLeftList);
		}
		else{	//login as owner
			List<Integer> availList = new ArrayList();
			List<Integer> maintainList = new ArrayList();
			List<Integer> occupyList = new ArrayList();
			List<HotelBean> hotelList = new ArrayList();
			HotelDAOImpl hDao = new HotelDAOImpl();
			hotelList = hDao.getHotelByOwner(user.getUserid());
			
			//get all the roomtypes for all hotels of the owner
			List<RoomTypeBean> rtList = new ArrayList();
			for(HotelBean h: hotelList){
				rtList.addAll(getRoomTypeList(h.getHotlid()));
				List<RoomBean> rList = new ArrayList();
				rList = getRooms(h.getHotlid());
				int avail=0, maintain=0, occupied=0;
				for(RoomBean r: rList){
					if(r.getCondition().equalsIgnoreCase("available")) avail++;
					else if(r.getCondition().equalsIgnoreCase("maintainence")) maintain++;
					else occupied++;
				}
				availList.add(avail);
				maintainList.add(maintain);
				occupyList.add(occupied);
			}
			session.setAttribute("hotellist", hotelList);
			session.setAttribute("roomtypelist", rtList);
			session.setAttribute("availlist", availList);
			session.setAttribute("maintainlist", maintainList);
			session.setAttribute("occupiedlist", occupyList);
		}
		
		return "/home.jsp";
	}
	
	/**
	 * @param hotelid
	 * @return
	 */
	private List<RoomBean> getRooms(int id) {
		List<RoomBean> r = null;
		RoomDAOImpl rDao = new RoomDAOImpl();
		r = rDao.getRoomByHotel(id);
		return r;
	}

	/**
	 * @param hotlid
	 * @return
	 */
	private List<RoomTypeBean> getRoomTypeList(int id) throws DataAccessException{
		List<RoomTypeBean> rtlist = null;
		RoomTypeDAOImpl rtDao = new RoomTypeDAOImpl();
		rtlist = (List<RoomTypeBean>) rtDao.getAllByHotel(id);
		return rtlist;
	}

	/**
	 * get hotel by manager id
	 * @param managerid
	 * @return hotelBean
	 */
	private HotelBean getHotel(int id) throws DataAccessException{
		HotelBean h = null;
		HotelDAOImpl hDao = new HotelDAOImpl();
		h = (HotelBean) hDao.getHotelByManager(id);
		return h;
	}

	/**
	 * get booking bean by userid 
	 * @param id
	 * @return BookingBean
	 * @throws DataAccessException
	 */
	public BookingBean getBooking(int id) throws DataAccessException{
		BookingBean booking = null;
		BookingDAOImpl bookingDao = new BookingDAOImpl();
		booking = (BookingBean) bookingDao.getBookingByUser(id);
		return booking;
	}
	
	/**
	 * get the records of a booking id 
	 * @param id
	 * @return List<RecordBean>
	 * @throws DataAccessException
	 */
	public List<RecordBean> getRecord(int id) throws  DataAccessException{
		List<RecordBean> list = new ArrayList();
		RecordDAOImpl recordDao = new RecordDAOImpl();
		list = recordDao.getAllByBooking(id);
		
		for(RecordBean rb:list)
			System.out.println(rb);
		
		return list;
	}
	
}
