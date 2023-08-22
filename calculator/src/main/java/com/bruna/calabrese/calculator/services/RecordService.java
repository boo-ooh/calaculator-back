package com.bruna.calabrese.calculator.services;

import com.bruna.calabrese.calculator.domain.operation.Operation;
import com.bruna.calabrese.calculator.domain.record.Record;
import com.bruna.calabrese.calculator.domain.user.User;
import com.bruna.calabrese.calculator.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;

    public List<Record> getAllByCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return recordRepository.findByUserIdAndDeletedAndOperationIsNotNull(user.getId(), false);
    }
    public Double getCurrentUserCredit(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return recordRepository.findTopByUserIdOrderByDateDesc(user.getId()).get().getUserBalance();
    }

    public void createNewRecord(Operation operation, String result) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Record previousRecord = recordRepository.findTopByUserIdOrderByDateDesc(user.getId()).get();
        double newBalance = previousRecord.getUserBalance() - operation.getCost();
        if(newBalance<0){
            throw new Exception("Not enough credit to perform operation.");
        }
        Record record = new Record();
        record.setUser(user);
        record.setOperation(operation);
        record.setAmount(operation.getCost());
        record.setUserBalance(newBalance);
        record.setOperationResponse(result);
        record.setDate(LocalDateTime.now());
        record.setDeleted(false);

        recordRepository.save(record);
    }

    public void deleteRecord(Integer recordId) {
        Record record = recordRepository.findById(recordId).get();
        record.setDeleted(true);
        recordRepository.save(record);
    }
}
