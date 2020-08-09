package math.expression;

import math.expression.type.Standard;
import org.jetbrains.annotations.NotNull;

public class ExpressionFactory {

    public Expression getExpression(String expression, @NotNull String label) {
        switch (label) {
            case "Standard": return new Standard(expression);
        }
        return null;
    }
}
