package com.bruna.calabrese.calculator.controller;

import com.bruna.calabrese.calculator.domain.record.Record;
import com.bruna.calabrese.calculator.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("record")
public class RecordController {

    @Autowired
    RecordRepository recordRepository;

    @GetMapping
    public List<Record> getRecords(@RequestParam Integer userId) {
        return recordRepository.findByUserIdAndOperationIsNotNull(userId);
    }
}
