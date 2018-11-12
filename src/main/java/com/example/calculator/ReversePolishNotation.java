package com.example.calculator;
import java.math.BigDecimal;
import java.util.*;


class ReversePolishNotation{
    public List<String> postfix;
    public boolean successfulParse = true;
    public boolean successfulCalculate = true;
    private int checkOperator(String str){
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

    private boolean checkNumber(String str)throws NumberFormatException {
        try {
            BigDecimal big = new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkBracket(String str){
        return (str.equals("(") || str.equals(")"));
    }

    private int getOperatorPriority(String str){
        int result = 4;
        switch(checkOperator(str)){
            case 0: result = 2; break;
            case 1: result = 2; break;
            case 2: result = 3; break;
            case 3: result = 3; break;
            case 4: result = 5; break;
            case 5: result = 4; break;
            case 6: result = 3; break;
        }
        if (str.equals("(")) result = 1;
        return result;
    }

    public List<String> parseExpression(String infix){
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

    public BigDecimal calculateExpression(){
        if (successfulParse == false) return BigDecimal.valueOf(0);
        successfulCalculate = true;
        Stack<BigDecimal> stack = new Stack<BigDecimal>();
        BigDecimal operand1, operand2;
        for(String x: postfix){
            switch(checkOperator(x)) {
                case -1:
                    operand1 = new BigDecimal(x);
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
                    if (operand2.equals(BigDecimal.ZERO)) {
                        successfulCalculate = false;
                        return BigDecimal.valueOf(Long.MAX_VALUE);
                    }
                    stack.push(operand1.divide(operand2, 5, BigDecimal.ROUND_HALF_EVEN));
                    break;
                case 4:
                    stack.push(stack.pop().negate());
                    break;
                case 5:
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    if (operand2.compareTo(BigDecimal.ZERO) >= 0){
                        stack.push(operand1.pow(operand2.intValue()));
                    }
                    else{
                        stack.push(BigDecimal.ONE.divide(operand1.pow(operand2.abs().intValue())));
                    } 
                    break;
                case 6:
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    operand1 = new BigDecimal((operand1.toBigInteger().mod(operand2.toBigInteger())).toString()); 
                    stack.push(operand1);
                    break;
            }
        }
        return stack.pop();
    }
}
