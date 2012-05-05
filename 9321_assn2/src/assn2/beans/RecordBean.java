package assn2.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RecordBean  implements Serializable {
	int recordid;
	int bookingid;
	int hotelid;
	int roomtypeid;
	int extrabed;
	double price;
	Timestamp checkindate;
	Timestamp checkoutdate;
	public RecordBean(int recordid, int bookingid, int hotelid, int roomtypeid,
			int extrabed, double price, Timestamp checkindate, Timestamp checkoutdate) {
		super();
		this.recordid = recordid;
		this.bookingid = bookingid;
		this.hotelid = hotelid;
		this.roomtypeid = roomtypeid;
		this.extrabed = extrabed;
		this.price = price;
		this.checkindate = checkindate;
		this.checkoutdate = checkoutdate;
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	public int getHotelid() {
		return hotelid;
	}
	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}
	public int getRoomtypeid() {
		return roomtypeid;
	}
	public void setRoomtypeid(int roomtypeid) {
		this.roomtypeid = roomtypeid;
	}
	public int getExtrabed() {
		return extrabed;
	}
	public void setExtrabed(int extrabed) {
		this.extrabed = extrabed;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getCheckindate() {
		return checkindate;
	}
	public void setCheckindate(Timestamp checkindate) {
		this.checkindate = checkindate;
	}
	public Date getCheckoutdate() {
		return checkoutdate;
	}
	public void setCheckoutdate(Timestamp checkoutdate) {
		this.checkoutdate = checkoutdate;
	}
	@Override
	public String toString() {
		return "RecordBean [recordid=" + recordid + ", bookingid=" + bookingid
				+ ", hotelid=" + hotelid + ", roomtypeid=" + roomtypeid
				+ ", extrabed=" + extrabed + ", price=" + price
				+ ", checkindate=" + checkindate + ", checkoutdate="
				+ checkoutdate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingid;
		result = prime * result
				+ ((checkindate == null) ? 0 : checkindate.hashCode());
		result = prime * result
				+ ((checkoutdate == null) ? 0 : checkoutdate.hashCode());
		result = prime * result + extrabed;
		result = prime * result + hotelid;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + recordid;
		result = prime * result + roomtypeid;
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
		RecordBean other = (RecordBean) obj;
		if (bookingid != other.bookingid)
			return false;
		if (checkindate == null) {
			if (other.checkindate != null)
				return false;
		} else if (!checkindate.equals(other.checkindate))
			return false;
		if (checkoutdate == null) {
			if (other.checkoutdate != null)
				return false;
		} else if (!checkoutdate.equals(other.checkoutdate))
			return false;
		if (extrabed != other.extrabed)
			return false;
		if (hotelid != other.hotelid)
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (recordid != other.recordid)
			return false;
		if (roomtypeid != other.roomtypeid)
			return false;
		return true;
	}
	
	
}
