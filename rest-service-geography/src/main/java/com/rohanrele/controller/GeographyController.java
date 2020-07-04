package com.rohanrele.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:4200")
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

		// retrieve all countries from database
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

		// retrieve provinces by country id from database
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

		// retrieve cities by province id from database
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

	@GetMapping(path = "/country/{id}")
	public CountryBO getCountry(@PathVariable(name = "id") int countryId) {
		// local variables
		CountryBO countryBO = null;
		Optional<Country> optionalCountry = null;
		Country country = null;

		// get country by country id from database
		optionalCountry = countryRepository.findById(countryId);

		// return country bo
		if (optionalCountry.isPresent()) {
			country = optionalCountry.get();
			countryBO = new CountryBO();
			countryBO.setId(country.getId());
			countryBO.setName(country.getName());
		}
		return countryBO;
	}
	
	@GetMapping(path = "/province/{id}")
	public ProvinceBO getProvince(@PathVariable(name = "id") int provinceId) {
		// local variables
		ProvinceBO provinceBO = null;
		Optional<Province> optionalProvince = null;
		Province province = null;

		// get province by province id from database
		optionalProvince = provinceRepository.findById(provinceId);

		// return province bo
		if (optionalProvince.isPresent()) {
			province = optionalProvince.get();
			provinceBO = new ProvinceBO();
			provinceBO.setId(province.getId());
			provinceBO.setName(province.getName());
		}
		return provinceBO;
	}
	
	@GetMapping(path = "/city/{id}")
	public CityBO getCity(@PathVariable(name = "id") int cityId) {
		// local variables
		CityBO cityBO = null;
		Optional<City> optionalCity = null;
		City city = null;

		// get city by city id from database
		optionalCity = cityRepository.findById(cityId);

		// return city bo
		if (optionalCity.isPresent()) {
			city = optionalCity.get();
			cityBO = new CityBO();
			cityBO.setId(city.getId());
			cityBO.setName(city.getName());
		}
		return cityBO;
	}
}
