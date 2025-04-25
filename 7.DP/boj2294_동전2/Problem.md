# 동전 2

[바로가기](https://www.acmicpc.net/problem/2294)

## 문제

n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.

## 입력

첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.

## 출력

첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.

# 해결 방법

전형적인 냅색(knapsack) 문제이다.

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/21788cbc-2a41-4eba-b9a0-9dc252aafd9f_20250425.png)

1개 씩만 사용하는 경우

```java
for (int i = 0; i < n; i++) {
    for (int j = k; j >= coins[i]; j--) {
        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
    }
}
```

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static int solution(int n, int k, int[] coins) {
        int[] dp = new int[k + 1];
        Arrays.fill(dp, 987654321);

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        if (dp[k] == 987654321) return -1;
        else return dp[k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(n, k, coins));

        br.close();
    }
}
```
