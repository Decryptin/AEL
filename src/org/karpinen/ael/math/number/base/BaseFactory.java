package org.karpinen.ael.math.number.base;

public class BaseFactory {

    public Base getBaseByID(int baseID) {
        switch (baseID) {
            case 2:  return new  Base2();
            case 8:  return new  Base8();
            case 10: return new Base10();
            case 16: return new Base16();
        }
        return null;
    }

    public Base getBaseBySuffix(char suffix) {
        switch (suffix) {
            case 'b': return new Base2();
            case 'o': return new Base8();
            case 'h': return new Base16();
            default:  return new Base10();
        }
    }
}
