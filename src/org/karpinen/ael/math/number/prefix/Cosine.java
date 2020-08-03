package org.karpinen.ael.math.number.prefix;

public class Cosine implements Prefix {
    private double argument;

    @Override
    public String getID() {
        return "cos";
    }

    @Override
    public double getResult() {
        return Math.cos(argument);
    }

    @Override
    public void setArgs(double argument) {
        this.argument = argument;
    }
}
