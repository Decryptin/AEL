package org.decryptin.ael.core.operand.convert;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Octal implements Convert {

    @Override
    public @NotNull String binary (@NotNull String s) {
        @NotNull StringBuilder oct = new StringBuilder();
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
    public String octal       (String s) {
        return s;
    }

    @Override
    public String decimal     (@NotNull String s) {
        return Objects.requireNonNull(Converter.getConverter()).getBinary().decimal(binary(s));
    }

    @Override
    public String hexadecimal (@NotNull String s) {
        return Objects.requireNonNull(Converter.getConverter()).getBinary().hexadecimal(binary(s));
    }
}
