package com.example.demo.contact.payloads;


import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.contact.model.CustomField;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class UserDto {

	
	
	@NotNull
	@Size(min=1)
	private String FirstName;
	private String LastName;
	
	
	@NotNull
	@Size(min=1)
	private String Email;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	@NotNull
	@Size(min=1)
	private String MobileNo;
	
	private Set<TagDto> tags;
	
	private List<CustomField> customFields;
	

	public List<CustomField> getCustomFields() {
		return customFields;
	}
	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}
	public Set<TagDto> getTags() {
		return tags;
	}
	public void setTags(Set<TagDto> tags) {
		this.tags = tags;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(Email, FirstName, LastName, MobileNo, customFields, tags);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(Email, other.Email) && Objects.equals(FirstName, other.FirstName)
				&& Objects.equals(LastName, other.LastName) && Objects.equals(MobileNo, other.MobileNo)
				&& Objects.equals(customFields, other.customFields) && Objects.equals(tags, other.tags);
	}
	
	
	
	
	
	
	
	
	
	
	
}
