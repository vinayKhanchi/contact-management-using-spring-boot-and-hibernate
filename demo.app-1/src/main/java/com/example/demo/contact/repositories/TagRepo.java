package com.example.demo.contact.repositories;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.contact.model.Tag;
import com.example.demo.contact.model.User;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long>{
	
    @Modifying
    @Transactional
    @Query(value="delete from contacts.tag as t where t.t_id=:id" , nativeQuery=true)
	void deleteById(Long id);
	
    @Modifying
    @Transactional
    @Query(value="delete from contacts.tag as t where t.u_id=:id" , nativeQuery=true)
	void deleteByUserId(Long id );
    
    @Query( value = "select * from contacts.tag as t where t.u_id=:id" , nativeQuery =true)
    List<Tag> getByUserId(Long id);

    @Query(value="select t.t_id from contacts.tag as t where t.tag_name=:tName" , nativeQuery=true)
	List<Long> findByTagName(String tName);
	
}
