/**
 * 
 */
package assn2.daosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		Connection conn = null;
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from Hotel where hotelid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that hotelid");
			if (rs.next()){
				HotelBean r = createHotelBean(rs);
				stmt.close(); //close it
				return r;
			}
		} catch (ServiceLocatorException e) {
			e.printStackTrace();//no connection
		} catch (SQLException e) {
			e.printStackTrace();//not execution of statement
		} finally {
		      if (conn != null) {
			         try {
			           conn.close();//and close the connections etc
			   		 } catch (SQLException e1) {  //if not close properly
			           e1.printStackTrace();
			         }
			  }
		}
		return null; //not found
	}

	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotelByCity(java.lang.String)
	 */
	@Override
	public List<HotelBean> getHotelByCity(String city)
			throws DataAccessException {
		List<HotelBean> list = new ArrayList<HotelBean>();
		Connection conn = null;
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Hotel WHERE city = ?");
			stmt.setString(1, city);
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find any hotel in that city");
			while(rs.next()){
				HotelBean r = createHotelBean(rs);
				list.add(r);
			}
		} catch (ServiceLocatorException e) {
			e.printStackTrace();//no connection
		} catch (SQLException e) {
			e.printStackTrace();//not execution of statement
		} finally {
				if (conn != null) {
			         try {
			           conn.close();//and close the connections etc
			   		 } catch (SQLException e1) {  //if not close properly
			           e1.printStackTrace();
			   		 }
				}     
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotelByName(java.lang.String)
	 */
	@Override
	public List<HotelBean> getHotelByName(String name)
			throws DataAccessException {
		List<HotelBean> list = new ArrayList<HotelBean>();
		Connection conn = null;
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Hotel WHERE name = ?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find any hotel in that name");
			while(rs.next()){
				HotelBean r = createHotelBean(rs);
				list.add(r);
			}
		} catch (ServiceLocatorException e) {
			e.printStackTrace();//no connection
		} catch (SQLException e) {
			e.printStackTrace();//not execution of statement
		} finally {
				if (conn != null) {
			         try {
			           conn.close();//and close the connections etc
			   		 } catch (SQLException e1) {  //if not close properly
			           e1.printStackTrace();
			   		 }
				}     
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotelByOwner(int)
	 */
	@Override
	public List<HotelBean> getHotelByOwner(int id) throws DataAccessException {
		List<HotelBean> list = new ArrayList<HotelBean>();
		Connection conn = null;
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Hotel WHERE ownerid = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find any hotel owned by that ownerid");
			while(rs.next()){
				HotelBean r = createHotelBean(rs);
				list.add(r);
			}
		} catch (ServiceLocatorException e) {
			e.printStackTrace();//no connection
		} catch (SQLException e) {
			e.printStackTrace();//not execution of statement
		} finally {
				if (conn != null) {
			         try {
			           conn.close();//and close the connections etc
			   		 } catch (SQLException e1) {  //if not close properly
			           e1.printStackTrace();
			   		 }
				}     
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see assn2.daos.HotelDAO#getHotelByManager(int)
	 */
	@Override
	public HotelBean getHotelByManager(int id) throws DataAccessException {
		Connection conn = null;
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from Hotel where managerid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that managerid");
			if (rs.next()){
				HotelBean r = createHotelBean(rs);
				stmt.close(); //close it
				return r;
			}
		} catch (ServiceLocatorException e) {
			e.printStackTrace();//no connection
		} catch (SQLException e) {
			e.printStackTrace();//not execution of statement
		} finally {
		      if (conn != null) {
			         try {
			           conn.close();//and close the connections etc
			   		 } catch (SQLException e1) {  //if not close properly
			           e1.printStackTrace();
			         }
			  }
		}
		return null; //not found
	}
	
	public HotelBean createHotelBean(ResultSet rs) throws SQLException {
		HotelBean h = new HotelBean(rs.getInt("hotelid"), 
									rs.getString("name"),
									rs.getString("city"), 
									rs.getInt("ownerid"), 
									rs.getInt("managerid"), 
									rs.getInt("phoneno"), 
									rs.getString("address"));
		return h;
	}

}
