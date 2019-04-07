package com.train.domain;

public class Route {

	private String originCity = null;
	private String destCity = null;
	private long distance = 0l;
	
	public Route(String originCity,String destCity,long distance){
		this.originCity = originCity;
		this.destCity = destCity;
		this.distance = distance;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}
	
}
