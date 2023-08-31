package calculator;

import java.math.BigInteger;

public class BigNumberProcessor {
    private String add(String a, String b) {
        return new BigInteger(a).add(new BigInteger(b)).toString();
    }

    private String subtract(String a, String b) {
        return new BigInteger(a).subtract(new BigInteger(b)).toString();
    }

    private String multiply(String a, String b) {
        return new BigInteger(a).multiply(new BigInteger(b)).toString();
    }

    private String divide(String a, String b) {
        return new BigInteger(a).divide(new BigInteger(b)).toString();
    }

    private String power(String a, String b) {
        BigInteger base = new BigInteger(a);
        BigInteger product = new BigInteger(a);
        for (int i = 0; i < Integer.parseInt(b); i++) {
            product = product.multiply(base);
        }
        return product.toString();
    }

    public String process(String a, String b, String operator) {
        String operation = null;

        switch(operator) {
            case "+" -> operation = add(a, b);
            case "-" -> operation = subtract(a, b);
            case "*" -> operation = multiply(a, b);
            case "/" -> operation = divide(a, b);
            case "^" -> operation = power(a, b);
        }
        return operation;
    }
}
