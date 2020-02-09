import java.io.*;
import java.util.EmptyStackException;
import java.util.Stack;

public class RPNCalc<E> {

    Stack<String> stack = new Stack<>();
    static BufferedReader br;

    static {
        try {
            br = new BufferedReader(new FileReader(new File("RPNCalc.dat")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        RPNCalc calculator = new RPNCalc();
        int tests = Integer.parseInt(br.readLine());
        for (int i = 0; i < tests; i++) {
            System.out.println(calculator.doExpression(br.readLine()));
        }
    }

    public int doExpression(String expression) {
        try {
        String[] elemStrs = expression.split(" ");
            stack.empty();
            for (int i = 0; i < elemStrs.length; i++) {
                if (elemStrs[i].matches("-?\\d+")) {
                    stack.push(elemStrs[i]);
                } else {
                    String operStr2 = stack.pop();
                    String operStr1 = stack.pop();
                    int operand2 = Integer.parseInt(operStr2);
                    int operand1 = Integer.parseInt(operStr1);
                    int output;
                    if (elemStrs[i].equals("+")) {
                        output = operand1 + operand2;
                    } else if (elemStrs[i].equals("-")) {
                        output = operand1 - operand2;
                    } else if (elemStrs[i].equals("*")) {
                        output = operand1 * operand2;
                    } else if (elemStrs[i].equals("/")) {
                        output = operand1 / operand2;
                    } else if (elemStrs[i].equals("^")) {
                        output = (int) Math.pow(operand1, operand2);
                    } else if (elemStrs[i].equals("%")) {
                        output = operand1 % operand2;
                    } else {
                        throw new IllegalArgumentException("Invalid operator found in expression: " + elemStrs[i] + " within " + expression);
                    }
                    stack.push(output + "");
                }
            }
            return Integer.parseInt(stack.pop());
        } catch(NullPointerException e) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }

}
