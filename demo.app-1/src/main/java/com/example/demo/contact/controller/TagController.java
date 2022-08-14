package com.example.demo.contact.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.contact.model.User;
import com.example.demo.contact.payloads.UserDto;
import com.example.demo.contact.services.TagService;

@RestController
@RequestMapping("/api/")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("/tag/{name}")
	public ResponseEntity<?> getUserFromTag(@PathVariable String name) throws Exception{
		try {
			Set<UserDto> upd= this.tagService.getUTag(name);
			return new ResponseEntity(upd, HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
}
