package math.base;

import java.util.Arrays;
import java.util.List;

public enum BaseSystem {
    base2 (Arrays.asList('0', '1'),
            2,128),
    base8 (Arrays.asList('0','1','2','3','4','5','6','7','8','9'),
            8,128),
    base10 (Arrays.asList('0','1','2','3','4','5','6','7','8','9','.','-'),
            10, 128),
    base16 (Arrays.asList('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'),
            16,128);

    private final List<Character> constraint;
    private final int             lenLimit;
    private final int             id;

    BaseSystem(List<Character> constraint, int id, int lengthLimit) {
        this.constraint = constraint;
        this.lenLimit   = lengthLimit;
        this.id         = id;
    }

    public int getID() {
        return this.id;
    }

    public List<Character> getConstraint() {
        return this.constraint;
    }

    public int getLengthLimit() {
        return this.lenLimit;
    }

    public static synchronized List<Character> getSuffixes() {
        return Arrays.asList('h', 'o', 'b');
    }
}
