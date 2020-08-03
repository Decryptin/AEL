package org.karpinen.ael.math.expression.operator;

import org.karpinen.ael.math.number.factory.NumberFactory;

public class Addition implements Operator {
    private NumberFactory factory = new NumberFactory();
    private String[]      args;
    private final int[]   coordinate;
    private double[]      parsed;

    public Addition(int[] coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public int getArgCount () {

        return 2;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public double getResult() {
        return parsed[0] + parsed[1];
    }

    @Override
    public String getID() {
        return "+";
    }

    @Override
    public int[] getCoordinates() {
        return coordinate;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
        parsed    = new double[2];
        parsed[0] = Double.parseDouble(factory.getNumber(args[0]).convert(10));
        parsed[1] = Double.parseDouble(factory.getNumber(args[1]).convert(10));
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public String getReplacement() {
        return args[0] + getID() + args[1];
    }
}
