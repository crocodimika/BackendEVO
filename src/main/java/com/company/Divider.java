package com.company;

public class Divider implements Operation {
    @Override
    public double getResult(double a, double b) {
        if (b == 0) {
            System.out.println("Ошибка: деление на ноль!");
            return Double.NaN;
        }
        return a / b;
    }

    @Override
    public String getOperationName() {
        return "деления";
    }
}
