package assn2.database;
/**
 * Modified
 */
import java.sql.Connection;
import javax.naming.*;

import junit.framework.TestCase;

public class DBConnectionFactoryTest extends TestCase {

	 public void setUp() throws Exception {
		 super.setUp();
	     
		 System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
             "org.apache.naming.java.javaURLContextFactory");
         System.setProperty(Context.URL_PKG_PREFIXES, 
             "org.apache.naming");            

         InitialContext ic = new InitialContext();
         ic.createSubcontext("java:");
         ic.createSubcontext("java:/comp");
         ic.createSubcontext("java:/comp/env");
         ic.createSubcontext("java:/comp/env/jdbc");
        
         // Construct BasicDataSource reference
         Reference ref = new Reference("javax.sql.DataSource", "org.apache.commons.dbcp.BasicDataSourceFactory", null);
         ref.add(new StringRefAddr("driverClassName", "com.mysql.jdbc.Driver"));
         ref.add(new StringRefAddr("url", "jdbc:mysql://127.0.0.1:33066/mydb"));
         ref.add(new StringRefAddr("username", "root"));
         ref.add(new StringRefAddr("password", ""));
         ic.bind("java:/comp/env/jdbc/mydb", ref);
    }
	 
	public DBConnectionFactoryTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(DBConnectionFactoryTest.class);
	}

	public void testCreateConnection() throws Exception {
		DBConnectionFactory services = new DBConnectionFactory();
		Connection con = services.createConnection(); 
		assertNotNull(con);
		if (con != null)
			con.close();
	}
}
