package org.karpinen.ael.math.prefix;

public class PrefixOperationFactory {
    private final double            input;
    private final OperandPrefixType type;

    public PrefixOperationFactory(double input, String prefix) {
        this.input = input;
        this.type  = getType(prefix);
    }

    public double evaluate() {
        return type.evaluate(input);
    }

    private OperandPrefixType getType(String prefix) {
        switch (prefix) {
            case "sin": return OperandPrefixType.Sine;
            case "cos": return OperandPrefixType.Cosine;
            case "tan": return OperandPrefixType.Tangent;
        }
        return null;
    }
}
