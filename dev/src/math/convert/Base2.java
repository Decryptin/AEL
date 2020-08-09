package math.convert;

import math.base.BaseSystemController;
import math.base.BaseSystem;
import math.operand.Operand;

public class Base2 extends Operand implements BaseSystemController, BaseConvert {
    private final BaseSystem system = BaseSystem.base2;

    public Base2(String input) {
        super(input);
        create(this);
        setEvaluated();
    }

    @Override
    //Converts to base 2, since this is a base2() object, we can just return the input.
    public String convertToBase2() {
        return getModifiedInput();
    }

    @Override
    //Converts to base 8, uses hybrid method @convertByBitGroups.
    public String convertToBase8() {
        return convertByBitGroups(3, getModifiedInput());
    }

    @Override
    //Converts to base 10 by long division.
    public String convertToBase10() {
        return convertToBase10(getModifiedInput());
    }

    private String convertToBase10(String input) {
        //Create a char[] of the string in reverse due to iterating left to right.
        char[] chars = new StringBuilder(input).reverse().toString().toCharArray();

        //@iteration represents the current iteration, used in Math.pow method.
        int iteration = 0;

        //@sum is the total value.
        int sum = 0;

        //Loops character by character.
        for (char c: chars) {
            //If the current character is a 1, we need to calculate the value of the bit placement.
            if (c == '1') {
                //We exponent 2 by the @iteration to get the bitwise value.
                sum += Math.pow (2, iteration);
            }
            //Increment the iteration.
            iteration++;
        }
        //Returns the converted value.
        return String.valueOf(sum);
    }

    @Override
    //Converts to base 16, uses hybrid method @convertByBitGroups.
    public String convertToBase16() {
        return convertByBitGroups(4, getModifiedInput());
    }

    @Override
    public BaseSystem getEnum() {
        return system;
    }

    @Override
    public BaseConvert getConversion() {
        return this;
    }

    //Returns a substring of @input based off @groupSize; substring starts at @startPoint
    private String getSubstringBySize (int groupSize, int startPoint, String input) {
        return input.substring (startPoint - groupSize, startPoint);
    }

    //Returns Hexadecimal character based off value of @param i
    private Character getHexadecimalEquivalent (int i) {
        char c = '\u0020';
        switch (i) {
            case 10: c = 'A'; break;
            case 11: c = 'B'; break;
            case 12: c = 'C'; break;
            case 13: c = 'D'; break;
            case 14: c = 'E'; break;
            case 15: c = 'F'; break;
        }
        return c;
    }

    //Hybrid function used to get base8 and base16 conversions.
    private String convertByBitGroups (int groupSize, String input) {
        //Blank StringBuilder used during substring iterations to mark value.
        StringBuilder sb = new StringBuilder();

        //@iterations represents the amount of even divisions we can get out the string based off @groupSize
        int iterations = input.length() / groupSize;

        //@StartPoint is equal to the end of the substring as we are iterating backwards.
        int startPoint = input.length();

        //Iterations loop.
        for (int i = 0; i < iterations; i++) {

            //Gets the substring for the current iteration.
            String substring = getSubstringBySize (groupSize, startPoint, input);

            //Decimal value of the current @substring
            int conversion = Integer.parseInt(convertToBase10(substring));

            //Branches here if the @conversion is > 9
            if (conversion > 9) {
                //Appends the hexadecimal value.
                sb.append (getHexadecimalEquivalent(conversion));
            } else {
                //Appends the decimal value if @conversion is < 10
                sb.append (conversion);
            }
            //Appends the hexadecimal value here if the
            startPoint -= groupSize;
        }
        //Since iterations only loops until all even groups are evaluated, there can be leftover digits.
        return concatRemaining (startPoint, groupSize, sb, input).reverse().toString();
    }

    //Gets a substring of remaining digits in the string and returns the decimal value.
    private StringBuilder concatRemaining (int endpoint, int groupSize, StringBuilder sb, String input) {
        //Boolean representing whether there are leftover bits.
        boolean hasRemainder = input.length() % groupSize > 0;

        //If there are leftover bits we need to evaluate them.
        if (hasRemainder) {

            //@remainder represent the leftover digits.
            String remainder = input.substring (endpoint - (input.length() % groupSize), endpoint);

            //Append the decimal value of remainder.
            sb.append (convertToBase10(remainder));
        }
        return sb;
    }
}
