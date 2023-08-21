package com.bruna.calabrese.calculator.services;

import com.bruna.calabrese.calculator.domain.operation.Operation;
import com.bruna.calabrese.calculator.domain.record.Record;
import com.bruna.calabrese.calculator.domain.user.User;
import com.bruna.calabrese.calculator.domain.user.UserDTO;
import com.bruna.calabrese.calculator.repositories.RecordRepository;
import com.bruna.calabrese.calculator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;
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
        record.setDate(LocalDate.now());

        recordRepository.save(record);
    }
}
