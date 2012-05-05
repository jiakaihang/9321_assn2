/**
 * 
 */
package assn2.daosImpl;

import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Timestamp;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assn2.beans.BookingBean;
import assn2.database.DBConnectionFactory;

/**
 * @author ASUS
 *
 */
public class BookingDAOImplTest2 {

	private BookingDAOImpl bookingdaoimpl;

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
		bookingdaoimpl = new BookingDAOImpl(new DBConnectionFactory());
	}

	/**
	 * @throws java.lang.Exception
	 */
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

	/**
	 * Test method for {@link assn2.daosImpl.BookingDAOImpl#insert(assn2.beans.BookingBean)}.
	 */
//	@Test
//	public void testInsert() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link assn2.daosImpl.BookingDAOImpl#delete(int)}.
//	 */
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link assn2.daosImpl.BookingDAOImpl#findAllByUser(int)}.
//	 */
//	@Test
//	public void testFindAllByUser() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link assn2.daosImpl.BookingDAOImpl#getBooking(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetBooking() throws ParseException {
		BookingBean b1 = bookingdaoimpl.get(3);
//		System.out.println(b1);
		//2012-09-01 13:00:00
		String str_date="2012-09-01:13-00-00";
		DateFormat  df = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
		java.util.Date date = df.parse(str_date);
		Timestamp ts = new Timestamp(date.getTime());
		BookingBean b2 = new BookingBean(3,	9,	500.0000, ts);
		assertEquals(b1,b2);
	}

}
