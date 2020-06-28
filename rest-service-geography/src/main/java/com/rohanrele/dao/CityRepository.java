package com.rohanrele.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rohanrele.entity.City;

public interface CityRepository extends PagingAndSortingRepository<City, Integer> {
	List<City> findByProvinceId(int provinceId);
}
