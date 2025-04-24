# 외판원 순회

[바로가기](https://www.acmicpc.net/problem/2098)

## 문제

외판원 순회 문제는 영어로 Traveling Salesman problem (TSP) 라고 불리는 문제로 computer science 분야에서 가장 중요하게 취급되는 문제 중 하나이다. 여러 가지 변종 문제가 있으나, 여기서는 가장 일반적인 형태의 문제를 살펴보자.

1번부터 N번까지 번호가 매겨져 있는 도시들이 있고, 도시들 사이에는 길이 있다. (길이 없을 수도 있다) 이제 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획하려고 한다. 단, 한 번 갔던 도시로는 다시 갈 수 없다. (맨 마지막에 여행을 출발했던 도시로 돌아오는 것은 예외) 이런 여행 경로는 여러 가지가 있을 수 있는데, 가장 적은 비용을 들이는 여행 계획을 세우고자 한다.

각 도시간에 이동하는데 드는 비용은 행렬 W[i][j]형태로 주어진다. W[i][j]는 도시 i에서 도시 j로 가기 위한 비용을 나타낸다. 비용은 대칭적이지 않다. 즉, W[i][j] 는 W[j][i]와 다를 수 있다. 모든 도시간의 비용은 양의 정수이다. W[i][i]는 항상 0이다. 경우에 따라서 도시 i에서 도시 j로 갈 수 없는 경우도 있으며 이럴 경우 W[i][j]=0이라고 하자.

N과 비용 행렬이 주어졌을 때, 가장 적은 비용을 들이는 외판원의 순회 여행 경로를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 도시의 수 N이 주어진다. (2 ≤ N ≤ 16) 다음 N개의 줄에는 비용 행렬이 주어진다. 각 행렬의 성분은 1,000,000 이하의 양의 정수이며, 갈 수 없는 경우는 0이 주어진다. W[i][j]는 도시 i에서 j로 가기 위한 비용을 나타낸다.

항상 순회할 수 있는 경우만 입력으로 주어진다.

## 출력

첫째 줄에 외판원의 순회에 필요한 최소 비용을 출력한다.

# 해결 방법

DP는 부분 최적회를 쌓아 Global 최적해를 구하는 문제이다.

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/527d7643-770e-48a2-8228-6c7bf23ffe41_20250424.png)

위의 그림과 같이 A, B, C, D라는 노드가 존재하고, A에서 D까지 가는 경로를 구한다고할 때, A-B-C-D라는 경로와 A-C-B-D라는 경로는 다르긴 하다.

그런데 해당 문제에서는 결국 최소값을 찾아서 다음 노드로 이동하게 된다. 따라서 경로에서 이전 노드까지 어떤 노드를 방문한 순서는 상관이 없다.

만약 순서가 상관있다면 N의 최대범위인 16에서 최대 16!개의 경우의 수를 구해야 한다.

또한, 출발지가 명시되어있지 않지만, 순회이기 때문에 어떤 한 지점을 선택한다면 해당 문제를 해결할 수 있다.

```java
import java.util.*;
import java.io.*;

public class Main {

    public static int INF = 987654321; // Integer.MAX_VALUE인 경우 덧셈 연산에서 Overflow 발생

    public static int tsp(int cur, int visited, int n, int[][] w, int[][] dp) {

        // 이미 모든 도시를 방문한 경우 (비트마스킹을 통해 모든 도시를 방문한 경우 1111)
        // 경로가 없는 경우 최댓값 반환 -> 경우의 수에서 제외
        if (visited == (1 << n) - 1) {
            return w[cur][0] == 0 ? INF : w[cur][0];
        }

        // 이미 최소 비용을 구한 경우
        if (dp[cur][visited] != -1) return dp[cur][visited];

        // 아직 최소 비용을 구하지 않은 경우, 최댓값으로 설정
        dp[cur][visited] = INF;

        for (int i = 0; i < n; i++) {

            // 이미 방문한 도시거나 이동할 수 없는 경우
            if ((visited & (1 << i)) != 0 || w[cur][i] == 0) continue;

            // 현재 도시에서 i번째 도시로 이동하는 경우의 최소 비용
            dp[cur][visited] = Math.min(dp[cur][visited], tsp(i, visited | (1 << i), n, w, dp) + w[cur][i]);
        }

        return dp[cur][visited];
    }

    public static int solution(int n, int[][] w) {

        int[][] dp = new int[n][1 << n];
        for (int[] row : dp) Arrays.fill(row, -1);

        return tsp(0, 1, n, w, dp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] w = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(solution(n, w) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
```
