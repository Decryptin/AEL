package org.karpinen.ael.math.load;

import org.karpinen.ael.math.expression.type.Parenthetical;

public class Main {
    public static void main(String[] args) {
        Parenthetical parenthetical = new Parenthetical("(5*5(6+5(9/65)+5^3))");
        parenthetical.solveExpression();
    }
}
