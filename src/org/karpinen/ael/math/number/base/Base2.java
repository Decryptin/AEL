package org.karpinen.ael.math.number.base;

import java.util.Arrays;
import java.util.List;

public class Base2 implements Base  {

    @Override
    public List<Character> constraint() {
        return Arrays.asList('0','1');
    }

    @Override
    public List<String> aliases() {
        return Arrays.asList("binary", "bin", "base2");
    }

    @Override
    public int id() {
        return 2;
    }

    @Override
    public char suffix() {
        return 'b';
    }

    @Override
    public String base2(String s) {
        return s;
    }

    @Override
    public String base8(String s) {
        return convertByBitGroups(3, s);
    }

    @Override
    public String base10(String s) {
        char[] chars  = new StringBuilder(s).reverse().toString().toCharArray();
        int iteration = 0;
        int sum       = 0;
        for (char c: chars) {
            if (c == '1') {
                sum += Math.pow (2, iteration);
            }
            iteration++;
        }
        return String.valueOf(sum);
    }

    @Override
    public String base16(String s) {
        return convertByBitGroups(4, s);
    }

    private String convertByBitGroups (int groupSize, String input) {
        StringBuilder sb = new StringBuilder();
        int iterations = input.length() / groupSize;
        int endpoint = input.length();
        for(int i = 0; i < iterations; i++) {
            String substring = getSubstringBySize (groupSize, endpoint, input);
            int conversion   = Integer.parseInt(base10(substring));
            if (conversion > 9 && conversion < 16) {
                sb.append (getHexadecimalEquivalent(conversion));
            }
            else {
                sb.append (conversion);
            }
            endpoint -= groupSize;
        }
        return concatRemaining (endpoint, groupSize, sb, input).reverse().toString();
    }

    private StringBuilder concatRemaining (int endpoint, int groupSize, StringBuilder sb, String input) {
        boolean hasRemainder = input.length() % groupSize > 0;
        if (hasRemainder) {
            String remainder = input.substring (endpoint - (input.length() % groupSize), endpoint);
            sb.append (base10(remainder));
        }
        return sb;
    }

    private String getSubstringBySize (int groupSize, int endpoint, String input) {
        return input.substring (endpoint - groupSize, endpoint);
    }

    private Character getHexadecimalEquivalent (int i) {
        switch (i) {
            case 10: return 'A';
            case 11: return 'B';
            case 12: return 'C';
            case 13: return 'D';
            case 14: return 'E';
            case 15: return 'F';
        }
        return null;
    }
}
