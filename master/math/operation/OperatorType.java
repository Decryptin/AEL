package org.karpinen.ael.math.operation;

import java.util.Arrays;
import java.util.List;

public enum  OperatorType implements Operator {
    Addition(1, "+") {
        public double evaluate(Operand[] operands) {
            return operands[0].getFinalizedValue() + operands[1].getFinalizedValue();
        }
    },
    Subtract(1, "-") {
        public double evaluate(Operand[] operands) {
            return operands[0].getFinalizedValue() - operands[1].getFinalizedValue();
        }
    },
    Multiply(2, "*") {
        public double evaluate(Operand[] operands) {
            return operands[0].getFinalizedValue() * operands[1].getFinalizedValue();
        }
    },
    Divide(2, "/") {
        public double evaluate(Operand[] operands) {
            return operands[0].getFinalizedValue() / operands[1].getFinalizedValue();
        }
    },
    Modulo(2, "%") {
        @Override
        public double evaluate(Operand[] operands) {
            return operands[0].getFinalizedValue() % operands[1].getFinalizedValue();
        }
    },
    Exponent(3, "^") {
        public double evaluate(Operand[] operands) {
            return Math.pow(operands[0].getFinalizedValue(), operands[1].getFinalizedValue());
        }
    },
    Square(3, "^^") {
        public double evaluate(Operand[] operands) {
            return Math.pow(operands[0].getFinalizedValue(), 2);
        }
    },
    SquareRoot(3, "^/") {
        public double evaluate(Operand[] operands) {
            return Math.sqrt(operands[0].getFinalizedValue());
        }
    };

    private final int    priority;
    private final String id;

    OperatorType(int priority, String id) {
        this.priority = priority;
        this.id       = id;
    }

    public static synchronized List<String> getOperatorList() {
        return Arrays.asList("+", "-", "*", "/", "%", "^", "^^", "^/");
    }

    public static synchronized int getOperatorPriority(String op) {
        switch (op) {
            case "^":
            case "^^":
            case "^/":
                return 3;
            case "*":
            case "/":
            case "%":
                return 2;
            case "+":
            case "-":
                return 1;
        }
        return -1;
    }

    public int getPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }
}
