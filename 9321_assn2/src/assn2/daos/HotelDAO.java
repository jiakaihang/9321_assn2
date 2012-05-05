/**
 * 
 */
package assn2.daos;

import java.util.List;
import assn2.beans.HotelBean;
import assn2.exceptions.DataAccessException;

/**
 * The DAO of hotel
 * Mostly used to find the hotel(s) by conditions.
 * 
 * @author Kaihang
 *
 */
public interface HotelDAO {
	
	/**
	 * get the hotelbean by hotel id
	 * 
	 * @param hotelid
	 * @return HotelBean
	 * @throws DataAccessException
	 */
	HotelBean getHotel(int id) throws DataAccessException;
	
	/**
	 * get the hotel(s) by the city name
	 * 
	 * @param city
	 * @return List<HotelBean>
	 * @throws DataAccessException
	 */
	List<HotelBean> getHotelByCity(String city) throws DataAccessException;
	
	/**
	 * get the hotel(s) by the hotel name
	 * 
	 * @param name
	 * @return List<HotelBean>
	 * @throws DataAccessException
	 */
	List<HotelBean> getHotelByName(String name) throws DataAccessException;
	
	/**
	 * get the hotel(s) by the hotel owner id
	 * 
	 * @param ownerid
	 * @return List<HotelBean>
	 * @throws DataAccessException
	 */
	List<HotelBean> getHotelByOwner(int id) throws DataAccessException;
	
	/**
	 * get the hotel by the manager id
	 * 
	 * @param managerid
	 * @return HotelBean
	 * @throws DataAccessException
	 */
	HotelBean getHotelByManager(int id) throws DataAccessException;

}
