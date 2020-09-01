package org.decryptin.ael.core.operand.convert;

import org.jetbrains.annotations.NotNull;

public class Hexadecimal implements Convert {

    @Override
    public @NotNull String binary (@NotNull String s) {
        @NotNull StringBuilder bin = new StringBuilder();
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
    public String octal       (@NotNull String s) {
        return Converter.getConverter().getBinary().octal(binary(s));
    }

    @Override
    public String decimal     (@NotNull String s) {
        return Converter.getConverter().getBinary().decimal(binary(s));
    }

    @Override
    public String hexadecimal (String s) {
        return s;
    }
}
