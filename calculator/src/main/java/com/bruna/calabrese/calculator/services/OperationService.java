package com.bruna.calabrese.calculator.services;

import org.springframework.stereotype.Service;

@Service
public class OperationService {

    public String add(Double paramOne, Double paramTwo) {
        double result = paramOne + paramTwo;
        return String.valueOf(result);
    }

    public String subtract(Double paramOne, Double paramTwo) {
        double result = paramOne - paramTwo;
        return String.valueOf(result);
    }

    public String multiply(Double paramOne, Double paramTwo) {
        double result = paramOne * paramTwo;
        return String.valueOf(result);
    }

    public String divide(Double paramOne, Double paramTwo) {
        double result = paramOne / paramTwo;
        return String.valueOf(result);
    }

    public String squareRoot(Double paramOne) {
        double result = Math.sqrt(paramOne);
        return String.valueOf(result);
    }
}
