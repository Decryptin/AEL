package org.karpinen.ael.math.operation;

public class MathOperatorFactory {

    public OperatorType getType(String operator) {
        switch (operator) {
            case "+":  return OperatorType.Addition;
            case "-":  return OperatorType.Subtract;
            case "*":  return OperatorType.Multiply;
            case "/":  return OperatorType.Divide;
            case "%":  return OperatorType.Modulo;
            case "^":  return OperatorType.Exponent;
            case "^^": return OperatorType.Square;
            case "^/": return OperatorType.SquareRoot;
        }
        return null;
    }
}
