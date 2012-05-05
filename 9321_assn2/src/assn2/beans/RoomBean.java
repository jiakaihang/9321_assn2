package assn2.beans;

import java.io.Serializable;

public class RoomBean  implements Serializable {
	int roomid;
	int roomno;
	int roomtypeid;
	String condition;
	int hotelid;
	public RoomBean(int roomid, int roomno, int roomtypeid, String condition,
			int hotelid) {
		super();
		this.roomid = roomid;
		this.roomno = roomno;
		this.roomtypeid = roomtypeid;
		this.condition = condition;
		this.hotelid = hotelid;
	}
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}
	public int getRoomtypeid() {
		return roomtypeid;
	}
	public void setRoomtypeid(int roomtypeid) {
		this.roomtypeid = roomtypeid;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getHotelid() {
		return hotelid;
	}
	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}
	@Override
	public String toString() {
		return "RoomBean [roomid=" + roomid + ", roomno=" + roomno
				+ ", roomtypeid=" + roomtypeid + ", condition=" + condition
				+ ", hotelid=" + hotelid + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + hotelid;
		result = prime * result + roomid;
		result = prime * result + roomno;
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
		RoomBean other = (RoomBean) obj;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (hotelid != other.hotelid)
			return false;
		if (roomid != other.roomid)
			return false;
		if (roomno != other.roomno)
			return false;
		if (roomtypeid != other.roomtypeid)
			return false;
		return true;
	}
	
	
}
