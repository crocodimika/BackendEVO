package com.company;

public class Multiplier implements Operation {
    @Override
    public double getResult(double a, double b) {
        return a * b;
    }

    @Override
    public String getOperationName() {
        return "умножения";
    }
}
