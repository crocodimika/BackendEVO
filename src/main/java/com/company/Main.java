package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число а");
        double a = scanner.nextDouble();
        System.out.println("Введите число б");
        double b = scanner.nextDouble();

        Calculator calculator = new Calculator(new Adder());
        calculator.calc(a, b);

        calculator = new Calculator(new Subtractor());
        calculator.calc(a, b);

        calculator = new Calculator(new Multiplier());
        calculator.calc(a,b);

        calculator = new Calculator(new Divider());
        calculator.calc(a,b);

        scanner.close();

    }
}
