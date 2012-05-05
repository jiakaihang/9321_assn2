package assn2.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookingBean  implements Serializable {
	int bookingid;
	int userid;
	double totalprice;
	Timestamp bookingdate;
	
	public BookingBean(){
		super();
		bookingid = 0;
		userid = 0;
		totalprice = 0.0d;
		bookingdate = new Timestamp(0L);//big integer
	}
	
	public BookingBean(int bookingid, int userid, double totalprice,
			Timestamp bookingdate) {
		super();
		this.bookingid = bookingid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.bookingdate = bookingdate;
	}
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public Timestamp getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(Timestamp bookingdate) {
		this.bookingdate = bookingdate;
	}
	@Override
	public String toString() {
		return "BookingBean [bookingid=" + bookingid + ", userid=" + userid
				+ ", totalprice=" + totalprice + ", bookingdate=" + bookingdate
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookingdate == null) ? 0 : bookingdate.hashCode());
		result = prime * result + bookingid;
		long temp;
		temp = Double.doubleToLongBits(totalprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + userid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingBean other = (BookingBean) obj;
		if (bookingdate == null) {
			if (other.bookingdate != null)
				return false;
		} else if (!bookingdate.equals(other.bookingdate))
			return false;
		if (bookingid != other.bookingid)
			return false;
		if (Double.doubleToLongBits(totalprice) != Double
				.doubleToLongBits(other.totalprice))
			return false;
		if (userid != other.userid)
			return false;
		return true;
	}
	
	
}
