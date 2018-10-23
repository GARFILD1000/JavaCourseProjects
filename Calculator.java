import java.math.BigInteger;
import java.util.Scanner;

import java.util.*;

class ReversePolishNotation{

    public static List<String> postfix;
    public static boolean successfulParse = true;
    public static boolean successfulCalculate = true;
    private static int checkOperator(String str){
        int result = -1;
        if (str.equals("+")) result = 0;
        else if (str.equals("-")) result = 1;
        else if (str.equals("*")) result = 2;
        else if (str.equals("/")) result = 3;
        else if (str.equals("minus")) result = 4;
        else if (str.equals("^")) result = 5;
        else if (str.equals("mod")) result = 6;
        return result;
    }

    private static boolean checkNumber(String str)throws NumberFormatException {
        try {
            BigInteger big = new BigInteger(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkBracket(String str){
        return (str.equals("(") || str.equals(")"));
    }

    private static int getOperatorPriority(String str){
        int result = 4;
        if (str.equals("(")) result = 1;
        else if (str.equals("+")) result = 2;
        else if (str.equals("-")) result = 2;
        else if (str.equals("*")) result = 3;
        else if (str.equals("/")) result = 3;
        return result;
    }

    public static List<String> parseExpression(String infix){
        successfulParse = true;
        postfix = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        StringTokenizer tokenizer = new StringTokenizer(infix, "( )", true);
        String current = "", previous = "";
        while (tokenizer.hasMoreTokens()) {
            current = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && (checkOperator(current) != -1)) {
                successfulParse = false;
                return postfix;
            }
            if (current.equals(" ")) continue;
            if (checkBracket(current)) {
                if (current.equals("(")) stack.push(current);
                else if (current.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            successfulParse = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                }
            }
            else if (checkOperator(current) != -1){
                if (current.equals("-") && ((checkOperator(previous) != -1) || (checkBracket(previous)  && !previous.equals(")")))) {
                    current = "minus";
                }
                else {
                    while (!stack.isEmpty() && (getOperatorPriority(current) <= getOperatorPriority(stack.peek()))) {
                        postfix.add(stack.pop());
                    }
                }
                stack.push(current);
            }
            else if(checkNumber(current)){
                if (checkNumber(previous)){
                    successfulParse = false;
                    return postfix;
                }
                else{
                    postfix.add(current);
                }
            }
            else{
                successfulParse = false;
                return postfix;
            }
            previous = current;
        }
        while(!stack.isEmpty()){
            if(checkOperator(stack.peek()) != -1){
                postfix.add(stack.pop());
            }
            else{
                successfulParse = false;
                return postfix;
            }
        }
        return postfix;
    }

    public static BigInteger calculateExpression(){
        if (successfulParse == false) return BigInteger.valueOf(0);
        successfulCalculate = true;
        Stack<BigInteger> stack = new Stack<BigInteger>();
        BigInteger operand1, operand2;
        for(String x: postfix){
            switch(checkOperator(x)) {
                case -1:
                    operand1 = new BigInteger(x);
                    stack.push(operand1);
                    break;
                case 0:
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    stack.push(operand1.add(operand2));
                    break;
                case 1:
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    stack.push(operand1.subtract(operand2));
                    break;
                case 2:
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    stack.push(operand1.multiply(operand2));
                    break;
                case 3:
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    if (operand2.equals(BigInteger.ZERO)) {
                        successfulCalculate = false;
                        return BigInteger.valueOf(Long.MAX_VALUE);
                    }
                    stack.push(operand1.divide(operand2));
                    break;
                case 4:
                    stack.push(stack.pop().negate());
                    break;
                case 5:
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    stack.push(operand1.pow(operand2.intValue()));
                    break;
                case 6:
                    operand2 = stack.pop();
                    operand1 = stack.pop(); 
                    stack.push(operand1.mod(operand2));
                    break;
            }
        }
        return stack.pop();
    }
}

class Calculator{
    public static void main(String[] args) {
        System.out.println("Hello, User!");
        System.out.print("Enter your expression you want to calculate (if you have some questions, enter 'help'):\n");
        Scanner in;
        String inputString, resultString;
        while(true){
            in = new Scanner(System.in);
            inputString = in.nextLine();
            if (inputString.isEmpty()) {
                System.out.println("Nothing to calculate, I'm out!");
                break;
            }
            else if (inputString.equals("help")){
                System.out.print("This program calculates the expression entered by the user in the format: " +
                        "\"operand1 operator operand2\"\nThere must be spaces between operators and operands!\n"+
                        "Examples of expressions:\n1 + 2 * 3 - 5\n5 * (1 + 3)\n"+
                        "This program works with INTEGER numbers of any length you want!" +
                        "\nTo stop this program, enter nothing and press enter button\n");
                continue;
            }
            ReversePolishNotation rpn = new ReversePolishNotation();
            rpn.parseExpression(inputString);
           
            if(rpn.successfulParse){
                System.out.print("Reverse Polish Notation: ");
                for (String x : rpn.postfix) System.out.print(x + " ");
                System.out.print("\nResult = ");
                BigInteger result = rpn.calculateExpression();
                if (rpn.successfulCalculate){
                    System.out.println(result.toString());
                }
                else{
                    System.out.println("not defined (Fail calculating)");
                }
            }
            else{
                System.out.println("Syntax error!");
            }
        }
        return;
    }
}
