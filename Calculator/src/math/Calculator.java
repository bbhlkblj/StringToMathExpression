package math;

public class Calculator {

    public static double calculate(String input) throws NumberFormatException{
        Equals equals = new Equals();
        equals.parseString(input);
        return Double.parseDouble(equals.toString());
    }

}