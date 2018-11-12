package com.example.calculator;
import java.math.MathContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.*;


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
                        "This program works with REAL numbers of any length you want!" +
                        "\nTo stop this program, enter nothing and press enter button\n");
                continue;
            }
            ReversePolishNotation rpn = new ReversePolishNotation();
            rpn.parseExpression(inputString);
           
            if(rpn.successfulParse){
                System.out.print("Reverse Polish Notation: ");
                for (String x : rpn.postfix) System.out.print(x + " ");
                System.out.print("\nResult = ");
                BigDecimal result = rpn.calculateExpression();
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
