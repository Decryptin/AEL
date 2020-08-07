package org.karpinen.ael.math.base;

import java.util.Arrays;
import java.util.List;

/*
@BaseType enums are used in validation and creation of @Number objects.
-baseID is used for identifying the numeral system being used.
-constraints are the allowed characters in string input for @BaseType.
-limit is used to limit the length of string inputs based on the @BaseType.
 */

public enum BaseType {
    Base2(
            Arrays.asList('0', '1'),
            Arrays.asList("bin", "binary"),
            2, 128, 'h'),
    Base8(
            Arrays.asList('0','1','2','3','4','5','6','7'),
            Arrays.asList("oct", "octal"),
            8, 128, 'o'),
    Base10(
            Arrays.asList('0','1','2','3','4','5','6','7','8','9','.','-'),
            Arrays.asList("dec", "decimal"),
            10, 128, 'd'),
    Base16(
            Arrays.asList('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'),
            Arrays.asList("hex", "hexadecimal"),
            16, 128, 'h');

    private final List<Character> constraint;
    private final List<String>    aliases;
    private final int             id;
    private final int             lengthLimit;
    private final char            suffix;

    BaseType(List<Character> constraint, List<String> aliases, int baseID, int limit, char suffixID) {
        this.constraint  = constraint;
        this.aliases     = aliases;
        this.id          = baseID;
        this.suffix      = suffixID;
        this.lengthLimit = limit;
    }

    public List<Character> getConstraint() {
        return this.constraint;
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    /*
    These methods are not used as of right now, but will be used later when a UI is developed.
    @getID
    @getSuffix
    @getAliases
     */

    public int getId() {
        return this.id;
    }

    public char getSuffix() {
        return suffix;
    }

    public int getLengthLimit() {
        return lengthLimit;
    }
}
