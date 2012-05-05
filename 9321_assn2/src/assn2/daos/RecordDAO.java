/**
 * 
 */
package assn2.daos;

import java.util.List;
import assn2.beans.RecordBean;
import assn2.exceptions.DataAccessException;

/**
 * The DAO of record.
 * multiple records may belong to same booking
 * @author Kaihang
 *
 */
public interface RecordDAO {
	
	/**
	 * Insert a single record entry to database
	 * 
	 * *************NOTICE*******************
	 * insert roomcalendar when insert record
	 * *************NOTICE*******************
	 * 
	 * @param recordBean
	 * @throws DataAccessException
	 */
	void insert(RecordBean record) throws DataAccessException;
	
	/**
	 * Insert a List of RecordBean entries to database
	 * 
	 * @param records
	 * @throws DataAccessException
	 */
	void insert(List<RecordBean> records) throws DataAccessException;
	
	/**
	 * Delete record entry by record id
	 * 
	 * @param recordid
	 * @throws DataAccessException
	 */
	void delete(int id) throws DataAccessException;
	
	/**
	 * Delete records of a booking by booking id
	 * 
	 * @param bookingid
	 * @throws DataAccessException
	 */
	void deleteAllByBooking(int id) throws DataAccessException;
	
	/**
	 * Get the record by record id
	 * 
	 * @param recordid
	 * @return RecordBean
	 * @throws DataAccessException
	 */
	RecordBean getRecord(int id) throws DataAccessException;
	
	/**
	 * Get a list of RecordBeans of a booking by booking id
	 * 
	 * @param bookingid
	 * @return List<RecordBean>
	 * @throws DataAccessException
	 */
	List<RecordBean> getAllByBooking(int id) throws DataAccessException;
	
	/**
	 * Get a list of RecordBeans of a User by User id
	 * 
	 * @param userid
	 * @return List<RecordBean>
	 * @throws DataAccessException
	 */
	List<RecordBean> getAllByHotel(int id) throws DataAccessException;

}
