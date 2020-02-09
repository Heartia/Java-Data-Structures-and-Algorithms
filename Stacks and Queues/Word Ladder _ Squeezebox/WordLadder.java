import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordLadder {

    private static HashSet<String> dictionary = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(new File("ladderInput.txt")));
        BufferedReader reader = new BufferedReader(new FileReader(new File("dictionary.txt")));

        String word;
        while ((word = reader.readLine()) != null) {
            dictionary.add(word);
        }

        int num = 0;
        String line;
        while ((line = input.readLine()) != null) {
            num++;
            String[] words = line.split("\\s");
            System.out.print("Test case " + num + ": " + words[0]);
            wordLadder(words[0], words[1]);
            System.out.println();
        }
    }

    private static void wordLadder(String start, String end) {
        wordLadder(start, end, new LinkedList<>());
    }

    private static void wordLadder(String start, String end, Queue<Deque<String>> potentials) {
        if (start.equals(end)) {
            System.out.print(" " + end);
            return;
        }

        LinkedList<String> wordsEncountered = new LinkedList<>();
        int encountered = 0;
        final int MAX_ENCOUNT = 1;

        Queue<Deque<String>> similar = isSimilar(start);
        while (!similar.isEmpty()) {
            Deque<String> oneOption = similar.poll();
            while (!oneOption.isEmpty()) {
                String word = oneOption.pop();
                if (word.equals(end)) {
                    System.out.print(" " + similar);
                    return;
                }
                if (!wordsEncountered.contains(word)) {
                    encountered = 0;
                    similar.addAll(isSimilar(word));
                    wordsEncountered.add(word);
                }
                else {
                    encountered++;
                    if (encountered > MAX_ENCOUNT) break;
                }
            }
        }
    }

    private static Queue<Deque<String>> isSimilar(String word) {
        Queue<Deque<String>> oneLetterDiff = new LinkedList<>();

        Iterator<String> iterator = dictionary.iterator();
        while (iterator.hasNext()) {
            String checkWord = iterator.next();
            if (checkWord.length() == word.length()) {
                int difference = 0;
                for (int i = 0; i < checkWord.length(); i++) {
                    if (!(checkWord.charAt(i) == word.charAt(i))) difference++;
                }
                Deque<String> temp = new ArrayDeque<>();
                temp.add(checkWord);
                if (difference == 1) oneLetterDiff.offer(temp);
            }
        }

        return oneLetterDiff;
    }

}