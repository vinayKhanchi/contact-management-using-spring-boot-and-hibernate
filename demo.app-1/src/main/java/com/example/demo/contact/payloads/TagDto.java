package com.example.demo.contact.payloads;

import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.example.demo.contact.model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Builder;


public class TagDto {

	
	
	@NotNull
	private String tagName;
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public TagDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    

	public TagDto(@NotNull String tagName) {
		super();
		this.tagName = tagName;
	}

	
	@Override
	public String toString() {
		return "TagDto [tagName=" + tagName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(tagName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagDto other = (TagDto) obj;
		return Objects.equals(tagName, other.tagName);
	}

	
	
	
}
