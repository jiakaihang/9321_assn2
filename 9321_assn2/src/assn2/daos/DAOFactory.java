/**
 * 
 */
package assn2.daos;

import java.util.HashMap;
import java.util.Map;
import assn2.daosImpl.*;
import assn2.exceptions.ServiceLocatorException;


/**
 * @author Kaihang
 *
 */
public class DAOFactory {

	private static final String USER_DAO 			= "userDAO";
	private static final String HOTEL_DAO 			= "hotelDAO";
	private static final String ROOM_DAO 			= "roomDAO";
	private static final String ROOM_TYPE_DAO 		= "roomTypeDAO";
	private static final String ROOM_CALENDAR_DAO 	= "roomCalDAO";
	private static final String RECORD_DAO 			= "recordDAO";
	private static final String BOOKING_DAO 		= "bookingDAO";
	
	private Map daos;
	
	private static DAOFactory instance = new DAOFactory();
	
	/** Creates a new instance of DAOFactory 
	 * @throws ServiceLocatorException */
	private DAOFactory() {
		daos = new HashMap();
		daos.put(USER_DAO, new UserDAOImpl());
		daos.put(HOTEL_DAO, new HotelDAOImpl());
		daos.put(ROOM_DAO, new RoomDAOImpl());
		daos.put(ROOM_TYPE_DAO, new RoomTypeDAOImpl());
		daos.put(ROOM_CALENDAR_DAO, new RoomCalDAOImpl());
		daos.put(RECORD_DAO, new RecordDAOImpl());
		daos.put(BOOKING_DAO, new BookingDAOImpl());
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	/**
	 * @return the userDao
	 */
	public UserDAO getUserDao() {
		return (UserDAO) daos.get(USER_DAO);
	}

	/**
	 * @return the hotelDao
	 */
	public HotelDAO getHotelDao() {
		return (HotelDAO) daos.get(HOTEL_DAO);
	}

	/**
	 * @return the roomDao
	 */
	public RoomDAO getRoomDao() {
		return (RoomDAO) daos.get(ROOM_DAO);
	}

	/**
	 * @return the roomTypeDao
	 */
	public RoomTypeDAO getRoomTypeDao() {
		return (RoomTypeDAO) daos.get(ROOM_TYPE_DAO);
	}

	/**
	 * @return the roomCalendarDao
	 */
	public RoomCalDAO getRoomCalendarDao() {
		return (RoomCalDAO) daos.get(ROOM_CALENDAR_DAO);
	}

	/**
	 * @return the recordDao
	 */
	public RecordDAO getRecordDao() {
		return (RecordDAO) daos.get(RECORD_DAO);
	}

	/**
	 * @return the bookingDao
	 */
	public BookingDAO getBookingDao() {
		return (BookingDAO) daos.get(BOOKING_DAO);
	}
	
	
}
