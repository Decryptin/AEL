package org.decryptin.ael.core.expression;

import java.util.Arrays;
import java.util.List;

public enum ExpressionSystem {
    Standard (Arrays.asList("+","-","*","/", "%","^","^^","^/"), "Std");

    private final List<String> operators;
    private final String       systemID;

    ExpressionSystem(List<String> operators, String systemID) {
        this.operators  = operators;
        this.systemID   = systemID;
    }

    public List<String> getOperators() {
        return this.operators;
    }

    public String getSystemID() {
        return this.systemID;
    }

}
