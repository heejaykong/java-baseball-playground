package study;

import java.util.Scanner;

public class StringCalculator {
    String[] input() {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        String[] values = value.split(" ");
        return values;
    }

    int add(int first, int second) {
        return first + second;
    }

    int subtract(int first, int second) {
        return first - second;
    }

    int multiply(int first, int second) {
        return first * second;
    }

    int divide(int first, int second) {
        return first / second;
    }

    int doTheMath(int firstOperand, String operator, int secondOperand) {
        if (operator.equals("+")) {
            return add(firstOperand, secondOperand);
        }
        if (operator.equals("-")) {
            return subtract(firstOperand, secondOperand);
        }
        if (operator.equals("*")) {
            return multiply(firstOperand, secondOperand);
        }
        if (operator.equals("/")) {
            return divide(firstOperand, secondOperand);
        }
        return 0;
    }

    int calculate(String[] values) {
        int firstOperand = Integer.parseInt(values[0]);
        String operator = values[1];
        for (int i = 2; i < values.length; i++){
            if (i % 2 == 0){ // 숫자일 경우
                int secondOperand = Integer.parseInt(values[i]);
                firstOperand = doTheMath(firstOperand, operator, secondOperand);
                continue;
            }
            operator = values[i];
        }
        return firstOperand;
    }

    void start() {
        String[] values = input();
        int result = calculate(values);
        System.out.println(result);
    }
}
