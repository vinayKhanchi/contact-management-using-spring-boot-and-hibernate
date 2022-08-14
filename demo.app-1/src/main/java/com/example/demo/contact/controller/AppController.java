package com.example.demo.contact.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.contact.exceptions.NoCustomFieldFound;
import com.example.demo.contact.exceptions.NoTagFound;
import com.example.demo.contact.exceptions.UserNotFound;
import com.example.demo.contact.model.Tag;
import com.example.demo.contact.model.User;
import com.example.demo.contact.payloads.UserDto;
import com.example.demo.contact.payloads.utilDto;
import com.example.demo.contact.services.UserService;

@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> GetAllUser() throws Exception {
		
			List<UserDto> all = this.userService.getAll();
			return new ResponseEntity(all, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> GetUserById(@PathVariable("userId") Long userId , @RequestBody utilDto util ) throws Exception  {
		try {
			UserDto temp= new UserDto();
			temp = this.userService.getById(userId,util.getEmail(),util.getMobile());
			return new ResponseEntity(temp, HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@RequestMapping(path="/user/{userId}" , method= RequestMethod.PUT)
	public ResponseEntity<User> UpdateUser(@RequestBody UserDto user , @PathVariable Long userId ) throws Exception {
		try {
			UserDto gtu = this.userService.updateUser(user, userId);
			return new ResponseEntity(gtu , HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		
	}

	@PostMapping("/user"  )
	@Transactional
	public ResponseEntity<?> AddUser( @RequestBody UserDto user) throws Exception  {
	
		try {
			UserDto getu = this.userService.createUser(user);
			return new ResponseEntity(getu , HttpStatus.CREATED);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() );
		}

	}
	
	@RequestMapping(path="/user/{userId}" , method= RequestMethod.DELETE)
	public ResponseEntity<?> DeleteUserById(@PathVariable Long userId) throws Exception {
		
		try {
		this.userService.deleteById(userId);
		return ResponseEntity.ok(Map.of("message","user deleted successfully"));
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		

	}
	
	
	
	@GetMapping("/user/tag/{id}")
	public ResponseEntity<?> getMappedTags(@PathVariable Long id) throws Exception{
		try {
			Set<String> up= this.userService.getAllTag(id);
			
			return new ResponseEntity(up , HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			throw new NoTagFound();
		}
		
	}
	
	@GetMapping("/user/custom/{id}")
	public ResponseEntity<?> getCustomField(@PathVariable Long id) throws Exception{
		try {
		Set<String> up= this.userService.getCustomFieldById(id);
		
		return new ResponseEntity(up , HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			throw new NoCustomFieldFound();
		}
	
	}
	
	
	
	
	
	
}