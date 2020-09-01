package org.decryptin.ael.core.operand.prefix;

import org.decryptin.ael.core.controller.Evaluation;
import org.jetbrains.annotations.NotNull;

public enum Prefix implements Evaluation {
    NONE ("none") {
        @Override
        public double calculate(double[] operands) {
            return operands[0];
        }
    },
    COS  ("cos") {
        @Override
        public double calculate (double[] operands) {
            return Math.cos(operands[0]);
        }
    },
    SIN  ("sin") {
        @Override
        public double calculate (double[] operands) {
            return Math.sin(operands[0]);
        }
    },

    TAN  ("tan") {
        @Override
        public double calculate (double[] operands) {
            return Math.tan(operands[0]);
        }
    },

    ACOS ("acos") {
        @Override
        public double calculate (double[] operands) {
            return Math.acos(operands[0]);
        }
    },

    ASIN ("asin") {
        @Override
        public double calculate (double[] operands) {
            return Math.asin(operands[0]);
        }
    },

    ATAN ("atan") {
        @Override
        public double calculate (double[] operands) {
            return Math.atan(operands[0]);
        }
    },

    COSH ("cosh") {
        @Override
        public double calculate (double[] operands) {
            return Math.cosh(operands[0]);
        }
    },

    SINH ("sinh") {
        @Override
        public double calculate (double[] operands) {
            return Math.sinh(operands[0]);
        }
    },

    TANH ("tanh") {
        @Override
        public double calculate (double[] operands) {
            return Math.tanh(operands[0]);
        }
    };

    private final String prefixID;

    Prefix (String prefixID) {
        this.prefixID = prefixID;
    }

    public String getPrefixID() {
        return this.prefixID;
    }


    public static @NotNull Prefix getPrefix (@NotNull String operand) {
        for (@NotNull Prefix prefix : values()) {
            if (operand.startsWith(prefix.getPrefixID())) {
                return prefix;
            }
        }
        return NONE;
    }
}
