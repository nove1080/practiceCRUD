package com.example.demo.web.dto.request;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FooRequest {

	private String name;
	private Integer age;
	private String email;
	private String password;
}
