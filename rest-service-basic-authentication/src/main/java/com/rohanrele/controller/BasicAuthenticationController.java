package com.rohanrele.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohanrele.bo.UserBO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthenticationController {

	@GetMapping(path = "/basic-authenticate")
	public UserBO basicAuthenticate() {
		// local variables
		UserBO userBO = null;

		// get logged in principal
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// construct user bo
		userBO = new UserBO();
		userBO.setUsername(userDetails.getUsername());
		for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
			userBO.getRoles().add(grantedAuthority.getAuthority());
		}
		return userBO;
	}
}
