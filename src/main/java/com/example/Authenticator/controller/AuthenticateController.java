package com.example.Authenticator.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Authenticator.model.AuthenticateRequest;
import com.example.Authenticator.model.AuthenticateResponse;

@RestController
@RequestMapping("/v1/auth/")
public class AuthenticateController {

	@Value("${userAuth.username}")
	private String userName;

	@Value("${userAuth.password}")
	private String userPassword;

	@PostMapping(value = "/user/authenticate", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AuthenticateResponse> autheticateUser(@Valid @RequestBody AuthenticateRequest request) {

		// For final Response Body
		AuthenticateResponse resp = new AuthenticateResponse();
		System.out.println("Request username : " + request.getUserName());

		if (request.userName.equals(userName)) {
			if (request.password.equals(userPassword)) {
				Calendar date = Calendar.getInstance();
				System.out.println("Current Date and TIme : " + date.getTime());
				long timeInSecs = date.getTimeInMillis();
				Date datePlus60 = new Date(timeInSecs + (60 * 60 * 1000));
				System.out.println("After adding 60 mins : " + datePlus60);
				SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmssMs");
				String sessionId = formatter.format(datePlus60);
				resp.setSessionId(sessionId);
			}
		}

		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

}
