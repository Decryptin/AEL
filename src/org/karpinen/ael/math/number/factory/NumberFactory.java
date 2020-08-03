package org.karpinen.ael.math.number.factory;

import org.karpinen.ael.math.number.prefix.Prefix;
import org.karpinen.ael.math.number.prefix.PrefixFactory;

public class NumberFactory {
    private PrefixFactory factory = new PrefixFactory();

    public Number getNumber(String argument) {
        if(hasPrefix(argument)) {
            Prefix prefix = factory.getPrefix(argument);
            String number = argument.replace(prefix.getID(), "");
            Number n = resolveNumber(number);
            prefix.setArgs(Double.parseDouble(n.convert(10)));
            Number solution = new Number(String.valueOf(prefix.getResult()), 10);
            return solution;
        }
        return resolveNumber(argument);
    }

    private Number resolveNumber(String argument) {
        char c     = argument.charAt(argument.length() - 1);
        String arg = argument.substring(0, argument.length() - 1);
        switch (c) {
            case 'h': return new Number(arg.replace('h', '\u0020'), 16);
            case 'o': return new Number(arg.replace('o', '\u0020'), 8);
            case 'b': return new Number(arg.replace('b', '\u0020'), 2);
            default:
                return new Number(argument, 10);
        }
    }

    private boolean hasPrefix(String argument) {
        if (factory.getPrefix(argument) != null) {
            return true;
        }
        return false;
    }
}
