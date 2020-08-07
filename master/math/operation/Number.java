package org.karpinen.ael.math.operation;

import org.karpinen.ael.math.base.Base;
import org.karpinen.ael.math.base.BaseFactory;
import org.karpinen.ael.math.prefix.OperandPrefixType;

public class Number {
    private final BaseFactory   baseFactory   = new BaseFactory();
    private Base                base;
    private final boolean       valid;
    private final String        prefix;

    public Number(String input) {
        this.prefix = getPrefix(input);
        init(input);
        this.valid  = isValidInput();
    }

    private void init(String input) {
        String s = input;
        if(prefix != null) {
            s = input.replace(prefix, "");
        }
        this.base = baseFactory.getBase(s);
    }

    private String getPrefix(String s) {
        for(String prefix : OperandPrefixType.getPrefixList()) {
            if(s.contains(prefix)) {
                return prefix;
            }
        }
        return null;
    }

    private boolean isValidInput() {
        for(char c : getNumber().toCharArray()) {
            if(!base.getBaseType().getConstraint().contains(c)) {
                return false;
            }
        }
        return getNumber().length() < base.getBaseType().getLengthLimit();
    }

    public boolean isValid() {
        return valid;
    }

    public String getNumber() {
        return base.getInput();
    }

    public String convert(int id) {
        if(valid) {
            switch (id) {
                case 2:  return base.base2();
                case 8:  return base.base8();
                case 10: return base.base10();
                case 16: return base.base16();
            }
        }
        return null;
    }

    public String getPrefix() {
        return  prefix;
    }
}
