package com.rohanrele.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rohanrele.bo.CityBO;
import com.rohanrele.bo.CountryBO;
import com.rohanrele.bo.ProvinceBO;
import com.rohanrele.dao.CityRepository;
import com.rohanrele.dao.CountryRepository;
import com.rohanrele.dao.ProvinceRepository;
import com.rohanrele.entity.City;
import com.rohanrele.entity.Country;
import com.rohanrele.entity.Province;

@RestController
public class GeographyController {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	ProvinceRepository provinceRepository;

	@Autowired
	CityRepository cityRepository;

	@GetMapping(path = "/country")
	public List<CountryBO> getAllCountries() {
		// local variables
		List<CountryBO> countryBOs = null;
		Iterator<Country> countiesIterator = null;
		CountryBO countryBO = null;
		Country country = null;

		// retrieve all countries form database
		countiesIterator = countryRepository.findAll().iterator();

		// return countries bo
		countryBOs = new ArrayList<CountryBO>();
		while (countiesIterator.hasNext()) {
			country = countiesIterator.next();
			countryBO = new CountryBO();
			countryBO.setId(country.getId());
			countryBO.setName(country.getName());
			countryBOs.add(countryBO);
		}
		return countryBOs;
	}

	@GetMapping(path = "/country/{id}/province")
	public List<ProvinceBO> getProvincesByCountryId(@PathVariable(name = "id") int countryId) {
		// local variables
		List<ProvinceBO> provinceBOs = null;
		List<Province> provinces = null;
		ProvinceBO provinceBO = null;

		// retrieve provinces by country id
		provinces = provinceRepository.findByCountryId(countryId);

		// return provinces bo
		provinceBOs = new ArrayList<ProvinceBO>();
		for (Province province : provinces) {
			provinceBO = new ProvinceBO();
			provinceBO.setId(province.getId());
			provinceBO.setName(province.getName());
			provinceBOs.add(provinceBO);
		}
		return provinceBOs;
	}

	@GetMapping(path = "/province/{id}/city")
	public List<CityBO> getCitiesByProvinceId(@PathVariable(name = "id") int provinceId) {
		// local variables
		List<CityBO> cityBOs = null;
		List<City> cities = null;
		CityBO cityBO = null;

		// retrieve cities by province id
		cities = cityRepository.findByProvinceId(provinceId);

		// return provinces bo
		cityBOs = new ArrayList<CityBO>();
		for (City city : cities) {
			cityBO = new CityBO();
			cityBO.setId(city.getId());
			cityBO.setName(city.getName());
			cityBOs.add(cityBO);
		}
		return cityBOs;
	}
}
