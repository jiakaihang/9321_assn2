/**
 * 
 */
package assn2.daosImpl;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assn2.beans.RoomCalendarBean;
import assn2.database.DBConnectionFactory;

/**
 * @author ASUS
 *
 */
public class RoomCalDAOImplTest {
	private RoomCalDAOImpl rcdao;
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
		rcdao = new RoomCalDAOImpl(new DBConnectionFactory());
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


//	//TODO:hand check database
//	//in order to see result, THIS TEST CAN NOT BE PLAYED WITH NEXT ONE !!
//	@Test
//	public void testInsert() throws ParseException {
//		Timestamp in = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss")).parse("2012-09-01:13-00-00")).getTime());
//		Timestamp out = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss")).parse("2012-09-01:13-00-00")).getTime());
//		RoomCalendarBean b = new RoomCalendarBean(6,2,in,out);
//		rcdao.insert(b);
//	}

//	//TODO: hard code id !!!! it may change
//	@Test
//	public void testDelete() {
//		rcdao.delete(6);
//	}

	/**
	 * Test method for {@link assn2.daosImpl.RoomCalDAOImpl#getRoomCalendar(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetRoomCalendar() throws ParseException {
		//room calendar id
		RoomCalendarBean t = rcdao.getRoomCalendar(1);
		Timestamp in = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-06 13:00:00")).getTime());
		Timestamp out = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-08 11:00:00")).getTime());
		RoomCalendarBean e = new RoomCalendarBean(1,1, 1, in, out);
		assertEquals(e, t);
	}

	/**
	 * Test method for {@link assn2.daosImpl.RoomCalDAOImpl#getRoomCalByRoomType(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetRoomCalByRoomType() throws ParseException {
		List<RoomCalendarBean> t = rcdao.getRoomCalByRoomType(1);
		List<RoomCalendarBean> e = new ArrayList<RoomCalendarBean>();
		
		Timestamp in = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-06 13:00:00")).getTime());
		Timestamp out = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-08 11:00:00")).getTime());
		RoomCalendarBean b1 = new RoomCalendarBean(1,1, 1, in, out);
		
		e.add(b1);
		assertEquals(e,t);
	}

}
