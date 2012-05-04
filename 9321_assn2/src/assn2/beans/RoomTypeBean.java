package assn2.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RoomTypeBean  implements Serializable {
	int roomtypeid;
	int hotelid;
	String type;
	double price;
	double discountrate;
	Timestamp discountfrom;
	Timestamp discountto;  //TODO: maybe not right type
	String description;
	
	public RoomTypeBean(int roomtypeid, int hotelid, String type, double price,
			double discountrate, Timestamp discountfrom, Timestamp discountto,
			String description) {
		super();
		this.roomtypeid = roomtypeid;
		this.hotelid = hotelid;
		this.type = type;
		this.price = price;
		this.discountrate = discountrate;
		this.discountfrom = discountfrom;
		this.discountto = discountto;
		this.description = description;
	}

	public int getRoomtypeid() {
		return roomtypeid;
	}

	public void setRoomtypeid(int roomtypeid) {
		this.roomtypeid = roomtypeid;
	}

	public int getHotelid() {
		return hotelid;
	}

	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountrate() {
		return discountrate;
	}

	public void setDiscountrate(double discountrate) {
		this.discountrate = discountrate;
	}

	public Timestamp getDiscountfrom() {
		return discountfrom;
	}

	public void setDiscountfrom(Timestamp discountfrom) {
		this.discountfrom = discountfrom;
	}

	public Timestamp getDiscountto() {
		return discountto;
	}

	public void setDiscountto(Timestamp discountto) {
		this.discountto = discountto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoomTypeBean [roomtypeid=" + roomtypeid + ", hotelid="
				+ hotelid + ", type=" + type + ", price=" + price
				+ ", discountrate=" + discountrate + ", discountfrom="
				+ discountfrom + ", discountto=" + discountto
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((discountfrom == null) ? 0 : discountfrom.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discountrate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((discountto == null) ? 0 : discountto.hashCode());
		result = prime * result + hotelid;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + roomtypeid;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		RoomTypeBean other = (RoomTypeBean) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discountfrom == null) {
			if (other.discountfrom != null)
				return false;
		} else if (!discountfrom.equals(other.discountfrom))
			return false;
		if (Double.doubleToLongBits(discountrate) != Double
				.doubleToLongBits(other.discountrate))
			return false;
		if (discountto == null) {
			if (other.discountto != null)
				return false;
		} else if (!discountto.equals(other.discountto))
			return false;
		if (hotelid != other.hotelid)
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (roomtypeid != other.roomtypeid)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
