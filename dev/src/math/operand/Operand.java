package math.operand;

import math.base.BaseSystem;
import math.prefix.Prefix;
import math.base.BaseSystemController;

public class Operand {
    private final String         rawInput;
    private String               modifiedInput;
    private double               evaluated;
    private BaseSystemController system;
    private Prefix               prefix;
    private boolean              valid;

    public Operand(String input) {
        this.rawInput = input;
    }

    public void create(BaseSystemController controller) {
        setMathPrefix();
        setSystem(controller);
        setModifiedInput();
        valid = validate();
    }

    private void replacePrefix() {
        if(prefix != null) {
            this.modifiedInput = modifiedInput.replace(prefix.getID(), "");
        }
    }

    private void replaceSuffix() {
        for(char c : BaseSystem.getSuffixes()) {
            if(getModifiedInput().endsWith(String.valueOf(c))) {
                this.modifiedInput = modifiedInput.substring(0, modifiedInput.length() - 1);
            }
        }
    }

    private boolean validate() {
        for(char c : modifiedInput.toCharArray()) {
            if(!getSystem().getEnum().getConstraint().contains(c)) {
                return false;
            }
        }
        return modifiedInput.length() < getSystem().getEnum().getLengthLimit();
    }

    public BaseSystemController getSystem() {
        return system;
    }

    public String getRawInput() {
        return rawInput;
    }

    public String getModifiedInput() {
        return modifiedInput;
    }

    public double getEvaluated() {
        return evaluated;
    }

    protected void setSystem(BaseSystemController system) {
        this.system = system;
    }

    private void setMathPrefix() {
        for(String prefix : Prefix.getMathPrefixes()) {
            if (rawInput.contains(prefix)) {
                this.prefix = Prefix.getMathPrefix(prefix);
            }
        }
    }

    private void setModifiedInput() {
        this.modifiedInput = rawInput;
        replacePrefix();
        replaceSuffix();
    }

    protected void setEvaluated() {
        if(valid) {
            String base10 = getSystem().getConversion().convertToBase10();
            double d      = Double.parseDouble(base10);
            if(prefix != null) {
                this.evaluated = prefix.evaluate(d);
            }else{
                this.evaluated = d;
            }
        }else{
            System.out.println("Error: Type Mismatch.");
        }
    }
}
