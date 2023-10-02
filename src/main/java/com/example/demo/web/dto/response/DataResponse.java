package com.example.demo.web.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class DataResponse {
	private Map<String, Object> data;
	private String message;
	private String code;
}
