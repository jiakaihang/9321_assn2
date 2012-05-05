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
import java.util.ArrayList;
import java.util.List;

import assn2.beans.RoomBean;
import assn2.beans.UserBean;
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
	public RoomBean getRoom(int roomid) throws DataAccessException {
		Connection con = null;
		RoomBean r = null;
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("select * from room where roomid=(?)");
		     stmt.setInt(1, roomid);
		     ResultSet rs = stmt.executeQuery();
			 if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find any room by that id");
			 while(rs.next()){
				r = createRoomBean(rs);
			 }
		   } catch (ServiceLocatorException e) {
		       throw new DataAccessException("Unable to retrieve connection; " + e.getMessage(), e);
		   } catch (SQLException e) {
		       throw new DataAccessException("Unable to execute query; " + e.getMessage(), e);
		   } finally {
		      if (con != null) {
		         try {
		           con.close();//and close the connections etc
		   		 } catch (SQLException e1) {  //if not close properly
		           e1.printStackTrace();
		         }
		   }	      
		}
		return r; //the last
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomDAO#getRoomByHotel(int)
	 */
	@Override
	public List<RoomBean> getRoomByHotel(int hid) throws DataAccessException {
		Connection con = null;
		RoomBean r = null;
		List<RoomBean> rms = null;
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("select * from room where hotelid=(?)");
		     stmt.setInt(1, hid);
		     ResultSet rs = stmt.executeQuery();
			 if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find any room by that hotel id");
			 rms = new ArrayList<RoomBean>();
			 while(rs.next()){
				r = createRoomBean(rs);
				rms.add(r);
			 }
		   } catch (ServiceLocatorException e) {
		       throw new DataAccessException("Unable to retrieve connection; " + e.getMessage(), e);
		   } catch (SQLException e) {
		       throw new DataAccessException("Unable to execute query; " + e.getMessage(), e);
		   } finally {
		      if (con != null) {
		         try {
		           con.close();//and close the connections etc
		   		 } catch (SQLException e1) {  //if not close properly
		           e1.printStackTrace();
		         }
		   }	      
		}
		return rms; //the last
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomDAO#setCondition(int, java.lang.String, java.sql.Timestamp, java.sql.Timestamp)
	 */
	@Override
	public void setCondition(int rid, String cond) throws DataAccessException {
		Connection con = null;
		RoomBean r = null;
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("UPDATE  `mydb`.`room` SET  `condition` = (?) WHERE  `room`.`roomid` =(?)");
		     stmt.setString(1, cond);
		     stmt.setInt(2, rid);
		     int rs = stmt.executeUpdate();
			 if (rs != 1)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot set condition for any room by that id");
			 
		   } catch (ServiceLocatorException e) {
		       throw new DataAccessException("Unable to retrieve connection; " + e.getMessage(), e);
		   } catch (SQLException e) {
		       throw new DataAccessException("Unable to execute query; " + e.getMessage(), e);
		   } finally {
		      if (con != null) {
		         try {
		           con.close();//and close the connections etc
		   		 } catch (SQLException e1) {  //if not close properly
		           e1.printStackTrace();
		         }
		   }	      
		}
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RoomDAO#setConditionByHotel(int, java.lang.String, java.sql.Timestamp, java.sql.Timestamp)
	 */
	@Override
	public void setConditionByHotel(int hid, String cond) throws DataAccessException {
		Connection con = null;
		
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("UPDATE  `mydb`.`room` SET  `condition` = ? WHERE  `room`.`hotelid` = ?");
		     stmt.setString(1, cond);
		     stmt.setInt(2, hid);
		     int rs = stmt.executeUpdate();
		     //rs is row count
			 if (rs == 0)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot set condition for any room by that hotel id");

		   } catch (ServiceLocatorException e) {
		       throw new DataAccessException("Unable to retrieve connection; " + e.getMessage(), e);
		   } catch (SQLException e) {
		       throw new DataAccessException("Unable to execute query; " + e.getMessage(), e);
		   } finally {
		      if (con != null) {
		         try {
		           con.close();//and close the connections etc
		   		 } catch (SQLException e1) {  //if not close properly
		           e1.printStackTrace();
		         }
		   }	      
		}
	}
	
	public RoomBean createRoomBean(ResultSet rs) throws SQLException {
		RoomBean rb = new RoomBean();
		rb.setCondition(rs.getString("condition"));
		rb.setHotelid(rs.getInt("hotelid"));
		rb.setRoomid(rs.getInt("roomid"));
		rb.setRoomno(rs.getInt("roomno"));
		rb.setRoomtypeid(rs.getInt("roomtypeid"));
		return rb;
	}

}