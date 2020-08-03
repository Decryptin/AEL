package org.karpinen.ael.math.number.prefix;

import java.util.Arrays;
import java.util.List;

public class PrefixFactory {

    public Prefix getPrefix(String s) {
        for(String prefix : getPrefixes()) {
            if(s.contains(prefix)) {
                return resolvePrefix(prefix);
            }
        }
        return null;
    }

    private Prefix resolvePrefix(String prefix) {
        switch (prefix) {
            case "cos": return new Cosine();
            case "sin": return new Sine();
            case "tan": return new Tangent();
        }
        return null;
    }

    public List<String> getPrefixes() {
        return Arrays.asList("cos", "sin", "tan");
    }
}
