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

import assn2.beans.HotelBean;

import assn2.daos.HotelDAO;
import assn2.database.DBConnectionFactory;
import assn2.exceptions.DataAccessException;
import assn2.exceptions.ServiceLocatorException;

/**
 * @author Kaihang
 *
 */
public class HotelDAOImpl implements HotelDAO {

	/**
	 * The service locator to retrieve database connections from
	 */
	private DBConnectionFactory services;
	
	/** Creates a new instance of HotelDAOImpl */
	public HotelDAOImpl() {
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
	public HotelDAOImpl(DBConnectionFactory services) {
		this.services = services;
	}
	
	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotel(int)
	 */
	@Override
	public HotelBean getHotel(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotelByCity(java.lang.String)
	 */
	@Override
	public List<HotelBean> getHotelByCity(String city)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotelByName(java.lang.String)
	 */
	@Override
	public List<HotelBean> getHotelByName(String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotelByOwner(int)
	 */
	@Override
	public List<HotelBean> getHotelByOwner(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotelByManager(int)
	 */
	@Override
	public HotelBean getHotelByManager(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
