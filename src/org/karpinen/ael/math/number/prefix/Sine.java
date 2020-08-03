package org.karpinen.ael.math.number.prefix;

public class Sine implements Prefix {
    private double argument;

    @Override
    public String getID() {
        return "sin";
    }

    @Override
    public double getResult() {
        return Math.sin(argument);
    }

    @Override
    public void setArgs(double argument) {
        this.argument = argument;
    }
}
