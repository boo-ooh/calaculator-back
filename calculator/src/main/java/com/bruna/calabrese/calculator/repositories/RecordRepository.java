package com.bruna.calabrese.calculator.repositories;

import com.bruna.calabrese.calculator.domain.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record,Integer> {

    @Override
    Optional<Record> findById(Integer integer);

    Optional<Record> findTopByUserIdOrderByDateDesc(Integer userId);

    List<Record> findByUserIdAndDeletedAndOperationIsNotNull(Integer userId, Boolean deleted);



}
