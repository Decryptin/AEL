package org.karpinen.ael.math.expression.operator;

import org.karpinen.ael.math.number.factory.NumberFactory;

public interface Operator {
    void            setArgs(String[] args);
    int         getArgCount();
    int         getPriority();
    double        getResult();
    String            getID();
    int[]    getCoordinates();
    String[]        getArgs();
    String   getReplacement();
}
