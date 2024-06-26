# Bruteforce, Back tracking

## 완전탐색과 백트래킹

완전탐색은 모든 경우의 수를 탐색하는 알고리즘이다. 여기서 경우의 수는 순열 또는 조합을 의미한다.

### 반복문을 활용한 완전탐색

단순히 선형적으로 모든 경우의 수를 확인하며 탐색하는 것도 완전탐색이다.

```cpp
vector<int> v = {1, 3, 2, 5, 6, 7, 8};

for (int i = 0; i < v.size(); i++) {
	if (v[i] == 5) {
		cout << i << '\n';
		break;
	}
}

int i = 0;
while (i < v.size()) {
	if (v[i] == 5) {
		cout << i << '\n';
		break;
	}
	i++;
}
```

### 재귀함수를 활용한 완전탐색

반복문으로 완전탐색이 가능한 경우 반복문을 사용해야 한다. 이는 함수 호출을 여러번 하는 것은 코스트가 크기 때문이다.

그런데 반복문으로 해결이 불가능할것 같거나, 어려운 경우 재귀함수를 사용하는 경우가 빠르다.

### 백트래킹

이는 완전탐색에 가지치기를 추가한 것으로, 최대한 불필요한 탐색을 피하며 완전탐색을 진행하는 것을 의미한다.

예를 들어 N과 N개의 자연수가 주어질 때, 합을 11로 mod연산을 진행하여 나오는 가장 큰 수를 구할 때 이를 완전탐색으로 풀게 된다면 다음과 같은 코드일것이다.

```cpp
void go(int idx, int sum) {
	if (idx == n) {
		ret = max(ret, sum % mod);
		return;
	}
	go(idx + 1, sum + v[idx]);
	go(idx + 1, sum);
}

go(0, 0);
```

그러나 이는 불필요한 경우의 수를 탐색하게 된다.

그런데 해당 문제에서 mod N을 하는경우 해당 숫자의 범위는 0 ~ N-1의 범위를 가지는 것은 자명하다.

따라서 11 mod는 10이 최대의 숫자가 된다. 이러한 부분을 넣어 경우의 수를 제거하는 것이 백트래킹이다.

```cpp
void go(int idx, int sum) {
	if (ret == 10) return;
	if (idx == n) {
		ret = max(ret, sum % mod);
		return;
	}
	go(idx + 1, sum + v[idx]);
	go(idx + 1, sum);
}

go(0, 0);
```

## 원상복구

현재까지는 모든 경우의 수를 따지는 완전탐색이다. 어떤 맵에서 색칠하거나, 세운다고 했을 때 경우의 수들끼리 서로의 상태값에 영향을 미치지 않게 하려는 방법이 원상복구이다.

보통은 방문배열을 통해 이를 수행할 수 있다.

A, B , C, D라는 정점이 있다고 가정할 때, A에 방문했다는 것은 `visited[A] = 1` 과 같은 형태일 것이다.

A → B → C와 A → B → D라는 경우의 수를 구하기 위해서는 C라는 정점에 방문한 상태를 되돌려야 한다. 이를 원상복구를 통해 구현해야 한다. 이를 코드로 나타내는 경우 다음과 같다.

```cpp
void go(int idx) {
	for (int there : adj[idx]) {
		if (visited[there]) continue;
		visited[there] = 1;
		go(there);
		visited[there] = 0;
	}
}
```

이를 통해 A → B → C와 A → B → D는 독립적인 상태로 확인할 수 있다. 방문한 경우의 수의 상태값이 다음 경우의 수에 영향을 미치지 않도록 이전 상태로 원상복구를 시켜주는 것이다.
