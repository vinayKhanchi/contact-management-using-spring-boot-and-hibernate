package com.example.demo.contact.payloads;

import java.util.Objects;

public class utilDto {

	private String email;
	private String mobile;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public utilDto(String email, String mobile) {
		super();
		this.email = email;
		this.mobile = mobile;
	}
	public utilDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "utilDto [email=" + email + ", mobile=" + mobile + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, mobile);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		utilDto other = (utilDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(mobile, other.mobile);
	}
	
	
	
	
	
}
