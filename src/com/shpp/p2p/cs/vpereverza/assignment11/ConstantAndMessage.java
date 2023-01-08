package com.shpp.p2p.cs.vpereverza.assignment11;

import java.util.ArrayList;
import java.util.HashMap;

public interface ConstantAndMessage {

    //Saves parsed previously entered formulas
    HashMap<String, ArrayList<String>> formuls = new HashMap<>();

    //Stores parsed parameters
    HashMap <String, Double> variables = new HashMap<>();

    String FRACTION_SIGN = "Hey. Are you sure the sign \".\" or \",\" is in the right place? "+
            "It seems that the fractional number is written incorrectly. Please check and correct it";

    String LOTS_OF_COMMA = "A fractional number can only have one sign. Review the spelling.";

    String FORMULA_IS_NOT_WRITTEN = "The formula cannot be calculated because it is not finished";

    String NOT_CLOSED_BRACKET = "The equation does not have a closed parenthesis. Check the spelling and enter correctly.";

    String INCORRECT_SIGN = "You have entered an incorrect character in a formula. " +
            "Review the entered data and enter it correctly.";

    String NAME_NOT_ONE_LETTER = "After the variable name (one letter) should be equal, " +
            "and then the value. Review the spelling and enter the value correctly";

    String NAME_NOT_LETTER = "The variable name is not a letter!" +
            "Review the rules for declaring variables and enter the value correctly.";

    String INCORRECT_FRACTION = "Incorrect input of a fractional number. " +
            "Check the correct placement of characters in the variables and enter the value correctly.";

    String PARAMETER_DOES_NOT_EXIST = " - no such variable exists." +
            "To perform calculations, add the values of this variable to the parameters.";

    String INCORRECT_CHARACTER_ORDER = "Some nonsense with signs. " +
            "Such an equation cannot be calculated. Check the spelling and enter the formula correctly.";

    String DO_NOT_ASSIGN = " cannot assign a value to a variable ";

    String CHANGING_THE_INPUT = ". Either announce it before assigning it to another variable," +
            "or check the correctness of the input.";

    String NULL_PARAMETER = "The empty value of the variable!" +
            " To count after equals in a variable, enter the value you want to assign to it.";

    String SUPER_EXCEPTION = "Whoa whoa whoa. What happened here? " +
            "Is this an exception that I didn't take into account? Or someone is cunning and introduced abracadabra";

    String UNKNOWN_FUNCTION= "An unknown function has been introduced. " +
            "This calculator supports the following functions: sin, cos, tan, atan, log10, log2, sqrt." +
            " Check the spelling and enter again.";

    String INCORRECT_VARIABLE_VALUE= "The variable cannot include math operations." +
            " Please enter a valid value.";

    String NO_PARAMETERS= "Oops. There are no parameters at all." +
            " In order for the program to calculate the equation, it must be written in the parameters.";

    String FORMULA_ERROR= "Oops. Something went wrong." +
            "The formulas are written incorrectly. Check, fix and try again";

    String MATHEMATICAL_REDUCTION= "Please do not use mathematical abbreviations. " +
            "Different calculators accept this in different ways " +
            "(someone \"*\", someone like \"^\") and in order to correctly give you the result, " +
            "please enter the sign you need in the formula.";

    String FUNCTION_PARAMETER_NOT_IN_BRACKETS= "The function is written incorrectly. " +
            "Function parameters must be in parentheses. Check spelling and enter correctly.";

    String ENTER_NOT_LATIN= "Entering letters is possible only in Latin! " +
            "Check the input language and enter correctly";

    String EMPTY_FORMULA= "It seems to me that the empty formula cannot be calculated. " +
            "Write the equation and run the program again.";

    String DIVIDING_BY_0= "According to mathematical rules, division by 0 is impossible." +
            "Check the formula and try again.";
}

