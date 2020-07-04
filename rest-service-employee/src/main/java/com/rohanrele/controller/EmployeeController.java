package com.rohanrele.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rohanrele.bo.CityBO;
import com.rohanrele.bo.CountryBO;
import com.rohanrele.bo.EmployeeBO;
import com.rohanrele.bo.ProvinceBO;
import com.rohanrele.dao.EmployeeRepository;
import com.rohanrele.entity.Employee;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;

	@Value("${url.RestServiceGeographyApplication}")
	String urlRestServiceGeographyApplication;

	@PostMapping(path = "/employee")
	public EmployeeBO createEmployee(@RequestBody EmployeeBO employeeBO) {
		// local variables
		Employee employee = null;

		if (employeeBO != null) {
			// construct entity object and save to db
			employee = new Employee();
			employee.setFirstName(employeeBO.getFirstName());
			employee.setLastName(employeeBO.getLastName());
			employee.setEmailId(employeeBO.getEmailId());
			employee.setCountryId(employeeBO.getCountryId());
			employee.setProvinceId(employeeBO.getProvinceId());
			employee.setCityId(employeeBO.getCityId());
			employee = employeeRepository.save(employee);

			// populate employee bo and send response
			employeeBO = new EmployeeBO();
			employeeBO.setId(employee.getId());
			employeeBO.setFirstName(employee.getFirstName());
			employeeBO.setLastName(employee.getLastName());
			employeeBO.setEmailId(employee.getEmailId());
			employeeBO.setCountryId(employee.getCountryId());
			employeeBO.setCountryName("PENDING");
			employeeBO.setProvinceId(employee.getProvinceId());
			employeeBO.setProvinceName("PENDING");
			employeeBO.setCityId(employee.getCityId());
			employeeBO.setCityName("PENDING");
		}
		return employeeBO;
	}

	@GetMapping(path = "/employee")
	public List<EmployeeBO> getAllEmployees() {
		// local variables
		List<EmployeeBO> employeeBOs = null;
		Iterator<Employee> employeeIterator = null;
		Employee employee = null;
		EmployeeBO employeeBO = null;

		// retrieve all employees from database
		employeeIterator = employeeRepository.findAll().iterator();

		// return employee bo in response
		employeeBOs = new ArrayList<EmployeeBO>();
		while (employeeIterator.hasNext()) {
			employee = employeeIterator.next();
			employeeBO = new EmployeeBO();
			employeeBO.setId(employee.getId());
			employeeBO.setFirstName(employee.getFirstName());
			employeeBO.setLastName(employee.getLastName());
			employeeBO.setEmailId(employee.getEmailId());
			employeeBO.setCountryId(employee.getCountryId());
			employeeBO.setCountryName(getCountryName(employee.getCountryId()));
			employeeBO.setProvinceId(employee.getProvinceId());
			employeeBO.setProvinceName(getProvinceName(employee.getProvinceId()));
			employeeBO.setCityId(employee.getCityId());
			employeeBO.setCityName(getCityName(employee.getCityId()));
			employeeBOs.add(employeeBO);
		}
		return employeeBOs;
	}

	@GetMapping(path = "/employee/{id}")
	public EmployeeBO getEmployee(@PathVariable(name = "id") int employeeId) {
		// local variables
		Optional<Employee> employeeOptional = null;
		EmployeeBO employeeBO = null;
		Employee employee = null;

		// retrieve employee from database
		employeeOptional = employeeRepository.findById(employeeId);

		// return employee bo
		if (employeeOptional.isPresent()) {
			employee = employeeOptional.get();
			employeeBO = new EmployeeBO();
			employeeBO.setId(employee.getId());
			employeeBO.setFirstName(employee.getFirstName());
			employeeBO.setLastName(employee.getLastName());
			employeeBO.setEmailId(employee.getEmailId());
			employeeBO.setCountryId(employee.getCountryId());
			employeeBO.setCountryName(getCountryName(employee.getCountryId()));
			employeeBO.setProvinceId(employee.getProvinceId());
			employeeBO.setProvinceName(getProvinceName(employee.getProvinceId()));
			employeeBO.setCityId(employee.getCityId());
			employeeBO.setCityName(getCityName(employee.getCityId()));
		}
		return employeeBO;
	}

	@PutMapping(path = "/employee/{id}")
	public EmployeeBO updateEmployee(@RequestBody EmployeeBO employeeBO, @PathVariable(name = "id") int employeeId) {
		// local variables
		Employee employee = null;

		if (employeeBO != null) {
			// construct entity object and save to db
			employee = new Employee();
			employee.setId(employeeId);
			employee.setFirstName(employeeBO.getFirstName());
			employee.setLastName(employeeBO.getLastName());
			employee.setEmailId(employeeBO.getEmailId());
			employee.setCountryId(employeeBO.getCountryId());
			employee.setProvinceId(employeeBO.getProvinceId());
			employee.setCityId(employeeBO.getCityId());
			employee = employeeRepository.save(employee);

			// populate employee bo and send response
			employeeBO = new EmployeeBO();
			employeeBO.setId(employee.getId());
			employeeBO.setFirstName(employee.getFirstName());
			employeeBO.setLastName(employee.getLastName());
			employeeBO.setEmailId(employee.getEmailId());
			employeeBO.setCountryId(employee.getCountryId());
			employeeBO.setCountryName("PENDING");
			employeeBO.setProvinceId(employee.getProvinceId());
			employeeBO.setProvinceName("PENDING");
			employeeBO.setCityId(employee.getCityId());
			employeeBO.setCityName("PENDING");
		}
		return employeeBO;
	}

	private String getCountryName(int countryId) {
		CountryBO countryBO = getCountry(countryId);
		if (countryBO != null) {
			return countryBO.getName();
		} else {
			return "INVALID";
		}
	}

	private String getProvinceName(int provinceId) {
		ProvinceBO provinceBO = getProvince(provinceId);
		if (provinceBO != null) {
			return provinceBO.getName();
		} else {
			return "INVALID";
		}
	}

	private String getCityName(int cityId) {
		CityBO cityBO = getCity(cityId);
		if (cityBO != null) {
			return cityBO.getName();
		} else {
			return "INVALID";
		}
	}

	private CountryBO getCountry(int countryId) {
		// local variables
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();

		uriVariables.put("id", countryId);
		CountryBO countryBO = new RestTemplate().getForObject(urlRestServiceGeographyApplication + "/country/{id}",
				CountryBO.class, uriVariables);
		return countryBO;
	}

	private ProvinceBO getProvince(int provinceId) {
		// local variables
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();

		uriVariables.put("id", provinceId);
		ProvinceBO provinceBO = new RestTemplate().getForObject(urlRestServiceGeographyApplication + "/province/{id}",
				ProvinceBO.class, uriVariables);
		return provinceBO;
	}

	private CityBO getCity(int cityId) {
		// local variables
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();

		uriVariables.put("id", cityId);
		CityBO cityBO = new RestTemplate().getForObject(urlRestServiceGeographyApplication + "/city/{id}", CityBO.class,
				uriVariables);
		return cityBO;
	}
}
