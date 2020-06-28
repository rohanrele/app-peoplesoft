package com.rohanrele.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohanrele.bo.CountryBO;
import com.rohanrele.dao.CountryRepository;
import com.rohanrele.entity.Country;

@RestController
public class CountryController {

	@Autowired
	CountryRepository countryRepository;

	@GetMapping(path = "/country")
	public List<CountryBO> getAllCountries() {
		// local variables
		List<CountryBO> countryBOs = null;
		Iterator<Country> countiesIterator = null;
		CountryBO countryBO = null;
		Country country = null;

		// retrieve all countries form database
		countiesIterator = countryRepository.findAll().iterator();

		// return countries
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
}
