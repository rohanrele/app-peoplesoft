package com.rohanrele.bo;

import java.util.ArrayList;
import java.util.List;

public class CountryBO {

	private int id;

	private String name;

	private List<ProvinceBO> provinces = new ArrayList<ProvinceBO>();

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

	public List<ProvinceBO> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<ProvinceBO> provinces) {
		this.provinces = provinces;
	}
}
