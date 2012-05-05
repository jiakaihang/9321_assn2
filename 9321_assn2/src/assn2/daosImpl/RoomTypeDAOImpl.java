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

import assn2.beans.RoomTypeBean;

import assn2.daos.RoomTypeDAO;
import assn2.database.DBConnectionFactory;
import assn2.exceptions.DataAccessException;
import assn2.exceptions.ServiceLocatorException;


/**
 * @author Kaihang
 *
 */
public class RoomTypeDAOImpl implements RoomTypeDAO {

	/**
	 * The service locator to retrieve database connections from
	 */
	private DBConnectionFactory services;
	
	/** Creates a new instance of RoomTypeDAOImpl */
	public RoomTypeDAOImpl() {
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
	public RoomTypeDAOImpl(DBConnectionFactory services) {
		this.services = services;
	}
	
	/* (non-Javadoc)
	 * @see assn2.daos.RoomTypeDAO#getRoomType(int)
	 */
	@Override
	public RoomTypeBean getRoomType(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomTypeDAO#getAllByHotel(int)
	 */
	@Override
	public List<RoomTypeBean> getAllByHotel(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomTypeDAO#getAllByType(java.lang.String)
	 */
	@Override
	public List<RoomTypeBean> getAllByType(String type)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
