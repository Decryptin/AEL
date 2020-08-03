package org.karpinen.ael.math.expression.type;

import org.karpinen.ael.math.expression.operator.Operator;
import org.karpinen.ael.math.expression.operator.OperatorFactory;

import java.util.ArrayList;
import java.util.List;

public class Standard implements Expression {
    private final OperatorFactory opFactory = new OperatorFactory();
    private final String          expression;
    private List<String>          steps = new ArrayList<>();

    public Standard(String expression) {
        this.expression = expression;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public double solveExpression() {
        String s = expression;
        steps.add(steps.size() + 1 + ". " + s);
        List<Operator> operators = getOperators(s);
        while(!operators.isEmpty()) {
            for(Operator o : operators) {
                int priority = highestPriority(operators);
                if(o.getPriority() == priority) {
                    o.setArgs(getArguments(operators, o.getCoordinates(), s));
                    String replace  = o.getReplacement();
                    double solution = o.getResult();
                    s               = s.replace(replace, String.valueOf(solution));
                    operators       = getOperators(s);
                    steps.add(steps.size() + 1 + ". " + s);
                    break;
                }
            }
        }
        return Double.parseDouble(s);
    }

    private int highestPriority(List<Operator> operators) {
        int priority = 0;
        for(Operator o : operators) {
            if(o.getPriority() > priority) {
                priority = o.getPriority();
            }
        }
        return priority;
    }

    private String[] getArguments(List<Operator> operator, int[] coordinate, String s) {
        String[] arguments = new String[2];
        arguments[0]       = getFirstArgument(operator, coordinate, s);
        arguments[1]       = getSecondArgument(operator, coordinate, s);
        return arguments;
    }

    public List<String> getSteps() {
        return steps;
    }

    //Gets all the operator positions and puts them into a iterable list.
    public List<Operator> getOperators(String e) {
        List<Operator> operators = new ArrayList<>();
        for (int i = 0; i < e.length(); i++) {
            for (int x = 3; x > 0; x--) {
                String[] substring = getSubstring(i + x, i, e).split(":");
                if (isOperator(substring[0])) {
                    i                = Integer.parseInt(substring[1]) - 1;
                    int endPoint     = Integer.parseInt(substring[1]);
                    int[] coordinate = getCoordinate(i, endPoint);

                    //The reason we check for this is in case we have a negative number.
                    if(!operators.isEmpty()) {
                        int size = operators.size();
                        if(breakLoop(coordinate, operators.get(size - 1), e)) {
                            break;
                        }else{
                            operators.add(opFactory.getOperator(substring[0], coordinate));
                        }
                    }else{
                        //This checks to see if the operator is at the very beginning of the string.
                        if(coordinate[0] == 0) {
                            break;
                        }
                    }
                    operators.add(opFactory.getOperator(substring[0], coordinate));
                    break;
                }
            }
        }
        return operators;
    }

    private boolean breakLoop(int[] coordinate, Operator lastOperator, String s) {
        String newOperator = s.substring(coordinate[0], coordinate[1]);
        if(newOperator.equalsIgnoreCase("-")) {
            int[] lastCoordinate = lastOperator.getCoordinates();
            if(coordinate[0] - lastCoordinate[1] == 1) {
                return true;
            }
        }
        return false;
    }

    private String getFirstArgument(List<Operator> operator, int[] coordinate, String s) {
        int point = 0;
        for(Operator o : operator) {
            int[] i = o.getCoordinates();
            if(i[1] < coordinate[0]) {
                point = i[1];
            }
        }
        return s.substring(point, coordinate[0]);
    }

    private String getSecondArgument(List<Operator> operator, int[]coordinate, String s) {
        int point = s.length();
        for(Operator o : operator) {
            int[] i = o.getCoordinates();
            if(i[0] > coordinate[1]) {
                point = i[0];
                break;
            }
        }
        return s.substring(coordinate[1], point);
    }

    private int[] getCoordinate(int start, int end) {
        int[] coordinate = new int[2];
        coordinate[0]    = start;
        coordinate[1]    = end;
        return coordinate;
    }

    private boolean isOperator(String s) {
        return opFactory.getOperatorList().contains(s);
    }

    private String getSubstring(int maxRange, int start, String s) {
        String substring;
        int operatorEnd;
        try {
            substring = s.substring(start, maxRange);
            operatorEnd = maxRange;
        }catch (IndexOutOfBoundsException e) {
            int difference = s.length() - start;
            operatorEnd    = s.length() - difference;
            substring      = s.substring(start, operatorEnd);
        }
        substring += ":" + operatorEnd;
        return substring;
    }
}
