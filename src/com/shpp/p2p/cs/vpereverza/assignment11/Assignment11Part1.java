package com.shpp.p2p.cs.vpereverza.assignment11;

import java.util.ArrayList;

/**
 * Create a calculator that accepts a mathematical equation as the first parameter,
 * and variables as the other parameters, if they are needed.
 * Parameters are named with a single letter, followed by the value of the parameter after equals.
 * The program supports mathematical operations: -, +, /, *, ^.
 * expressions taken in "()"
 * sin, cos, tan, atan, log10, log2, sqrt functions. Where the parameter must be taken in "()".
 * Not sensitive to cases, spaces and is very friendly with unary characters.
 * the program has a check for the correctness of the input
 * (according to the rules of mathematics, naming variables is possible only in Latin)
 * For all incorrect entries, the user will receive a warning that will help him correct the data entry error.
 * To optimize the program, the parsed formula is saved for the duration of the program
 * and is not parsed again in case of repeated input
 */
public class Assignment11Part1 implements ConstantAndMessage {

    //current formula element.
    public static int i = 0;

    //Parsed formula
    public static ArrayList<String> formula = new ArrayList<>();


    /**
     * The method checks for arguments
     * If they exist, it passes the value to the parsing class and receives the answer in the form of a parsed formula and arguments.
     * Passes these values to the following methods for mathematical calculations.
     * The obtained result of the mathematical expression is displayed on the console
     *
     * @param args arguments are entered by the user
     */
    public static void main(String[] args) {
        try {
            //Report an error if there are no parameters
            if (args.length == 0) {
                exception(NO_PARAMETERS);
            } else {
                //Parse the entered parameters
                Parsing parsing = new Parsing();
                formula = parsing.analysisFormula(args[0]);
                parsing.variableInitialization(args);
                System.out.println("Result:  " + solveEquations());
            }
        } catch (Exception e) {
            exception(SUPER_EXCEPTION);
        }
    }

    /**
     * Equation calculation method (created by recursive descent method)
     */
    public static double solveEquations() {
        //If an empty formula parameter is entered, report an error.
        if (formula.size() == 0) {
            exception(EMPTY_FORMULA);
            return 0;
        } else {
            return additionSubtraction();
        }
    }

    /**
     * Create a method that calculates addition and subtraction.
     * Analyze the current sign and if it is "+" or "-"
     * then call the function for finding the value of the elements
     * from the method with the highest priority and count.
     *
     * @return the result of a mathematical operation
     */
    public static double additionSubtraction() {
        double value = multiplicationDivision();
        while (true) {
            i++;
            if (i < formula.size()) {
                char element = formula.get(i).charAt(0);
                switch (element) {
                    case '+':
                        i++;
                        if (i < formula.size())
                            value += multiplicationDivision();
                        else
                            exception(FORMULA_IS_NOT_WRITTEN);
                        break;
                    case '-':
                        i++;
                        if (i < formula.size())
                            value -= multiplicationDivision();
                        else
                            exception(FORMULA_IS_NOT_WRITTEN);
                        break;
                    case ')':
                        i--;
                        return value;
                    default:
                        exception(INCORRECT_CHARACTER_ORDER);
                }
            } else {
                return value;
            }
        }
    }

    /**
     * Method of multiplication and division degree.
     * Analyze the current sign and if it is "+" or "-"
     * then call the function for finding the value of the elements
     * from the method with the highest priority and count.
     *
     * @return the result of a mathematical operation
     */
    public static double multiplicationDivision() {
        double value = exponentiate();
        while (true) {
            i++;
            if (i < formula.size()) {
                char element = formula.get(i).charAt(0);
                switch (element) {
                    case '*':
                        i++;
                        if (i < formula.size())
                            value *= exponentiate();
                        else
                            exception(FORMULA_IS_NOT_WRITTEN);
                        break;

                    case '/':
                        i++;
                        if (i < formula.size())
                            value = checkingDivisionBy0(value);
                        else
                            exception(FORMULA_IS_NOT_WRITTEN);
                        break;

                    case ')', '+', '-':
                        i--;
                        return value;

                    default:
                        exception(INCORRECT_CHARACTER_ORDER);
                }
            } else {
                return value;
            }
        }
    }

    /**
     * The method checks the rule of division by 0
     *
     * @param value The number to be divided
     * @return the result of division, or an error in the case of division by 0
     */
    private static double checkingDivisionBy0(double value) {
        double divisor = exponentiate();
        if (divisor != 0) {
            return value / divisor;
        } else {
            exception(DIVIDING_BY_0);
            return 0;
        }
    }

    /**
     * Exponentiation method.
     * Analyze the current character and if it is "^"
     * then call the function of finding the formula element and count.
     *
     * @return the result of a mathematical operation
     */
    public static double exponentiate() {
        double value = findFactor();
        while (true) {
            i++;
            if (i < formula.size()) {
                char element = formula.get(i).charAt(0);
                switch (element) {
                    case '^':
                        i++;
                        if (i < formula.size())
                            value = Math.pow(value, findFactor());
                        else
                            exception(FORMULA_IS_NOT_WRITTEN);
                        break;

                    case '*', '/', ')', '+', '-':
                        i--;
                        return value;

                    default:
                        exception(INCORRECT_CHARACTER_ORDER);
                }
            } else {
                return value;
            }
        }
    }

    /**
     * Write a method that analyzes a formula element that takes part in mathematical operations
     *
     * @return formula element value
     */
    public static double findFactor() {
        double value;
        String factor = formula.get(i);
        char element = factor.charAt(0);

        //The element can be just a number
        if (element <= '9' && element >= '0') {
            return Double.parseDouble(factor);
        }

        //can be a letter (variable) or a set of letters (function)
        else if (element <= 'z' && element >= 'a') {
            return analyzeLetters(factor);
        }

        //can be a value with a unary minus
        else if (element == '-') {
            i++;
            value = findFactor();
            return -value;
        }

        //can be an expression in brackets
        else if (element == '(') {
            return countValueInBrackets();
        }

        //in all other cases, inform the user about incorrect input
        else {
            exception(FORMULA_ERROR);
            return 0;
        }
    }

    /**
     * A method that analyzes the correctness of writing equations in parentheses
     * and calls methods to evaluate this equation
     *
     * @return the result of calculating the equation in brackets
     */
    private static double countValueInBrackets() {
        i++;
        double value = solveEquations();
        i++;
        //If the bracket is not closed, then tell the user about it
        if (i > formula.size() || formula.get(i).charAt(0) != ')')
            exception(NOT_CLOSED_BRACKET);
        return value;
    }

    /**
     * Create a method that parses a formula element containing letters and performs the appropriate operations
     *
     * @param factor Formula element with letters
     * @return the result of a function or variable
     */
    private static double analyzeLetters(String factor) {
        double value = 0;
        //Variables are named with a single letter. If there are more letters, then this is a function
        if (factor.length() > 1) {
            value = solveTheFunction();
            return value;
        } else {
            //Find the value of the variable by the name key in the hash map.
            // If the variable is not found, report an error
            if (variables.get(factor) == null) {
                System.out.println(factor + PARAMETER_DOES_NOT_EXIST);
                System.exit(0);
            }
            value = variables.get(factor);
            return value;
        }
    }

    /**
     * A method that analyzes the correct spelling of a function
     * and returns a result (if spelled correctly) and an error otherwise.
     *
     * @return function result.
     */
    private static double solveTheFunction() {
        String name = formula.get(i);
        double value;
        i++;
        // The calculator supports log2 and log10.
        // Check for numbers after letters and store full function name.
        if (name.equals("log") && Character.isDigit(formula.get(i).charAt(0))) {
            name += formula.get(i);
            i++;
        }
        //If there is no "(" after the function name, then inform the user about invalid input.
        if (formula.get(i).charAt(0) != '(') {
            exception(FUNCTION_PARAMETER_NOT_IN_BRACKETS);
            return 0;
        } else {
            value = countValueInBrackets();
            return calculateFunction(name, value);
        }
    }

    /**
     * Method that calculates mathematical functions
     * and reports an error if a function is found that is not supported by the calculator, or is entered incorrectly
     *
     * @param name  function name
     * @param value parameter value
     * @return function result
     */
    private static double calculateFunction(String name, double value) {
        switch (name) {
            case "sin":
                return Math.sin(value);
            case "cos":
                return Math.cos(value);
            case "tan":
                return Math.tan(value);
            case "atan":
                return Math.atan(value);
            case "log10":
                return Math.log10(value);
            case "log2":
                return Math.log10(value) / Math.log10(2);
            case "sqrt":
                return Math.sqrt(value);
            default: {
                exception(UNKNOWN_FUNCTION);
                return 0;
            }
        }
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