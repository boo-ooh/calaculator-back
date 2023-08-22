package com.bruna.calabrese.calculator.controller;

import com.bruna.calabrese.calculator.domain.record.RecordDTO;
import com.bruna.calabrese.calculator.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("record")
public class RecordController {

    @Autowired
    RecordService recordService;

    @GetMapping
    public List<RecordDTO> getRecords() {
        return recordService.getAllByCurrentUser()
                            .stream()
                            .map((record -> new RecordDTO(record)))
                            .collect(Collectors.toList());
    }

    @GetMapping(value = "credit")
    public Double getUserCredit(){
        return recordService.getCurrentUserCredit();
    }


    @DeleteMapping("/{id}")
    public void getUserCredit(@PathVariable Integer id){
         recordService.deleteRecord(id);
    }
}
