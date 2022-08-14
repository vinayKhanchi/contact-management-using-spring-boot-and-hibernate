package com.example.demo.contact.serviceimpl.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.contact.model.CustomField;
import com.example.demo.contact.model.Tag;
import com.example.demo.contact.model.User;
import com.example.demo.contact.payloads.TagDto;
import com.example.demo.contact.payloads.UserDto;
import com.example.demo.contact.repositories.CustomFieldRepo;
import com.example.demo.contact.repositories.TagRepo;
import com.example.demo.contact.repositories.UserRepo;
import com.example.demo.contact.services.serviceImp.TagServiceImpl;

public class TestTagServiceImpl {
	
	@InjectMocks
	TagServiceImpl tagService;
	
	@Mock
	UserRepo userRepo;
	
	@Mock
	TagRepo tagRepo;
	
	@Mock
	CustomFieldRepo crepo;
	
	@Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getTagTest() throws Exception {
		UserDto expectedUser = prepareUserMock();
		User expectedUser2 = DtoToUser(expectedUser);
		List<Long> uid= new ArrayList<>();
		uid.add(1L);
		Mockito.when(tagRepo.findByTagName("Yellow")).thenReturn(uid);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(expectedUser2));
		Set<UserDto> exps= new HashSet<>();
		exps.add(expectedUser);
		
		assertEquals(exps, tagService.getUTag("Yellow"));
	}
	
	@Test(expected= NullPointerException.class)
	public void getTagTestException() throws Exception {
		tagService.getUTag(null);
	}
	
	private UserDto prepareUserMock() {
		UserDto user= new UserDto();
		user.setFirstName("Mock");
		user.setLastName("Singh");
		user.setEmail("Mock.singh@mocker.com");
		user.setMobileNo("9018307705");
	
		
		
		HashSet<TagDto> hs= new HashSet<>();
		user.setTags(hs);
		List<CustomField> ls= new ArrayList<>();
		user.setCustomFields(ls);
		
		return user;
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
		ud.setCustomFields(new ArrayList<>());

		return ud;
	}
}
