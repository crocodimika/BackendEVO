package com.company;

public class Calculator {
    Operation operation;
    public Calculator(Operation operation) {
        this.operation = operation;
    }
    public void calc(double a, double b) {
        System.out.println("Результат " + operation.getOperationName() + " a и b: " + operation.getResult(a, b));
    }
}
