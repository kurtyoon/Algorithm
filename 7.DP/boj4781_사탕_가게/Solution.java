import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dp = new int[1000001];
        while (true) {
            String line = br.readLine();

            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            String[] input = st.nextToken().split("\\.");
            int cost = Integer.parseInt(input[0]) * 100 + Integer.parseInt(input[1]);

            Arrays.fill(dp, 0);

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int c = Integer.parseInt(st.nextToken());

                input = st.nextToken().split("\\.");
                int price = Integer.parseInt(input[0]) * 100 + Integer.parseInt(input[1]); 

                for (int j = price; j <= cost; j++) {
                    dp[j] = Math.max(dp[j], dp[j - price] + c);
                }
            }

            System.out.println(dp[cost]);
        }
    }

}
