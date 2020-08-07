package org.karpinen.ael.math.base;

public class Base16 implements Base {
    private final BaseType baseType = BaseType.Base16;
    private final String   input;

    /*
    We store a base2 object in memory to avoid creating multiple instances.
    We convert to base2 -> targetBase using @base2 object methods.
    */

    private final Base2    base2;

    public Base16(String input) {
        this.input = input;
        this.base2 = new Base2(base2());
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public BaseType getBaseType() {
        return baseType;
    }

    /*
    Following methods used in conversion process.
     */

    @Override
    public String base2() {
        StringBuilder bin = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '0': break;
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
        //For loop is used to remove trailing 0's as the beginning of the string.
        for(int i = 0; i < bin.length(); i++) {
            if(bin.charAt(i) != '0') {
                break;
            }else{
                bin.deleteCharAt(i);
            }
        }
        return bin.toString();
    }

    @Override
    public String base8() {
        return base2.base8();
    }

    @Override
    public String base10() {
        return base2.base10();
    }

    @Override
    public String base16() {
        return input;
    }
}
