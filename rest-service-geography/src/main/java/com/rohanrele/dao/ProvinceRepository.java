package com.rohanrele.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rohanrele.entity.Province;

public interface ProvinceRepository extends PagingAndSortingRepository<Province, Integer> {
	List<Province> findByCountryId(int countryId);
}
