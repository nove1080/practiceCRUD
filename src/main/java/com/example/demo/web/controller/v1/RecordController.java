package com.example.demo.web.controller.v1;

import com.example.demo.domain.dto.update.RecordUpdateDto;
import com.example.demo.domain.service.RecordService;
import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.request.RecordRequest;
import com.example.demo.web.dto.response.DataResponse;
import com.example.demo.web.dto.response.RecordResponse;
import com.example.demo.web.dto.response.SimpleResponse;
import com.sun.tools.javac.util.DefinedBy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/record")
    public ApiResponse<ApiResponse.SimpleSuccessBody> addRecord(@RequestBody RecordRequest recordRequest) {
        recordService.save(recordRequest);
        return ApiResponseGenerator.simpleResponse(HttpStatus.OK);
    }

    @GetMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> getRecord(Long id) {
        RecordEntity record = recordService.findRecord(id);
        RecordResponse data = getRecordResponse(id, record);
        return ApiResponseGenerator.success(data, HttpStatus.OK);
    }

    @PutMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> editRecord(@RequestBody RecordRequest recordRequest) {
        Long id = recordRequest.getRid();
        RecordUpdateDto updateDto = getUpdateDto(recordRequest);
        RecordEntity updatedRecord = recordService.updateRecord(id, updateDto);

        RecordResponse data = getRecordResponse(id, updatedRecord);
        return ApiResponseGenerator.success(data, HttpStatus.OK);
    }

    @DeleteMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<Void>> deleteRecord(@RequestBody RecordRequest recordRequest) {
        recordService.deleteRecord(recordRequest.getRid());
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    private RecordResponse getRecordResponse(Long id, RecordEntity record) {
        return RecordResponse.builder()
                .rid(id)
                .title(record.getTitle())
                .content(record.getContent())
                .build();
    }

    private RecordUpdateDto getUpdateDto(RecordRequest recordRequest) {
        return RecordUpdateDto.builder()
                .title(recordRequest.getTitle())
                .content(recordRequest.getContent())
                .build();
    }

}
