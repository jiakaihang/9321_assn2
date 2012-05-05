/**
 * 
 */
package assn2.daosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import assn2.beans.RoomBean;
import assn2.exceptions.DataAccessException;
import assn2.daos.RoomDAO;
import assn2.database.DBConnectionFactory;
import assn2.exceptions.ServiceLocatorException;

/**
 * @author Kaihang
 *
 */
public class RoomDAOImpl implements RoomDAO {

	/**
	 * The service locator to retrieve database connections from
	 */
	private DBConnectionFactory services;
	
	/** Creates a new instance of RoomDAOImpl */
	public RoomDAOImpl() {
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
	public RoomDAOImpl(DBConnectionFactory services) {
		this.services = services;
	}
	
	/* (non-Javadoc)
	 * @see assn2.daos.RoomDAO#getRoom(int)
	 */
	@Override
	public RoomBean getRoom(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomDAO#getRoomByHotel(int)
	 */
	@Override
	public List<RoomBean> getRoomByHotel(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomDAO#setCondition(int, java.lang.String, java.sql.Timestamp, java.sql.Timestamp)
	 */
	@Override
	public void setCondition(int id, String cond, Timestamp start, Timestamp end)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomDAO#setConditionByHotel(int, java.lang.String, java.sql.Timestamp, java.sql.Timestamp)
	 */
	@Override
	public void setConditionByHotel(int id, String cond, Timestamp start,
			Timestamp end) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

}
