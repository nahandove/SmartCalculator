package calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SmartCalculator {
    PostfixConverter converter;
    Map<String, String> variablesMap;

    public SmartCalculator() {
        variablesMap = new HashMap<>();
        converter = new PostfixConverter();
    }

    public String calculate(String input) throws IllegalArgumentException {
        String postFix = converter.convert(input);
        String[] entries = parseSigns(postFix.split(" "));

        if (entries.length == 2) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < entries.length; i++) {
            if (entries[i].matches("[A-Za-z]+")) {
                if (!variablesMap.containsKey(entries[i])) {
                    throw new IllegalArgumentException("Unknown variable");
                }
                for (String key : variablesMap.keySet()) {
                    if (entries[i].equals(key)) {
                        entries[i] = variablesMap.get(key);
                    }
                }
            }
        }
        return calculateSumFromArray(entries);
    }

    public String[] assignVariables(String input) {
        input = input.replaceAll("\\s+", "");

        String[] variables = input.split("=");

        if (variables.length != 2) {
            throw new IllegalArgumentException("Invalid assignment");
        }
        if (!variables[0].matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("Invalid identifier");
        }
        if (!variables[1].matches("[A-Za-z]+|[+]?-?[0-9]+")) {
            throw new IllegalArgumentException("Invalid assignment");
        }

        return variables;
    }

    public void storeVariables(String[] variables) {
        String assignment = "";

        if (variables != null) {
            if (variables[1].matches("[A-Za-z]+")) {
                if (!variablesMap.containsKey(variables[1])) {
                    throw new IllegalArgumentException("Unknown variable");
                } else {
                    for (String key : variablesMap.keySet()) {
                        if (variables[1].equals(key)) {
                            assignment = variablesMap.get(key);
                        }
                    }
                }
            } else {
                assignment = variables[1];
            }
            variablesMap.put(variables[0], assignment);
        }
    }

    private String[] parseSigns(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].replaceAll("-{2}", "+");
            array[i] = array[i].replaceAll("[+]+", "+");
            array[i] = array[i].replaceAll("[+][-]", "-");
            if (!array[i].matches("[A-Za-z]+|[+]?-?[0-9]+|[+]|-|[*]|/|\\^")) {
                throw new IllegalArgumentException("Invalid Expression");
            }
        }
        return array;
    }

    private String calculateSumFromArray(String[] array) {
        if (array.length == 1) {
            return array[0];
        }

        Deque<String> calculations = new ArrayDeque<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i].matches("[+]?-?[0-9]+")) {
                calculations.push(array[i]);
            } else {
                String secondNumber = calculations.pop();
                String firstNumber = calculations.pop();
                if (!areNumbersTooBig(firstNumber, secondNumber, array[i])) {
                    int num1 = Integer.parseInt(firstNumber);
                    int num2 = Integer.parseInt(secondNumber);
                    int result = performOperation(num1, num2, array[i]);
                    calculations.push("" + result);
                } else {
                    BigNumberProcessor bigNumberProcessor = new BigNumberProcessor();
                    String bigNumberResult = bigNumberProcessor.process(firstNumber, secondNumber, array[i]);
                    calculations.push(bigNumberResult);
                }
            }
        }
        return calculations.peek();
    }

    public int performOperation(int firstNumber, int secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            case "^":
                return (int) Math.pow(firstNumber, secondNumber);
            default:
                throw new IllegalArgumentException("Unknown operator");
        }
    }

    private boolean areNumbersTooBig(String first, String second, String operator) {
        if (first.startsWith("+") || first.startsWith("-")) {
            first = first.substring(1);
        }
        if (second.startsWith("+") || second.startsWith("-")) {
            second = second.substring(1);
        }

        if ("+".equals(operator) || "-".equals(operator) || "/".equals(operator)) {
            return first.length() > 9 || second.length() > 9;
        }

        if ("*".equals(operator)) {
            return first.length() + second.length() > 9;
        }

        if ("^".equals(operator)) {
            return second.length() >= 2
                    || (Integer.parseInt(second) >= 5 && Integer.parseInt(second) <= 9 && first.length() >= 2)
                    || (Integer.parseInt(second) == 4 && first.length() >= 3)
                    || (Integer.parseInt(second) == 3 && first.length() >= 4)
                    || (Integer.parseInt(second) == 2 && first.length() >= 5);
        }
        return false;
    }
}
