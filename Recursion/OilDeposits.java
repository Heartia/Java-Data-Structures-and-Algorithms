import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OilDeposits {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("OilDeposits.dat")));

        String line;
        while ((line = br.readLine()) != null && !line.equals("0 0")) {
            String[] data = line.split("\\s");
            int r = Integer.parseInt(data[0]);
            int c = Integer.parseInt(data[1]);
            char[][] plot = new char[r][c];
            for (int i = 0; i < r; i++) {
                plot[i] = br.readLine().toCharArray();
            }
            System.out.println(checkForOil(plot));
        }
    }

    private static int checkForOil(char[][] plot) {
        int pockets = 0;
        for (int r = 0; r < plot.length; r++) {
            for (int c = 0; c < plot[r].length; c++) {
                if (plot[r][c] == '@') {
                    pockets++;
                    floodFill(plot, r, c);
                }
            }
        }
        return pockets;
    }

    private static void floodFill(char[][] map, int r, int c) {
        if (r < 0 || r > map.length - 1 ||
            c < 0 || c > map[r].length - 1 ||
            map[r][c] != '@') {
            return;
        }

        map[r][c] = 'O';
        floodFill(map, r - 1, c);
        floodFill(map, r - 1, c + 1);
        floodFill(map, r, c + 1);
        floodFill(map, r + 1, c + 1);
        floodFill(map, r + 1, c);
        floodFill(map, r + 1, c - 1);
        floodFill(map, r, c - 1);
        floodFill(map, r - 1, c - 1);
    }
}
