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
		Connection con = null;
		   try {
			 //get the connection 
			 con = services.createConnection();
			 PreparedStatement stmt = con.prepareStatement(
					 "Insert Into RoomCalendar (roomcalid, roomtypeid, checkindate, checkoutdate) " +
					 "values (?, ?, ?, ?)");
		     stmt.setInt(1, roomcal.getRoomcalid());
		     
		     stmt.setInt(2, roomcal.getRoomtypeid());
		     stmt.setTimestamp(3, roomcal.getCheckindate());
		     stmt.setTimestamp(4, roomcal.getCheckoutdate());
		     
		     //execute the update
		     int n = stmt.executeUpdate();
		     if (n != 1)//remember to catch the exceptions
		 	   throw new DataAccessException("Did not insert one row into database");
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
	 * @see assn2.daos.RoomCalDAO#delete(int)
	 */
	@Override
	public void delete(int id) throws DataAccessException {
		Connection con = null;	
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("delete from RoomCalendar where roomcalid=(?)");
		     stmt.setInt(1, id);
		     int status = stmt.executeUpdate();
			 if (status != 1)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot delete any roomcalendar of that id");
			 
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
	
	public void deleteByBooking(int id) throws DataAccessException {
		Connection con = null;	
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("delete from RoomCalendar where bookingid=(?)");
		     stmt.setInt(1, id);
		     int status = stmt.executeUpdate();
			 if (status < 1)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot delete any roomcalendar of that bookingid");
			 
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
	 * @see assn2.daos.RoomCalDAO#getRoomCalendar(int)
	 */
	@Override
	public RoomCalendarBean getRoomCalendar(int id) throws DataAccessException {
		Connection conn = null;
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from RoomCalendar where roomcalid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that roomcalid");
			if (rs.next()){
				RoomCalendarBean r = createRoomCalendarBean(rs);
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
	 * @see assn2.daos.RoomCalDAO#getRoomCalByRoomType(int)
	 */
	@Override
	public List<RoomCalendarBean> getRoomCalByRoomType(int id)
			throws DataAccessException {
		Connection conn = null;
		List<RoomCalendarBean> list = new ArrayList<RoomCalendarBean>();
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from RoomCalendar where roomtypeid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that hotelid");
			while (rs.next()){
				RoomCalendarBean r = createRoomCalendarBean(rs);
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
	
	public RoomCalendarBean createRoomCalendarBean(ResultSet rs) throws SQLException {
		RoomCalendarBean r = new RoomCalendarBean(
				rs.getInt("roomcalid"), 
				rs.getInt("bookingid"),
				rs.getInt("roomtypeid"), 
				rs.getTimestamp("checkindate"), 
				rs.getTimestamp("checkoutdate"));
		return r;
	}

}
