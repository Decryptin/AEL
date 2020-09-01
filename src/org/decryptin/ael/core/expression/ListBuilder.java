package org.decryptin.ael.core.expression;

import org.decryptin.ael.core.operation.Operation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListBuilder {

    public @NotNull List<int[]> getOperatorList (@NotNull String s) {
        int operatorCount     = 0;
        @NotNull List<int[]> operators = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int x = 2; x > 0; x--) {
                String[] substring = getSubstring(s, i, (i+x)).split(":");
                if (Operation.getOperatorList().contains(substring[0])) {
                    int   endpoint   = Integer.parseInt(substring[1]);
                    int @NotNull [] nextCoordinate = getCoordinates(i, endpoint);
                    if (operatorCount > 0 && substring[0].equalsIgnoreCase("-")) {
                        int[] lastCoordinate = operators.get(operatorCount - 1);
                        if (lastCoordinate[1] == nextCoordinate[0]) {
                            break;
                        }
                    }else if(nextCoordinate[0] == 0 && !Objects.requireNonNull
                            (Operation.getOperation(substring[0])).isSpecial()) {
                        break;
                    }

                    operators.add (nextCoordinate);
                    operatorCount++;
                    i                = endpoint - 1;
                    break;
                }
            }
        }
        return operators;
    }

    private @NotNull String getSubstring (@NotNull String s, int start, int end) {
        String substring;
        boolean outOfBounds = end > s.length();
        int     endpoint;
        if (outOfBounds) {
            endpoint = s.length() - (s.length() - start);
        } else {
            endpoint = end;
        }
        substring = s.substring (start, endpoint);
        return substring + ":" + endpoint;
    }

    private int @NotNull [] getCoordinates (int start, int end) {
        int @NotNull [] coordinate = new int[2];
        coordinate[0]    = start;
        coordinate[1]    = end;
        return coordinate;
    }
}
