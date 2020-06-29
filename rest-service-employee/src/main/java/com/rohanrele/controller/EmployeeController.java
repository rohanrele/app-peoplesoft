package com.rohanrele.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rohanrele.bo.CityBO;
import com.rohanrele.bo.CountryBO;
import com.rohanrele.bo.EmployeeBO;
import com.rohanrele.bo.ProvinceBO;
import com.rohanrele.dao.EmployeeRepository;
import com.rohanrele.entity.Employee;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Value("url.RestServiceGeographyApplication")
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
			// local variables
			Map<String, Integer> uriVariables = new HashMap<String, Integer>();

			employee = employeeIterator.next();
			employeeBO = new EmployeeBO();
			employeeBO.setId(employee.getId());
			employeeBO.setFirstName(employee.getFirstName());
			employeeBO.setLastName(employee.getLastName());
			employeeBO.setEmailId(employee.getEmailId());

			// make call to geography rest service to get country name
			employeeBO.setCountryId(employee.getCountryId());
			uriVariables.put("id", employee.getCountryId());
			CountryBO countryBO = new RestTemplate().getForObject(urlRestServiceGeographyApplication + "/country/{id}", CountryBO.class,
					uriVariables);
			if (countryBO != null) {
				employeeBO.setCountryName(countryBO.getName());
			} else {
				employeeBO.setCountryName("INVALID");
			}

			// make call to geography rest service to get province name
			employeeBO.setProvinceId(employee.getProvinceId());
			uriVariables.put("id", employee.getProvinceId());
			ProvinceBO provinceBO = new RestTemplate().getForObject(urlRestServiceGeographyApplication + "/province/{id}",
					ProvinceBO.class, uriVariables);
			if (provinceBO != null) {
				employeeBO.setProvinceName(provinceBO.getName());
			} else {
				employeeBO.setProvinceName("INVALID");
			}

			// make call to geography rest service to get city name
			employeeBO.setCityId(employee.getCityId());
			uriVariables.put("id", employee.getCityId());
			CityBO cityBO = new RestTemplate().getForObject(urlRestServiceGeographyApplication +  "/city/{id}", CityBO.class,
					uriVariables);
			if (cityBO != null) {
				employeeBO.setCityName(cityBO.getName());
			} else {
				employeeBO.setCityName("INVALID");
			}

			employeeBOs.add(employeeBO);
		}

		return employeeBOs;
	}
}
