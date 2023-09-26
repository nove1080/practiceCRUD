package com.example.demo.web.controller.v1;

import com.example.demo.authentication.TokenInfo;
import com.example.demo.domain.service.UserService;
import com.example.demo.repository.entity.UserEntity;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.request.UserRequest;
import com.example.demo.web.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ApiResponse<ApiResponse.SuccessBody<UserResponse>> loginOrRegisterUser(@RequestBody UserRequest userRequest) {
        String name = userRequest.getName();
        String password = userRequest.getPassword();

        Long id = null;
        Optional<UserEntity> findUser = userService.findUser(name);
        if(findUser.equals(Optional.empty())) {
            id = userService.save(userRequest);
        } else {
            id = findUser.get().getId();
        }

        TokenInfo tokenInfo = userService.login(id, password);
        UserResponse data = getUserResponse(id, tokenInfo);
        return ApiResponseGenerator.success(data, HttpStatus.OK);
    }

    private UserResponse getUserResponse(Long id, TokenInfo tokenInfo) {
        return UserResponse.builder()
                .id(id)
                .authToken(tokenInfo)
                .build();
    }
}
