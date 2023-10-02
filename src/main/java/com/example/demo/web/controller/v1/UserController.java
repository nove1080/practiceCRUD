package com.example.demo.web.controller.v1;

import com.example.demo.authentication.TokenInfo;
import com.example.demo.domain.service.UserService;
import com.example.demo.domain.support.session.SessionConst;
import com.example.demo.repository.entity.UserEntity;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.request.UserRequest;
import com.example.demo.web.dto.response.UserResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;

	@PostMapping("/users")
	public ApiResponse<ApiResponse.SuccessBody<UserResponse>> loginOrRegisterUser(
			@RequestBody UserRequest userRequest, HttpServletRequest request) {
		String name = userRequest.getName();
		String password = userRequest.getPassword();

		Long id = null;
		try {
			UserEntity findUser = userService.findUserByName(name);
			id = findUser.getId();
		} catch (UsernameNotFoundException e) { // 회원가입
			log.debug("error: ", e);
			id = userService.save(userRequest);
		}

		TokenInfo tokenInfo = null;
		try {
			tokenInfo = userService.login(id, password);
			HttpSession session = request.getSession();
			UserEntity findUser = userService.findUserByName(name);
			session.setAttribute(SessionConst.USER, findUser);
		} catch (AuthenticationException e) { // 로그인 실패
			throw e;
		}

		UserResponse data = getUserResponse(id, tokenInfo);
		return ApiResponseGenerator.success(data, HttpStatus.OK);
	}

	// soft delete 결과 확인
	@GetMapping("/users")
	public String users() {
		return userService.findAllUser().toString();
	}

	// soft delete
	@DeleteMapping("/users")
	public ResponseEntity<String> deleteUser(@RequestBody UserRequest userRequest) {
		try {
			UserEntity findUser = userService.findUserByName(userRequest.getName());
			userService.login(findUser.getId(), userRequest.getPassword());
			userService.removeUser(findUser.getId());
		} catch (AuthenticationException e) { // user를 찾지 못하거나, name/password 매핑 실패
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
	}

	@PutMapping("/users")
	public String test() {
		return "success";
	}

	private UserResponse getUserResponse(Long id, TokenInfo tokenInfo) {
		return UserResponse.builder().id(id).authToken(tokenInfo).build();
	}
}
