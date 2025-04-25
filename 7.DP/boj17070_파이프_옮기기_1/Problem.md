# 파이프 옮기기 1

[바로가기](https://www.acmicpc.net/problem/17070)

## 문제

유현이가 새 집으로 이사했다. 새 집의 크기는 N×N의 격자판으로 나타낼 수 있고, 1×1크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 (r, c)로 나타낼 수 있다. 여기서 r은 행의 번호, c는 열의 번호이고, 행과 열의 번호는 1부터 시작한다. 각각의 칸은 빈 칸이거나 벽이다.

오늘은 집 수리를 위해서 파이프 하나를 옮기려고 한다. 파이프는 아래와 같은 형태이고, 2개의 연속된 칸을 차지하는 크기이다.

![](https://upload.acmicpc.net/3ceac594-87df-487d-9152-c532f7136e1e/-/preview/)

파이프는 회전시킬 수 있으며, 아래와 같이 3가지 방향이 가능하다.

![](https://upload.acmicpc.net/b29efafa-dbae-4522-809c-76d5c184a231/-/preview/)

파이프는 매우 무겁기 때문에, 유현이는 파이프를 밀어서 이동시키려고 한다. 벽에는 새로운 벽지를 발랐기 때문에, 파이프가 벽을 긁으면 안 된다. 즉, 파이프는 항상 빈 칸만 차지해야 한다.

파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다. 파이프는 밀면서 회전시킬 수 있다. 회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향이어야 한다.

파이프가 가로로 놓여진 경우에 가능한 이동 방법은 총 2가지, 세로로 놓여진 경우에는 2가지, 대각선 방향으로 놓여진 경우에는 3가지가 있다.

아래 그림은 파이프가 놓여진 방향에 따라서 이동할 수 있는 방법을 모두 나타낸 것이고, 꼭 빈 칸이어야 하는 곳은 색으로 표시되어져 있다.

![](https://upload.acmicpc.net/0f445b26-4e5b-4169-8a1a-89c9e115907e/-/preview/)
가로

![](https://upload.acmicpc.net/045d071f-0ea2-4ab5-a8db-61c215e7e7b7/-/preview/)
세로

![](https://upload.acmicpc.net/ace5e982-6a52-4982-b51d-6c33c6b742bf/-/preview/)
대각선

가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.

## 입력

첫째 줄에 집의 크기 N(3 ≤ N ≤ 16)이 주어진다. 둘째 줄부터 N개의 줄에는 집의 상태가 주어진다. 빈 칸은 0, 벽은 1로 주어진다. (1, 1)과 (1, 2)는 항상 빈 칸이다.

## 출력

첫째 줄에 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력한다. 이동시킬 수 없는 경우에는 0을 출력한다. 방법의 수는 항상 1,000,000보다 작거나 같다.

# 해결 방법

경우의 수는 더하기이다.

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/452e1f42-881a-4342-b13c-12454541b885_20250424.png)

예를 들어 위의 그래프에서 정점 D로 갈 수 있는 경우의 수는 A까지 가는 경우의 수인 3, B까지 가는 경우의 수인 3(A) + 1 = 4, C까지 가는 경우의 수인 3(A) + 2 = 5를 모두 더한 3 + 4 + 5 = 12이다.

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/22d6a4d7-f7ac-405a-be54-9fb865839f70_20250424.png)

문제에서 파란색 점에 대한 경우의 수는 빨간색까지의 경우의 수를 더하면 된다. 이를 DP 배열로 만들어 보면 `dp[y][x][dir]`로 선언할 수 있다.

즉, `dir`의 상태값에 따라 0인 경우 가로, 1인 경우 대각선, 2인 경우 세로로 정의할 수 있다.

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/f5c975df-9419-4fa0-9dfc-b3e086800422_20250424.png)

```java
import java.util.*;
import java.io.*;

public class Main {

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

```
