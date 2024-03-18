package com.spring.jpa.service;

import java.util.List;
import java.util.Optional;

import com.spring.jpa.model.User;

public interface UserService {

	List<User> findAll();

	Optional<User> findById(Long instructorId);

	void delete(User instructor);

	User save(User instructor);

}
