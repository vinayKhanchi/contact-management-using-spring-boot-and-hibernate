package com.example.demo.contact.services;

import java.util.List;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.contact.model.Tag;
import com.example.demo.contact.model.User;
import com.example.demo.contact.payloads.UserDto;
import com.example.demo.contact.repositories.UserRepo;

@Service
public interface UserService {
	
	 List<UserDto> getAll() throws Exception;
	 UserDto getById( Long id , String email , String mobile) throws Exception;
	 UserDto deleteById( Long id) throws Exception;
	 UserDto createUser(UserDto user) throws Exception;
	 UserDto updateUser(UserDto user , Long id) throws Exception;
	 UserDto getByEmail(String email) throws Exception ;
	 UserDto getByMobile(String mobile) throws Exception;
	 Set<String> getAllTag(Long id) throws Exception;
	 Set<String> getCustomFieldById(Long id) throws Exception;
	 
	
}
