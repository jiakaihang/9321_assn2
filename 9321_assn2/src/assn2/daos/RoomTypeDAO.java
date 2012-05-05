/**
 * 
 */
package assn2.daos;

import java.sql.Timestamp;
import java.util.List;
import assn2.beans.RoomTypeBean;
import assn2.exceptions.DataAccessException;

/**
 * The DAO for RoomType
 * @author Kaihang
 *
 */
public interface RoomTypeDAO {

	/**
	 * Get Room Type by roomtype id
	 * @param roomtypeid
	 * @return RoomTypeBean
	 * @throws DataAccessException
	 */
	RoomTypeBean getRoomType(int id) throws DataAccessException;
	
	/**
	 * find all room types by hotel id
	 * @param hotelid
	 * @return List<RoomTypeBean>
	 * @throws DataAccessException
	 */
	List<RoomTypeBean> getAllByHotel(int id) throws DataAccessException;
	
	/**
	 * find all room type entries by type name
	 * @param type
	 * @return List<RoomTypeBean>
	 * @throws DataAccessException
	 */
	List<RoomTypeBean> getAllByType(String type) throws DataAccessException;
	
}
