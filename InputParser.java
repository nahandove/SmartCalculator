package calculator;

import java.util.Scanner;

public class InputParser {
    Scanner scanner = new Scanner(System.in);
    SmartCalculator calculator = new SmartCalculator();
    public void parseInput() {
        while(true) {
            String total = "";

            String input = scanner.nextLine();
            try {
                if (isExit(input)) break;

                if (isHelp(input)) continue;

                if (isInvalidCommand(input)) continue;

                if ("".equals(input)) continue;

                if (isVariableAssignment(input)) {
                    calculator.storeVariables(calculator.assignVariables(input));
                    continue;
                }

                total = calculator.calculate(input);

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            System.out.println(total);
        }
    }

    private boolean isExit(String input) {
        if ("/exit".equals(input)) {
            System.out.println("Bye!");
            return true;
        }
        return false;
    }

    private boolean isHelp(String input) {
        if ("/help".equals(input)) {
            System.out.println("The calculator performs the following operations on integers:\n" +
                    "Addition, Subtraction, Multiplication, Division, and Power.\n" +
                    "Use \"+\" to perform Addition,\"-\" to perform Subtraction,\n" +
                    "\"*\" to perform Multiplication, and \"/\" to perform Division.\n" +
                    "Power calculation is used in the form of \"x^y\".\n" + "\n" +
                    "You can add negative unary operators if you want to calculate negative numbers.\n" + "\n" +
                    "The calculator can also store variables. Please declare variables in the form \"x=y\"\n" +
                    "Where x is the variable and y the assignment. Variables can be resigned a new number\n" +
                    "or a previously assigned variable.\n" + "\n" +
                    "The calculator makes use of operator precedence and you can add parenthesis to change it.\n" + "\n" +
                    "The calculator supports operations on large numbers.\n" +
                    "Special notes: if you input an even number of subtraction signs between numbers, the program\n" +
                    "interprets it as addition as per usual mathematical rules.\n" + "\n" +
                    "Enter \"!exit\" to exit the program");
            return true;
        }
        return false;
    }

    private boolean isInvalidCommand(String input) {
        if (input.startsWith("/")) {
            System.out.println("Unknown command");
            return true;
        }
        return false;
    }

    private boolean isVariableAssignment(String input) {
        if (input.contains("=")) {
            return true;
        }
        return false;
    }
}
