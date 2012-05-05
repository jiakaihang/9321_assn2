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

import assn2.beans.HotelBean;
import assn2.database.DBConnectionFactory;

/**
 * @author ASUS
 *
 */
public class HotelDAOImplTest {

	private HotelDAOImpl hoteldao;
	
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
		hoteldao = new HotelDAOImpl(new DBConnectionFactory());
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
	 * Test method for {@link assn2.daosImpl.HotelDAOImpl#getHotel(int)}.
	 */
	@Test
	public void testGetHotel() {
		HotelBean hb = hoteldao.getHotel(1);
		HotelBean hb2 = new HotelBean(1,"a","Sydney",1,2,2345,"1 george st");
		assertEquals(hb2,hb);
	}

	/**
	 * Test method for {@link assn2.daosImpl.HotelDAOImpl#getHotelByCity(java.lang.String)}.
	 */
	@Test
	public void testGetHotelByCity() {
		List<HotelBean> rms = hoteldao.getHotelByCity("Sydney");
		List<HotelBean> rms2 = new ArrayList<HotelBean>();
		HotelBean hb2 = new HotelBean(1,"a","Sydney",1,2,2345,"1 george st");
		rms2.add(hb2);
		assertEquals(rms2, rms);
	}

	/**
	 * Test method for {@link assn2.daosImpl.HotelDAOImpl#getHotelByName(java.lang.String)}.
	 */
	@Test
	public void testGetHotelByName() {
		//TODO:TYPE
		List<HotelBean> rms = hoteldao.getHotelByName("a");
		
	}

	/**
	 * Test method for {@link assn2.daosImpl.HotelDAOImpl#getHotelByOwner(int)}.
	 */
	@Test
	public void testGetHotelByOwner() {
		List<HotelBean> rms = hoteldao.getHotelByOwner(1);
		HotelBean b1= new HotelBean(1,"a","Sydney",1,2,	2345,	"1 george st");
		HotelBean b2= new HotelBean(2,"b","Melbourne",1,3,	3456,	"1 mel st");
		HotelBean b3= new HotelBean(3,"c","Brisbane",1,4,	4567,	"1 bri st");
		HotelBean b4= new HotelBean(4,"d","Adelaide",1,5,	5678,	"1 ade st");
		HotelBean b5= new HotelBean(5,"e","Hobart",1,6,	6789,	"1 hob st");
		List<HotelBean> rms2 = new ArrayList<HotelBean>();
		rms2.add(b1);
		rms2.add(b2);
		rms2.add(b3);
		rms2.add(b4);
		rms2.add(b5);
		assertEquals(rms2, rms);
	}

	/**
	 * Test method for {@link assn2.daosImpl.HotelDAOImpl#getHotelByManager(int)}.
	 */
	@Test
	public void testGetHotelByManager() {
		HotelBean b = hoteldao.getHotelByManager(2);
		HotelBean hb2 = new HotelBean(1,"a","Sydney",1,2,2345,"1 george st");
		assertEquals(hb2, b);
	}

}
