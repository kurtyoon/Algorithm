import java.io.*;
import java.util.*;

public class Solution {

    public static long dfs(int w, int h, long[][] dp) {
        
        // 모든 알약을 먹은 경우
        if (w == 0 && h == 0) return 1;

        // 이미 계산된 경우
        if (dp[w][h] != 0) return dp[w][h];

        if (w > 0) dp[w][h] += dfs(w - 1, h + 1, dp); // 반으로 쪼개고 먹는경우
        if (h > 0) dp[w][h] += dfs(w, h - 1, dp); // 반개짜리를 먹는 경우

        return dp[w][h];
    } 

    public static long solution(int n) {
        long[][] dp = new long[n + 1][n + 1];
        return dfs(n, 0, dp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            bw.write(solution(n) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
