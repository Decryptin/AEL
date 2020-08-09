package math.convert;

import math.base.BaseSystemController;
import math.base.BaseSystem;
import math.operand.Operand;

public class Base16 extends Operand implements BaseSystemController, BaseConvert {
    private final BaseSystem     system = BaseSystem.base16;
    private       Base2          base2;

    public Base16(String input) {
        super(input);
        create(this);
        setBase();
        setEvaluated();
    }

    @Override
    public String convertToBase2() {
        StringBuilder bin = new StringBuilder();
        for (char c : getModifiedInput().toCharArray()) {
            switch (c) {
                case '0': bin.append ("0000"); break;
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
        return bin.toString();
    }

    @Override
    public String convertToBase8() {
        return base2.convertToBase8();
    }

    @Override
    public String convertToBase10() {
        return base2.convertToBase10();
    }

    @Override
    public String convertToBase16() {
        return getModifiedInput();
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
