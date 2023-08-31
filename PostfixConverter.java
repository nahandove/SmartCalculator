package calculator;

import java.util.*;

public class PostfixConverter {
    public String convert(String infix) {
        List<String> postFixElements = new ArrayList<>();

        if (!hasEqualParenthesis(infix)) {
            throw new IllegalArgumentException("Invalid expression");
        }

        Deque<String> operatorsDeque = new ArrayDeque<>();

        for (String element: getAllElements(infix)) {
            if (element.matches("[+]?-?[A-Za-z0-9]+")) {
                postFixElements.add(element);
            } else if ("(".equals(element)) {
                operatorsDeque.push(element);
            } else if (")".equals(element)) {
                while (!operatorsDeque.isEmpty() && !"(".equals(operatorsDeque.peek())) {
                    postFixElements.add(operatorsDeque.pop());
                }
                operatorsDeque.pop();
            } else {
                while(!operatorsDeque.isEmpty() && !("(".equals(operatorsDeque.peek())) &&
                        getPrecedence(element) <= getPrecedence(operatorsDeque.peek())) {
                    postFixElements.add(operatorsDeque.pop());
                }
                operatorsDeque.push(element);
            }
        }

        while (!operatorsDeque.isEmpty()) {
            postFixElements.add(operatorsDeque.pop());
        }

        return String.join(" ", postFixElements);
    }

    private boolean hasEqualParenthesis(String infix) {
        int openParenthesis = 0;
        int closeParenthesis = 0;
        for (char element: infix.toCharArray()) {
            if (element == '(') {
                openParenthesis++;
            } else if (element == ')') {
                closeParenthesis++;
            }
        }
        return openParenthesis == closeParenthesis;
    }

    private List<String> getAllElements(String infix) {
        StringBuilder builder = new StringBuilder();
        List<String> elements = new ArrayList<>();

        builder.append(infix.charAt(0));

        if (infix.length() > 1) {
            for (int i = 1; i < infix.length(); i++) {
                if (Character.isLetterOrDigit(infix.charAt(i)) && Character.isLetterOrDigit(infix.charAt(i - 1))) {
                    builder.append(infix.charAt(i));
                } else if (Character.isLetterOrDigit(infix.charAt(i))
                        && (infix.charAt(i - 1) == '+' || infix.charAt(i - 1) == '-')) {
                    if (i == 1) {
                        builder.append(infix.charAt(i));
                    } else if (infix.charAt(i - 2) == '+' || infix.charAt(i - 2) == '-' ||
                            infix.charAt(i - 2) == '*' || infix.charAt(i - 2) == '/' || infix.charAt(i - 2) == '(') {
                        builder.append(infix.charAt(i));
                    } else {
                        builder.append(" ");
                        builder.append(i);
                    }
                } else if (infix.charAt(i) != '(' && infix.charAt(i) != ')' && infix.charAt(i) == infix.charAt(i - 1)) {
                    builder.append(infix.charAt(i));
                } else {
                    builder.append(" ");
                    builder.append(infix.charAt(i));
                }
            }
        }

        elements.addAll(Arrays.asList(builder.toString().split("\\s+")));
        return elements;
    }

    private int getPrecedence(String operator) {
        if ("+".equals(operator) || "-".equals(operator)) {
            return 1;
        } if ("*".equals(operator) || "/".equals(operator)) {
            return 2;
        } if ("^".equals(operator)) {
            return 3;
        }
        return 0;
    }
}
