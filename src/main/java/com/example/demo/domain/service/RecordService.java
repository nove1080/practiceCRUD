package com.example.demo.domain.service;

import com.example.demo.domain.dto.update.RecordUpdateDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.repository.repository.RecordRepository;
import com.example.demo.web.dto.request.RecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;

    public Long save(RecordRequest record) {
        RecordEntity recordEntity = getRecordEntity(record);
        repository.save(recordEntity);
        return recordEntity.getRid();
    }

    public RecordEntity findRecord(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("record not found"));
    }

    public void deleteRecord(Long id){
        repository.deleteById(id);
    }

    public RecordEntity updateRecord(Long id, RecordUpdateDto updateDto) {
        RecordEntity updateRecord = findRecord(id);
        updateRecord.setTitle(updateDto.getTitle());
        updateRecord.setContent(updateDto.getContent());
        return updateRecord;
    }

    private RecordEntity getRecordEntity(RecordRequest record) {
        RecordEntity recordEntity = RecordEntity.builder()
                .title(record.getTitle())
                .content(record.getContent())
                .build();
        return recordEntity;
    }
}
