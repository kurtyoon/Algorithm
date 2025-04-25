import java.util.*;
import java.io.*;

public class Solution {

    public static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static final long INF = 987654321L;
    static int n, m;
    static List<Edge> edges = new ArrayList<>();
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n + 1];
        Arrays.fill(dist, INF);

        dist[1] = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }

        boolean hasNegativeCycle = false;

        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println("-1");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(dist[i] == INF ? -1 : dist[i]);
            }
        }
    }
}
