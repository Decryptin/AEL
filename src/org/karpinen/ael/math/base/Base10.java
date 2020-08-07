package org.karpinen.ael.math.base;

public class Base10 implements Base {
    private final BaseType baseType = BaseType.Base10;
    private final String   input;

    /*
    We store a base2 object in memory to avoid creating multiple instances.
    We convert to base2 -> targetBase using @base2 object methods.
    */

    private Base2    base2;

    //Initiation.
    public Base10(String input) {
        this.input = input;
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
        StringBuilder base2 = new StringBuilder();
        double dividend        = Integer.parseInt (input);
        while (dividend > 0) {
            base2.append (dividend % 2);
            dividend /= 2;
        }
        if(base2 == null) {
            this.base2 = new Base2(base2());
        }
        return base2.reverse().toString();
    }

    @Override
    public String base8() {
        return base2.base8();
    }

    @Override
    public String base10() {
        return input;
    }

    @Override
    public String base16() {
        return base2.base16();
    }
}
