package math.expression;

public class Expression {
    private String         expression;
    private double         solution;
    private ExpressionType type;
    private boolean        valid;

    public Expression(String expression) {
        this.expression = expression.replaceAll("\\s", "");
    }

    /*
    There are two parts to validation in a expression, the first is done here through @ExpressionType. The second
    is done through the child class itself based off it's own validation needs.
    The child validation method won't proceed if the base requirements aren't met.
     */

    protected void setExpressionType(ExpressionType type) {
        this.type = type;
    }

    protected void setSolution(double solution) {
        this.solution = solution;
    }
}
