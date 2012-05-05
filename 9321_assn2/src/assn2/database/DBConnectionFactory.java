package assn2.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.*;
import javax.sql.DataSource;

import assn2.exceptions.ServiceLocatorException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionFactory extends AbstractJndiLocator {

	private DataSource ds;
	//TODO why constructor does not have super()
	public DBConnectionFactory() throws ServiceLocatorException {
	}
	
	public DBConnectionFactory(DataSource ds) throws ServiceLocatorException {
		this.ds = ds;
	}
	
	public Connection createConnection() throws ServiceLocatorException {
		try {
			//DataSource is an interface, getDataSource() is function in this file
			return getDataSource().getConnection();
		} catch (SQLException e) {
			throw new ServiceLocatorException("Unable to create connection: " + e.getMessage(), e);
		}
	}

	/**
	 * Finds a data source by looking up the initial context
	 * @return
	 * @throws ServiceLocatorException
	 */
	public DataSource getDataSource() throws ServiceLocatorException {
		if (ds == null) {
			try {
				//TODO this lookup inherent abstract class function
				ds = (DataSource) lookup("jdbc/mydb");
			} catch (NamingException e) {
				throw new ServiceLocatorException("Unable to locate data source; " + e.getMessage(), e);
			}
		}
		return ds;
	}
}
