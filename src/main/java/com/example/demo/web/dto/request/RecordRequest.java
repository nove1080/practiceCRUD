package com.example.demo.web.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class RecordRequest {
    private Long rid;
    private String title;
    private String content;
}
