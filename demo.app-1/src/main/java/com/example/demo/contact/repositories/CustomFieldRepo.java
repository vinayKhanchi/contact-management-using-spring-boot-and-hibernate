package com.example.demo.contact.repositories;





import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.contact.model.CustomField;

@Repository
public interface CustomFieldRepo extends JpaRepository<CustomField, Long>{

	@Modifying
	@Transactional
	void deleteByUserId(Long id);
	

	List<CustomField> getByUserId(Long id);

	
	
}
