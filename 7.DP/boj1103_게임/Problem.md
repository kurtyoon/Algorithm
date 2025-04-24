# 외판원 순회

[바로가기](https://www.acmicpc.net/problem/1103)

## 문제

형택이는 1부터 9까지의 숫자와, 구멍이 있는 직사각형 보드에서 재밌는 게임을 한다.

일단 보드의 가장 왼쪽 위에 동전을 하나 올려놓는다. 그다음에 다음과 같이 동전을 움직인다.

1. 동전이 있는 곳에 쓰여 있는 숫자 X를 본다.
2. 위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
3. 동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.

만약 동전이 구멍에 빠지거나, 보드의 바깥으로 나간다면 게임은 종료된다. 형택이는 이 재밌는 게임을 되도록이면 오래 하고 싶다.

보드의 상태가 주어졌을 때, 형택이가 최대 몇 번 동전을 움직일 수 있는지 구하는 프로그램을 작성하시오.

## 입력

줄에 보드의 세로 크기 N과 가로 크기 M이 주어진다. 이 값은 모두 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 보드의 상태가 주어진다. 쓰여 있는 숫자는 1부터 9까지의 자연수 또는 H이다. 가장 왼쪽 위칸은 H가 아니다. H는 구멍이다.

## 출력

첫째 줄에 문제의 정답을 출력한다. 만약 형택이가 동전을 무한번 움직일 수 있다면 -1을 출력한다.

# 해결 방법

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/69b55fea-ef7e-488e-994b-1050a4409577_20250424.png)

```java
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
```
