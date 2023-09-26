package com.example.demo.domain.service;

import com.example.demo.authentication.JwtTokenProvider;
import com.example.demo.authentication.TokenInfo;
import com.example.demo.domain.model.UserRole;
import com.example.demo.repository.entity.UserEntity;
import com.example.demo.repository.repository.UserRepository;
import com.example.demo.web.dto.request.UserRequest;
import com.example.demo.web.support.generator.UserEntityGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserEntityGenerator userEntityGenerator;

    @Transactional
    public TokenInfo login(Long id, String password) {
        log.info("login");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id.toString(), password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        return tokenInfo;
    }

    public Long save(UserRequest user) {
        UserEntity userEntity = userEntityGenerator.generateUserEntity(user, generateRoles(UserRole.USER));
        userRepository.save(userEntity);
        return userEntity.getId();
    }

    public Optional<UserEntity> findUser(String name) {
        return userRepository.findByName(name);
    }

    public List<String> generateRoles(UserRole... roles) {
        return Arrays.stream(roles)
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }
}
