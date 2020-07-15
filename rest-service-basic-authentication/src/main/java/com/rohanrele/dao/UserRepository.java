package com.rohanrele.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rohanrele.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
