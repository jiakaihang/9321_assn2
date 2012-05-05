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

import assn2.beans.RecordBean;
import assn2.exceptions.DataAccessException;
import assn2.daos.RecordDAO;
import assn2.database.DBConnectionFactory;
import assn2.exceptions.ServiceLocatorException;

/**
 * @author Kaihang
 *
 */
public class RecordDAOImpl implements RecordDAO {

	/**
	 * The service locator to retrieve database connections from
	 */
	private DBConnectionFactory services;
	
	/** Creates a new instance of RecordDAOImpl */
	public RecordDAOImpl() {
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
	public RecordDAOImpl(DBConnectionFactory services) {
		this.services = services;
	}
	
	/* (non-Javadoc)
	 * @see assn2.daos.RecordDAO#insert(assn2.beans.RecordBean)
	 */
	@Override
	public void insert(RecordBean record) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RecordDAO#insert(java.util.List)
	 */
	@Override
	public void insert(List<RecordBean> records) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RecordDAO#delete(int)
	 */
	@Override
	public void delete(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RecordDAO#deleteAllByBooking(int)
	 */
	@Override
	public void deleteAllByBooking(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RecordDAO#getRecord(int)
	 */
	@Override
	public RecordBean getRecord(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RecordDAO#getAllByBooking(int)
	 */
	@Override
	public List<RecordBean> getAllByBooking(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RecordDAO#getAllByUser(int)
	 */
	@Override
	public List<RecordBean> getAllByUser(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
