package org.karpinen.ael.math.expression;

import org.jetbrains.annotations.NotNull;
import org.karpinen.ael.math.operation.OperatorType;

import java.util.ArrayList;
import java.util.List;

public class StandardExpression {
    private List<int[]> operatorList = new ArrayList<>();
    private String      finalized;
    private boolean     valid        = true;

    public StandardExpression(String expression) {
        //Initial operator's object build.
        List<int[]> operators = new ArrayList<>();
        updateOperators(expression, operators);

        //We need to validate the expression when constructor is called.
        startValidation(expression, operators);
    }

    //Used to the update the @operatorList, used by the StandardExpressionSolver class.
    public void update(String expression) {
        //Here we set @operatorList equal to the list of operators that @setOperators builds.
        updateOperators(expression, operatorList);

        //Set the expression equal to our updated expression.
        finalized = expression;
        valid = true;
    }

    //Validate the expression.
    private void startValidation(String e, List<int[]> operators) {
        //If there's a operator at the beginning of the string which is invalid, we remove from the expression.
        removeInvalidStartingOperator(e, operators);

        //If there's a operator at the end of the string which is invalid, we remove it from the expression.
        removeInvalidEndOperator(e, operators);

        //Update the operators list.
        updateOperators(finalized, operatorList);

        //Set the boolean @valid to the boolean validateSpecialOperators returns.
        valid = validateSpecialOperators(finalized, operatorList);
    }


    private void updateOperators(@NotNull String e, List<int[]> operators) {
        //We need to empty the operator list prior to adding the next set of operators.
        operatorList.clear();


        //@operatorCount is used as a conditional of whether we have a previous operator or not.
        int operatorCount = 0;

        //@parent_loop iterates every character in the expression string.
        for (int i = 0; i < e.length(); i++) {

            /*
            @child_loop decrements the value of x. @i & @x are added together to get the max range for an operator.
            This is done so we check for the longest length operators first to avoid adding an operator that contains
            another operator first. i.e, '^' is an operator but is also part of '^^', and '^/' operators.
             */

            for (int x = 2; x > 0; x--) {

                //Returns a string of this format which is then split into an array. '<operator>:<endpoint>'
                String[] substring = getSubstring(e, i, i + x).split(":");
                String op = substring[0];
                if (OperatorType.getOperatorList().contains(op)) {
                    //Endpoint for the current operator in param @e
                    int endpoint = Integer.parseInt(substring[1]);

                    /*
                    Get's an int[] object containing the start point and endpoint of the operator.
                    int[0] = operator, int[1] = endpoint
                     */

                    //Coordinate for the next operator.
                    int[] nextCoordinate = getCoordinateArray(i, endpoint);

                    //Check's if there's a previous operator and if the current operator is '-'.
                    if (operatorCount > 0 && op.equalsIgnoreCase("-")) {
                        int[] lastCoordinate = operators.get(operatorCount - 1);

                        //Are the operators parallel?
                        if (areParallelOperators(lastCoordinate, nextCoordinate)) {

                            /*
                            At this point the operator is '-' and we have parallel operators, so we'll break to allow
                            the program to evaluate it as a negative number.
                             */

                            break;
                        }
                    }

                    //Add the operator.
                    operators.add(nextCoordinate);

                    //Increment the operator count;
                    operatorCount++;

                    //Set @parent_loop @i equal to endpoint-1 so we don't do unnecessary iterations.
                    i = endpoint - 1;
                    break;
                }
            }
        }
    }

    //Check's if the end coordinate of the lastOP == the start coordinate of the nextOP
    private boolean areParallelOperators(int[] lastCoordinate, int[] nextCoordinate) {
        return lastCoordinate[1] == nextCoordinate[0];
    }

    //Returns a coordinate array based off the @params.
    private int[] getCoordinateArray(int start, int end) {
        int[] coordinate = new int[2];
        coordinate[0] = start;
        coordinate[1] = end;
        return coordinate;
    }

    //Removes the starting operator from the string if it's invalid.
    private void removeInvalidStartingOperator(@NotNull String e, @NotNull List<int[]> operators) {
        int[] coordinate = operators.get(0);
        String op = e.substring(coordinate[0], coordinate[1]);
        if(coordinate[0] == 0 && !op.equals("^^") && !op.equals("^/")) {
            this.finalized = e.substring(coordinate[1]);
        }else{
            finalized = e;
        }
    }

    //Removes the ending operator from the string if it's invalid.
    private void removeInvalidEndOperator(@NotNull String e, @NotNull List<int[]> operators) {
        int[] coordinate = operators.get(operators.size() - 1);
        if(coordinate[1] == e.length()) {
            finalized = finalized.substring(0, coordinate[0] - 1);
        }else{
            finalized = e;
        }
    }

    //Validates all special operators have a prior parallel operator.
    private boolean validateSpecialOperators(String e, @NotNull List<int[]> operators) {
        int iteration = 0;
        for(int[] i : operators) {
            iteration++;
            if (iteration > 1) {
                int[] lastCoordinate = operators.get(iteration - 2);
                String lastOP = e.substring(lastCoordinate[0], lastCoordinate[1]);
                if (areParallelOperators(lastCoordinate, i)) {
                    if (lastOP.equals("^^") || lastOP.equals("^/") || lastOP.equals("^")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Returns a substring based off @start & @end
    private @NotNull String getSubstring(@NotNull String s, int start, int end) {
        String substring;

        //boolean is used to avoid IndexOutOfBounds exceptions.
        boolean outOfBounds = end > s.length();

        //Endpoint of the substring is TBD at this instruction.
        int endpoint;

        //If the string is out of bounds we need to calculate where the endpoint should be.
        if (outOfBounds) {
            endpoint = s.length() - (s.length() - start);
        } else {
            endpoint = end;
        }

        //Retrieve the substring.
        substring = s.substring(start, endpoint);
        //Format: substring:endpoint
        return substring + ":" + endpoint;
    }

    //Returns the operator list.
    public List<int[]> getOperator() {
        return operatorList;
    }

    //Returns the current expression.
    public String getExpression() {
        return finalized;
    }

    //Returns whether the expression is valid or not.
    public boolean isValid() {
        return valid;
    }

}
