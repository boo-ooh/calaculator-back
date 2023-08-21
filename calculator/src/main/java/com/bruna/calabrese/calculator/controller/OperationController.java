package com.bruna.calabrese.calculator.controller;

import com.bruna.calabrese.calculator.domain.operation.Operation;
import com.bruna.calabrese.calculator.domain.operation.OperationDTO;
import com.bruna.calabrese.calculator.domain.operation.ResultDTO;
import com.bruna.calabrese.calculator.domain.user.UserDTO;
import com.bruna.calabrese.calculator.repositories.OperationRepository;
import com.bruna.calabrese.calculator.services.RecordService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("operation")
public class OperationController {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    RecordService recordService;

    @GetMapping
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @PostMapping(value="/addition")
    public ResponseEntity add(@RequestBody OperationDTO operationDTO){
        double result = operationDTO.paramOne()+operationDTO.paramTwo();
        String resultText = String.valueOf(result);

        return createNewRecord(operationDTO.operation(), resultText);

    }

    private ResponseEntity createNewRecord(Operation operation, String resultText) {
        try{
            recordService.createNewRecord(operation, resultText);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(new ResultDTO(resultText));
    }
}
