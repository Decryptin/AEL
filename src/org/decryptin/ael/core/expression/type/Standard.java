package org.decryptin.ael.core.expression.type;

import org.decryptin.ael.core.exception.ExpressionFormatException;
import org.decryptin.ael.core.exception.OperandFormatException;
import org.decryptin.ael.core.expression.Controller;
import org.decryptin.ael.core.expression.Expression;
import org.decryptin.ael.core.expression.ExpressionSystem;
import org.decryptin.ael.core.expression.ExpressionUtility;
import org.decryptin.ael.core.operand.Operand;
import org.decryptin.ael.core.operation.OperationSet;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Standard extends Expression implements Controller {

    public Standard (String expression, String targetID) throws OperandFormatException, ExpressionFormatException {
        init  (expression, ExpressionSystem.Standard, targetID);
        solve ();
    }

    @Override
    public void solve () throws OperandFormatException, ExpressionFormatException {
        String working = getExpression();
        ExpressionUtility.getUtility()
                .validate(working, ExpressionUtility.getUtility().getOperatorList(working), ExpressionSystem.Standard);
        while (ExpressionUtility.getUtility().hasOperator(working)) {
            @NotNull List<int[]>  operators = ExpressionUtility.getUtility().getOperatorList    (working);
            int          priority           = ExpressionUtility.getUtility().getHighestPriority (working, operators);
            if (priority < 4) {
                if(!operators.isEmpty()) {
                    int                   next = ExpressionUtility.getUtility().getNextOperatorIndex(priority, working, operators);
                    @NotNull OperationSet set  = new OperationSet  (operators, next, working);
                    working                    = working.replace   (set.getReplace(), String.valueOf(set.getSolution()));
                    java.lang.System.out.println                   ("working: " + working);
                }else{
                    break;
                }
            }
        }
        setSolution                 (new Operand(working));
        java.lang.System.out.println("Solution: " + getSolution());
    }
}
