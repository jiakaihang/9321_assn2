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
		Connection con = null;
		   try {
			 //get the connection 
			 con = services.createConnection();
			 PreparedStatement stmt = con.prepareStatement(
					 "Insert Into Record (recordid, bookingid, hotelid, roomtypeid, extrabed, price, checkindate, checkoutdate) " +
					 "values (?, ?, ?, ?, ?, ?, ?, ?)");
		     stmt.setInt(1, record.getRecordid());
		     stmt.setInt(2, record.getBookingid());
		     stmt.setInt(3, record.getHotelid());
		     stmt.setInt(4, record.getRoomtypeid());
		     stmt.setInt(5, record.getExtrabed());
		     stmt.setDouble(6, record.getPrice());
		     stmt.setTimestamp(7, record.getCheckindate());
		     stmt.setTimestamp(8, record.getCheckoutdate());
		     
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
	 * @see assn2.daos.RecordDAO#insert(java.util.List)
	 */
	@Override
	public void insert(List<RecordBean> records) throws DataAccessException {		
		for(RecordBean r: records)
			insert(r);	
	}

	/* (non-Javadoc)
	 * @see assn2.daos.RecordDAO#delete(int)
	 */
	@Override
	public void delete(int id) throws DataAccessException {
		Connection con = null;	
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("delete from Record where recordid=(?)");
		     stmt.setInt(1, id);
		     int status = stmt.executeUpdate();
			 if (status != 1)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot delete any record owned by that id");
			 
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
	 * @see assn2.daos.RecordDAO#deleteAllByBooking(int)
	 */
	@Override
	public void deleteAllByBooking(int id) throws DataAccessException {
		Connection con = null;	
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("delete from Record where bookingid=(?)");
		     stmt.setInt(1, id);
		     int status = stmt.executeUpdate();
			 if (status != 1)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot delete any record owned by that id");
			 
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
	 * @see assn2.daos.RecordDAO#getRecord(int)
	 */
	@Override
	public RecordBean getRecord(int id) throws DataAccessException {
		Connection conn = null;
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from Record where recordid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that recordid");
			if (rs.next()){
				RecordBean r = createRecordBean(rs);
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
	 * @see assn2.daos.RecordDAO#getAllByBooking(int)
	 */
	@Override
	public List<RecordBean> getAllByBooking(int id) throws DataAccessException {
		Connection conn = null;
		List<RecordBean> list = new ArrayList<RecordBean>();
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from Record where bookingid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that hotelid");
			while (rs.next()){
				RecordBean r = createRecordBean(rs);
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
	 * @see assn2.daos.RecordDAO#getAllByUser(int)
	 */
	@Override
	public List<RecordBean> getAllByHotel(int id) throws DataAccessException {
		Connection conn = null;
		List<RecordBean> list = new ArrayList<RecordBean>();
		try {
			conn = services.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from Record where hotelid = ? ");
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find entity of that userid");
			while (rs.next()){
				RecordBean r = createRecordBean(rs);
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
	
	public RecordBean createRecordBean(ResultSet rs) throws SQLException {
		RecordBean r = new RecordBean(	rs.getInt("recordid"), 
										rs.getInt("bookingid"), 
										rs.getInt("hotelid"), 
										rs.getInt("roomtypeid"), 
										rs.getInt("extrabed"), 
										rs.getDouble("price"), 
										rs.getTimestamp("checkindate"), 
										rs.getTimestamp("checkoutdate"));
		return r;
	}

}
