package org.karpinen.ael.math.base;

public class Base8 implements Base {
    private final BaseType baseType = BaseType.Base8;
    private final String   input;

    /*
    We store a base2 object in memory to avoid creating multiple instances.
    We convert to base2 -> targetBase using @base2 object methods.
     */

    private final Base2    base2;

    //Initiation.
    public Base8(String input) {
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
        StringBuilder oct = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '0': break;
                case '1': oct.append ("001"); break;
                case '2': oct.append ("010"); break;
                case '3': oct.append ("011"); break;
                case '4': oct.append ("100"); break;
                case '5': oct.append ("101"); break;
                case '6': oct.append ("110"); break;
                case '7': oct.append ("111"); break;
            }
        }

        //For loop is used to remove trailing 0's as the beginning of the string.
        for(int i = 0; i < oct.length(); i++) {
            if(oct.charAt(i) != '0') {
                break;
            }else{
                oct.deleteCharAt(i);
            }
        }
        return oct.toString();
    }

    @Override
    public String base8() {
        return input;
    }

    @Override
    public String base10() {
        return base2.base10();
    }

    @Override
    public String base16() {
        return base2.base16();
    }
}
