package org.karpinen.ael.math.expression;

public interface Expression {
    double  evaluate(String s);
    boolean  isValid();
}
