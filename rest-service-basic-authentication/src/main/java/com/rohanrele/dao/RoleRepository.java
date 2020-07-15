package com.rohanrele.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rohanrele.entity.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

}
