import java.io.*;
import java.util.*;

public class Solution {

    public static int dfs(int t, int pos, int w, int[][][] dp, int[] tree) {
        if (w < 0) return Integer.MIN_VALUE;
        if (t == tree.length) return 0;

        if (dp[t][pos][w] != -1) return dp[t][pos][w];

        return dp[t][pos][w] = Math.max(
            dfs(t + 1, pos, w, dp, tree), 
            dfs(t + 1, 1 - pos, w - 1, dp, tree)
            ) + (tree[t] - 1 == pos ? 1 : 0); 
    }

    public static int solution(int t, int w, int[] tree) {
        int[][][] dp = new int[t + 1][2][w + 1];

        for (int[][] level1 : dp) {
            for (int[] level2 : level1) {
                Arrays.fill(level2, -1);
            }
        }

        return Math.max(
            dfs(0, 0, w, dp, tree),
            dfs(0, 1, w - 1, dp, tree)
        );

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] tree = new int[t];
        for (int i = 0; i < t; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        bw.write(solution(t, w, tree) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
