import java.io.*;
import java.util.*;

public class Main {

    public static class GraphTraversal {
        private final List<List<Integer>> adj;
        private final boolean[] visited;
        private final int nodes;
        private int components;

        public GraphTraversal(int nodes) {
            this.nodes = nodes;
            this.adj = new ArrayList<>();
            this.visited = new boolean[nodes + 1];
            this.components = 0;

            for (int i = 0; i <= nodes; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int a, int b) {
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        private void dfs(int node) {
            visited[node] = true;
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    dfs(neighbor);
                }
            }
        }

        public String determineTreeOrGraph(int edges) {
            components = 0;
            Arrays.fill(visited, false);

            for (int i = 1; i <= nodes; i++) {
                if (!visited[i]) {
                    dfs(i);
                    components++;
                }
            }

            if (edges == nodes - 1 && components == 1) {
                return "tree";
            } else {
                return "graph";
            }
        }
    }

    public static String solution(int n, int m, int[][] edges) {
        GraphTraversal graph = new GraphTraversal(n);

        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        return graph.determineTreeOrGraph(m);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
            }

            String result = solution(n, m, edges);
            bw.write(result);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}