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
import com.tus.user.response.ErrorResponse;
import com.tus.user.response.Response;
import com.tus.user.response.ResponseCode;

@RestController
@RequestMapping("/userapi")
public class UserRestController {

	@Autowired
	private UserRepository repo;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public Response addUser(@RequestBody User user) {
		User existingUser = repo.findUser(user.getPhone());

		return saveUser(user, existingUser);

	}

	@GetMapping("/users")
	public List<User> get() {

		return repo.findAll();

	}

	private Response saveUser(User user, User existingUser) {
		ResponseCode responseCode;
		String responseMessage = "";

		if (existingUser == null) {
			try {
				repo.save(user);
				responseCode = ResponseCode.SUCCESS;
				responseMessage = "User " + user.getFirstName() + " " + user.getLastName() + " registered.";
				return new Response(responseCode, responseMessage);

			} catch (Exception exc) {
				responseCode = ResponseCode.FAILED;
				responseMessage = "User " + user.getFirstName() + " " + user.getLastName() + " cannot be registered.";
				return new ErrorResponse(responseCode, responseMessage, exc.getCause().getCause().getMessage());
			}

		} else {
			responseCode = ResponseCode.FAILED;
			responseMessage = "User " + user.getFirstName() + " " + user.getLastName() + " cannot be registered.";
			return new ErrorResponse(responseCode, responseMessage,
					"User with phone number " + user.getPhone() + " already exists.");
		}

	}

}
