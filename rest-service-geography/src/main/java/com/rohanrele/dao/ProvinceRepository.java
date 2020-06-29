package com.rohanrele.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rohanrele.entity.Province;

@Repository
public interface ProvinceRepository extends PagingAndSortingRepository<Province, Integer> {
	List<Province> findByCountryId(int countryId);
}
