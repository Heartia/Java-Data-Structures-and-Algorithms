package Trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RightTree {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("righttree.dat")));

        int tests = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tests; t++) {
            char[] tree = br.readLine().toCharArray();

            // If p is the index of our node, then…
            //
            // left child index = 2 * p + 1
            // right child index = 2 * p + 2
            // parent index = (p – 1) / 2

            boolean isRightTree = true;
            for (int n = 0; n < tree.length; n++) {
                int left = findSubTrees(tree, 2 * n + 1);
                int right = findSubTrees(tree, 2 * n + 2);
                if (left > right) {
                    isRightTree = false;
                    break;
                }
            }
            if (isRightTree) {
                System.out.println("Tree " + t + " is a right-tree.");
            }
            else {
                System.out.println("Tree " + t + " is not a right-tree.");
            }
        }
    }

    private static int findSubTrees(char[] tree, int index) {
        if (index < 0 || index >= tree.length || tree[index] == '0') {
            return 0;
        }
        return 1 + findSubTrees(tree, 2 * index + 1) +
                findSubTrees(tree, 2 * index + 2);
    }

}
