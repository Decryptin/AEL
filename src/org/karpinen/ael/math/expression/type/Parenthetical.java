package org.karpinen.ael.math.expression.type;

import org.karpinen.ael.math.expression.operator.OperatorFactory;

public class Parenthetical implements Expression {
    private String expression;
    private OperatorFactory factory = new OperatorFactory();

    public Parenthetical(String expression) {
        this.expression = expression;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }

    @Override
    public double solveExpression() {
        return 0;
    }

    private boolean hasParentheses(String e) {
        return e.contains("(");
    }

}
