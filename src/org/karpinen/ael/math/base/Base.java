package org.karpinen.ael.math.base;

public interface Base {

    //BaseType is stored inside the @Base Object.
    BaseType getBaseType();

    //Input is stored in the @Base object.
    String   getInput();

    //Methods used to convert the input to other numeral systems.
    String   base2();
    String   base8();
    String   base10();
    String   base16();
}
