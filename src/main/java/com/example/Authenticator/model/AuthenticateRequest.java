package com.example.Authenticator.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Validated
public class AuthenticateRequest {
	
	@NotNull
	@JsonProperty("username")
	public String userName;	
	
	@NotNull
	@JsonProperty("password")
	public String password;

}
