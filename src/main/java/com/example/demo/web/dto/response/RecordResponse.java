package com.example.demo.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class RecordResponse {
	private Long rid;
	private String title;
	private String content;
}
