package org.karpinen.ael.math.number.base;

import java.util.List;

public interface Base {
    String          base2     (String s);
    String          base8     (String s);
    String          base10    (String s);
    String          base16    (String s);
    List<Character> constraint();
    List<String>    aliases();
    int             id();
    char            suffix();
}
