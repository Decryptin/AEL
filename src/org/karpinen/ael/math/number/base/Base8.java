package org.karpinen.ael.math.number.base;

import java.util.Arrays;
import java.util.List;

public class Base8 implements Base {

    @Override
    public List<Character> constraint() {
        return Arrays.asList('0','1','2','3','4','5','6','7');
    }

    @Override
    public List<String> aliases() {
        return Arrays.asList("octal", "oct", "base8");
    }

    @Override
    public int id() {
        return 8;
    }

    @Override
    public char suffix() {
        return 'o';
    }

    @Override
    public String base2(String s) {
        StringBuilder oct = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '0': break;
                case '1': oct.append ("001"); break;
                case '2': oct.append ("10");  break;
                case '3': oct.append ("11");  break;
                case '4': oct.append ("100"); break;
                case '5': oct.append ("101"); break;
                case '6': oct.append ("110"); break;
                case '7': oct.append ("111"); break;
            }
        }
        return oct.toString();
    }

    @Override
    public String base8(String s) {
        return s;
    }

    @Override
    public String base10(String s) {
        return new Base2().base10(base2(s));
    }

    @Override
    public String base16(String s) {
        return new Base2().base16(base2(s));
    }
}
