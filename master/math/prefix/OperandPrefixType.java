package org.karpinen.ael.math.prefix;

import java.util.Arrays;
import java.util.List;

public enum OperandPrefixType implements OperandPrefix {
    Cosine {
        @Override
        public double evaluate(double input) {
            return Math.cos(input);
        }
    },
    Sine {
        @Override
        public double evaluate(double input) {
            return Math.sin(input);
        }
    },
    Tangent {
        @Override
        public double evaluate(double input) {
            return Math.tan(input);
        }
    };

    public static synchronized List<String> getPrefixList() {
        return Arrays.asList("sin", "tan", "cos");
    }
}
