package org.decryptin.ael.core.expression;

import org.decryptin.ael.core.exception.ExpressionFormatException;
import org.decryptin.ael.core.operation.Operation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public final class ExpressionUtility {
    private static @Nullable ExpressionUtility expressionUtility = null;
    private final Validator   validator = new Validator();
    private final ListBuilder builder   = new ListBuilder();

    //This class needs to be subclassed into two smaller classes.

    private ExpressionUtility() {}

    public void validate(String expression, List<int[]> operator, ExpressionSystem system)
            throws ExpressionFormatException {
        validator.validate(expression, operator, system);
    }

    public boolean hasOperator (@NotNull String expression) {
        for (@NotNull String op : Operation.getOperatorList()) {
            if (expression.contains(op)) {
                return true;
            }
        }
        return false;
    }

    public static synchronized @NotNull ExpressionUtility getUtility() {
        if (expressionUtility == null) {
            expressionUtility = new ExpressionUtility();
        }
        return expressionUtility;
    }

    public List<int[]> getOperatorList(String expression) {
        return builder.getOperatorList(expression);
    }

    public int getHighestPriority (@NotNull String expression, @NotNull List<int[]> operator) {
        int priority = 0;
        for (int[] i : operator) {
            @NotNull String op = expression.substring(i[0], i[1]);
            int x     = Objects.requireNonNull(Operation.getOperation(op)).getPriority();
            if(x > priority) {
                priority = x;
            }
        }
        return priority;
    }

    public int getNextOperatorIndex (int priority, @NotNull String expression, @NotNull List<int[]> operator) {
        for (int i = 0; i < operator.size(); i++) {
            int[] coordinate = operator.get(i);
            @NotNull String op = expression.substring(coordinate[0], coordinate[1]);
            if (Objects.requireNonNull(Operation.getOperation(op)).getPriority() == priority) {
                return i;
            }
        }
        return -1;
    }
}
