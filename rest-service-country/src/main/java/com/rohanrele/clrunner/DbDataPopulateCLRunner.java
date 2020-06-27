package com.rohanrele.clrunner;

import java.util.ArrayList;
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

		// if country table has data break
		if (countriesCount == 0) {
			// local variables
			List<Country> countries = new ArrayList<Country>();
			Country country = null;
			Province province = null;
			City city = null;
			List<Province> provinces = null;
			List<City> cities = null;

			// populate data
			country = new Country();
			country.setName("India");
			provinces = new ArrayList<Province>();
			country.setProvinces(provinces);
			
			province = new Province();
			province.setName("Maharashtra");
			province.setCountry(country);
			cities = new ArrayList<City>();
			province.setCities(cities);
			provinces.add(province);

			city = new City();
			city.setName("Mumbai");
			city.setProvince(province);
			cities.add(city);

			// save to db
			countryRepository.saveAll(countries);
		} else {

		}
	}
}
