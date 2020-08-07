package org.karpinen.ael.math.operation;

public class MathOperation {
    private final MathOperatorFactory factory = new MathOperatorFactory();

    /*
    MathOperation takes an Operand[] object and evaluates the array based on an operator.
     */

    private final OperatorType  operator;
    private final Operand[]     operands;
    private String              expression;

    public MathOperation(String op , Operand[] operands) {
        this.operands   = operands;
        MathOperatorFactory factory = new MathOperatorFactory();
        this.operator   = factory.getType(op);
        setExpression(op);
    }

    private void setExpression(String op) {
        if(operands[1] == null) {
            this.expression = op + operands[0].getInput();
        }else{
            this.expression = operands[0].getInput() + op + operands[1].getInput();
        }
    }

    public String getExpression() {
        return expression;
    }

    public double evaluate() {
        return operator.evaluate(operands);
    }
}
