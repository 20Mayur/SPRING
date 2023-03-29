package com.Springboot.MySql.Authe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDemo {
	
	private String email;
	private String password;
	
	

}
