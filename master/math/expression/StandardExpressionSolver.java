package org.karpinen.ael.math.expression;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.karpinen.ael.math.operation.*;

import java.util.List;

public class StandardExpressionSolver implements Expression {
    private boolean valid;

    public StandardExpressionSolver(String expression) {
        evaluate(expression);
    }

    @Override
    public double evaluate(String s) {
        //String to be modified after each sub-expression evaluation.
        String working = s;

        //StandardExpression object used while evaluating expression.
        StandardExpression e = new StandardExpression(working);

        //Set validity
        this.valid = e.isValid();

        //Highest priority in the string currently.
        int highestPriority;

        //Reusable variables used in @while loop.
        List<int[]> operator;
        String      expression;

        //While loop checking if there's still an operator.
        if(valid) {
            while(hasOperator(working)) {
                operator = e.getOperator();
                expression = e.getExpression();
                highestPriority = getHighestPriority(operator, expression);
                int[] nextOperator = getNextOperator(operator, highestPriority, expression);
                working = expression;
                assert nextOperator != null;
                String op = working.substring(nextOperator[0], nextOperator[1]);
                Operand[] operands = null;
                switch (op) {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                    case "%":
                    case "^":
                        operands = getOperand(2, nextOperator, operator, expression); break;
                    case "^^":
                    case "^/":
                        operands = getOperand(1, nextOperator, operator, expression); break;
                }

                MathOperation operation = new MathOperation(op, operands);
                working = working.replace(operation.getExpression(), String.valueOf(operation.evaluate()));
                e.update(working);
                System.out.println("New Expression: " + working);
            }
        }else{
            System.out.println("Expression Error!");
        }
        return 0.0;
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    //Check if the string has any operators left.
    private boolean hasOperator(String expression) {
        for(String op : OperatorType.getOperatorList()) {
            if(expression.contains(op)) {
                return true;
            }
        }
        return false;
    }

    //Get the highest priority operator in the expression.
    private int getHighestPriority(@NotNull List<int[]> operators, String s) {
        int priority = 0;
        for(int[] i : operators) {
            String op = s.substring(i[0], i[1]);
            if(OperatorType.getOperatorPriority(op) > priority) {
                priority = OperatorType.getOperatorPriority(op);
            }
        }
        return priority;
    }

    //Retrieves the next operator in the expression based off priority.
    private int @Nullable [] getNextOperator(@NotNull List<int[]> operators, int priority, String s) {
        for(int[] i : operators) {
            String op = s.substring(i[0], i[1]);
            if(OperatorType.getOperatorPriority(op) == priority) {
                return i;
            }
        }
        return null;
    }

    //Get's an operand[] of the needed arguments based off the operator.
    private Operand @NotNull [] getOperand(int args, int[] coordinate, List<int[]> operators, String s) {
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

    //Get the first argument for the target operator.
    @Contract(pure = true)
    private @NotNull String getFirstArgument(int[] coordinate, @NotNull List<int[]> operators, String s) {
        int startPoint = 0;
        for(int[] i : operators) {
            if(!(i[1] < coordinate[0])) {
                break;
            }
            startPoint = i[0] + 1;
        }
        return s.substring(startPoint, coordinate[0]);
    }

    //Gets the second argument for the target operator.
    private @NotNull String getSecondArgument(int[] coordinate, @NotNull List<int[]> operators, @NotNull String s) {
        int endpoint = s.length();
        for(int[] i : operators) {
            if(i[0] > coordinate[1]) {
                endpoint = i[0];
                break;
            }
        }
        return s.substring(coordinate[1], endpoint);
    }
}
