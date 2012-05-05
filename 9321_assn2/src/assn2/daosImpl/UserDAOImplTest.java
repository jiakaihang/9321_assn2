package assn2.daosImpl;
/**
 * Modified on 4th May
 */
import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assn2.beans.UserBean;
import assn2.database.DBConnectionFactory;


public class UserDAOImplTest extends TestCase {

	private UserDAOImpl userdaoimpl;

	public UserDAOImplTest(String arg0) {
		super(arg0);
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		InitialContext ic = new InitialContext();
		ic.createSubcontext("java:");
		ic.createSubcontext("java:/comp");
		ic.createSubcontext("java:/comp/env");
		ic.createSubcontext("java:/comp/env/jdbc");

		// Construct BasicDataSource reference
		Reference ref = new Reference("javax.sql.DataSource",
				"org.apache.commons.dbcp.BasicDataSourceFactory", null);
		ref.add(new StringRefAddr("driverClassName", "com.mysql.jdbc.Driver"));
		ref.add(new StringRefAddr("url", "jdbc:mysql://127.0.0.1:33066/mydb"));
		ref.add(new StringRefAddr("username", "root"));
		ref.add(new StringRefAddr("password", ""));
		ic.bind("java:/comp/env/jdbc/mydb", ref);
		//initialize
		userdaoimpl = new UserDAOImpl(new DBConnectionFactory());
	}

	@After
	public void tearDown() throws Exception {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

		InitialContext ic = new InitialContext();
		ic.unbind("java:/comp/env/jdbc/mydb");
		ic.destroySubcontext("java:/comp/env/jdbc");
		ic.destroySubcontext("java:/comp/env");
		ic.destroySubcontext("java:/comp");
		ic.destroySubcontext("java:");
	}
	
	@Test
	public void testGet() {
		UserBean o1 = userdaoimpl.get(99); //to be checked
		UserBean o2 = new UserBean(1,"steven","jobs","owner","sj@sj.com","sj","sj","us");
		assertEquals(o1,o2);
	}
	
//	@Test
//	public void testAddandDelete() {
//		//first try delete the 10th record
//		try{
//			userdaoimpl.delete(10);
//		}catch(Exception e){//add normal user
//			e.printStackTrace();
//		}
//		UserBean n = new UserBean(10,"min","han","user","mh@mh.com","mh","mh","oz");
//		userdaoimpl.insert(n);
//		UserBean o = userdaoimpl.get(10);
//		assertEquals(n,o);
//		userdaoimpl.delete(10);//cancel out the testing data
//	}
//	@Test
//	public void testFindByLoginDetails() {
//		UserBean n = new UserBean(10,"min","han","user","mh@mh.com","mh","mh","oz");
//		userdaoimpl.insert(n);
//		UserBean n1 = userdaoimpl.getByLogin("mh", "mh");
//		assertEquals(n,n1);
//		//clear up
//		userdaoimpl.delete(10);
//		
//	}

}
