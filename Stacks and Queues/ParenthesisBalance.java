import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class ParenthesisBalance {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("ParenthesisBalance.dat")));
        int tests = Integer.parseInt(br.readLine());
        for (int i = 0; i < tests; i++) {
            System.out.println(isValid(br.readLine()) ? "Yes" : "No");
        }
    }

    private static boolean isValid(String expression) {
        Stack<String> values = new Stack<>();
        String[] expressArr = expression.split("");
        for (int i = 0; i < expression.length(); i++) {
            if (values.isEmpty()) {
                values.add(expressArr[i]);
            }
            else {
                if (isPaired(values.peek(), expressArr[i])) {
                    values.pop();
                }
                else {
                    values.add(expressArr[i]);
                }
            }
        }
        return values.isEmpty();
    }

    private static boolean isPaired(String value1, String value2) {
        if (value1.equals("(") && value2.equals(")")) {
            return true;
        }
        else if (value1.equals("[") && value2.equals("]")) {
            return true;
        }
        return false;
    }

}
