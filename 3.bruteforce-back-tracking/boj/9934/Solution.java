import java.io.*;
import java.util.*;

public class Solution {

    public static class TreeTraversal {
        private List<List<Integer>> levels;
        private int[] nodes;

        public TreeTraversal(int n, int[] nodes) {
            this.levels = new ArrayList<>();
            this.nodes = nodes;

            for (int i = 0; i <= n; i++) {
                levels.add(new ArrayList<>());
            }
        }

        private void traverse(int start, int end, int level) {
            if (start > end) return;

            int mid = (start + end) / 2;
            levels.get(level).add(nodes[mid]);

            traverse(start, mid - 1, level + 1);
            traverse(mid + 1, end, level + 1);
        }

        public List<List<Integer>> getLevels() {
            return levels;
        }
    }

    public static List<List<Integer>> solution(int n, int[] nodes) {
        TreeTraversal treeTraversal = new TreeTraversal(n, nodes);
        treeTraversal.traverse(0, nodes.length - 1, 1);
        return treeTraversal.getLevels();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int size = (int) Math.pow(2, n) - 1;
        int[] nodes = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            nodes[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Integer>> result = solution(n, nodes);

        for (int i = 1; i <= n; i++) {
            for (int num : result.get(i)) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        br.close();
    }
}
