package load;

import math.operand.Operand;
import math.operand.OperandFactory;

public class Main {

    public static void main(String[] args) {
        OperandFactory factory = new OperandFactory();
        Operand op1 = factory.getNumber("sin0110b");
        System.out.println("Base: " + op1.getSystem().getEnum().getID());
        System.out.println("Modified: " + op1.getModifiedInput());
        System.out.println("Evaluated: " + op1.getEvaluated());
        System.out.println("--------------------");
        Operand op2 = factory.getNumber("tanF.h");
        System.out.println("Base: " + op2.getSystem().getEnum().getID());
        System.out.println("Modified: " + op2.getModifiedInput());
        System.out.println("Evaluated: " + op2.getEvaluated());
        System.out.println("--------------------");
        Operand op3 = factory.getNumber("cos27o");
        System.out.println("Base: " + op3.getSystem().getEnum().getID());
        System.out.println("Modified: " + op3.getModifiedInput());
        System.out.println("Evaluated: " + op3.getEvaluated());
        System.out.println("--------------------");
    }
}
