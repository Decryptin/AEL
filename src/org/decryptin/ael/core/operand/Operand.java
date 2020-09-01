package org.decryptin.ael.core.operand;

import org.decryptin.ael.core.exception.OperandFormatException;
import org.decryptin.ael.core.operand.prefix.Prefix;

public class Operand {

    /*
    @input represents the raw input during object creation. This includes any prefixes and type identifiers.
    @extract = @input without type identifiers & operand prefixes.
    @type = the operand type this instance conforms/obeys to.
    @prefix: if there's a prefix for the operand, it's enum will be located, otherwise the default type is "Type.NONE"
    @eval is the decimal double equivalent of @extract
     */

    private String  input;
    private String  extract;
    private Type    type;
    private Prefix  prefix;
    private double  eval;

    //Constructor methods follow a specific step-by-step process.
    public Operand (String operand) throws OperandFormatException {
        setInput    (operand);
        setType     (operand);
        setPrefix   (operand);
        setExtract  ();
        validate    ();
        setEvaluated();
    }

    private void validate() throws OperandFormatException {
        for (char c : extract.toCharArray()) {
            if (!getType().getCharacters().contains(c)) {
                throw new OperandFormatException ("Character", String.valueOf(c), getType().getSystemID());
            }
        }
    }

    //Returns the raw input passed in through constructor.
    public String getInput() {
        return this.input;
    }

    //
    public String getExtract() {
        return this.extract;
    }

    public Type getType() {
        return this.type;
    }

    public double getEvaluation() {
        return this.eval;
    }

    private void setInput (String operand) {
        this.input    = operand;
        this.extract  = input;
    }

    private void setType (String operand) {
        this.type = Type.getSystem(operand);
    }

    private void setPrefix (String operand) {
        this.prefix = Prefix.getPrefix(operand);
    }

    private void setExtract() {
        replacePrefix();
        replaceSuffix();
    }

    private void setEvaluated() {
        double[] d     = new double[1];
        d     [0]      = Double.parseDouble(convert("#d"));
        if (prefix != null) {
            d[0] = prefix.calculate(d);
        }
        this.eval = d[0];
    }

    private void replacePrefix() {
        if (prefix != null) {
            this.extract = extract.replace (prefix.getPrefixID(), "");
        }
    }

    private void replaceSuffix() {
        this.extract = extract.replace (getType().getSystemID(), "");
    }
}
