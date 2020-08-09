package math.operation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public enum Operation implements OperationController {
    //Takes two double primitive types in an array, adds them together.
    Addition(1) {
        @Override
        public double evaluate (double[] operands) {
            return operands[0] + operands[1];
        }
    },

    //Takes two double primitive types in an array, performs subtraction.
    Subtract(1) {
        @Override
        public double evaluate (double[] operands) {
            return operands[0] - operands[1];
        }
    },

    //Takes two double primitive types in an array, performs multiplication.
    Multiply(2) {
        @Override
        public double evaluate (double[] operands) {
            return operands[0] * operands[1];
        }
    },

    //Takes two double primitive types in an array, performs division.
    Divide(2) {
        @Override
        public double evaluate (double[] operands) {
            return operands[0] / operands[1];
        }
    },

    ////Takes two double primitive types in an array, performs modulation.
    Modulo(2) {
        @Override
        public double evaluate (double[] operands) {
            return operands[0] % operands[1];
        }
    },

    //Takes one double primitive types in an array, performs square() function.
    Square(3) {
        @Override
        public double evaluate (double[] operands) {
            return Math.pow(operands[0], 2);
        }
    },

    //Takes two double primitive types in an array, performs exponent function.
    Exponent(3) {
        @Override
        public double evaluate (double[] operands) {
            return Math.pow(operands[0], operands[1]);
        }
    },

    //Takes one double primitive types in an array, performs sqrt() function.
    SquareRoot(3) {
        @Override
        public double evaluate (double[] operands) {
            return Math.sqrt(operands[0]);
        }
    };

    //Used to assess the priority of the current operator.
    private int priority;

    //Object enum creation
    Operation(int priority) {
        this.priority = priority;
    }

    //Returns an @MathOperation object based off @param operation
    @Contract(pure = true)
    public static synchronized @Nullable Operation getOperation (@NotNull String operation) {
        switch (operation) {
            case "+":  return Addition;
            case "-":  return Subtract;
            case "*":  return Multiply;
            case "/":  return Divide;
            case "%":  return Modulo;
            case "^":  return Exponent;
            case "^^": return Square;
            case "^/": return SquareRoot;
        }
        return null;
    }

    @Contract(pure = true)
    public static synchronized int getHighestPriority(@NotNull List<Operation> operators) {
        int priority = 0;
        for(Operation operation : operators) {
            if(operation.priority > priority) {
                priority = operation.priority;
            }
        }
        return priority;
    }
}
