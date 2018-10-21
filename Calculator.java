import java.math.BigInteger;
import java.util.Scanner;
class Calculator{
    public static void main(String[] arg){
        System.out.println("Hello, User!");
        System.out.print("Enter your string you want to calculate (if you have some questions, enter 'help'):\n");
        Scanner in;
        String inputString;
        while(true){
            in = new Scanner(System.in);
            inputString = in.nextLine();
            if (inputString.isEmpty()) {
                System.out.println("Nothing to calculate, I'm out!");
                break;
            }
            String subStr[] = inputString.split(" ");
            if (subStr[0].equals("help")) {
                System.out.print("This program calculates the expression entered by the user in the format: " +
                        "\"operand1 operator operand2\"\nThere must be spaces between operators and operands!"+
                        "\nTo stop this program, enter nothing and press enter button\n");
                continue;
            }
            if(subStr.length < 3){
                System.out.println("Syntax error!");
                continue;
            }
            BigInteger firstOperand, secondOperand, result = BigInteger.valueOf(0);
            if (checkNumber(subStr[0])) {
                firstOperand = new BigInteger(subStr[0]);
            }
            else {
                System.out.println("Syntax error!");
                continue;
            }
            if (checkNumber(subStr[2])) {
                secondOperand = new BigInteger(subStr[2]);
            }
            else {
                System.out.println("Syntax error!");
                continue;
            }
            String resultString = "";
            int operator = checkOperator(subStr[1]);
            switch (operator) {
                case 0:
                    result = firstOperand.add(secondOperand);
                    break;
                case 1:
                    result = firstOperand.subtract(secondOperand);
                    break;
                case 2:
                    result = firstOperand.multiply(secondOperand);
                    break;
                case 3:
                    if (!secondOperand.equals(BigInteger.ZERO))
                        result = firstOperand.divide(secondOperand);
                    else
                        resultString = "infinity";
                    break;
                default:  resultString = "not defined (unknown operator)";
            }
            if (resultString.equals("")) {
                resultString = result.toString();
            }
            System.out.printf("Result = %s\n", resultString);
        }
        return;
    }

    public static int checkOperator(String str){
        int result = -1;
        if (str.equals("+")) result = 0;
        else if (str.equals("-")) result = 1;
        else if (str.equals("*")) result = 2;
        else if (str.equals("/")) result = 3;
        return result;
    }

    public static boolean checkNumber(String str)throws NumberFormatException {
        try {
            BigInteger big = new BigInteger(str);
            return true;
        } 
        catch (NumberFormatException e) {
            return false;
        }
    }
    
}
