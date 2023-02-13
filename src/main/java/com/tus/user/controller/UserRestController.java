package com.tus.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tus.user.model.User;
import com.tus.user.repo.UserRepository;

@RestController
@RequestMapping("/userapi")
public class UserRestController {

	@Autowired
	private UserRepository repo;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) {

		return repo.save(user);

	}

	@GetMapping("/users")
	public List<User> get() {

		return repo.findAll();

	}

}
