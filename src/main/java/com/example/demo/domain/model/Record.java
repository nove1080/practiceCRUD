package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
public class Record {

    private Long rid;
    private String title;
    private String content;
}
