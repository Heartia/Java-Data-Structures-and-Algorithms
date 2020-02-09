import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InfixToPostfix {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("InfixToPostfix.dat")));
        int tests = Integer.parseInt(br.readLine());
        for (int i = 0; i < tests; i++) {
            System.out.println(convert(br.readLine()));
        }
    }

    public static String convert(String infix) {
        String[] elemStrsArr = infix.split(" ");
        ArrayList<String> elemStrs = new ArrayList<>(Arrays.asList(elemStrsArr));
        // Looks for parentheses and then evaluates them separately before adding them back in as if they were a number
        for (int i = 0; i < elemStrs.size(); i++) {
            if (elemStrs.get(i).equals("(")) {
                int count = 1;
                int k = i + 1;
                StringBuilder section = new StringBuilder();
                while (count > 0) {
                    if (elemStrs.get(k).equals(")")) {
                        count--;
                    }
                    else if (elemStrs.get(k).equals("(")) {
                        count++;
                    }
                    if (count != 0) {
                        section.append(elemStrs.remove(k));
                    }
                }
                elemStrs.add(k, convert(section.toString().substring(0, section.toString().length() - 1)));
            }
        }
        // Looks for exponents and converts
        for (int i = 0; i < elemStrs.size(); i++) {
            if (elemStrs.get(i).equals("^")) {
                elemStrs.add(elemStrs.get(i - 1) + elemStrs.get(i + 1) + elemStrs.get(i));
                for (int k = 0; k < 3; k++) {
                    elemStrs.remove(i - 1);
                }
            }
        }
        // Looks for modulo, division or multiplication and converts
        for (int i = 0; i < elemStrs.size(); i++) {
            if (elemStrs.get(i).equals("*")) {
                elemStrs.add(elemStrs.get(i - 1) + elemStrs.get(i + 1) + elemStrs.get(i));
                for (int k = 0; k < 3; k++) {
                    elemStrs.remove(i - 1);
                }
            }
            else if (elemStrs.get(i).equals("/")) {
                elemStrs.add(elemStrs.get(i - 1) + elemStrs.get(i + 1) + elemStrs.get(i));
                for (int k = 0; k < 3; k++) {
                    elemStrs.remove(i - 1);
                }
            }
            else if (elemStrs.get(i).equals("%")) {
                elemStrs.add(elemStrs.get(i - 1) + elemStrs.get(i + 1) + elemStrs.get(i));
                for (int k = 0; k < 3; k++) {
                    elemStrs.remove(i - 1);
                }
            }
        }
        // Looks for addition or subtraction and converts
        for (int i = 0; i < elemStrs.size(); i++) {
            if (elemStrs.get(i).equals("+")) {
                elemStrs.add(elemStrs.get(i - 1) + elemStrs.get(i + 1) + elemStrs.get(i));
                for (int k = 0; k < 3; k++) {
                    elemStrs.remove(i - 1);
                }
            }
            if (elemStrs.get(i).equals("-")) {
                elemStrs.add(elemStrs.get(i - 1) + elemStrs.get(i + 1) + elemStrs.get(i));
                for (int k = 0; k < 3; k++) {
                    elemStrs.remove(i - 1);
                }
            }
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < elemStrs.size(); i++) {
            output.append(elemStrs.get(i)).append(" ");
        }
        return output.toString();
    }

}
