package com.example.demo.exception;

import javax.validation.constraints.NotEmpty;

/** 접근 대상에 권한이 없는 경우 : ex) 타인의 record를 수정/삭제 */
public class AccessDeniedException extends RuntimeException {
	public AccessDeniedException(@NotEmpty String message) {
		super(message);
	}

	public AccessDeniedException(@NotEmpty String message, Throwable cause) {
		super(message, cause);
	}
}
