import java.io.*;
import java.util.*;

public class Solution {

    public static class Tree {
        private final List<List<Integer>> adj;
        private final int root;
        private final int removeNode;

        public Tree(int root, int removeNode, List<List<Integer>> adj) {
            this.adj = adj;
            this.root = root;
            this.removeNode = removeNode;
        }

        public int countLeafNodes() {
            if (removeNode == root) {
                return 0;
            }
            return countLeaves(root);
        }

        private int countLeaves(int current) {
            int childrenCount = 0;
            int leafCount = 0;

            for (int neighbor : adj.get(current)) {
                if (neighbor == removeNode) continue;
                leafCount += countLeaves(neighbor);
                childrenCount++;
            }

            if (childrenCount == 0) {
                return 1;
            }
            return leafCount;
        }
    }

    public static int solution(int n, int root, int removeNode, List<List<Integer>> adj) {
        Tree tree = new Tree(root, removeNode, adj);
        return tree.countLeafNodes();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine().trim());

            String[] parentNodes = br.readLine().split(" ");
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            int root = -1;
            for (int i = 0; i < n; i++) {
                int parent = Integer.parseInt(parentNodes[i]);

                if (parent == -1) {
                    root = i;
                } else {
                    adj.get(parent).add(i);
                }
            }

            int removeNode = Integer.parseInt(br.readLine().trim());

            // 결과 계산
            int result = solution(n, root, removeNode, adj);
        
            bw.write(result + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}