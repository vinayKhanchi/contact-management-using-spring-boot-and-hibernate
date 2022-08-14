package com.example.demo.contact.services.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.demo.contact.model.CustomField;
import com.example.demo.contact.model.Tag;
import com.example.demo.contact.model.User;
import com.example.demo.contact.payloads.TagDto;
import com.example.demo.contact.payloads.UserDto;
import com.example.demo.contact.repositories.CustomFieldRepo;
import com.example.demo.contact.repositories.TagRepo;
import com.example.demo.contact.repositories.UserRepo;

import com.example.demo.contact.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CustomFieldRepo crepo;
	
	@Autowired
	private TagRepo tagRepo;
	
	
	
	@Override
	public List<UserDto> getAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<User> ls= this.userRepo.findAll();
			if(ls.size()==0 || ls==null)throw new NotFoundException();
			List<UserDto> rtn= new ArrayList<>();
			for(User x : ls) {
				UserDto ud= UserToDto(x);
				rtn.add(ud);
			}
			return rtn;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public UserDto getById(Long id , String email , String mobile) throws Exception {
		// TODO Auto-generated method stub
		User temp= new User();
		
			Optional<User> temp1 = this.userRepo.findById(id);
			
			if(temp1.isPresent())return UserToDto(temp1.get());
			
	        if(!temp1.isPresent()) {
	        	temp= this.userRepo.getByEmail(email);
	        	
	        }
	        if(!temp1.isPresent() && temp==null) {
	        	temp= this.userRepo.getByMobileNo(mobile);
	        }
	        if(!temp1.isPresent() && temp==null) {
	        	throw new NotFoundException();
	        }
		
        
        return UserToDto(temp);
	}

	@Override
	@Transactional
	public UserDto deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> tempopt= this.userRepo.findById(id);
		
		if(!tempopt.isPresent()) {throw new NotFoundException();}
		try {
			
				this.userRepo.deleteById(id);
				this.tagRepo.deleteByUserId(id);
				this.crepo.deleteByUserId(id);
				return UserToDto(tempopt.get());
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public UserDto createUser(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		String email= user.getEmail();
		String mobile= user.getMobileNo();
		if(email==null || email.length()==0  ) {
			throw new Exception("Email can not be null or empty");
		}
		
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		if(pattern.matcher(email).matches()==false) {
			throw new Exception("email is invalid");
		}
		
		
		if(mobile==null || mobile.length()==0) {
			throw new Exception("mobile can not be null or empty");
		}

		Pattern pattern2 = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		if(pattern2.matcher(mobile).matches()==false) {
			throw new Exception("mobile no is invalid");
		}
		
		
		List<User> all = this.userRepo.findAll();
		for(User x: all) {
			if(user.getEmail().equals(x.getEmail())) {
				throw new Exception("Email already exists");
			}
		}
		for(User x: all) {
			if(user.getMobileNo().equals(x.getMobileNo())) {
				throw new Exception("Mobile No already exists");
			}
		}
		User temp= DtoToUser(user);
		temp = this.userRepo.saveAndFlush(temp);
		
		for(TagDto x: user.getTags()) {
			Tag tf = new Tag(x.getTagName(),temp.getId());
			this.tagRepo.saveAndFlush(tf);
		}
		
		for(CustomField x: user.getCustomFields()) {
			CustomField cf= new CustomField(x.getCustomName(), x.getCustomValue() , temp.getId() );
			this.crepo.saveAndFlush(cf);
		}
		
		
		return user;
		
		
	}

	@Override
	public UserDto updateUser(UserDto user, Long id) throws Exception {
		// TODO Auto-generated method stub

		Optional<User> tempOptional= this.userRepo.findById(id);
		
		
		if(!tempOptional.isPresent()) {
			throw new NotFoundException();
		}
		
		String email= user.getEmail();
		String mobile= user.getMobileNo();
		if(email==null || email.length()==0  ) {
			throw new Exception("Email can not be null or empty");
		}
		
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		if(pattern.matcher(email).matches()==false) {
			throw new Exception("Email is invalid");
		}
		
		
		if(mobile==null || mobile.length()==0) {
			throw new Exception("mobile can not be null or empty");
		}

		
		Pattern pattern2 = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		if(pattern2.matcher(mobile).matches()==false) {
			throw new Exception("mobile no is invalid");
		}
		User temp= tempOptional.get();

		temp.setEmail(user.getEmail());
		temp.setFirstName(user.getFirstName());
		temp.setLastName(user.getLastName());
		temp.setMobileNo(user.getMobileNo());
		
		
	
		this.tagRepo.deleteByUserId(id);
		this.crepo.deleteByUserId(id);
		this.userRepo.save(temp);
		
		for(CustomField x: user.getCustomFields()) {
			CustomField cf= new CustomField(x.getCustomName(), x.getCustomValue() , id );
			this.crepo.save(cf);
		}
		
		for(TagDto x: user.getTags()) {
			Tag tf = new Tag(x.getTagName(),temp.getId());
			this.tagRepo.save(tf);
		}
		
		return user;
	
		
	}

	@Override
	public UserDto getByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		if(email==null || email.length()==0  ) {
			throw new Exception("Email can not be null or empty");
		}
		
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		if(pattern.matcher(email).matches()==false) {
			throw new Exception("Email is invalid");
		}
		
		User temp=  this.userRepo.getByEmail(email);
		if(temp==null) throw new NotFoundException();
		
		return UserToDto(temp);
	}

	@Override
	public UserDto getByMobile(String mobile) throws Exception {
		// TODO Auto-generated method stub
		if(mobile==null || mobile.length()==0) {
			throw new Exception("mobile can not be null or empty");
		}

		Pattern pattern2 = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		if(pattern2.matcher(mobile).matches()==false) {
			throw new Exception("mobile no is invalid");
		}
		
		
		User temp =this.userRepo.getByMobileNo(mobile);	
		if(temp==null) throw new NotFoundException();
	
		return UserToDto(temp);
		
	}

	@Override
	public Set<String> getAllTag(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> temp= this.userRepo.findById(id);
		if(!temp.isPresent())throw new NotFoundException();
		
		List<Tag> all = this.tagRepo.getByUserId(id);
		
		Set<String> tf = new HashSet<>();
		for(Tag x: all) {
			tf.add(x.getTagName());
		}
		return tf;
		
	}
	
	

	@Override
	public Set<String> getCustomFieldById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> temp= userRepo.findById(id);
		if(!temp.isPresent())throw new NotFoundException();
		
		List<CustomField> ls= this.crepo.getByUserId(id);
		Set<String> cfs= new HashSet<>();
		for(CustomField x: ls) {
			cfs.add(x.getCustomName());
		}
		return cfs;
				
	}
	
	public User DtoToUser(UserDto userDto) {
		User temp= new User();
		
		temp.setEmail(userDto.getEmail());
		temp.setFirstName(userDto.getFirstName());
		temp.setLastName(userDto.getLastName());
		temp.setMobileNo(userDto.getMobileNo());

		return temp;
	}
	public UserDto UserToDto(User temp) {
		UserDto ud= new UserDto();
		ud.setFirstName(temp.getFirstName());
		ud.setLastName(temp.getLastName());
		ud.setEmail(temp.getEmail());
		ud.setMobileNo(temp.getMobileNo());
		
		List<CustomField> cf = this.crepo.getByUserId(temp.getId());
		ud.setCustomFields(cf);
		
		List<Tag> tf = this.tagRepo.getByUserId(temp.getId());
		Set<TagDto> stf = new HashSet<>();
		for(Tag x : tf) {
			TagDto y = new TagDto();
			y.setTagName(x.getTagName());
			stf.add(y);
		}
		
		ud.setTags(stf);		
		return ud;
	}

	

}
