package assn2.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RoomCalendarBean  implements Serializable {
	int roomcalid;
	int roomtypeid;
	Timestamp checkindate;
	Timestamp checkoutdate;
	public RoomCalendarBean(int roomcalid, int roomtypeid,
			Timestamp checkindate, Timestamp checkoutdate) {
		super();
		this.roomcalid = roomcalid;
		this.roomtypeid = roomtypeid;
		this.checkindate = checkindate;
		this.checkoutdate = checkoutdate;
	}
	public int getRoomcalid() {
		return roomcalid;
	}
	public void setRoomcalid(int roomcalid) {
		this.roomcalid = roomcalid;
	}
	public int getRoomtypeid() {
		return roomtypeid;
	}
	public void setRoomtypeid(int roomtypeid) {
		this.roomtypeid = roomtypeid;
	}
	public Timestamp getCheckindate() {
		return checkindate;
	}
	public void setCheckindate(Timestamp checkindate) {
		this.checkindate = checkindate;
	}
	public Timestamp getCheckoutdate() {
		return checkoutdate;
	}
	public void setCheckoutdate(Timestamp checkoutdate) {
		this.checkoutdate = checkoutdate;
	}
	@Override
	public String toString() {
		return "RoomCalendar [roomcalid=" + roomcalid 
				+ ", roomtypeid=" + roomtypeid + ", checkindate=" + checkindate
				+ ", checkoutdate=" + checkoutdate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((checkindate == null) ? 0 : checkindate.hashCode());
		result = prime * result
				+ ((checkoutdate == null) ? 0 : checkoutdate.hashCode());
		result = prime * result + roomcalid;
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
		RoomCalendarBean other = (RoomCalendarBean) obj;
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
		if (roomcalid != other.roomcalid)
			return false;
		if (roomtypeid != other.roomtypeid)
			return false;
		return true;
	}
	
	
}
