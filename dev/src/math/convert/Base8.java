package math.convert;

import math.base.BaseSystemController;
import math.base.BaseSystem;
import math.operand.Operand;

public class Base8 extends Operand implements BaseSystemController, BaseConvert {
    private final BaseSystem system = BaseSystem.base8;
    private       Base2          base2;

    public Base8(String input) {
        super(input);
        create(this);
        setBase();
        setEvaluated();
    }

    @Override
    public String convertToBase2() {
        StringBuilder oct = new StringBuilder();
        for (char c : getModifiedInput().toCharArray()) {
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
    public String convertToBase8() { return getModifiedInput();
    }

    @Override
    public String convertToBase10() {
        return base2.convertToBase10();
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
