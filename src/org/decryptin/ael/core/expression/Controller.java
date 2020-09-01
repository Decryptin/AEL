package org.decryptin.ael.core.expression;

import org.decryptin.ael.core.exception.ExpressionFormatException;
import org.decryptin.ael.core.exception.OperandFormatException;

public interface Controller {
    void solve () throws OperandFormatException, ExpressionFormatException;
}
