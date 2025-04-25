import java.io.*;
import java.util.*;

public class Solution {

    public static void dfs(int curr, int[] dp) {
        if (curr == 0) return;

        System.out.print(curr + " ");

        if (curr % 3 == 0 && dp[curr] == (dp[curr / 3] + 1)) dfs(curr / 3, dp);
        else if (curr % 2 == 0 && dp[curr] == (dp[curr / 2] + 1)) dfs(curr / 2, dp);
        else if (curr - 1 >= 0 && dp[curr] == (dp[curr - 1] + 1)) dfs(curr - 1, dp);

        return;
    }

    public static void solution(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 987654321);

        dp[1] = 0;

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
            if (i % 2 == 0) dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
        }

        System.out.println(dp[n]);

        dfs(n, dp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        solution(n);

        br.close();
    }    
}
