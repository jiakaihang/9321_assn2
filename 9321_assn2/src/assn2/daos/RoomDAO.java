/**
 * 
 */
package assn2.daos;

import java.sql.Timestamp;
import java.util.List;
import assn2.beans.RoomBean;
import assn2.exceptions.DataAccessException;

/**
 * The DAO of Room
 * @author Kaihang
 *
 */
public interface RoomDAO {
	
	/**
	 * get the room by room id
	 * @param roomid
	 * @return RoomBean
	 * @throws DataAccessException
	 */
	RoomBean getRoom(int id) throws DataAccessException;
	
	/**
	 * get all rooms of the hotel
	 * @param id
	 * @return List<RoomBean>
	 * @throws DataAccessException
	 */
	List<RoomBean> getRoomByHotel(int id) throws DataAccessException;
	
	/**
	 * set the condition of the room by room id
	 * @param roomid
	 * @param cond
	 * @param start
	 * @param end
	 * @throws DataAccessException
	 */
	void setCondition(int id, String cond) throws DataAccessException;
	
	/**
	 * set the condition to the hotels rooms
	 * @param hotelid
	 * @param cond
	 * @param start
	 * @param end
	 * @throws DataAccessException
	 */
	void setConditionByHotel(int id, String cond) throws DataAccessException;
}
