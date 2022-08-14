package com.example.demo.contact.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.contact.model.Tag;
import com.example.demo.contact.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
	
	
	
	public User getByEmail(String email);
	
	
	public User getByMobileNo(String mobile);
	
	
	

}
