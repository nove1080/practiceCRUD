package com.example.demo.web.controller.v1;

import com.example.demo.domain.dto.update.RecordUpdateDto;
import com.example.demo.domain.service.RecordService;
import com.example.demo.domain.support.session.SessionConst;
import com.example.demo.exception.AccessDeniedException;
import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.repository.entity.UserEntity;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.request.RecordRequest;
import com.example.demo.web.dto.response.RecordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecordController {

	private final RecordService recordService;

	@PostMapping("/record")
	public ApiResponse<ApiResponse.SimpleSuccessBody> addRecord(
			@SessionAttribute(name = SessionConst.USER) UserEntity userEntity,
			@RequestBody RecordRequest recordRequest) {
		recordService.save(recordRequest, userEntity);
		return ApiResponseGenerator.simpleResponse(HttpStatus.OK);
	}

	@GetMapping("/record")
	public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> getRecord(@RequestParam Long id) {
		RecordEntity record = recordService.findRecord(id);
		RecordResponse data = getRecordResponse(id, record);
		return ApiResponseGenerator.success(data, HttpStatus.OK);
	}

	@PutMapping("/record")
	public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> editRecord(
			@SessionAttribute(name = SessionConst.USER) UserEntity userEntity,
			@RequestBody RecordRequest recordRequest) {
		checkPermission(userEntity, recordRequest);

		Long rid = recordRequest.getRid();
		RecordUpdateDto updateDto = getUpdateDto(recordRequest);
		RecordEntity updatedRecord = recordService.updateRecord(rid, updateDto);

		RecordResponse data = getRecordResponse(rid, updatedRecord);
		return ApiResponseGenerator.success(data, HttpStatus.OK);
	}

	@DeleteMapping("/record")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteRecord(
			@SessionAttribute(name = SessionConst.USER) UserEntity userEntity,
			@RequestBody RecordRequest recordRequest) {
		checkPermission(userEntity, recordRequest);

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

	/** 본인의 게시물인지 확인 */
	private void checkPermission(UserEntity userEntity, RecordRequest recordRequest) {
		Long rid = recordRequest.getRid();
		UserEntity writer = recordService.findRecord(rid).getUserEntity();
		if (!writer.equals(userEntity)) {
			throw new AccessDeniedException("타인의 게시물을 수정할 수 없습니다.");
		}
	}
}
