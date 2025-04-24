import java.util.*;
import java.io.*;

public class Solution {

    public static boolean isInfiniteLoop = false;

    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static boolean isInRange(int y, int x, int n, int m) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    public static int dfs(int y, int x, char[][] map, int[][] dp, boolean[][] visited) {

        // 경계 밖이거나 구멍이라면 종료
        if (!isInRange(y, x, map.length, map[0].length) || map[y][x] == 'H') return 0;

        // 사이클 탐지 (재방문의 경우)
        if (visited[y][x]) {
            isInfiniteLoop = true;
            return -1;
        }

        // 이미 계산된 경우 값 재사용
        if (dp[y][x] != 0) return dp[y][x];

        visited[y][x] = true;
        int move = map[y][x] - '0'; // 몇 칸 이동인지

        // 4 방향 탐색
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d] * move;
            int nx = x + dx[d] * move;

            dp[y][x] = Math.max(dp[y][x], dfs(ny, nx, map, dp, visited) + 1);
            if (isInfiniteLoop) return -1; // 사이클 탐지 시 즉시 종료
        }

        visited[y][x] = false; // 방문 처리 해제
        return dp[y][x];
    }

    public static int solution(int n, int m, char[][] map) {
        int[][] dp = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        int result = dfs(0, 0, map, dp, visited);
        if (isInfiniteLoop) return -1;
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        
        bw.write(solution(n, m, map) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}