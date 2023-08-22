package com.bruna.calabrese.calculator.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationServiceTest {


    OperationService service = new OperationService();

    @Test
    void add() {
        String result = service.add(5.0, 5.0);
        assertEquals(true, result.equals("10.0"));
        result = service.add(-5.0, -5.0);
        assertEquals(true, result.equals("-10.0"));
    }

    @Test
    void subtract() {
        String result = service.subtract(5.0, 5.0);
        assertEquals(true, result.equals("0.0"));
        result = service.subtract(-5.0, -5.0);
        assertEquals(true, result.equals("0.0"));
        result = service.subtract(5.0, -5.0);
        assertEquals(true, result.equals("10.0"));
    }

    @Test
    void multiply() {
        String result = service.multiply(5.0, 5.0);
        assertEquals(true, result.equals("25.0"));
        result = service.multiply(-5.0, -5.0);
        assertEquals(true, result.equals("25.0"));
        result = service.multiply(5.0, -5.0);
        assertEquals(true, result.equals("-25.0"));
    }

    @Test
    void divide() {
        String result = service.divide(5.0, 5.0);
        assertEquals(true, result.equals("1.0"));
        result = service.divide(-5.0, -5.0);
        assertEquals(true, result.equals("1.0"));
        result = service.divide(5.0, -5.0);
        assertEquals(true, result.equals("-1.0"));
        result = service.divide(5.0, 0.0);
        assertEquals(true, result.equals("Infinity"));
    }


    @Test
    void squareRoot() {
        String result = service.squareRoot(49.0);
        assertEquals(true, result.equals("7.0"));
        result = service.squareRoot(-49.0);
        assertEquals(true, result.equals("NaN"));
        result = service.squareRoot(55.0);
        assertEquals(true, result.equals("7.416198487095663"));
    }
}