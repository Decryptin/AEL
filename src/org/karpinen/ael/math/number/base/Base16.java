package org.karpinen.ael.math.number.base;

import java.util.Arrays;
import java.util.List;

public class Base16 implements Base {

    @Override
    public List<Character> constraint() {
        return Arrays.asList('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F');
    }

    @Override
    public List<String> aliases() {
        return Arrays.asList("hexadecimal", "hex", "base16");
    }

    @Override
    public int id() {
        return 16;
    }

    @Override
    public char suffix() {
        return 'h';
    }

    @Override
    public String base2(String s) {
        StringBuilder bin = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '0': bin.append ("0000"); break;
                case '1': bin.append ("0001"); break;
                case '2': bin.append ("0010"); break;
                case '3': bin.append ("0011"); break;
                case '4': bin.append ("0100"); break;
                case '5': bin.append ("0101"); break;
                case '6': bin.append ("0110"); break;
                case '7': bin.append ("0111"); break;
                case '8': bin.append ("1000"); break;
                case '9': bin.append ("1001"); break;
                case 'A': bin.append ("1010"); break;
                case 'B': bin.append ("1011"); break;
                case 'C': bin.append ("1100"); break;
                case 'D': bin.append ("1101"); break;
                case 'E': bin.append ("1110"); break;
                case 'F': bin.append ("1111"); break;
            }
        }
        return bin.toString();
    }

    @Override
    public String base8(String s) {
        return new Base2().base8(base2(s));
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
