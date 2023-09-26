package com.example.demo.domain.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class RecordUpdateDto {
    private String title;
    private String content;
}
