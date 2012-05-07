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
 
import java.util.List;
import java.util.ArrayList;

/**
 * @author Kaihang
 *
 */
public class CmdListBooking implements Command {

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
		
		/**try to get bookingbean of a user, if not null, get records of the booking**/
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
		else{
			BookingBean bb = new BookingBean();
			System.out.println(bb);
			session.setAttribute("booking", null);
		}
		
		return "/home.jsp";
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
		if(bookingDao.getBookingByUser(id)!=null)
			booking = (BookingBean) bookingDao.getBookingByUser(id);
		System.out.println(booking);
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
