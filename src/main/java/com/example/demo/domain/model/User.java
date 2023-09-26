package com.example.demo.domain.model;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

	private Long id;
	private String name;
	private Integer age;
	private String email;
	private String password;
}
