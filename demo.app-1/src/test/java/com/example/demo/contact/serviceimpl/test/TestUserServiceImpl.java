package com.example.demo.contact.serviceimpl.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.contact.exceptions.UserNotFound;
import com.example.demo.contact.model.CustomField;
import com.example.demo.contact.model.Tag;
import com.example.demo.contact.model.User;
import com.example.demo.contact.payloads.TagDto;
import com.example.demo.contact.payloads.UserDto;
import com.example.demo.contact.repositories.CustomFieldRepo;
import com.example.demo.contact.repositories.TagRepo;
import com.example.demo.contact.repositories.UserRepo;
import com.example.demo.contact.services.UserService;
import com.example.demo.contact.services.serviceImp.UserServiceImplementation;


public class TestUserServiceImpl {

	@InjectMocks
	private UserServiceImplementation userService;
	
	@Mock
	private UserRepo userRepo;
	
	@Mock
	private CustomFieldRepo crepo;
	
	@Mock
	private TagRepo tagRepo;
	
	@Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void getAllTest() throws Exception {
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		List<User> ls= new ArrayList<>();
		ls.add(expectedUser2);
		Mockito.when(userRepo.findAll()).thenReturn(ls);
		userService.getAll();
	}
	
	@Test(expected= Exception.class)
	public void getAllTestException() throws Exception {
		userService.getAll();
	}
	
	@Test
	public void getByIdTest() throws Exception {
		Long id= 1L;
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		List<Tag> xm = new ArrayList<>();
		Mockito.when(tagRepo.getByUserId(id)).thenReturn(xm);
		Mockito.when(crepo.getByUserId(id)).thenReturn(expectedUser.getCustomFields());
		Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(expectedUser2));
		UserDto actualUser = userService.getById(id,"xy@gmail.com","9018307705");
		assertEquals(expectedUser, actualUser);	
	}
	@Test
	public void getByIdTest_whenIdisNull() throws Exception {
		Long id= 1L;
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		List<Tag> xm = new ArrayList<>();
		Mockito.when(tagRepo.getByUserId(id)).thenReturn(xm);
		Mockito.when(crepo.getByUserId(id)).thenReturn(expectedUser.getCustomFields());
		Mockito.when(userRepo.getByEmail(expectedUser.getEmail())).thenReturn(expectedUser2);
		UserDto actualUser = userService.getById(null,"Mock.singh@mocker.com","9018307705");
		assertEquals(expectedUser, actualUser);	
	}
	@Test
	public void getByIdTest_whenIdnEmailisNull() throws Exception {
		Long id= 1L;
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		List<Tag> xm = new ArrayList<>();
		Mockito.when(tagRepo.getByUserId(id)).thenReturn(xm);
		Mockito.when(crepo.getByUserId(id)).thenReturn(expectedUser.getCustomFields());
		Mockito.when(userRepo.getByMobileNo(expectedUser.getMobileNo())).thenReturn(expectedUser2);
		UserDto actualUser = userService.getById(null,null,"9018307705");
		assertEquals(expectedUser, actualUser);	
	}
	@Test
	public void getByIdTest_whenIdnMobileisNull() throws Exception {
		Long id= 1L;
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		List<Tag> xm = new ArrayList<>();
		Mockito.when(tagRepo.getByUserId(id)).thenReturn(xm);
		Mockito.when(crepo.getByUserId(id)).thenReturn(expectedUser.getCustomFields());
		Mockito.when(userRepo.getByEmail(expectedUser.getEmail())).thenReturn(expectedUser2);
		UserDto actualUser = userService.getById(null,"Mock.singh@mocker.com",null);
		assertEquals(expectedUser, actualUser);	
	}
	@Test
	public void getByIdTest_whenMobilenEmailisNull() throws Exception {
		Long id= 1L;
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		List<Tag> xm = new ArrayList<>();
		Mockito.when(tagRepo.getByUserId(id)).thenReturn(xm);
		Mockito.when(crepo.getByUserId(id)).thenReturn(expectedUser.getCustomFields());
		Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(expectedUser2));
		UserDto actualUser = userService.getById(id,null,null);
		assertEquals(expectedUser, actualUser);	
	}

	@Test(expected = Exception.class)
	public void getByIdNFExceptionTest() throws Exception {
		userService.getById(null, null, null);
	}
	@Test
	public void deleteByIdTest() throws Exception {
		Long id=1L;
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
	    Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(expectedUser2));
		userService.deleteById(id);
	}
	
	@Test(expected= NotFoundException.class)
	public void deleteByIdNFExceptionTest() throws Exception {
		userService.deleteById(null);
	}
	@Test(expected= Exception.class)
	public void deleteByIdExceptionTest() throws Exception {
		userService.deleteById(null);
	}
	
	
	@Test(expected = Exception.class)
	public void CreateUserTest_whenEmailIsNull() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setEmail(null);
		userService.createUser(expectedUser);
	}
	
	@Test(expected = Exception.class)
	public void CreateUserTest_whenMobileIsNull() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setMobileNo(null);
		userService.createUser(expectedUser);
	}
	
	@Test(expected = Exception.class)
	public void CreateUserTest_whenEmailIsInvalid() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setEmail("xy");
		userService.createUser(expectedUser);
	}
	
	@Test(expected = Exception.class)
	public void CreateUserTest_whenMobileIsInvalid() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setMobileNo("90");
		userService.createUser(expectedUser);
	}
	
	@Test
	public void CreateUserTest() throws Exception {
		Long id=1L;
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		Mockito.when(userRepo.save(expectedUser2)).thenReturn(expectedUser2);
		assertEquals(expectedUser,userService.createUser(expectedUser));
	}
	
	@Test(expected = Exception.class)
	public void CreateUserTest_whenMobileExists() throws Exception {
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		expectedUser2.setEmail("vinay@gmail.com");
		List<User> ls= new ArrayList<>();
		ls.add(expectedUser2);
		Mockito.when(userRepo.findAll()).thenReturn(ls);
		userService.createUser(expectedUser);
	}
	
	@Test(expected = Exception.class)
	public void CreateUserTest_whenEmailExists() throws Exception {
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		List<User> ls= new ArrayList<>();
		ls.add(expectedUser2);
		Mockito.when(userRepo.findAll()).thenReturn(ls);
		userService.createUser(expectedUser);
	}
	@Test(expected= Exception.class)
	public void CreateUserTest_whenMobileSize() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setMobileNo("");
		userService.createUser(expectedUser);
	}
	@Test(expected= Exception.class)
	public void CreateUserTest_whenEmailSize() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setEmail("");
		userService.createUser(expectedUser);
	}
	
	@Test(expected = Exception.class)
	public void UpdateUserTest_whenEmailIsNull() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setEmail(null);
		User expectedUser2 = DtoToUser(expectedUser);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(expectedUser2));
		Mockito.when(userRepo.save(expectedUser2)).thenReturn(expectedUser2);
		userService.updateUser(expectedUser, 1L);
	}
	
	@Test(expected = Exception.class)
	public void UpdateUserTest_whenMobileIsNull() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setMobileNo(null);
		User expectedUser2 = DtoToUser(expectedUser);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(expectedUser2));
		Mockito.when(userRepo.save(expectedUser2)).thenReturn(expectedUser2);
		userService.updateUser(expectedUser, 1L);
	}
	
	@Test(expected = Exception.class)
	public void UpdateUserTest_whenEmailIsInvalid() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setEmail("xy");
		User expectedUser2 = DtoToUser(expectedUser);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(expectedUser2));
		Mockito.when(userRepo.save(expectedUser2)).thenReturn(expectedUser2);
		userService.updateUser(expectedUser, 1L);
	}
	
	@Test(expected = Exception.class)
	public void UpdateUserTest_whenMobileIsInvalid() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setMobileNo("90");
		User expectedUser2 = DtoToUser(expectedUser);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(expectedUser2));
		Mockito.when(userRepo.save(expectedUser2)).thenReturn(expectedUser2);
		userService.updateUser(expectedUser, 1L);
	}
	@Test(expected = Exception.class)
	public void UpdateUserTest_whenEmailSize() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setEmail("");
		User expectedUser2 = DtoToUser(expectedUser);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(expectedUser2));
		Mockito.when(userRepo.save(expectedUser2)).thenReturn(expectedUser2);
		userService.updateUser(expectedUser, 1L);
	}
	@Test(expected = Exception.class)
	public void UpdateUserTest_whenMobileSize() throws Exception {
		UserDto expectedUser= prepareUserMock();
		expectedUser.setMobileNo("");
		User expectedUser2 = DtoToUser(expectedUser);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(expectedUser2));
		Mockito.when(userRepo.save(expectedUser2)).thenReturn(expectedUser2);
		userService.updateUser(expectedUser, 1L);
	}
	
	@Test
	public void UpdateUserTest() throws Exception {
		Long id=1L;
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(expectedUser2));
		Mockito.when(userRepo.save(expectedUser2)).thenReturn(expectedUser2);
		assertEquals(expectedUser,userService.updateUser(expectedUser,1L));
	}
	
	@Test(expected = NotFoundException.class)
	public void UpdateUserTestIdException() throws Exception {
		UserDto expectedUser= prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(expectedUser2));
		userService.updateUser(expectedUser, null);
	}
	
	@Test
	public void getByEmailTest() throws Exception {
		UserDto expectedUser= prepareUserMock();
		User expectedUser2 = DtoToUser(expectedUser);
		Mockito.when(userRepo.getByEmail(expectedUser.getEmail())).thenReturn(expectedUser2);
		assertEquals(expectedUser ,userService.getByEmail(expectedUser.getEmail()));
	}
	
	@Test(expected = Exception.class)
	public void getByEmail_whenEmailisNull() throws Exception {
		userService.getByEmail(null);
	}
	
	@Test(expected = Exception.class)
	public void getByEmail_whenEmailIsInvalid() throws Exception {
		userService.getByEmail("xy");
	}
	
	@Test(expected = NotFoundException.class)
	public void getByEmail_whenEmailNotFound() throws Exception {
		userService.getByEmail("vk@gmail.com");
	}
	@Test(expected= Exception.class)
	public void getByEmail_whenEmailSize() throws Exception {
		userService.getByEmail("");
	}
	
	@Test
	public void getByMobileTest() throws Exception {
		UserDto expectedUser= prepareUserMock();
		User expectedUser2 = DtoToUser(expectedUser);
		Mockito.when(userRepo.getByMobileNo(expectedUser.getMobileNo())).thenReturn(expectedUser2);
		assertEquals(expectedUser , userService.getByMobile(expectedUser.getMobileNo()));
	}
	
	@Test(expected = Exception.class)
	public void getByMobile_whenMobileisNull() throws Exception {
		userService.getByMobile(null);
	}
	
	@Test(expected = Exception.class)
	public void getByMobile_whenMobileIsInvalid() throws Exception {
		userService.getByMobile("90");
	}
	
	@Test(expected = NotFoundException.class)
	public void getByMobile_whenMobileNotFound() throws Exception {
		userService.getByMobile("7015145455");
	}
	@Test(expected= Exception.class)
	public void getByMobile_whenMobileSize() throws Exception {
		userService.getByMobile("");
	}
	
	@Test
	public void getAllTagTest() throws Exception {
		Long id=1L;
		UserDto expectedUser = prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		Set<TagDto> ts= expectedUser.getTags();
		List<String> expls= new ArrayList<>();
		for(TagDto x: ts) {
			expls.add(x.getTagName());
		}
		Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(expectedUser2));
		Set<String> set = expls.stream().collect(Collectors.toSet());
		assertEquals(set, userService.getAllTag(id));
	}		
	
	@Test(expected= NotFoundException.class)
	public void getAllTag_NotFound() throws Exception {
		userService.getAllTag(null);
	}
	
	@Test
	public void getCustomFieldTest() throws Exception {
		Long id=1L;
		UserDto expectedUser = prepareUserMock();
		User expectedUser2= DtoToUser(expectedUser);
		List<CustomField> cl = expectedUser.getCustomFields();
		Set<String> expls = new HashSet<>();
		
		Mockito.when(userRepo.findById(id)).thenReturn(Optional.of(expectedUser2));
		Mockito.when(crepo.getByUserId(id)).thenReturn(cl);
		
		for(CustomField x :cl) {
			expls.add(x.getCustomName());
		}
		
		assertEquals( expls , userService.getCustomFieldById(id));
	}
	
	@Test(expected= NotFoundException.class)
	public void getCustomField_NotFound() throws Exception {
		userService.getCustomFieldById(null);
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
	
}
