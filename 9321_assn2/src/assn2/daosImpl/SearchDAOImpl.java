package assn2.daosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import assn2.beans.UserBean;
import assn2.daos.UserDAO;
import assn2.database.*;
import assn2.exceptions.*;
import assn2.database.DBConnectionFactory;



public class SearchDAOImpl {
	
	private DBConnectionFactory services;
	
	public SearchDAOImpl() {
		try {
			services = new DBConnectionFactory();
		} catch (ServiceLocatorException e) {
			
			e.printStackTrace();
		}
	}

	public SearchDAOImpl(DBConnectionFactory services) {
		this.services = services;
	}
	
	//insert a new record in user table
	public int overlapnumber (String city, String roomtype, Timestamp checkindate, Timestamp checkoutdate) {
		Connection con = null;
//		String o = checkindate.toString();
//		String instr = o.substring(0,o.length()-2);//a
//		String o2 = checkoutdate.toString();
//		String outstr = o2.substring(0,o2.length()-2);//b
		int size = 0;
		   try {
			 //get the connection 
			 con = services.createConnection();
			 PreparedStatement stmt = con.prepareStatement("SELECT * FROM  `roomcalendar` " +
			 		"WHERE (`checkindate` <= (?) AND (?) <= `checkoutdate`) OR " +
			 		"(`checkindate` <= (?) AND (?) <= `checkoutdate`) OR" +
			 		"( (?) <= `checkindate` AND (?) >= `checkoutdate`)");
			 stmt.setTimestamp(3, checkindate); //A
		     stmt.setTimestamp(4, checkindate);
		     stmt.setTimestamp(5, checkindate);
		     
			 stmt.setTimestamp(1, checkoutdate); //B
		     stmt.setTimestamp(2, checkoutdate);
		     stmt.setTimestamp(6, checkoutdate);
		     //execute the update
		     ResultSet n = stmt.executeQuery();
		     
		     if (n == null)//remember to catch the exceptions
		 	   throw new DataAccessException("Did not insert one row into database");
		     while(n.next()){
		    	 size++;
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
		   return size;
	}
	
	
	
}
