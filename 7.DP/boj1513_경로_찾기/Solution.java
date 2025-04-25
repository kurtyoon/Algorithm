import java.io.*;
import java.util.*;

public class Solution {

    public static int n, m, c;
    public static int[][] arcade = new int[51][51];
    public static int[][][][] dp = new int[51][51][51][51];
    public static int MOD = 1000007;

    public static int dfs(int y, int x, int cnt, int prev) {
        if (y > n || x > m) return 0;

        if (y == n && x == m) {
            if (cnt == 0 && arcade[y][x] == 0) return 1;
            if (cnt == 1 && arcade[y][x] > prev) return 1;
            return 0;
        }

        if (dp[y][x][cnt][prev] != -1) return dp[y][x][cnt][prev];

        if (arcade[y][x] == 0) {
            dp[y][x][cnt][prev] = (dfs(y + 1, x, cnt, prev) + dfs(y, x + 1, cnt, prev)) % MOD;
        } else if (arcade[y][x] > prev) {
            dp[y][x][cnt][prev] = (dfs(y + 1, x, cnt - 1, arcade[y][x]) + dfs(y, x + 1, cnt - 1, arcade[y][x])) % MOD;
        }

        return dp[y][x][cnt][prev];
    }

    public static void solution() {
        for (int[][][] level1 : dp) {
            for (int[][] level2 : level1) {
                for (int[] level3 : level2) {
                    Arrays.fill(level3, -1);
                }
            }
        }

        for (int i = 0; i <= c; i++) {
            System.out.print(dfs(1, 1, i, 0) + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= c; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            arcade[y][x] = i;
        }

        solution();

        br.close();
    }
}
