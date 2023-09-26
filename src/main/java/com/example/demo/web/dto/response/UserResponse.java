package com.example.demo.web.dto.response;

import com.example.demo.authentication.TokenInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private TokenInfo authToken;
}
