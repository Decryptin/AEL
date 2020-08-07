package org.karpinen.ael.math.operation;

import org.karpinen.ael.math.prefix.PrefixOperationFactory;

public class Operand {
    private final String        input;
    private final Number        number;
    private double              decimal;
    private double              finalized;

    public Operand(String input) {
        this.input  = input;
        this.number = new Number(input);
        init();
    }

    private void init() {
        convertToDecimal();
        if(number.getPrefix() != null) {
            PrefixOperationFactory operation = new PrefixOperationFactory(decimal, number.getPrefix());
            finalized = operation.evaluate();
        }else{
            finalized = decimal;
        }
    }

    public String getInput() {
        return input;
    }

    private void convertToDecimal() {
        this.decimal = Double.parseDouble(number.convert(10));
    }

    public double getFinalizedValue() {
        return finalized;
    }
}
