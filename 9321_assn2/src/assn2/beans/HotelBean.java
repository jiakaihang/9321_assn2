package assn2.beans;

import java.io.Serializable;

public class HotelBean implements Serializable {
	int hotlid;
	String name;
	String city;
	int ownerid;
	int managerid;
	int phoneno;
	String address;
	
	public HotelBean(int hotlid, String name, String city, int ownerid, int managerid,
			int phoneno, String address) {
		super();
		this.hotlid = hotlid;
		this.name = name;
		this.city = city;
		this.ownerid = ownerid;
		this.managerid = managerid;
		this.phoneno = phoneno;
		this.address = address;
	}

	public int getHotlid() {
		return hotlid;
	}

	public void setHotlid(int hotlid) {
		this.hotlid = hotlid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public int getManagerid() {
		return managerid;
	}

	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}

	public int getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(int phoneno) {
		this.phoneno = phoneno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Hotel [hotlid=" + hotlid + ", city=" + city + ", ownerid="
				+ ownerid + ", managerid=" + managerid + ", phoneno=" + phoneno
				+ ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + hotlid;
		result = prime * result + managerid;
		result = prime * result + ownerid;
		result = prime * result + phoneno;
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
		HotelBean other = (HotelBean) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (hotlid != other.hotlid)
			return false;
		if (managerid != other.managerid)
			return false;
		if (ownerid != other.ownerid)
			return false;
		if (phoneno != other.phoneno)
			return false;
		return true;
	}
	
	
}
