package com.bruna.calabrese.calculator.controller;

import com.bruna.calabrese.calculator.domain.operation.Operation;
import com.bruna.calabrese.calculator.domain.operation.OperationDTO;
import com.bruna.calabrese.calculator.domain.operation.ResultDTO;
import com.bruna.calabrese.calculator.repositories.OperationRepository;
import com.bruna.calabrese.calculator.services.OperationService;
import com.bruna.calabrese.calculator.services.RandomStringService;
import com.bruna.calabrese.calculator.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RandomStringService randomStringService;

    @Autowired
    OperationService operationService;

    @GetMapping
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @PostMapping(value = "/addition")
    public ResponseEntity addition(@RequestBody OperationDTO operationDTO) {
        String resultText = operationService.add(operationDTO.paramOne(), operationDTO.paramTwo());

        return createNewRecord(operationDTO.operation(), resultText);

    }

    @PostMapping(value = "/subtraction")
    public ResponseEntity subtraction(@RequestBody OperationDTO operationDTO) {
        String resultText = operationService.subtract(operationDTO.paramOne(), operationDTO.paramTwo());

        return createNewRecord(operationDTO.operation(), resultText);

    }

    @PostMapping(value = "/multiplication")
    public ResponseEntity multiplication(@RequestBody OperationDTO operationDTO) {
        String resultText = operationService.multiply(operationDTO.paramOne(), operationDTO.paramTwo());

        return createNewRecord(operationDTO.operation(), resultText);

    }

    @PostMapping(value = "/division")
    public ResponseEntity division(@RequestBody OperationDTO operationDTO) {
        String resultText = operationService.divide(operationDTO.paramOne(), operationDTO.paramTwo());

        return createNewRecord(operationDTO.operation(), resultText);

    }

    @PostMapping(value = "/square_root")
    public ResponseEntity squareRoot(@RequestBody OperationDTO operationDTO) {
        String resultText = operationService.squareRoot(operationDTO.paramOne());

        return createNewRecord(operationDTO.operation(), resultText);

    }

    @PostMapping(value = "/random_string")
    public ResponseEntity randomString(@RequestBody OperationDTO operationDTO) {

        String resultText = randomStringService.generateRandomString();
        return createNewRecord(operationDTO.operation(), resultText);

    }

    private ResponseEntity createNewRecord(Operation operation, String resultText) {
        try {
            recordService.createNewRecord(operation, resultText);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(new ResultDTO(resultText));
    }
}
