package v2;

import java.util.Arrays;
import java.util.List;

public enum NumeralSystem {
    BINARY     (Arrays.asList('0','1'), 128, "b"),
    OCTAL      (Arrays.asList('0','1','2','3','4','5','6','7'), 128, "o"),
    DECIMAL    (Arrays.asList('0','1','2','3','4','5','6','7','8','9','.','-'), 128, "d"),
    HEXADECIMAL(Arrays.asList('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'), 128, "h");

    private final char      hook   = '#';
    private List<Character> allow;
    private int             limit;
    private String          ID;

    NumeralSystem(List<Character> allowedChars, int limit, String ID) {
        this.allow = allowedChars;
        this.limit = limit;
        this.ID    = hook + ID;
    }

    public List<Character> getAllowedCharacters() {
        return this.allow;
    }

    public int getLengthLimit() {
        return this.limit;
    }

    public String getID() {
        return this.ID;
    }
}
