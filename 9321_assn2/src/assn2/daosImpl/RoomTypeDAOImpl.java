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
		Connection conn = null;
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from RoomType where roomtypeid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that roomtypeid");
			if (rs.next()){
				RoomTypeBean r = createRoomTypeBean(rs);
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
	 * @see assn2.daos.RoomTypeDAO#getAllByHotel(int)
	 */
	@Override
	public List<RoomTypeBean> getAllByHotel(int id) throws DataAccessException {
		Connection conn = null;
		List<RoomTypeBean> list = new ArrayList<RoomTypeBean>();
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from RoomType where hotelid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that hotelid");
			while (rs.next()){
				RoomTypeBean r = createRoomTypeBean(rs);
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
	 * @see assn2.daos.RoomTypeDAO#getAllByType(java.lang.String)
	 */
	@Override
	public List<RoomTypeBean> getAllByType(String type)
			throws DataAccessException {
		Connection conn = null;
		List<RoomTypeBean> list = new ArrayList<RoomTypeBean>();
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from RoomType where type = ? ");
			stmt.setString(1, type);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that type");
			while (rs.next()){
				RoomTypeBean r = createRoomTypeBean(rs);
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
	
	public RoomTypeBean createRoomTypeBean(ResultSet rs) throws SQLException {
		RoomTypeBean r = new RoomTypeBean(
				rs.getInt("roomtypeid"), 
				rs.getInt("hotelid"), 
				rs.getString("type"), 
				rs.getDouble("price"), 
				rs.getDouble("discountrate"), 
				rs.getTimestamp("discountfrom"), 
				rs.getTimestamp("discountto"), 
				rs.getString("description"));
		return r;
	}

}
