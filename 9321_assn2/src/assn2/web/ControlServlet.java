package assn2.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlServlet
 */
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Map commands;
	
	/** Initializes the servlet.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		commands = new HashMap();
		commands.put("addBooking", new CmdAddBooking());
		commands.put("delBooking", new CmdDelBooking());
		
		commands.put("addUser", new CmdAddUser());
		commands.put("delUser", new CmdDelUser());
		
		commands.put("changeCondition", new CmdChangeCondition());
		commands.put("listBooking", new CmdListBooking());

		commands.put("login", new CmdLogin());
		commands.put("logout", new CmdLogout());
		
		commands.put("PAGE_NOT_FOUND", new CmdError());
	}
	
	/** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		Command cmd = resolveCommand(request);
		//Interface method, result string next is page to be viewed next
		String next = cmd.execute(request, response);
		//if page does not contains "." than treat it as a command
		if (next.indexOf('.') < 0) {
			cmd = (Command) commands.get(next);
			next = cmd.execute(request, response);
		}		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(next);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Return command string from HashMap
	 * @param request
	 * @return
	 */
	private Command resolveCommand(HttpServletRequest request) {
		Command cmd = (Command) commands.get(request.getParameter("operation"));
		if (cmd == null) {
			cmd = (Command) commands.get("PAGE_NOT_FOUND");
		}
		return cmd;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/** Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "This servlet implements a command pattern for a hotel booking application";
	}

}
