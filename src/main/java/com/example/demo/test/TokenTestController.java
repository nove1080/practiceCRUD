package com.example.demo.web.controller;

import com.example.demo.authentication.filter.TokenInfo;
import com.example.demo.domain.model.User;
import com.example.demo.domain.service.UserService;
import com.example.demo.repository.entity.UserEntity;
import com.example.demo.repository.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class TokenTestController {
    private final UserService userService;
    private final UserRepository userRepository;

//    @PostConstruct
//    public void init() {
//        UserEntity testUser = UserEntity.builder()
//                .name("test").password("1234").build();
//        userRepository.save(testUser);
//    }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody User user) {

        String name = user.getName();
        String password = user.getPassword();
        log.info("name={}, password={}", name, password);
        TokenInfo tokenInfo = userService.login(name, password);

        log.info("token={}, {}", tokenInfo.getAccessToken(), tokenInfo.getRefreshToken());
        return tokenInfo;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
