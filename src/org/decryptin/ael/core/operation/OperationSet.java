package org.decryptin.ael.core.operation;

import org.decryptin.ael.core.exception.OperandFormatException;
import org.decryptin.ael.core.operand.Operand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class OperationSet {
    private List<int[]>           coordinates;
    private Operand[]             operands;
    private int[]                 operatorCoordinate;
    private int                   listIndex;
    private String                operator;
    private @Nullable Operation   operation;
    private String                replace;
    private String                expression;
    private double                solution;

    public OperationSet (List<int[]> coordinates, int listIndex, String expression) throws OperandFormatException {
        setExpression        (expression);
        setListIndex         (listIndex);
        setCoordinate        (coordinates);
        init                 ();
    }

    private void init() throws OperandFormatException {
        if(!coordinates.isEmpty()) {
            setOperatorCoordinate();
            setOperator          ();
            setOperation         ();
            setOperands          ();
            setReplace           ();
            setSolution          ();
        }
    }

    private @NotNull Operand getPriorOperand () throws OperandFormatException {
        int start = 0;
        if (listIndex > 0) {
            if (coordinates.get(listIndex - 1) != null) {
                start = coordinates.get(listIndex - 1)[1];
            }
        }
        return new Operand(expression.substring(start, operatorCoordinate[0]));
    }

    private @NotNull Operand getPostOperand () throws OperandFormatException {
        int end = expression.length();
        if (coordinates.size() > listIndex + 1) {
            if (coordinates.get(listIndex + 1) != null) {
                end = coordinates.get(listIndex + 1)[0];
            }
        }
        return new Operand (expression.substring(operatorCoordinate[1], end));
    }

    private Operand @NotNull [] getRequiredOperands () throws OperandFormatException {
        Operand @NotNull [] operands = new Operand[2];
        switch (Objects.requireNonNull(operation).getArgCount()) {
            case 2: operands[0] = getPriorOperand();
                    operands[1] = getPostOperand (); break;
            case 1: operands[0] = getPostOperand (); break;
        }
        return operands;
    }

    public String getReplace() {
        return this.replace;
    }

    public double getSolution() {
        return this.solution;
    }


    private void setListIndex(int index) {
        this.listIndex = index;
    }

    private void setOperatorCoordinate() {
        this.operatorCoordinate = coordinates.get(listIndex);
    }

    private void setExpression(String expression) {
        this.expression = expression;
    }

    private void setOperator() {
        this.operator = expression.substring(operatorCoordinate[0], operatorCoordinate[1]);
    }

    private void setCoordinate(List<int[]> coordinates) {
        this.coordinates = coordinates;
    }

    private void setReplace() {
        switch (Objects.requireNonNull(operation).getArgCount()) {
            case 2: this.replace = operands[0].getRawInput() + operator + operands[1].getRawInput(); break;
            case 1: this.replace = operator + operands[0].getRawInput();                             break;
        }
    }

    private void setOperation() {
        this.operation = Operation.getOperation(operator);
    }

    private void setSolution() {
        double @NotNull [] d = new double[2];
        d[0]       = operands[0].getEvaluation();
        assert operation != null;
        if(operation.getArgCount() == 2) {
            d[1] = operands[1].getEvaluation();
        }
        this.solution = operation.calculate(d);
    }

    private void setOperands () throws OperandFormatException {
        this.operands = getRequiredOperands();
    }
}
