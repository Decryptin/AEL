package org.decryptin.ael.core.operand.convert;

import org.jetbrains.annotations.NotNull;

public class Decimal implements Convert {

    @Override
    public @NotNull String binary (@NotNull String s) {
        @NotNull StringBuilder base2 = new StringBuilder();
        int dividend = (int) Math.abs(Double.parseDouble(s));
        while (dividend > 0) {
            base2.append (dividend % 2);
            dividend /= 2;
        }
        return base2.reverse().toString();
    }

    @Override
    public String octal       (@NotNull String s) {
        return Converter.getConverter().getBinary().octal(binary(s));
    }

    @Override
    public String decimal     (String s) {
        return s;
    }

    @Override
    public String hexadecimal (@NotNull String s) {
        return Converter.getConverter().getBinary().hexadecimal(binary(s));
    }
}
