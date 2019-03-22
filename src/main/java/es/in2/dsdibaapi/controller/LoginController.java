package es.in2.dsdibaapi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.json.UserJson;

@RestController
public class LoginController {
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
    public Authentication authenticateUser(@Valid @RequestBody UserJson loginRequest) throws IOException {

		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(), loginRequest.getPassword(), new ArrayList<>()));
		
		return auth;
    }
}
