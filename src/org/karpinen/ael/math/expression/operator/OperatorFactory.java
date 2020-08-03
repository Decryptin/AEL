package org.karpinen.ael.math.expression.operator;

import java.util.Arrays;
import java.util.List;

public class OperatorFactory {

    public Operator getOperator(String op, int[] coordinate) {
        switch (op) {
            case "+":  return new    Addition(coordinate);
            case "-":  return new    Subtract(coordinate);
            case "*":  return new    Multiply(coordinate);
            case "/":  return new    Division(coordinate);
            case "^":  return new    Exponent(coordinate);
            case "^^": return new      Square(coordinate);
            case "^/": return new  SquareRoot(coordinate);
            case "%":  return new      Modulo(coordinate);
        }
        return null;
    }

    public List<String> getOperatorList() {
        return Arrays.asList("+", "-", "*", "/", "^", "^^", "^/", "%");
    }
}
