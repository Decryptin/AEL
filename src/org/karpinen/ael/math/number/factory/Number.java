package org.karpinen.ael.math.number.factory;

import org.karpinen.ael.math.number.base.Base;
import org.karpinen.ael.math.number.base.BaseFactory;

public class Number {
    private final BaseFactory factory = new BaseFactory();
    private final String      input;
    private Base              base;
    private final boolean     valid;
    private final int         limit = 128;

    public Number(String input, Object id) {
        setBase(id);
        this.input = input;
        this.valid = getValidity();
    }

    private boolean getValidity() {
        if(!meetConstraint()) {
            return false;
        }
        return input.length() < limit;
    }

    private void setBase(Object o) {
        if(o instanceof Integer) {
            this.base = factory.getBaseByID((int) o);
        }else{
            this.base = factory.getBaseBySuffix((char) o);
        }
    }

    private boolean meetConstraint() {
        for(char c : input.toCharArray()) {
            if(!base.constraint().contains(c)) {
                return false;
            }
        }
        return true;
    }

    public Base getBase() {
        if(isValid()) {
            return base;
        }
        return null;
    }

    public String getInput() {
        return input;
    }

    public boolean isValid() {
        return valid;
    }

    public int getLimit() {
        return limit;
    }

    public String convert(int id) {
        if(isValid()) {
            switch (id) {
                case 2:  return base.base2(input);
                case 8:  return base.base8(input);
                case 10: return base.base10(input);
                case 16: return base.base16(input);
            }
        }
        return null;
    }
}
