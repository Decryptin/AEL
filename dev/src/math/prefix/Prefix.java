package math.prefix;

import java.util.Arrays;
import java.util.List;

public enum Prefix implements PrefixController {
    //Returns the cosine of the @param operand
    Cosine("cos") {
        @Override
        public double evaluate (double operand) {
            return Math.cos(operand);
        }
    },
    //Returns the sine of the @param operand
    Sine("sin") {
        @Override
        public double evaluate (double operand) {
            return Math.sin(operand);
        }
    },
    //Returns the tangent of @param operand.
    Tangent("tan") {
        @Override
        public double evaluate (double operand) {
            return Math.tan(operand);
        }
    };

    //The identifier for the specific @MathPrefix enum
    private String id;

    //@MathPrefix object creation.
    Prefix(String id) {
        this.id = id;
    }

    //Returns @MathPrefix enum type based off @param math.prefix.
    public static synchronized Prefix getMathPrefix (String prefix) {
        switch (prefix) {
            case "cos": return Cosine;
            case "sin": return Sine;
            case "tan": return Tangent;
        }
        return null;
    }

    //Returns the ID for the enum type.
    public String getID() {
        return id;
    }

    //Returns all @MathPrefixes enum id's.
    public static synchronized List<String> getMathPrefixes() {
        return Arrays.asList("cos","sin","tan");
    }
}
