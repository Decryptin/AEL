package math.operand;

import math.convert.Base10;
import math.convert.Base16;
import math.convert.Base2;
import math.convert.Base8;

public class OperandFactory {

    public Operand getNumber(String input) {
        int    length   = input.length();
        char   lastChar = input.charAt(length - 1);
        switch (lastChar) {
            case 'h': return new Base16(input);
            case 'o': return new Base8 (input);
            case 'b': return new Base2 (input);
            default:  return new Base10(input);
        }
    }
}
