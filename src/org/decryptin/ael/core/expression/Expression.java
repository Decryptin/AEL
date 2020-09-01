package org.decryptin.ael.core.expression;

import org.decryptin.ael.core.exception.ExpressionFormatException;
import org.decryptin.ael.core.operand.Operand;
import org.jetbrains.annotations.NotNull;

public class Expression {
    private ExpressionSystem expressionSystem;
    private String expression;
    private String targetID;
    private String solution;

    protected void init (String expression, ExpressionSystem expressionSystem, String targetID) throws ExpressionFormatException {
        setExpression(expression);
        setExpressionSystem(expressionSystem);
        setTargetID  (targetID);
    }

    protected String getExpression() {
        return this.expression;
    }

    protected ExpressionSystem getExpressionSystem() {
        return expressionSystem;
    }

    public String getSolution () {
        return this.solution;
    }

    private void setExpression (String expression) {
        this.expression = expression;
    }

    private void setExpressionSystem(ExpressionSystem expressionSystem) {
        this.expressionSystem = expressionSystem;
    }

    private void setTargetID   (String targetID) {
        this.targetID = targetID;
    }

    protected void setSolution (@NotNull Operand operand) {
        this.solution = operand.convert(targetID);
    }
}
