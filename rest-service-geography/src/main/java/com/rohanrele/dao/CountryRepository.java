package com.rohanrele.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rohanrele.entity.Country;

public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

}
