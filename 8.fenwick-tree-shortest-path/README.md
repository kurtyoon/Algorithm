# Fenwick tree, Shortest path

## 팬윅트리

팬윅 트리(Fenwick Tree)는 최하위의 켜져있는 비트를 기반으로 트리를 만들어 동적 배열에서 구간합을 효율적으로 구할 수 있는 자료구조이다. 구간 합을 구하는 쿼리와 갱신하는 작업을 $O(\log{N})$ 만에 할 수 있다.

기존에는 정적 배열인 조건에서 누적합을 통해 구간합을 구할 수 있었다. 그런데 만약 동적 배열이 되는 경우에는 어떨까?

예를 들어 배열의 요소가 계속 바뀌는 환경에서 구간합을 구하기 위해서는 매번 수정해야 한다.

### 원리

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/aa4b4748-642b-44d4-a461-da248d1236ba_20250425.png)

팬윅트리는 `{3, 4, 10, 11}`이라는 배열이 주어졌을 때, 위의 그림의 초록색 노드를 만들어 구간합을 구한다.

이때, 초록색 노드를 만들기 위해 최하위 켜져있는 비트 (`idx & -idx`) 개념을 사용한다.

만약 배열의 `{3}`을 1번째 요소라고 할때, 최하위 인덱스를 더하면 `10`, 즉 2가 된다.

이 상태에서 최하위 인덱스를 다시 더하면 `100`, 즉 4가 된다.

코드로 간단하게 알아보자.

```java
public class FenwickTree {

    public static int n;
    public static int[] tree;

    // 요소의 값 업데이트
    public static void update(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += index & -index;
        }
    }

    // 1 ~ index 까지의 누적합
    public static int sum(int index) {
        int result = 0;

        while (index > 0) {
            result += tree[index];
            index -= index & -index;
        }

        return result;
    }

    // [left, right]의 구간합
    public static int query(int left, int right) {
        return sum(right) - sum(left - 1);
    }

    public static void main(String[] args) {
        int[] data = {3, 4, 10, 11};
        n = data.length;
        tree = new int[n + 1];

        for (int i = 0; i < n; i++) {
            update(i + 1, data[i]); // BIT는 1-based
        }

        System.out.println("1 ~ 4 sum: " + query(1, 4)); // 3 + 4 + 10 + 11 = 28
        System.out.println("2 ~ 3 sum: " + query(2, 3)); // 4 + 10 = 14

        // index 2 (즉, data[1])에 5 추가 (4 -> 9)
        update(2, 5);

        System.out.println("1 ~ 4 sum: " + query(1, 4)); // 3 + 9 + 10 + 11 = 33
        System.out.println("2 ~ 3 sum: " + query(2, 3)); // 9 + 10 = 19
    }
}
```

`update()` 함수는 최하위 초록색 노드를 만드는 역할을 한다. 예를 들어 `data[1] = 5`인 상태에서 9로 변했다면, 해당 차이를 각각의 노드에 더하는 식으로 업데이트한다.

`sum(index)` 함수는 해당 index까지의 합을 초록색 노들르 기반으로 더해서 값을 만든다.

## 최단거리 알고리즘

최단거리 알고리즘은 그래프 상의 두 정점 사이의 거리가 최소가 되는 경로를 찾는 알고리즘이다.

앞의 BFS는 가중치가 동일한 그래프에서 최단거리 알고리즘으로 사용할 수 있었지만, 가중치가 다를 경우 최단거리 알고리즘을 사용해야 한다.

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/8ab2cc7e-48b4-439e-9ccb-d133dacaa9d3_20250425.png)

위의 그래프에서 A부터 C까지의 최단거리를 BFS를 통해 구한다면 14의 비용을 가지게 된다. 그러나 실제로는 A->B->D->C의 경로를 통해 이동해 6의 비용을 가지는 경로가 최단거리이다.

이처럼 가중치가 다를 경우 BFS를 최단거리 알고리즘으로 사용할 수 없다.

### 다익스트라 알고리즘 (Dijkstra's Algorithm)

다익스트라 알고리즘은 양의 가중치만을 가지는 그래프에서 한 정점에서 다른 모든 정점까지의 최단거리를 구하는 알고리즘이다.

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/a1e1f92b-69e1-49d6-8ff0-16620ea5fc54_20250425.png)

정점 A가 시작점일 때, 모든 정점으로의 최단거리를 구하는 알고리즘이다.

`u -> {a, b, c}` 우선순위 큐를 사용해 현자 방문할 정점 중 가장 비용이 작은 경로를 우선적으로 선택하고, 거리 배열을 갱신하여 최단 경로를 찾는 방식이다.

다익스트라 알고리즘의 경우 가중치는 항상 양의 가중치여야 한다. 예를 들어, 문제에서 타임머신을 통해 되돌아간다는 형식으로 음의 가중치를 갖는 경우 다익스트라 알고리즘을 사용할 수 없다.

원리는 다음과 같다.

1. 우선순위 큐에서 가장 짧은 경로의 정점을 뽑아낸다.
2. 선택한 정점을 통해 이동할 수 있는 정점들의 거리 값을 계산하여, 기존 거리보다 짧으면 `dist[]` 배열을 갱신한다. 이때, 정점 u에서 정점 v까지 거리와 비교하여 더 짧아진 경우 `dist[u] + w(u, v) = dist[v]`로 갱신하는데, 이를 완화가 일어났다고 한다.
3. 갱신된 최단거리와 정점의 정보를 다시 우선순위 큐에 넣고 1번을 반복한다.

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/e45d7b02-63ad-45ef-8a60-ebe678c058e7_20250425.png)

#### 다익스트라 알고리즘 예제

- N개의 교차로와 M개의 도로가 있다. 각 도로는 두 교차로를 연결하며, 이 도로를 지나는 데 걸리는 시간이 주어진다. 특정 시작 교차로에서 다른 모든 교차로까지의 최단시간을 구하라
- 1 <= N <= 20000
- 1 <= M <= 100000
- 모든 가중치는 양수이고, 연결되지 않은 교차로가 존재할 수 있다.
- 첫 줄에 N, M, 시작 교차로 S가 주어진다. 그 다음 M개의 줄에 걸쳐 각 도로의 정보가 세 개의 정수로 주어진다. (출발 교차로, 도착 교차로, 도로를 지나는데 걸리는 시간)
- S에서 각 교차로까지의 최단 거리를 출력하라. 도달할 수 없다면 INF를 출력하라.

입력

```sh
5 7 1
1 2 1
1 3 4
2 3 2
2 3 1
2 4 5
2 4 1
4 5 2
```

출력

```sh
0
1
2
3
5
```

```java
public static class Dijkstra {

    public static class Node implements Comparable<Node> {
        int vertex, cost;

        Node(int vertex, cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    public static final INF = Integer.MAX_VALUE;
    public static List<NODE>[] graph;
    public static int[] dist;

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            int cost = current.cost;

            // 느긋한 삭제
            // 이미 더 짧은 경로로 방문된 경우 무시함
            if (dist[u] < cost) continue;

            for (Node neighbor : graph[u]) {
                int v = neighbor.vertex;
                int weight = neighbor.cost;

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight; // dist 배열 완화
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 교차로 수
        int m = Integer.parseInt(st.nextToken()); // 도로 수
        int s = Integer.parseInt(st.nextToken()); // 시작 교차로

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()); // 출발
            int v = Integer.parseInt(st.nextToken()); // 도착
            int w = Integer.parseInt(st.nextToken()); // 비용
            graph[u].add(new Node(v, w));
        }

        dijkstra(s);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                sb.append("INF\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
}
```

최단거리 배열은 최솟값을 만드는 것이 핵심이다. 최솟값은 최댓값부터 시작해야 하기 때문에 아주 큰 갑승로 초기화하고, 시작점은 0부터 시작해서 최단거리 배열을 담는다.

```java
for (int i = 1; i <= n; i++) {
    graph[i] = new ArrayList<>();
    dist[i] = INF;
}
```

우선순위 큐를 사용하여 최단거리가 가장 작은 수능로 뽑고, 내가 탐색하려는 이 정점이 지금의 정점 + 그 정점까지의 거리가 더 작다면 갱신한다.

```java
int v = neighbor.vertex;
int weight = neighbor.cost;

if (dist[v] > dist[u] + weight) {
    dist[v] = dist[u] + weight; // dist 배열 완화
    pq.offer(new Node(v, dist[v]));
}
```

우선순위 큐 안에는 여러 개의 같은 정점이 저장될 수 있다. 이 중에서 가장 짧은 정점만을 처리하는 것을 느긋한 삭제라 하며, 그 외의 불필요한 정점을 바로 삭제하지 않고, 나중에 다음과 같이 `continue`를 통해 거르는 것을 말한다. 이로 인해 최솟값을 가진 정점 하나만을 기반으로 인접 정점들을 탐색하여 최적의 최단거리를 구할 수 있다.

아래의 두 코드로 표현되며, 둘 다 같은 의미이다.

```java
if (dist[u] < cost) continue;
if (dist[u] != cost) continue;
```

`dist[]`와 `cost`의 값이 다르다는 것은 우선순위 큐에 집어 넣을 때의 값과 달라졌다는 것을 의미한다. 즉, 집어넣은 이후에 앞서서 더 작은 값으로 완화되었다는 것을 의미하며, 이미 더 작은 값으로 완화되었기 때문에 이 부분을 다시 탐색하는 것은 불필요하다. 따라서 그냥 넘어가게 된다.

시간 복잡도는 $E\log{E}$ 또는 $E\log{V}$이다.

최악의 경우 모든 간선에 대해 우선순위 큐에 집어넣어야 하며 이때, E개의 간선에 대해 우선순위 큐를 기반으로 최단거리 정점 추출에 $\log{E}$가 들어 $E\log{E}$가 된다.

여기서 E는 방향성이 있는 완전 그래프에서 $E = V(V - 1)$이라는 특징을 가진다.

이 때문에 $E\log{V^{^2{}}}$가 되고, 이는 $2E\log{V}$가 되어 $E\log{V}$가 된다.

### 플로이드 워셜 (Floyd-Warshall)

플로이드 워셜 알고리즘은 모든 정점에서 모든 정점 까지의 최단 경로를 구하는 알고리즘이다.

음수 가중치가 있는 그래프에서도 사용할 수 있지만, 음수 사이클이 존재하면 사용할 수 없다. 모든 노드 쌍 (i, j)에 대해, 노드 k를 경유했을 때의 최단 경로를 구하고 갱신하는 방식으로 진행된다.

노드 i에서 j까지의 경로가 존재할 때, 노드 k를 경유하는 경우가 더 짧은 경로라면 해당 경로로 완화한다.

$dist[i][j] = \min{(dist[i][j], dist[i][k] + dist[k][j])}$

![](https://kurtyoon-space.s3.ap-northeast-2.amazonaws.com/f615a94a-ded3-475f-9b75-254ce9f1429a_20250425.png)

모든 노드 쌍이라는 것은 위의 그림에서 표현된 모든 갈 수있는 경로를 의미한다.

#### 플로이드 워셜 알고리즘 예제

- N개의 도시, M개의 단방향 도로가 존재한다. 각 도로는 특정 두 도시를 연결하고, 비용이 주어진다. 모든 도시에 대해 다른 도시까지의 최소 비용을 구하라. 경로가 없다면 INF를 출력한다.
- N <= 100, M <= 10000, 최소 비용 >= 0

```java
class FloydWarshall {

    public static int INF = Integer.MAX_VALUE;
    public static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            dist[u][v] = Math.min(dist[u][v], w);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // i -> k, k -> j가 존재할 때, 경유하는 경로가 비용이 더 작다면 완화
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF" + " ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
```

### 벨만-포드 (Bellman-Ford)

벨만포드 알고리즘은 음수 가중치가 있고 음수 사이클이 있는 그래프에서 단일 출발점에서의 최단 경로를 구하는 알고리즘이고, 음수 사이클이 존재하는 것도 판별 가능하다.

문제에서 타임머신, 블랙홀, 과거로 돌아갈 수 있음을 언급하는 경우 벨만포드를 고려해봐야 한다.

벨만포드 알고리즘은 모든 간선을 반복적으로 확인하며 최단 경로를 갱신한다.

원리는 아래와 같다.

1. 초기화: 출발 노드에서 출발 노드까지의 거리를 0으로 설정하고, 나머지 노드들은 무한대로 초기화한다.
2. 최단경로 갱신: 모든 간선을 V-1 번 반복하여 검사하며, 현재 경로가 기존 경로보다 짧다면 해당 경로로 완화한다.
3. 음수 사이클 확인: 최단 경로가 확정된 후에도, 다시 모든 간선을 검사하여 최단 경로가 더 짧아지는 경로가 있다면, 이는 음수 사이클이 존재함을 의미한다.

#### 벨만-포드 알고리즘 예제

- 주어진 그래프에서 출발도시 1부터 다른 모든 도시에 대한 최단경로를 구하라.
- 음수 사이클이 존재하는 경우 -1을 출력하고, 없는 경우 각 도시까지의 최단 거리를 출력하라.
- 노드의 수 n (2 <= n <= 1000), 간선의 수 m (1 <= m <= 5000)
- 다음 m개의 줄에 (a, b, c), 도시 a에서 도시 b로 가는 가중치 c의 단방향 간선이 있음을 의미함 (-10000 <= c <= 10000)

```java
import java.util.*;
import java.io.*;

public class Solution {

    public static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static final long INF = 987654321L;
    static int n, m;
    static List<Edge> edges = new ArrayList<>();
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n + 1];
        Arrays.fill(dist, INF);

        dist[1] = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }

        boolean hasNegativeCycle = false;

        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println("-1");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.println(dist[i] == INF ? -1 : dist[i]);
            }
        }
    }
}
```

최소값을 구하기 때문에 dist 배열을 최댓값으로 설정한다. 정점의 번호가 1부터 시작하기 떄문에 `n + 1` 크기의 배열을 사용하고, 출발점인 1번 정점에 대해 거리를 0으로 설정한다.

```java
dist = new long[n + 1];
Arrays.fill(dist, INF);

dist[1] = 0;
```

간선의 수가 아닌 정점의 수 - 1번 반복하는 것이 정확한 벨만포드 알고리즘이다. 그러나 n번 반복하고, n번째에서 갱신되는 경우 음수 사이클을 확인할 수 있기 때문에 n번 반복한다.

```java
for (int i = 0; i < n; i++) {
    for (Edge edge : edges) {
        if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
            dist[edge.to] = dist[edge.from] + edge.cost;
        }
    }
}
```

n번째 루프에서 거리 배열이 갱신된다면, 이는 음수 사이클이 존재하는 것을 의미한다. 즉, 더 짧은 경로가 존재할 수 있다는 것은 무한히 줄어드는 사이클이 있다는 뜻이다.

```java
for (Edge edge : edges) {
    if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
        hasNegativeCycle = true;
        break;
    }
}
```

총 연산의 수는 O(n \* m)으로 여기서의 n은 정점의 수, m은 간선의 수를 의미한다.
