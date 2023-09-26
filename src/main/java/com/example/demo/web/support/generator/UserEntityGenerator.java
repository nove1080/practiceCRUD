package com.example.demo.web.support.generator;

import com.example.demo.repository.entity.UserEntity;
import com.example.demo.web.dto.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserEntityGenerator {

    public UserEntity generateUserEntity(UserRequest userRequest, List<String> roles) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .roles(roles)
                .build();
    }
}
