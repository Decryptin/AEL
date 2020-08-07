package org.karpinen.ael.math.base;

public class Base2 implements Base {
    private final BaseType baseType = BaseType.Base2;
    private final String   input;

    //Initiation.
    public Base2(String input) {
        this.input = input;
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public BaseType getBaseType() { return baseType; }

    /*
    Following methods are used to convert to othr numeral systems.
     */

    @Override
    public String base2() {
        return input; }

    @Override
    public String base8() {
        return convertByBitGroups(3, input);
    }

    @Override
    public String base10() {
        return base10(input);
    }

    //Method Overloading to allow @convertByBitGroups to function properly.
    public String base10(String s) {
        char[] chars = new StringBuilder(s).reverse().toString().toCharArray();
        int iteration = 0;
        int sum = 0;
        for (char c: chars)
        {
            if (c == '1')
            {
                sum += Math.pow (2, iteration);
            }
            iteration++;
        }
        return String.valueOf(sum);
    }

    @Override
    public String base16() {
        return convertByBitGroups(4, input);
    }

    //Method takes group sizes of 3/4 and converts to Octal or Hexadecimal accordingly.
    private String convertByBitGroups (int groupSize, String input)
    {
        StringBuilder sb = new StringBuilder();

        //Specify how many iterations the string will require to convert.
        int iterations = input.length() / groupSize;
        int endpoint = input.length();

        for(int i = 0; i < iterations; i++)
        {
            /*
            Substring of the input string by size starting at end the of the string
            and working it's way towards the beginning.
             */
            String substring = getSubstringBySize (groupSize, endpoint, input);

            //Decimal value of the bit group substring.
            int conversion = Integer.parseInt(base10(substring));

            //Checks to see if the bit grouping is a Hexadecimal digit.
            if (conversion > 9 && conversion < 16)
            {
                sb.append (getHexadecimalEquivalent(conversion));
            }
            else
            {
                //Not a hexadecimal digit, normal digit append.
                sb.append (conversion);
            }
            endpoint -= groupSize;
        }
        //Due to the group size iterations, there could be leftover digits, this handles those.
        return concatRemaining (endpoint, groupSize, sb, input).reverse().toString();
    }

    //Gets remaining digits and appends the decimal value of the group.
    private StringBuilder concatRemaining (int endpoint, int groupSize, StringBuilder sb, String input)
    {
        boolean hasRemainder = input.length() % groupSize > 0;
        if (hasRemainder)
        {
            String remainder = input.substring (endpoint - (input.length() % groupSize), endpoint);
            sb.append (base10(remainder));
        }
        return sb;
    }

    //Returns a substring of a string based on size, endpoint.
    private String getSubstringBySize (int groupSize, int endpoint, String input) {
        return input.substring (endpoint - groupSize, endpoint);
    }

    //Returns hexadecimal equivalent based on integer input.
    private Character getHexadecimalEquivalent (int i) {
        switch (i) {
            case 10: return 'A';
            case 11: return 'B';
            case 12: return 'C';
            case 13: return 'D';
            case 14: return 'E';
            case 15: return 'F';
        }
        return null;
    }
}
