package org.decryptin.ael.core.expression.format;

import org.decryptin.ael.core.controller.Evaluation;

public enum Format implements Evaluation {
    ROUND {
        @Override
        public double calculate (double[] operands){
            return Math.round(operands[0]);
        }
    },

    ROUND_NEAREST {
        @Override
        public double calculate (double[] operands){
            return -1;
        }
    },

    SCIENTIFIC {
        @Override
        public double calculate (double[] operands){
            return operands[0];
        }
    }
}
