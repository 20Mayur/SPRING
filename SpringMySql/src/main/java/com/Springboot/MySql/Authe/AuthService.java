package com.Springboot.MySql.Authe;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Springboot.MySql.Config.JwtService;
import com.Springboot.MySql.Dao.CourseDao;
import com.Springboot.MySql.Entities.CourseModel;
import com.Springboot.MySql.Entities.Role;




@Service
@RequiredArgsConstructor
public class AuthService {
	private final CourseDao Repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	//register
	public AuthResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		var user = CourseModel.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())) 
				.role(Role.User)
		        .build();
		Repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		 return AuthResponse.builder()
			        .token(jwtToken)
			        .build();
	}

	public AuthResponse authenticate(AuthRequest request) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user= Repository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		 return AuthResponse.builder()
			        .token(jwtToken)
			        .build();
	}
}
