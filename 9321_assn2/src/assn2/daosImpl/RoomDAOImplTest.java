/**
 * 
 */
package assn2.daosImpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assn2.beans.RoomBean;
import assn2.database.DBConnectionFactory;

/**
 * @author ASUS
 *
 */
public class RoomDAOImplTest {

	private RoomDAOImpl roomdaoimpl;
	
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
		roomdaoimpl = new RoomDAOImpl(new DBConnectionFactory());
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
	 * Test method for {@link assn2.daosImpl.RoomDAOImpl#getRoom(int)}.
	 */
	@Test
	public void testGetRoom() {
		RoomBean r = roomdaoimpl.getRoom(1);
		RoomBean r2 = new RoomBean(1,101,1,"available",1);
		assertEquals(r,r2);
	}

	/**
	 * Test method for {@link assn2.daosImpl.RoomDAOImpl#getRoomByHotel(int)}.
	 */
	@Test
	public void testGetRoomByHotel() {
		List<RoomBean> rms = roomdaoimpl.getRoomByHotel(1);
		RoomBean r1 = new RoomBean(1,101,1,"available",1);
		RoomBean r2 = new RoomBean(6,101,1,"available",1);
		RoomBean r3 = new RoomBean(11,101,1,"available",1);
		RoomBean r4 = new RoomBean(16,101,1,"available",1);
		RoomBean r5 = new RoomBean(21,101,1,"available",1);
		List<RoomBean> rms2 = new ArrayList<RoomBean>();
		rms2.add(r1);
		rms2.add(r2);
		rms2.add(r3);
		rms2.add(r4);
		rms2.add(r5);
		assertEquals(rms,rms2);
	}

	/**
	 * Test method for {@link assn2.daosImpl.RoomDAOImpl#setCondition(int, java.lang.String)}.
	 */
	@Test
	public void testSetCondition() {
		roomdaoimpl.setCondition(25, "available");
		RoomBean r = roomdaoimpl.getRoom(25);
		assertEquals(r.getCondition(),"available");
	}

	/**
	 * Test method for {@link assn2.daosImpl.RoomDAOImpl#setConditionByHotel(int, java.lang.String)}.
	 */
	@Test
	public void testSetConditionByHotel() {
		roomdaoimpl.setConditionByHotel(1, "maintainence");
		List<RoomBean> rms = roomdaoimpl.getRoomByHotel(1);
		RoomBean r1 = new RoomBean(1,101,1,"maintainence",1);
		RoomBean r2 = new RoomBean(6,101,1,"maintainence",1);
		RoomBean r3 = new RoomBean(11,101,1,"maintainence",1);
		RoomBean r4 = new RoomBean(16,101,1,"maintainence",1);
		RoomBean r5 = new RoomBean(21,101,1,"maintainence",1);
		List<RoomBean> rms2 = new ArrayList<RoomBean>();
		rms2.add(r1);
		rms2.add(r2);
		rms2.add(r3);
		rms2.add(r4);
		rms2.add(r5);
		assertEquals(rms,rms2);
	}

}
