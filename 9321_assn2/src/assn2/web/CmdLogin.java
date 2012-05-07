/**
 * 
 */
package assn2.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assn2.beans.*;
import assn2.daosImpl.UserDAOImpl;
import assn2.exceptions.*;

/**
 * @author Kaihang
 *
 */
public class CmdLogin implements Command {
	
	/* (non-Javadoc)
	 * @see assn2.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			UserBean user = login(
				request.getParameter("username"), request.getParameter("password"));
			String secLvl = request.getParameterValues("loginType")[0];
			System.out.println("Login as: "+secLvl);
			if (user == null) {
				request.getSession().setAttribute("errorMsg", "Not a Registered User!");
				return "/error.jsp";
			} else if (!user.getSecurity_level().equalsIgnoreCase(secLvl)){
				request.getSession().setAttribute("errorMsg", "Security Level Mismatch");
				return "/error.jsp";
			}
			System.out.println("User info retrieved successfully!\n"+user);
			HttpSession session = request.getSession();
			//set user bean !!
			session.setAttribute("user", user);

			// this is not a jsp so it will chain the commands together
			return "listBooking";
//			return "/home.jsp";
		} catch (UserLoginFailedException e) {
			e.printStackTrace();
			return "/loginfailed.jsp";
		}
	}

	/**
	 * @param parameter
	 * @param parameter2
	 * @return
	 */
	private UserBean login(String username, String password) throws UserLoginFailedException{
		UserBean user = null;
		UserDAOImpl userDao = new UserDAOImpl();
        //TODO: this should try to find a UserBean using the UserDAO  
        //TODO: throw LoginFailedException if the user is not found or the operation fails.
        //TODO: if the user is found, return the user
		user = userDao.getByLogin(username, password);
		if(user == null)
			System.out.println("Cannot find the User");
			
		return user;
	}

}
