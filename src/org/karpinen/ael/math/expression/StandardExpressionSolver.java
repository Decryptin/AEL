package org.karpinen.ael.math.expression;

import org.karpinen.ael.math.operation.*;

import java.util.List;

public class StandardExpressionSolver implements Expression {
    private final MathOperatorFactory factory = new MathOperatorFactory();
    private boolean                   valid;

    public StandardExpressionSolver(String expression) {
        evaluate(expression);
    }

    @Override
    public double evaluate(String s) {
        String working = s;
        StandardExpression e = new StandardExpression(working);
        int highestPriority = getHighestPriority(e.getOperator(), e.getExpression());
        while(getNextOperator(e.getOperator(), highestPriority, e.getExpression()) != null) {
            int[] nextOperator = getNextOperator(e.getOperator(), highestPriority, e.getExpression());
            working = e.getExpression();
            String op = working.substring(nextOperator[0], nextOperator[1]);
            Operand[] operands = null;
            switch (op) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":
                    operands = getOperand(2, nextOperator, e.getOperator(), e.getExpression()); break;
                case "^^":
                case "^/":
                    operands = getOperand(1, nextOperator, e.getOperator(), e.getExpression()); break;
            }

            MathOperation operation = new MathOperation(op, operands);
            working = working.replace(operation.getExpression(), String.valueOf(operation.evaluate()));
            e.update(working);
            highestPriority = getHighestPriority(e.getOperator(), e.getExpression());
            System.out.println("New Expression: " + working);
        }
        return 0.0;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    private int getHighestPriority(List<int[]> operators, String s) {
        int priority = 0;
        for(int[] i : operators) {
            String op = s.substring(i[0], i[1]);
            if(OperatorType.getOperatorPriority(op) > priority) {
                priority = OperatorType.getOperatorPriority(op);
            }
        }
        return priority;
    }

    private int[] getNextOperator(List<int[]> operators, int priority, String s) {
        for(int[] i : operators) {
            String op = s.substring(i[0], i[1]);
            if(OperatorType.getOperatorPriority(op) == priority) {
                return i;
            }
        }
        return null;
    }

    private Operand[] getOperand(int args, int[] coordinate, List<int[]> operators, String s) {
        Operand[] operands = new Operand[2];
        switch (args) {
            case 2: operands[0] = new Operand(getFirstArgument(coordinate, operators, s));
                    operands[1] = new Operand(getSecondArgument(coordinate, operators, s));
                    break;
            case 1: operands[0] = new Operand(getSecondArgument(coordinate, operators, s));
                    break;
        }
        return operands;
    }

    private String getFirstArgument(int[] coordinate, List<int[]> operators, String s) {
        int startPoint = 0;
        for(int[] i : operators) {
            if(!(i[1] < coordinate[0])) {
                break;
            }
            startPoint = i[0] + 1;
        }
        return s.substring(startPoint, coordinate[0]);
    }

    private String getSecondArgument(int[] coordinate, List<int[]> operators, String s) {
        int endpoint = s.length();
        for(int[] i : operators) {
            if(i[0] > coordinate[1]) {
                String op = s.substring(coordinate[0], coordinate[1]);
                endpoint = i[0];
                break;
            }
        }
        return s.substring(coordinate[1], endpoint);
    }
}
