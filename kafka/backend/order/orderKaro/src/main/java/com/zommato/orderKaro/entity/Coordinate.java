package com.zommato.orderKaro.entity;

import java.util.Objects;

public class Coordinate {
	
	private double latitude;
	private double longitude;
	public Coordinate() {
		super();
	}
	public Coordinate(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}
	
	

}
