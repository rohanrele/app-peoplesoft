package com.rohanrele.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rohanrele.entity.City;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Integer> {
	List<City> findByProvinceId(int provinceId);
}
