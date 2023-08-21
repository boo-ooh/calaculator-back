package com.bruna.calabrese.calculator.repositories;

import com.bruna.calabrese.calculator.domain.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    @Override
    List<Operation> findAll();
}
