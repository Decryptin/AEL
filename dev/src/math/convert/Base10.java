package math.convert;

import math.base.BaseSystemController;
import math.base.BaseSystem;
import math.operand.Operand;

public class Base10 extends Operand implements BaseSystemController, BaseConvert {
    private final BaseSystem system = BaseSystem.base10;
    private       Base2          base2;

    public Base10(String input) {
        super(input);
        create(this);
        setBase();
        setEvaluated();
    }

    @Override
    public String convertToBase2() {
        //New StringBuilder object to represent base2 value.
        StringBuilder base2 = new StringBuilder();

        //Create a dividend = value;
        int dividend = Integer.parseInt (getModifiedInput());

        //iterate until dividend < 1
        while (dividend > 0) {
            //Append the modulus of dividend % 2 to @base2
            base2.append (dividend % 2);
            dividend /= 2;
        }
        return base2.reverse().toString();
    }

    @Override
    public String convertToBase8() {
        return base2.convertToBase10();
    }

    @Override
    public String convertToBase10() {
        return getModifiedInput();
    }

    @Override
    public String convertToBase16() {
        return base2.convertToBase16();
    }

    @Override
    public BaseSystem getEnum() {
        return system;
    }

    @Override
    public BaseConvert getConversion() {
        return this;
    }

    private void setBase() {
        base2 = new Base2(convertToBase2());
    }
}
