package com.spring.jpa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpa.model.User;
import com.spring.jpa.repository.UserRepository;
import com.spring.jpa.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

}
