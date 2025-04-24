import java.util.*;
import java.io.*;

public class Solution {

    public static int solution(int n, int[][] map) {

        // 가로: 0, 대각선: 1, 세로: 2
        int[][][] dp = new int[n][n][3];

        // 시작 위치: (0, 1)에 가로 방향으로 파이프가 놓여있음
        dp[0][1][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // 벽이 있는 칸은 도달 불가능
                if (map[i][j] == 1) continue;

                // 가로로 끝나는 파이프
                // 이전 칸에서 가로 또는 대각선으로 온 경우
                if (j - 1 >= 0) {
                    dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][1];
                } 

                // 세로로 끝나는 파이프
                // 이전 칸에서 세로 또는 대각선으로 온 경우
                if (i - 1 >= 0) {
                    dp[i][j][2] += dp[i - 1][j][2] + dp[i - 1][j][1];
                }

                // 대각선으로 끝나는 파이프
                // 이전 칸에서 가로, 세로, 대각선 방향으로 온 경우
                // 대각선은 해당 칸 뿐만 아니라 좌, 상 방향 모두 벽이 없어야 함
                if (j - 1 >= 0 && i - 1 >= 0) {
                    if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                        dp[i][j][1] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                    }
                }
            }
        }

        // 도착점에 도달하는 모든 방향의 합을 반환
        return dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(solution(n, map) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
