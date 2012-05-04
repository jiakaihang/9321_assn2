package assn2.daosImpl;
import assn2.daos.UserDAO;
import assn2.database.*;
import assn2.exceptions.*;
import assn2.beans.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * The Data Access Object for users.
 */
public class UserDAOImpl implements UserDAO {
	
	private DBConnectionFactory services;
	
	public UserDAOImpl() throws ServiceLocatorException {
		services = new DBConnectionFactory();
	}

	public UserDAOImpl(DBConnectionFactory services) {
		this.services = services;
	}

	/**
	 * @see com.enterprise.dao.UserDAO#findByLoginDetails(java.lang.String, java.lang.String)
	 */
	public UserBean findByLoginDetails(String username, String password) throws DataAccessException {
		Connection con = null;
		try {
			con = services.createConnection();
			PreparedStatement stmt = con.prepareStatement("select * from users where username = ? and password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserBean user = createUserBean(rs);
				stmt.close(); 
				return user;
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("Unable to retrieve connection; " + e.getMessage(), e);
		} catch (SQLException e) {
			throw new DataAccessException("Unable to execute query; " + e.getMessage(), e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	//insert a new record in user table
	public void InsertUserBean(UserBean bean) {
		Connection con = null;
		   try {
			 //get the connection 
			 con = services.createConnection();
		 	 //create the prepared statement
//			 Statement st = con.createStatement();
//			 st.execute("select id from tbl_contacts");
//			 
			 PreparedStatement stmt = con.prepareStatement("Insert Into user (userid, fname, lname, security_level, email, username,password,address) values (?, ?, ?, ?, ?, ?,?,?)");
		     stmt.setInt(1, bean.getUserid());
		     stmt.setString(2, bean.getFname());
		     stmt.setString(3, bean.getLname());
		     stmt.setString(4, bean.getSecurity_level());
		     stmt.setString(5, bean.getEmail());
		     stmt.setString(6, bean.getUsername());
		     stmt.setString(7, bean.getPassword());
		     stmt.setString(8, bean.getAddress());
		     
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
	
	public UserBean getUserBean(int id) {
		Connection con = null;
		UserBean r = null;
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("select * from user where userid=(?)");
		     stmt.setInt(1, id);
		     ResultSet rs = stmt.executeQuery();
			 if (rs == null)//remember to catch the exceptions
			 	  throw new DataAccessException("cannot find any record owned by that id");
			 while(rs.next()){
				r = createUserBean(rs);
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
	
	public void deleteUserBean(int id) {
		Connection con = null;
		
		try {
			 //get the connection 
			 con = services.createConnection();		 
			 PreparedStatement stmt = con.prepareStatement("delete from user where userid=(?)");
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
		//return nothing !!
	}
	
	//helper 
	private UserBean createUserBean(ResultSet rs) throws SQLException {
		UserBean user = new UserBean();
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setFname(rs.getString("fname"));
		user.setLname(rs.getString("lname"));
		user.setPassword(rs.getString("password")); //column
		user.setSecurity_level(rs.getString("security_level"));
		user.setUserid(rs.getInt("userid"));
		user.setUsername(rs.getString("username"));
		return user;
	}
}
