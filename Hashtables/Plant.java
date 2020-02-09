import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Plant {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("plant.dat")));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String line = br.readLine();
        int cases = Integer.parseInt(line);
        br.readLine(); // Blank Line
        for (int c = 0; c < cases; c++) {
            HashMap<String, Integer> analysis = new HashMap<>();
            List<String> trees = new ArrayList<>();
            int total = 0;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                if (analysis.containsKey(line)) {
                    analysis.put(line, analysis.get(line) + 1);
                }
                else {
                    analysis.put(line, 1);
                    trees.add(line);
                }
                total++;
            }
            //pw.println(analysis);
            Collections.sort(trees);
            //pw.println(trees);
            //pw.println(total);
            for (int i = 0; i < trees.size(); i++) {
                pw.printf("%s %.4f", trees.get(i), ((float)analysis.get(trees.get(i)) / total) * 100);
                pw.println();
            }
            if (c + 1 != cases) {
                pw.println();
            }
        }
        pw.flush();
    }

}
