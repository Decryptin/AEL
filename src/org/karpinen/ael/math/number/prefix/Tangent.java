package org.karpinen.ael.math.number.prefix;

public class Tangent implements Prefix {
    private double argument;

    @Override
    public String getID() {
        return "tan";
    }

    @Override
    public double getResult() {
        return Math.tan(argument);
    }

    @Override
    public void setArgs(double argument) {
        this.argument = argument;
    }
}
