package com.in28min.testdemo.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	@GetMapping("/user")
	public List<User> retreiveAllUser(){
		return service.getAll();
	}
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
		return service.get(id);
		}
	
	@PostMapping("/user")
	public ResponseEntity<User> CreateUser(@RequestBody User user) {
		User savedUser=service.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
												.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		User user=service.delete(id);
		if(user==null)
			throw new UserNotFoundException("id-"+id);
		service.delete(id);
	}
}
