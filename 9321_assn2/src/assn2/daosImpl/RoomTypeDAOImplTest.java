/**
 * 
 */
package assn2.daosImpl;

import static org.junit.Assert.*;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assn2.beans.RoomTypeBean;
import assn2.daos.RoomTypeDAO;
import assn2.database.DBConnectionFactory;

/**
 * @author ASUS
 *
 */
public class RoomTypeDAOImplTest {
	private RoomTypeDAO rmdao;
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
		rmdao = new RoomTypeDAOImpl(new DBConnectionFactory());
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
	 * Test method for {@link assn2.daosImpl.RoomTypeDAOImpl#getRoomType(int)}.
	 */
	@Test
	public void testGetRoomType() {
		RoomTypeBean c = rmdao.getRoomType(1);
		RoomTypeBean e = new RoomTypeBean(1, 1, "single", 100.0d, 0, null, null, "single of Hotel 1");
		assertEquals(c,e);
	}

	/**
	 * Test method for {@link assn2.daosImpl.RoomTypeDAOImpl#getAllByHotel(int)}.
	 */
	@Test
	public void testGetAllByHotel() {
		List<RoomTypeBean> c = rmdao.getAllByHotel(1);
		for (RoomTypeBean roomTypeBean : c) {
			System.out.println(roomTypeBean);
		}
		System.out.println("---------------------");
	}

	/**
	 * Test method for {@link assn2.daosImpl.RoomTypeDAOImpl#getAllByType(java.lang.String)}.
	 */
	@Test
	public void testGetAllByType() {
		List<RoomTypeBean> c = rmdao.getAllByType("single");
		for (RoomTypeBean roomTypeBean : c) {
			System.out.println(roomTypeBean);
		}
	}

}
