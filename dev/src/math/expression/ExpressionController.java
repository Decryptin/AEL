package math.expression;

public interface ExpressionController {
    void           validate();
    void           solve();
    ExpressionType getType();
}
