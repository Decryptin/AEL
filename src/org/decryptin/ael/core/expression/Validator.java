package org.decryptin.ael.core.expression;

import org.decryptin.ael.core.exception.ExpressionFormatException;
import org.decryptin.ael.core.operation.Operation;

import java.util.List;
import java.util.Objects;

public class Validator {

    public void validate(String expression, List<int[]> operator, ExpressionSystem system)
            throws ExpressionFormatException {
        verifyOperatorsAllowed(expression, operator, system);
        verifySpecialOperators(expression, operator, system);
        verifyStartingOperator(expression, operator, system);
        verifyExpressionEnd   (expression, operator, system);
    }

    //Verifies all operators are valid
    private void verifyOperatorsAllowed (String expression, List<int[]> operator, ExpressionSystem system)
            throws ExpressionFormatException {
        for(int[] i : operator) {
            String op = expression.substring(i[0], i[1]);
            if(!system.getOperators().contains(op)) {
                throw new ExpressionFormatException("Operator", op, system.getSystemID());
            }
        }
    }

    //Verifies all special operators have a prior operator directly before it.
    private void verifySpecialOperators (String expression, List<int[]> operator, ExpressionSystem system)
            throws ExpressionFormatException {
        for(int i = 0; i < operator.size(); i++) {
            if(i > 0) {
                int[] coordinate = operator.get(i);
                String op = expression.substring(coordinate[0], coordinate[1]);
                if(Objects.requireNonNull(Operation.getOperation(op)).isSpecial()) {
                    int[] lastCoordinate = operator.get(i - 1);
                    if(!isParallelOperator(coordinate, lastCoordinate)) {
                        throw new ExpressionFormatException("Missing Prior Operator: ", op, system.getSystemID());
                    }
                }
            }
        }
    }

    //Verifies if there is a starting operator, that's it's of an allowed type.
    private void verifyStartingOperator (String expression, List<int[]> operator, ExpressionSystem system)
            throws ExpressionFormatException {
        int[] i = operator.get(0);
        String op = expression.substring(i[0], i[1]);
        if(i[0] == 0 && !op.equalsIgnoreCase("-")) {
            if(!Objects.requireNonNull(Operation.getOperation(op)).isSpecial()) {
                throw new ExpressionFormatException("Invalid Starting Operator: ", op, system.getSystemID());
            }
        }
    }

    //Verifies that the end of the expression doesn't include any illegal characters/operators.
    private void verifyExpressionEnd    (String expression, List<int[]> operator, ExpressionSystem system)
            throws ExpressionFormatException{
        int[] lastCoordinate = operator.get(operator.size() - 1);
        if(lastCoordinate[1] == expression.length() - 1) {
            String op = expression.substring(lastCoordinate[0], lastCoordinate[1]);
            throw new ExpressionFormatException("Invalid Expression End: ", op, system.getSystemID());
        }
    }

    private boolean isParallelOperator(int[] nextCoordinate, int[] lastCoordinate) {
        return lastCoordinate[1] == nextCoordinate[0];
    }
}
