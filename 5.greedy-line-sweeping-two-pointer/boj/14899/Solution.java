import java.io.*;
import java.util.*;

public class Solution {

    public static class TeamBalancer {
        private final int[][] stats;
        private final int n;
        private final int INF = 987654321;

        public TeamBalancer(int[][] stats, int n) {
            this.stats = stats;
            this.n = n;
        }

        public int balanceTeams() {
            int result = INF;

            for (int i = 0; i < (1 << n); i++) {
                if (Integer.bitCount(i) != n / 2) continue;

                List<Integer> start = new ArrayList<>();
                List<Integer> link = new ArrayList<>();

                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) != 0) {
                        start.add(j);
                    } else {
                        link.add(j);
                    }
                }

                result = Math.min(result, calculateDifference(start, link));
            }

            return result;
        }

        private int calculateDifference(List<Integer> start, List<Integer> link) {
            int startSum = 0, linkSum = 0;

            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    if (i != j) {
                        startSum += stats[start.get(i)][start.get(j)];
                        linkSum += stats[link.get(i)][link.get(j)];
                    }
                }
            }

            return Math.abs(startSum - linkSum);
        }
    }

    public static int solution(int[][] stats, int n) {
        TeamBalancer balancer = new TeamBalancer(stats, n);
        return balancer.balanceTeams();

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] stats = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution(stats, n);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}