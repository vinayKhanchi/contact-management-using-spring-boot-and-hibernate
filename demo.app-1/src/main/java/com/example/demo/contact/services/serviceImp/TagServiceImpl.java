package com.example.demo.contact.services.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.contact.model.CustomField;
import com.example.demo.contact.model.Tag;
import com.example.demo.contact.model.User;
import com.example.demo.contact.payloads.TagDto;
import com.example.demo.contact.payloads.UserDto;
import com.example.demo.contact.repositories.CustomFieldRepo;
import com.example.demo.contact.repositories.TagRepo;
import com.example.demo.contact.repositories.UserRepo;
import com.example.demo.contact.services.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepo tagRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CustomFieldRepo crepo;
	
	
	@Override
	public Set<UserDto> getUTag(String tName) throws Exception{
		// TODO Auto-generated method stub
		if(tName==null || tName.length()==0) throw new NullPointerException("enter valid tag name");
		
		List<Long> userid= this.tagRepo.findByTagName(tName);

		Set<UserDto> userall= new HashSet<>();
		for(Long x : userid) {
			User temp= this.userRepo.findById(x).get();
			UserDto ud= UserToDto(temp);
			if(!userall.contains(ud)) {
				userall.add(ud);
			}
		}
		return userall;
		
		
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
