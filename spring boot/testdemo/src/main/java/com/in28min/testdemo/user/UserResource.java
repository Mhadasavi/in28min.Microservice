package com.in28min.testdemo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
	public EntityModel<User> getUser(@PathVariable int id) {
		User user=service.get(id);
		if(user==null) throw new UserNotFoundException("id"+id);
		//Hateoas
		EntityModel<User> resource=EntityModel.of(user);
		 Link link= WebMvcLinkBuilder.linkTo(
				methodOn(this.getClass()).retreiveAllUser()).withRel("all-users");
		resource.add(link);
		return resource;
		}
	
	@PostMapping("/user")
	public ResponseEntity<User> CreateUser(@Valid @RequestBody User user) {
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
