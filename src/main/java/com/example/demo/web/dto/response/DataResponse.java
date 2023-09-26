package com.example.demo.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class DataResponse {
    private Map<String, Object> data;
    private String message;
    private String code;
}
