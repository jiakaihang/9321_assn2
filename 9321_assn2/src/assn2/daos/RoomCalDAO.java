/**
 * 
 */
package assn2.daos;

import java.util.List;
import assn2.beans.RoomCalendarBean;
import assn2.exceptions.DataAccessException;

/**
 * The DAO of roomcalendar
 * record the roomtypes that is been occupied or booked
 * @author Kaihang
 *
 */
public interface RoomCalDAO {

	/**
	 * Insert a room calendar entry
	 * @param roomcal
	 * @throws DataAccessException
	 */
	void insert(RoomCalendarBean roomcal) throws DataAccessException;
	
	/**
	 * Delete a room calendar entry
	 * @param id
	 * @throws DataAccessException
	 */
	void delete(int id) throws DataAccessException;
	
	/**
	 * Get room calendar entry by id
	 * @param roomcalendarid
	 * @return RoomCalendarBean
	 * @throws DataAccessException
	 */
	RoomCalendarBean getRoomCalendar(int id) throws DataAccessException;
	
	/**
	 * Get room calendar(s) by roomtype id
	 * @param roomtypeid
	 * @throws DataAccessException
	 */
	List<RoomCalendarBean> getRoomCalByRoomType(int id) throws DataAccessException;
}
