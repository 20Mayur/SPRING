package com.Springboot.MySql.Authe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AutheController {
	@Autowired
	private AuthService authService;
	
@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authService.register(request));
		}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
		return ResponseEntity.ok(authService.authenticate(request));
	}
 	
	@PostMapping("/hello")
	public RequestDemo hello(@RequestBody RequestDemo request) {
		return request;
	}
}
