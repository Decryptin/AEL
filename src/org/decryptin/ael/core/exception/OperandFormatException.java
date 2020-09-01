package org.decryptin.ael.core.exception;

public class OperandFormatException extends Exception {

    public OperandFormatException (String reason, String context, String systemID) {
        System.out.println("OFE-" + "Invalid[" + reason + "]: " + context + " for type " + systemID);
    }
}
