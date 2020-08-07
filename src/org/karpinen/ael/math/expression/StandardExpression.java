package org.karpinen.ael.math.expression;

import org.karpinen.ael.math.operation.OperatorType;

import java.util.ArrayList;
import java.util.List;

        /*
        1.Check for a operator at the beginning of the list. if it's minus, remove it from the list.
        Do not replace this in the string, only remove from the list.
         else if it's '/','*','+','^','%' remove it from the list, also replace it in the string with a blank string. **** done
        --------
        2.Check if the operator is a negative sign, if it is then we need to check if it has a operator directly behind it,
        if so remove the negative sign from the list.
        3. Check to make sure any of the following operators have a prior following operator: '^^', '^/'
        4. Check for a operator at the end of the list. If there is one, remove it from list and replace with blank string.
        the operators have to be one of the following: '+','-','*','/','^'
        If one of these checks fail we need to set @valid to false, stop evaluating the expression and return an error to the user.
         */

public class StandardExpression {
    private List<int[]> operatorList = new ArrayList<>();
    private String      finalized;
    private boolean     valid        = true;

    public StandardExpression(String expression, boolean needsValidation) {
        List<int[]> operators = new ArrayList<>();
        getOperators(expression, operators);
        if(needsValidation) {
            startValidation(expression, operators);
        }else{
            operatorList = getOperators(expression, operators);
            finalized = expression;
            valid = true;
        }
    }

    private void startValidation(String e, List<int[]> operators) {
        removeInvalidStartingOperator(e, operators);
        removeInvalidEndOperator(e, operators);
        getOperators(finalized, operatorList);
        valid = validateSpecialOperators(finalized, operatorList);
    }

    private synchronized List<int[]> getOperators(String e, List<int[]> operators) {
        int operatorCount = 0;
        for (int i = 0; i < e.length(); i++) {
            for (int x = 2; x > 0; x--) {
                String[] substring = getSubstring(e, i, i + x).split(":");
                if (OperatorType.getOperatorList().contains(substring[0])) {
                    //Coordinate for the current operator.
                    int[] coordinate = new int[2];
                    coordinate[0] = i;
                    coordinate[1] = Integer.parseInt(substring[1]);
                    //Is the current operator a subtract sign? Used to pro-actively not add invalid operator placements to the list.
                    if(operatorCount > 0 && substring[0].equalsIgnoreCase("-")) {
                        int[] lastCoordinate = operators.get(operatorCount - 1);
                        //if the last coordinate's endpoint is equal to the new coordinate's startpoint, we need to break.
                        if(lastCoordinate[1] == coordinate[0]) {
                            break;
                        }
                    }
                    //Add the operator.
                    operators.add(coordinate);
                    operatorCount++;
                    i = Integer.parseInt(substring[1]) - 1;
                    break;
                }
            }
        }
        return operators;
    }

    private void removeInvalidStartingOperator(String e, List<int[]> operators) {
        int[] coordinate = operators.get(0);
        String op = e.substring(coordinate[0], coordinate[1]);
        if(coordinate[0] == 0 && !op.equals("^^") && !op.equals("^/")) {
            operators.remove(coordinate);
            this.finalized = e.substring(coordinate[1]);
        }else{
            finalized = e;
        }
    }

    private void removeInvalidEndOperator(String e, List<int[]> operators) {
        int[] coordinate = operators.get(operators.size() - 1);
        if(coordinate[1] == e.length()) {
            finalized = finalized.substring(0, coordinate[0] - 1);
            operators.remove(coordinate);
        }else{
            finalized = e;
        }
    }

    private boolean validateSpecialOperators(String e, List<int[]> operators) {
        int iteration = 0;
        for(int[] i : operators) {
            iteration++;
            if(iteration > 1) {
                int[] lastCoordinate = operators.get(iteration - 2);
                String lastOP = e.substring(lastCoordinate[0], lastCoordinate[1]);
                if(lastCoordinate[1] == i[0]) {
                    if (lastOP.equals("^^") || lastOP.equals("^/") || lastOP.equals("^")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private String getSubstring(String s, int start, int end) {
        String substring;
        boolean outOfBounds = end > s.length();
        int endpoint;
        if (outOfBounds) {
            endpoint = s.length() - (s.length() - start);
        } else {
            endpoint = end;
        }
        substring = s.substring(start, endpoint);
        //Format: substring:endpoint
        return substring + ":" + endpoint;
    }

    public List<int[]> getOperator() {
        return operatorList;
    }

    public String getExpression() {
        return finalized;
    }

    public boolean isValid() {
        return valid;
    }

}
