package com.shpp.p2p.cs.vpereverza.assignment11;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class receives arguments from the user and parses them for further work.
 */
public class Parsing implements ConstantAndMessage {

    //Saves the entered formula divided into elements (parsed formula)
    private static ArrayList<String> elementsFormula;

    /**
     * Method that checks if the formula has been entered before.
     * And returns the parsed hash map or calls a method to parse the formula
     *
     * @param arg original formula input
     * @return parsed formula
     */
    public ArrayList<String> analysisFormula(String arg) {
        String formula = arg.toLowerCase();
        if (formuls.get(formula) == null) {
            formuls.put(formula, parsingFormula(formula));
        }
        return formuls.get(formula);
    }

    /**
     * Create a method that parses the formula by separating each element separately
     * and saves the final version for later use
     * in case of incorrect input, warning the user about an error
     *
     * @param formula original formula input
     * @return parsed formula
     */
    public static ArrayList<String> parsingFormula(String formula) {
        System.out.println("PARSING");
        elementsFormula = new ArrayList<>();
        int index = 0;
        while (index < formula.length()) {
            char element = formula.charAt(index);
            switch (element) {
                case '(', ')', '+', '-', '*', '/', '^':
                    elementsFormula.add(String.valueOf(element));
                    index++;
                    continue;
                default:
                    if (Character.isDigit(element)) {
                        index = addNumber(index, formula);
                    } else if (Character.isLetter(element)) {
                        index = addLeter(index, formula);
                    } else {
                        if (element != ' ') {
                            //Tell the user that "." or "," can only be used in a fractional number
                            if (element == '.' || element == ',') {
                                exception(FRACTION_SIGN);
                            }
                            //If there is a character in the formula that does not correspond to the above,
                            // then inform the user that he entered an incorrect character
                            exception(INCORRECT_SIGN);
                        }
                        index++;
                    }
            }
        }
        return elementsFormula;
    }

    /**
     * A method that parses the entered letters and saves them.
     * Warns the user that the program does not support math abbreviations.
     *
     * @param index   current char index
     * @param formula user entered formula
     * @return element containing letters
     */
    private static int addLeter(int index, String formula) {
        String leters = "";
        char leter = formula.charAt(index);
        //If there is a number before the letter,
        //then report the need to enter a character for the correct calculations
        if (index - 1 >= 0 && Character.isDigit(formula.charAt(index - 1))) {
            exception(MATHEMATICAL_REDUCTION);
        }
        do {
            if (leter <= 'z' && leter >= 'a') {
                leters = leters + leter;
                index++;
                if (index >= formula.length()) {
                    break;
                }
                leter = formula.charAt(index);
            } else {
                // If the user entered non-Latin characters, then report an error
                exception(ENTER_NOT_LATIN);
            }
        } while (Character.isLetter(leter));
        //Variables are named with one letter, so it checks the next element and reports an error in case of a digit.
        //Functions (consisting of more than 1 letter)
        // can have a digit after them as it can be part of the function name
        if (leters.length() == 1) {
            if (index < formula.length() && Character.isDigit(formula.charAt(index))) {
                exception(MATHEMATICAL_REDUCTION);
            }
        }
        elementsFormula.add(leters.toLowerCase());
        return index;
    }

    /**
     * A method that saves a number by analyzing the correctness of entering fractional numbers
     *
     * @param index   current char index
     * @param formula user entered formula
     * @return element containing number
     */
    private static int addNumber(int index, String formula) {
        String number = "";
        int sign = 0;
        char numeric = formula.charAt(index);
        do {
            number = number + numeric;
            index++;
            if (index >= formula.length()) {
                break;
            }
            if (formula.charAt(index) == '.' || formula.charAt(index) == ',') {
                if (sign == 0) {
                    number = number + '.';
                    sign++;
                } else {
                    exception(INCORRECT_FRACTION);
                }
                index++;
            }
            numeric = formula.charAt(index);
        } while (Character.isDigit(numeric));
        elementsFormula.add(number);
        return index;
    }

    /**
     * Create a method that parses parameters outside of the formula.
     * Captures the names and values of variables in a hash map.
     * In case of incorrect user input, reports an error
     *
     * @param args all program arguments
     * @return hash map with variable names and values
     */
    public HashMap<String, Double> variableInitialization(String[] args) {
        for (int i = 1; i < args.length; i++) {
            String variable = args[i].replaceAll(" ", "");
            if (variable.length() == 0) {
                continue;
            }

            if (Character.isLetter(variable.charAt(0))) {
                if (variable.length() > 1 && variable.charAt(1) == '=') {
                    if (2 < variable.length()) {
                        String nameVariable = String.valueOf(variable.charAt(0)).toLowerCase();
                        variables.put(nameVariable, writeTheValue(variable, variables));
                    } else {
                        exception(NULL_PARAMETER);
                    }
                } else {
                    exception(NAME_NOT_ONE_LETTER);
                }
            } else {
                exception(NAME_NOT_LETTER);
            }
        }
        return variables;
    }

    /**
     * Method that parses the value of the parameter and informs the user about an error in case of incorrect input
     *
     * @param variable  variable name
     * @param variables hash map with variable names and values
     * @return variable value
     */
    private static double writeTheValue(String variable, HashMap<String, Double> variables) {
        String value = "";
        int sign = 0;
        for (int i = 2; i < variable.length(); i++) {
            if (i == 2 && variable.charAt(2) == '-') {
                value = value + variable.charAt(i);
                continue;
            }
            if (Character.isDigit(variable.charAt(i))) {
                value = value + variable.charAt(i);
            } else if (variable.charAt(i) == '.' || variable.charAt(i) == ',') {
                if (sign == 1) {
                    exception(LOTS_OF_COMMA);
                } else if (Character.isDigit(variable.charAt(i - 1)) && Character.isDigit(variable.charAt(i + 1))) {
                    value = value + ".";
                    sign++;
                } else {
                    exception(INCORRECT_FRACTION);
                }
            } else if (Character.isLetter(variable.charAt(i))) {
                if (variables.get(String.valueOf(variable.charAt(i)).toLowerCase()) != null) {
                    value = value + variables.get(String.valueOf(variable.charAt(i)).toLowerCase());
                } else {
                    System.out.println(variable.charAt(i) + DO_NOT_ASSIGN + variable.charAt(0) + CHANGING_THE_INPUT);
                    System.exit(0);
                }
            } else {
                exception(INCORRECT_VARIABLE_VALUE);
            }
        }
        return Double.parseDouble(value);
    }

    /**
     * A method that, in case of an input error, displays the desired message and closes the program
     *
     * @param message message to show to the user
     */
    private static void exception(String message) {
        System.out.println(message);
        System.exit(0);
    }
}