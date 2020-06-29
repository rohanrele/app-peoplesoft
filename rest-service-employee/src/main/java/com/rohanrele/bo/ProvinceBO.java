package com.rohanrele.bo;

import java.util.ArrayList;
import java.util.List;

public class ProvinceBO {

	private int id;

	private String name;

	private List<CityBO> cities = new ArrayList<CityBO>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CityBO> getCities() {
		return cities;
	}

	public void setCities(List<CityBO> cities) {
		this.cities = cities;
	}
}
