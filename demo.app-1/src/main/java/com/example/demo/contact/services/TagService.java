package com.example.demo.contact.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.contact.model.User;
import com.example.demo.contact.payloads.UserDto;

@Service
public interface TagService {
	
	Set<UserDto> getUTag(String tName) throws Exception;
	
}
