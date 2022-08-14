package com.example.demo.contact.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.ToString;

@Entity
@Table(name="custom_field")
public class CustomField implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="custom_name",length= 150)
	private String customName;
	
	@Column(name="custom_value")
	private String customValue;
	
	@Column(name="user_id")
	private Long userId;
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long long1) {
		this.userId = long1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomValue() {
		return customValue;
	}

	public void setCustomValue(String customValue) {
		this.customValue = customValue;
	}

	public CustomField( String customName,  String customValue,
			 Long userId) {
		super();
		//this.id = id;
		this.customName = customName;
		this.customValue = customValue;
		this.userId =  userId;
	}

	public CustomField() {
		
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(customName, customValue, id, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomField other = (CustomField) obj;
		return Objects.equals(customName, other.customName) && Objects.equals(customValue, other.customValue)
				&& Objects.equals(id, other.id) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "CustomField [id=" + id + ", customName=" + customName + ", customValue=" + customValue + ", userId="
				+ userId + "]";
	}

	
	
	
	
	
	
	
	

	

	
	
	
	
	
}
