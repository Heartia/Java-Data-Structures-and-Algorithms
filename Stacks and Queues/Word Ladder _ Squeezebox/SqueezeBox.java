import java.io.*;
import java.util.*;

public class SqueezeBox {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("squeezebox.dat")));

        String line;
        while ((line = br.readLine()) != null && !line.substring(0, 1).equals("#")) {
            // Reading in the data and organising the cards into a deck.
            String[] data1 = line.split(" ");
            String[] data2 = br.readLine().split(" ");
            Card[] hand1 = new Card[data1.length];
            Card[] hand2 = new Card[data2.length];

            for (int i = 0; i < hand1.length; i++) {
                hand1[i] = new Card(data1[i]);
                hand2[i] = new Card(data2[i]);
            }

            ArrayList<Deque<Card>> deck = new ArrayList<>();

            for (int i = 0; i < hand1.length; i++) {
                Deque<Card> newCard = new LinkedList<Card>();
                newCard.add(hand1[i]);
                deck.add(i, newCard);
            }

            for (int i = 0; i < hand2.length; i++) {
                Deque<Card> newCard = new LinkedList<Card>();
                newCard.add(hand2[i]);
                deck.add(i, newCard);
            }

            // Simulate the Squeezebox algorithm with the deck of cards.

            for (int i = 1; i < deck.size(); i++) {
                if (i >= 4) {
                    if(matches(deck.get(i).peek(), deck.get(i - 3).peek())) {
                        deck.get(i - 3).add(deck.get(i).poll());
                        deck.remove(deck.get(i));
                        i = 0;
                        continue;
                    }
                }
                if(matches(deck.get(i).peek(), deck.get(i - 1).peek())) {
                    deck.get(i - 1).add(deck.get(i).poll());
                    deck.remove(deck.get(i));
                    i = 0;
                }
            }

            System.out.println(deck);
        }
    }

    private static boolean matches(Card card1, Card card2) {
        return card1.value == card2.value || card1.suit.equals(card2.suit);
    }

    public static class Card implements Comparable<Card> {

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
                value = 1;
            }
            suit = val.substring(1, 2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Card card = (Card) o;
            return value == card.value &&
                    Objects.equals(suit, card.suit);
        }

        @Override
        public int compareTo(Card c) {
            return value - c.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, suit);
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
