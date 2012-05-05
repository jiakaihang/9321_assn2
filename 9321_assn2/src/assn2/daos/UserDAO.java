/**
 * 
 */
package assn2.daos;

import assn2.beans.UserBean;
import assn2.exceptions.DataAccessException;

/**
 * The DAO of User
 * @author Kaihang
 *
 */
public interface UserDAO {
	
	/**
	 * Insert a User entry to database
	 * @param user
	 * @throws DataAccessException
	 */
	void insert(UserBean user) throws DataAccessException;
	
	/**
	 * Delete a User entry of the given userid
	 * @param userid
	 * @throws DataAccessException
	 */
	void delete(int id) throws DataAccessException;
	
	/**
	 * Tries to locate a user with the given username and password.
	 * @param username
	 * @param password
	 * @return UserBean
	 * @throws DataAccessException
	 */
	UserBean getByLogin(String username, String password) throws DataAccessException;

}
