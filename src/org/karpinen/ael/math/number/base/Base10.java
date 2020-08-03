package org.karpinen.ael.math.number.base;

import java.util.Arrays;
import java.util.List;

public class Base10 implements Base {

    @Override
    public List<Character> constraint() {
        return Arrays.asList('0','1','2','3','4','5','6','7','8','9','.','-');
    }

    @Override
    public List<String> aliases() {
        return Arrays.asList("decimal", "dec", "base10");
    }

    @Override
    public int id() {
        return 10;
    }

    @Override
    public char suffix() {
        return 'd';
    }

    @Override
    public String base2(String s) {
        StringBuilder base2 = new StringBuilder();
        int dividend        = Integer.parseInt (s);
        while (dividend > 0) {
            base2.append (dividend % 2);
            dividend /= 2;
        }
        return base2.reverse().toString();
    }

    @Override
    public String base8(String s) {
        return new Base2().base8(base2(s));
    }

    @Override
    public String base10(String s) {
        return s;
    }

    @Override
    public String base16(String s) {
        return new Base2().base16(base2(s));
    }
}
