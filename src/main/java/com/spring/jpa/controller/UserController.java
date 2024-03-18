package com.spring.jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jpa.exception.ResourceNotFoundException;
import com.spring.jpa.model.User;
import com.spring.jpa.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getUsers() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails)
			throws ResourceNotFoundException {
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));
		user.setEmail(userDetails.getEmail());
		final User updatedUser = userService.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));

		userService.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
