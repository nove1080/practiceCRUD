package com.example.demo.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {

	private String accessToken;
	private String refreshToken;
}
