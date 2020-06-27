package com.rohanrele.clrunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rohanrele.dao.CountryRepository;
import com.rohanrele.entity.City;
import com.rohanrele.entity.Country;
import com.rohanrele.entity.Province;

@Configuration
public class DbDataPopulateCLRunner implements CommandLineRunner {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public void run(String... args) throws Exception {
		// local variables
		long countriesCount;

		// check if country table has any data
		countriesCount = countryRepository.count();

		// if country table has no data then populate data
		if (countriesCount == 0) {
			// save to db
			countryRepository.saveAll(populateCountries());
		} else { // else delete data and then populate data
			countryRepository.deleteAll();			
			countryRepository.saveAll(populateCountries());
		}
	}

	private List<Country> populateCountries() {
		// local variables
		List<Country> countries = new ArrayList<Country>();
		Country country = null;
		List<Province> provinces = null;

		// populate country 1
		country = new Country();
		country.setName("India");
		provinces = new ArrayList<Province>();
		provinces.add(populateProvince("Maharashtra", country, Arrays.asList("Mumbai", "Pune")));
		provinces.add(populateProvince("Karnataka", country, Arrays.asList("Bangalore", "Mangalore")));
		country.setProvinces(provinces);
		countries.add(country);

		// populate country 2
		country = new Country();
		country.setName("Canada");
		provinces = new ArrayList<Province>();
		provinces.add(populateProvince("Ontario", country, Arrays.asList("Toronto", "Ottawa")));
		provinces.add(populateProvince("British Columbia", country, Arrays.asList("Vancouver", "Victoria")));
		country.setProvinces(provinces);
		countries.add(country);

		return countries;
	}

	private List<City> populateCities(List<String> cityNames, Province province) {
		// local variables
		List<City> cities = new ArrayList<City>();
		City city = null;

		for (String cityName : cityNames) {
			city = new City();
			city.setName(cityName);
			city.setProvince(province);
			cities.add(city);
		}
		return cities;
	}

	private Province populateProvince(String provinceName, Country country, List<String> cityNames) {
		// local variables
		Province province = null;

		province = new Province();
		province.setName(provinceName);
		province.setCountry(country);
		province.setCities(populateCities(cityNames, province));

		return province;
	}
}
