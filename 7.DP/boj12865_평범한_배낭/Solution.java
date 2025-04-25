import java.io.*;
import java.util.*;

public class Solution {

    public static int solution(int n, int k, int[][] products) {
        int[] dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = k; j >= products[i][0]; j--) {
                dp[j] = Math.max(dp[j], dp[j - products[i][0]] + products[i][1]);
            }
        }

        return dp[k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] products = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            products[i][0] = Integer.parseInt(st.nextToken());
            products[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, k, products));

        br.close();
    }
}
