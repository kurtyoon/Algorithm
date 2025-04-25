# 외판원 순회

[바로가기](https://www.acmicpc.net/problem/12852)

## 문제

정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
2. X가 2로 나누어 떨어지면, 2로 나눈다.
3. 1을 뺀다.

정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.

## 입력

첫째 줄에 1보다 크거나 같고, $10^{6}$보다 작거나 같은 자연수 N이 주어진다.

## 출력

첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다. 정답이 여러 가지인 경우에는 아무거나 출력한다.

# 해결 방법

매우 큰 수를 입력할 때는 `Integer.MAX_VALUE` 대신 직접 정의해서 사용하자. `Integer.MAX_VALUE`를 사용하는 경우 Overflow가 발생할 수 있다.

```java
import java.io.*;
import java.util.*;

public class Solution {

    // 경로 추적 용
    public static void dfs(int curr, int[] dp) {
        if (curr == 0) return;

        System.out.print(curr + " ");

        if (curr % 3 == 0 && dp[curr] == (dp[curr / 3] + 1)) dfs(curr / 3, dp);
        else if (curr % 2 == 0 && dp[curr] == (dp[curr / 2] + 1)) dfs(curr / 2, dp);
        else if (curr - 1 >= 0 && dp[curr] == (dp[curr - 1] + 1)) dfs(curr - 1, dp);

        return;
    }

    public static void solution(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 987654321);

        dp[1] = 0;

        for (int i = 1; i <= n; i++) {
            // 가장 위의 경우를 예로 들면, 3으로 나눠떨어지는 경우, 3으로 나눴을 때의 값 + 지금 값 경우의 수
            if (i % 3 == 0) dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
            if (i % 2 == 0) dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
        }

        System.out.println(dp[n]);

        dfs(n, dp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        solution(n);

        br.close();
    }
}

```
