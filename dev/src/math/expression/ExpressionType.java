package math.expression;

import java.util.Arrays;
import java.util.List;

public enum ExpressionType {
    Standard(
            null,
            -1,
            Arrays.asList("+","-","*","/","^","^^","^/"),
            null

    );

    private String          format;          //null if there isn't a required format.
    private int             argumentCount;   //-1 if there is no limit.
    private List<String>    allowedOperator;
    private List<Character> allowedCharacter; //null if there isn't any allowed non-numeric characters.

    ExpressionType(String format, int argumentCount, List<String> allowedOperator, List<Character> allowedCharacter) {
        this.format           = format;
        this.argumentCount    = argumentCount;
        this.allowedOperator  = allowedOperator;
        this.allowedCharacter = allowedCharacter;
    }

    public String getFormat() {
        return format;
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    public List<String> getAllowedOperator() {
        return allowedOperator;
    }

    public List<Character> getAllowedCharacter() {
        return allowedCharacter;
    }

}
