package math.expression.type;

import math.expression.Expression;
import math.expression.ExpressionController;
import math.expression.ExpressionType;

public class Standard extends Expression implements ExpressionController {
    private final ExpressionType type = ExpressionType.Standard;

    public Standard(String expression) {
        super(expression);
        setExpressionType(type);
        validate();
        solve();
    }

    @Override
    public void validate() {

    }

    @Override
    public void solve() {

    }

    @Override
    public ExpressionType getType() {
        return type;
    }
}
