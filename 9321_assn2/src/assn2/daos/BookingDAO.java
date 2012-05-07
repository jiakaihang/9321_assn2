/**
 * 
 */
package assn2.daos;

import java.util.List;

import assn2.beans.BookingBean;
import assn2.exceptions.DataAccessException;

/**
 * The DAO for Booking entries 
 * One user can no more than 1 booking entry
 * A booking entry may have multiple records
 * @author Kaihang
 *
 */
public interface BookingDAO {
	
	/**
	 * Inserts a new booking entry in database
	 * 
	 * @param bookingBean
	 * @throws DataAccessException
	 */
	void insert(BookingBean bookingBean) throws DataAccessException;
	
	
	/**
	 * Delete the booking entry of a user in the database
	 * 
	 * **********NOTICE*****************************************
	 * Delete records of the booking first before delete booking
	 * **********NOTICE*****************************************
	 * 
	 * @param bookingid
	 * @throws DataAccessException
	 */
	void delete(int id) throws DataAccessException;
	
	/**
	 * Get the booking entry of a user from the database by userid
	 * 
	 * @param userid
	 * @return BookingBean
	 * @throws DataAccessException
	 */
	BookingBean getBookingByUser(int id) throws DataAccessException;

}
