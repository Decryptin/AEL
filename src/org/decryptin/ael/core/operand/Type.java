package org.decryptin.ael.core.operand;

import org.decryptin.ael.core.operand.convert.*;

import java.util.Arrays;
import java.util.List;

public enum Type implements Convert {
    BINARY     (Arrays.asList('0','1'), "#b"),
    OCTAL      (Arrays.asList('0','1','2','3','4','5','6','7'), "#o"),
    DECIMAL    (Arrays.asList('0','1','2','3','4','5','6','7','8','9','-', '.'), "#d"),
    HEXADECIMAL(Arrays.asList('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'), "#h");

    private final List<Character> characters;
    private final String          systemID;

    private final Convert binary = new Binary();
    private final Convert octal  = new Octal();
    private final Convert decimal = new Decimal();
    private final Convert hexadecimal = new Hexadecimal();

    Type(List<Character> characters, String systemID) {
        this.characters = characters;
        this.systemID   = systemID;
    }

    public List<Character> getCharacters() {
        return this.characters;
    }

    public String getSystemID() {
        return this.systemID;
    }

    public static synchronized Type getSystem (String operand) {
        for (Type type : values()) {
            if (operand.endsWith(type.getSystemID())) {
                return type;
            }
        }
        return DECIMAL;
    }

    @Override
    public String binary(String s) {
        return null;
    }

    @Override
    public String octal(String s) {
        return null;
    }

    @Override
    public String decimal(String s) {
        return null;
    }

    @Override
    public String hexadecimal(String s) {
        return null;
    }
}
