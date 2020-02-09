import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class War {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("War.dat"));

        Queue<Card> p1 = new LinkedList<>();
        Queue<Card> p2 = new LinkedList<>();

        while (scan.hasNextLine()) {
            String[] line1 = scan.nextLine().split("\\s");
            String[] line2 = scan.nextLine().split("\\s");

            for (String s : line1) {
                p1.add(new Card(s));
            }
            for (String s : line2) {
                p2.add(new Card(s));
            }

            int rounds = 0;
            int max = 100000;

            while (!p1.isEmpty() && !p2.isEmpty() && rounds != max) {
                if (p1.peek().compare(p2.peek()) > 0) {
                    Card temp = p1.poll();
                    p1.add(temp);
                    p1.add(p2.poll());
                }
                else if (!p1.isEmpty() && !p2.isEmpty() && p1.peek().compare(p2.peek()) < 0) {
                    Card temp = p1.poll();
                    p2.add(temp);
                    p2.add(p2.poll());
                }
                else if (!p1.isEmpty() && !p2.isEmpty() && p1.peek().compare(p2.peek()) == 0) {
                    Queue<Card> deck = new LinkedList<>();
                    while (!p1.isEmpty() && !p2.isEmpty() && p1.peek().compare(p2.peek()) == 0) {
                        deck.add(p1.poll());
                        deck.add(p2.poll());
                        if (p1.isEmpty()) {
                            p2.addAll(deck);
                            break;
                        }
                        else if (p2.isEmpty()) {
                            p1.addAll(deck);
                            break;
                        }
                        deck.add(p1.poll());
                        deck.add(p2.poll());
                        if (!p1.isEmpty() && !p2.isEmpty() && p1.peek().compare(p2.peek()) > 0) {
                            Card temp = p1.poll();
                            p1.addAll(deck);
                            p1.add(temp);
                            p1.add(p2.poll());
                            break;
                        }
                        else if (!p1.isEmpty() && !p2.isEmpty() && p1.peek().compare(p2.peek()) < 0) {
                            Card temp = p1.poll();
                            p2.addAll(deck);
                            p2.add(temp);
                            p2.add(p2.poll());
                            break;
                        }
                        else {
                            if (!p1.isEmpty() && !p2.isEmpty()) {
                                p1.peek().compare(p2.peek());
                            }
                        }
                    }
                }
                rounds++;
            }

            if (p1.isEmpty() && p2.isEmpty()) {
                System.out.println("Perfect Match! Neither could win.");
            }
            else if (p1.isEmpty()) {
                System.out.println("Player 2 wins!");
            }
            else if (p2.isEmpty()) {
                System.out.println("Player 1 wins!");
            }
            else {
                System.out.println("Tie game stopped at " + rounds + " plays.");
            }

            //System.out.println(p1);
            //System.out.println(p2);
            //System.out.println();

            p1.clear();
            p2.clear();
        }
    }

    public static class Card {

        int value = 0;
        String suit;

        Card(String val) {
            if (val.substring(0, 1).matches("-?\\d+")) {
                value = Integer.parseInt(val.substring(0, 1));
            }
            else if (val.substring(0, 1).equals("T")) {
                value = 10;
            }
            else if (val.substring(0, 1).equals("J")) {
                value = 11;
            }
            else if (val.substring(0, 1).equals("Q")) {
                value = 12;
            }
            else if (val.substring(0, 1).equals("K")) {
                value = 13;
            }
            else if (val.substring(0, 1).equals("A")) {
                value = 14;
            }
            suit = val.substring(1, 2);
        }

        int compare(Card c) {
            return value - c.value;
        }

        @Override
        public String toString() {
            String num = "";
            switch(value) {
                case 10:
                    num = "T";
                    break;
                case 11:
                    num = "J";
                    break;
                case 12:
                    num = "Q";
                    break;
                case 13:
                    num = "K";
                    break;
                case 1:
                    num = "A";
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    num = value + num;
                    break;
                default:
                    num = "?";
            }
            return num + suit;
        }

    }

}
