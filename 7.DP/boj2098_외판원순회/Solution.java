import java.util.*;
import java.io.*;

public class Solution {

    public static int INF = 987654321; // Integer.MAX_VALUE인 경우 덧셈 연산에서 Overflow 발생

    public static int tsp(int cur, int visited, int n, int[][] w, int[][] dp) {
        
        // 이미 모든 도시를 방문한 경우 (비트마스킹을 통해 모든 도시를 방문한 경우 1111)
        // 경로가 없는 경우 최댓값 반환 -> 경우의 수에서 제외
        if (visited == (1 << n) - 1) {
            return w[cur][0] == 0 ? INF : w[cur][0];
        }

        // 이미 최소 비용을 구한 경우
        if (dp[cur][visited] != -1) return dp[cur][visited];

        // 아직 최소 비용을 구하지 않은 경우, 최댓값으로 설정
        dp[cur][visited] = INF;

        for (int i = 0; i < n; i++) {

            // 이미 방문한 도시거나 이동할 수 없는 경우
            if ((visited & (1 << i)) != 0 || w[cur][i] == 0) continue;

            // 현재 도시에서 i번째 도시로 이동하는 경우의 최소 비용
            dp[cur][visited] = Math.min(dp[cur][visited], tsp(i, visited | (1 << i), n, w, dp) + w[cur][i]);
        }

        return dp[cur][visited];
    }

    public static int solution(int n, int[][] w) {

        int[][] dp = new int[n][1 << n];
        for (int[] row : dp) Arrays.fill(row, -1);

        return tsp(0, 1, n, w, dp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int[][] w = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(solution(n, w) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}