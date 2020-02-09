import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Rake {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("rake.dat")));

        int cases = 0;
        String line;
        while ((line = br.readLine()) != null && !line.equals("-1")) {
            cases++;
            if(cases != 1) {
                System.out.println();
            }
            System.out.println("Case: " + cases);

            String[] data = line.split(" ");

            Node root = new Node(Integer.parseInt(data[0]), 0, null);
            List<Node> pileList = new ArrayList<>();
            pileList.add(root);
            List<Integer> possibleHorizs = new ArrayList<>();
            possibleHorizs.add(root.horizDist);
            Node currNode = root;
            for (int i = 1; i < data.length; i++) {
                //System.out.println(currNode);
                //System.out.println("Left: " + currNode.left);
                //System.out.println("Right: " + currNode.right);
                if (currNode.left == null) {
                    Node newNode = new Node(Integer.parseInt(data[i]), currNode.horizDist - 1, currNode);
                    currNode.left = newNode;
                    pileList.add(newNode);
                    if (!possibleHorizs.contains(newNode.horizDist)) {
                        possibleHorizs.add(newNode.horizDist);
                    }
                    if (currNode.left.value != -1) {
                        currNode = currNode.left;
                    }
                }
                else if (currNode.right == null) {
                    Node newNode = new Node(Integer.parseInt(data[i]), currNode.horizDist + 1, currNode);
                    currNode.right = newNode;
                    pileList.add(newNode);
                    if (!possibleHorizs.contains(newNode.horizDist)) {
                        possibleHorizs.add(newNode.horizDist);
                    }
                    if (currNode.right.value != -1) {
                        currNode = currNode.right;
                    }
                }
                else {
                    currNode = currNode.parent;
                    i--;
                }
            }

            possibleHorizs.sort(Comparator.naturalOrder());
            HashMap<Integer, Integer> leafMap = new HashMap<>();
            //System.out.println("All nodes: " + pileList);
            for (int i = 0; i < possibleHorizs.size(); i++) {
                //System.out.print(possibleHorizs.get(i) + ": ");
                for (int j = 0; j < pileList.size(); j++) {
                    if (pileList.get(j).horizDist == possibleHorizs.get(i) &&
                        pileList.get(j).value != -1) {
                        if (leafMap.containsKey(possibleHorizs.get(i))) {
                            leafMap.put(possibleHorizs.get(i), leafMap.get(possibleHorizs.get(i)) + pileList.get(j).value);
                        }
                        else {
                            leafMap.put(possibleHorizs.get(i), pileList.get(j).value);
                        }
                    }
                }
            }
            for (int i = 0; i < possibleHorizs.size(); i++) {
                if (leafMap.containsKey(possibleHorizs.get(i))) {
                    System.out.print(leafMap.get(possibleHorizs.get(i)) + " ");
                }
            }
            System.out.println();
        }
    }

    static class Node {
        public Node left;
        public Node right;
        public Node parent;
        public int value;
        public int horizDist;
        public boolean wentLeft;

        Node(int val, int hDist, Node par) {
            value = val;
            horizDist = hDist;
            parent = par;
            wentLeft = false;
        }

        public String toString() {
            return "[" + value + ", " + horizDist + "]";
        }
    }

}
