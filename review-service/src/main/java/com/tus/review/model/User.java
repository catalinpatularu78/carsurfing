package com.tus.review.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

	private Long id;

	private String username;

	private String password;

	private String description;

	private String email;

	private Integer phone;

	private String verified;
	

	public User() {
	}
	
	public Long getId() {
		return id;
	}

	public User(@NotBlank @Size(max = 100) String username, @NotBlank @Size(max = 100) String password,
			@NotBlank @Size(max = 3000) String description, @NotBlank @Size(max = 100) String email,
			@NotBlank @Size(max = 100) Integer phone, @NotBlank @Size(max = 10) String verified) {
		this.username = username;
		this.password = password;
		this.description = description;
		this.email = email;
		this.phone = phone;
		this.verified = verified;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}