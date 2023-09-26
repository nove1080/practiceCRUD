package com.example.demo.repository.repository;

import com.example.demo.repository.entity.RecordEntity;
import com.example.demo.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

}
