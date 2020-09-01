package org.decryptin.ael.core.exception;

public class ExpressionFormatException extends Exception {

    public ExpressionFormatException (String reason, String context, String systemID) {
        System.out.println ("EFE-" + "Invalid[" + reason + "]: " + context + " for type " + systemID);
    }
}
