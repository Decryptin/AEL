package org.karpinen.ael.math.base;

import java.util.Arrays;
import java.util.List;

public class BaseFactory {

    //Factory returns a base object based off input.
    public Base getBase(String input) {
        Base b = getBaseBySuffix(input);
        return getBaseBySuffix(input);
    }

    //Returns base object based on last character in the string.
    private Base getBaseBySuffix(String input) {
        char c = input.charAt(input.length() - 1);
        String withoutSuffix = input.substring(0, input.length() - 1);
        switch (c) {
            case 'h': return new Base16(withoutSuffix);
            case 'o': return new Base8 (withoutSuffix);
            case 'b': return new Base2 (withoutSuffix);
            default:  return new Base10(input);
        }
    }

    //List of all allowed suffixes.
    public List<String> getSuffixes() {
        return Arrays.asList("h","o","b");
    }
}
