/**
 * 
 */
package assn2.daosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import assn2.beans.RoomCalendarBean;
import assn2.exceptions.DataAccessException;
import assn2.daos.RoomCalDAO;
import assn2.database.DBConnectionFactory;
import assn2.exceptions.ServiceLocatorException;

/**
 * @author Kaihang
 *
 */
public class RoomCalDAOImpl implements RoomCalDAO {

	/**
	 * The service locator to retrieve database connections from
	 */
	private DBConnectionFactory services;
	
	/** Creates a new instance of RoomCalDAOImpl */
	public RoomCalDAOImpl() {
		try {
			services = new DBConnectionFactory();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Constructor with argument
	 * @param services
	 */
	public RoomCalDAOImpl(DBConnectionFactory services) {
		this.services = services;
	}
	
	/* (non-Javadoc)
	 * @see assn2.daos.RoomCalDAO#insert(assn2.beans.RoomCalendarBean)
	 */
	@Override
	public void insert(RoomCalendarBean roomcal) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomCalDAO#delete(int)
	 */
	@Override
	public void delete(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomCalDAO#getRoomCalendar(int)
	 */
	@Override
	public RoomCalendarBean getRoomCalendar(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomCalDAO#getRoomCalByRoomType(int)
	 */
	@Override
	public List<RoomCalendarBean> getRoomCalByRoomType(int id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
