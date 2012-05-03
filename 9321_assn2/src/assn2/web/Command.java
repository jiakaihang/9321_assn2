/**
 * 
 */
package assn2.web;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Kaihang
 *
 */
public interface Command {

	String execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException;
	
}
