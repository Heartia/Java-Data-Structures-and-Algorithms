import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class InfixEvaluation {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("InfixToPostfix.dat")));
        int tests = Integer.parseInt(br.readLine());
        for (int i = 0; i < tests; i++) {
            System.out.println(convert(br.readLine()));
        }
    }

    private static String convert(String expression) {
        Stack<String> opStack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        String[] expArr = expression.split(" ");

        for (int i = 0; i < expArr.length; i++) {
            if (expArr[i].matches("-?\\d+")) {
                postfix.append(expArr[i]).append(" ");
            }
            else if(isOperator(expArr[i])) {
                if (opStack.isEmpty() || opStack.peek().equals("("))
                    opStack.push(expArr[i]);
                else {
                    int incomingPrecedence = getPrecedence(expArr[i]);
                    int stackPrecedence = getPrecedence(opStack.peek());
                    if (incomingPrecedence > stackPrecedence) {
                        opStack.push(expArr[i]);
                    }
                    else {
                        postfix.append(opStack.pop()).append(" ");
                        i--;
                    }
                }
            }
            else if(expArr[i].equals("(")) {
                opStack.push(expArr[i]);
            }
            else if(expArr[i].equals(")")) {
                String operator = "";
                while (!operator.equals("(")) {
                    operator = opStack.pop();
                    if (!operator.equals("(")) postfix.append(operator).append(" ");
                }
            }
        }

        while (!opStack.isEmpty()) {
            postfix.append(opStack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    private static boolean isOperator(String operator) {
        switch(operator) {
            case "^":
            case "*":
            case "/":
            case "%":
            case "+":
            case "-":
                return true;
            default:
                return false;
        }
    }

    private static int getPrecedence(String operator) {
        switch(operator) {
            case "^":
                return 3;
            case "*":
            case "/":
            case "%":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

}
