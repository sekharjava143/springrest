package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.model.RestUser;
import com.spring.service.RestUserService;

@RestController
@SessionAttributes("userInSession")
public class HomeController {
	@Autowired
	RestUserService userService;
	
	@GetMapping(value="/")
	public String getMessage() {
		return "Welcome to Spring Rest World";
	}
	
	@ResponseBody
	@GetMapping(value="/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RestUser>> listAllUsers(HttpServletResponse res) {
		List<RestUser> users = userService.findAllRestUsers();
		
		if (users.isEmpty()) {
			// You many decide to return HttpStatus.NOT_FOUND
			return new ResponseEntity<List<RestUser>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<RestUser>>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestUser> getUser(@PathVariable("id") int id) {
		System.out.println("Fetching User with id " + id);
		RestUser user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<RestUser>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<RestUser>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody RestUser user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getUsername());

		userService.saveRestUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
