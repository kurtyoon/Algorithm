import java.io.*;
import java.util.*;

public class Solution {

    public static class Graph {
        private final List<List<Integer>> adjacencyList;
        private final int[] visited;
        private final int[] dp;
        private int maxCount = 0;

        public Graph(int n) {
            this.adjacencyList = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            this.visited = new int[n + 1];
            this.dp = new int[n + 1];
        }

        public void addEdge(int from, int to) {
            adjacencyList.get(to).add(from);
        }

        public int performDFS(int node) {
            visited[node] = 1;
            int count = 1;

            for (int neighbor : adjacencyList.get(node)) {
                if (visited[neighbor] == 0) {
                    count += performDFS(neighbor);
                }
            }

            return count;
        }
  
        public List<Integer> calculateMaxInfluentialNodes(int n) {
            List<Integer> result = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                Arrays.fill(visited, 0);
                dp[i] = performDFS(i);
                maxCount = Math.max(maxCount, dp[i]);
            }

            for (int i = 1; i <= n; i++) {
                if (dp[i] == maxCount) {
                    result.add(i);
                }
            }

            return result;
        }
    }

    public static List<Integer> solution(int n, int m, List<int[]> edges) {
        Graph graph = new Graph(n);

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        return graph.calculateMaxInfluentialNodes(n);
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            List<int[]> edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                String[] edge = br.readLine().split(" ");
                int a = Integer.parseInt(edge[0]);
                int b = Integer.parseInt(edge[1]);
                edges.add(new int[]{a, b});
            }

            List<Integer> result = solution(n, m, edges);

            for (int node : result) {
                bw.write(node + " ");
            }
            bw.write("\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}