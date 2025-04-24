import java.util.*;
import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        long[] dp = new long[10001];

        dp[0] = 1;

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 10000; j++) {
                if (j - i >= 0) {
                    dp[j] += dp[j - i];
                }
            }
        }

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n] + "\n");
        } 
        
        bw.flush();

        bw.close();
        br.close();
    }
}
