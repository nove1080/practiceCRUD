package com.example.demo.web.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class UserRequest {
    private String name;
    private Integer age;
    private String email;
    private String password;
}
