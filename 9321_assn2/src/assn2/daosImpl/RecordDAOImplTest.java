/**
 * 
 */
package assn2.daosImpl;

import static org.junit.Assert.*;

import java.sql.Timestamp;
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

import assn2.beans.RecordBean;
import assn2.database.DBConnectionFactory;

/**
 * @author ASUS
 *
 */
public class RecordDAOImplTest {
	private RecordDAOImpl rdao;
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
		rdao = new RecordDAOImpl(new DBConnectionFactory());
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

	@Test
	public void testAdd() throws ParseException {
		
		Timestamp in = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-09-20 13:00:00")).getTime());
		Timestamp out = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-09-22 11:00:00")).getTime());
		RecordBean b = new RecordBean(5,3,3,15,0,500.0d,in,out);
		try{
			rdao.insert(b);		
		}catch(Exception e){ //if already has that data
			e.printStackTrace();
		}
	}
	
	
//	@Test
//	public void testDelete() throws ParseException {		
//		Timestamp in = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-09-20 13:00:00")).getTime());
//		Timestamp out = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-09-22 11:00:00")).getTime());
//		RecordBean b = new RecordBean(5,3,3,15,0,500.0d,in,out);
//		try{
//			rdao.delete(5);
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			RecordBean b2 = rdao.getRecord(5);
//			assertEquals(b2, null);  //cannot get anything
//		}
//	}

	/**
	 * Test method for {@link assn2.daosImpl.RecordDAOImpl#deleteAllByBooking(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testDeleteAllByBooking() {
		
	}

	/**
	 * Test method for {@link assn2.daosImpl.RecordDAOImpl#getRecord(int)}.
	 */
	@Test
	public void testGetRecord()  throws ParseException{
		RecordBean a = rdao.getRecord(1);
		Timestamp in = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-06 13:00:00")).getTime());
		Timestamp out = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-08 11:00:00")).getTime());
	
		RecordBean e = new RecordBean(1,1,1,1,0,100.0d,in,out);
		assertEquals(e, a);
	}

	/**
	 * Test method for {@link assn2.daosImpl.RecordDAOImpl#getAllByBooking(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetAllByBooking() throws ParseException {
		List<RecordBean> arms = rdao.getAllByBooking(1); //bookingid
		List<RecordBean> erms = new ArrayList<RecordBean>();
		//1	1	1	1	0	100.0000	2012-07-06 13:00:00	2012-07-08 11:00:00
		Timestamp in = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-06 13:00:00")).getTime());
		Timestamp out = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-08 11:00:00")).getTime());	
		RecordBean e = new RecordBean(1,1,1,1,0,100.0d,in,out);

//		2	1	2	6	0	120.0000	2012-07-08 13:00:00	2012-07-10 11:00:00
		Timestamp in2 = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-08 13:00:00")).getTime());
		Timestamp out2 = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-10 11:00:00")).getTime());	
		RecordBean e2 = new RecordBean(2,	1,	2,	6,	0	,120.0d,in2,out2);
		
		erms.add(e);
		erms.add(e2);
		assertEquals(erms,arms);
	}

	/**
	 * Test method for {@link assn2.daosImpl.RecordDAOImpl#getAllByUser(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetAllByHotel() throws ParseException {
		List<RecordBean> arms = rdao.getAllByHotel(2);
		List<RecordBean> erms = new ArrayList<RecordBean>();
		
		Timestamp in = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-08 13:00:00")).getTime());
		Timestamp out = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-07-10 11:00:00")).getTime());	
		RecordBean e = new RecordBean(2,1,2,6,0,120.0d,in,out);


		Timestamp in2 = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-08-15 13:00:00")).getTime());
		Timestamp out2 = new Timestamp(((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2012-08-18 11:00:00")).getTime());	
		RecordBean e2 = new RecordBean(3,2,	2,	8,	1	,250.0d,in2,out2);
		
		erms.add(e);
		erms.add(e2);
		assertEquals(erms,arms);
	}

}
