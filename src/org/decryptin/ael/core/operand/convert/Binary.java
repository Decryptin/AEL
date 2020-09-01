package org.decryptin.ael.core.operand.convert;

import org.jetbrains.annotations.NotNull;

public class Binary implements Convert {

    @Override
    public String binary           (String s) {
        return s;
    }

    @Override
    public @NotNull String octal   (@NotNull String s) {
        return convertByBitGroups(3, s);
    }

    @Override
    public @NotNull String decimal (@NotNull String s) {
        char[] chars = new StringBuilder(s).reverse().toString().toCharArray();
        int iteration = 0;
        int sum = 0;
        for (char c: chars) {
            if (c == '1') {
                sum += Math.pow (2, iteration);
            }
            iteration++;
        }
        return String.valueOf(sum);
    }

    @Override
    public @NotNull String hexadecimal (@NotNull String s) {
        return convertByBitGroups(4, s);
    }

    private @NotNull String getSubstringBySize (int groupSize, int startPoint, @NotNull String input) {
        return input.substring (startPoint - groupSize, startPoint);
    }

    private Character getHexadecimalEquivalent (int i) {
        char c = '\u0020';
        switch (i) {
            case 10: c = 'A'; break;
            case 11: c = 'B'; break;
            case 12: c = 'C'; break;
            case 13: c = 'D'; break;
            case 14: c = 'E'; break;
            case 15: c = 'F'; break;
        }
        return c;
    }

    private @NotNull StringBuilder concatRemaining (int endpoint, int groupSize, @NotNull StringBuilder sb, @NotNull String input) {
        boolean hasRemainder = input.length() % groupSize > 0;
        if (hasRemainder) {
            @NotNull String remainder = input.substring (endpoint - (input.length() % groupSize), endpoint);
            int    value     = Integer.parseInt(decimal(remainder));
            if(value > 0) {
                sb.append (decimal(remainder));
            }
        }
        return sb;
    }

    private @NotNull String convertByBitGroups (int groupSize, @NotNull String input) {
        @NotNull StringBuilder sb = new StringBuilder();
        int iterations = input.length() / groupSize;
        int startPoint = input.length();

        for (int i = 0; i < iterations; i++) {
            @NotNull String substring = getSubstringBySize (groupSize, startPoint, input);
            int conversion = Integer.parseInt(decimal(substring));
            if (conversion > 9) {
                sb.append (getHexadecimalEquivalent(conversion));
            } else {
                sb.append (conversion);
            }
            startPoint -= groupSize;
        }
        return concatRemaining (startPoint, groupSize, sb, input).reverse().toString();
    }
}
