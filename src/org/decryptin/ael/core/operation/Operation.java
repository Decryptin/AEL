package org.decryptin.ael.core.operation;

import org.decryptin.ael.core.controller.Evaluation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public enum Operation implements Evaluation {
    ADD (1,2, false, "+") {
        @Override
        public double calculate (double[] operands){
            return operands[0] + operands[1];
        }
    },

    SUB (1,2, false, "-") {
        @Override
        public double calculate (double[] operands){
            return operands[0] - operands[1];
        }
    },

    MUL (2,2, false,  "*") {
        @Override
        public double calculate (double[] operands){
            return operands[0] * operands[1];
        }
    },
    DIV (2,2, false, "/") {
        @Override
        public double calculate (double[] operands){
            return operands[0] / operands[1];
        }
    },

    MOD (2,2,false,"%") {
        @Override
        public double calculate (double[] operands){
            return operands[0] % operands[1];
        }
    },

    EXP (3,2,false,"^") {
        @Override
        public double calculate (double[] operands){
            return Math.pow(operands[0], operands[1]);
        }
    },

    SQR (3,1,true,"^^") {
        @Override
        public double calculate (double[] operands){
            return Math.pow(operands[0], 2);
        }
    },

    SQRT (3,1, true,"^/") {
        @Override
        public double calculate (double[] operands){
            return Math.sqrt(operands[0]);
        }
    };

    private final int     priority;
    private final int     argCount;
    private final boolean special;
    private final String  operationID;

    private static List<String> operators;

    Operation(int priority, int argCount, boolean special, String operationID) {
        this.priority    = priority;
        this.argCount    = argCount;
        this.operationID = operationID;
        this.special     = special;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getArgCount() {
        return this.argCount;
    }

    public String getOperationID() {
        return this.operationID;
    }

    public boolean isSpecial() {
        return this.special;
    }

    public static synchronized @Nullable Operation getOperation (String operation) {
        for (@NotNull Operation op : values()) {
            if (op.getOperationID().equalsIgnoreCase(operation)) {
                return op;
            }
        }
        return null;
    }

    public static synchronized List<String> getOperatorList() {
        if (operators == null) {
            operators = new ArrayList<>();
            for (@NotNull Operation op : values()) {
                operators.add (op.getOperationID());
            }
        }
        return operators;
    }

}
