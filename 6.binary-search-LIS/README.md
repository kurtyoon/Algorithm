# Binary search, LIS

## 이분탐색

이분 탐색은 정렬된 배열에서 탐색 범위를 절반씩 줄여나가는 방식으로 특정 값을 찾는 알고리즘이다. 시간 복잡도는 $O(\log{N})$이며 이진탐색이라고도 부른다.

문제에서 주어진 배열의 크기가 너무나도 큰 상태에서 어떤 값을 찾아야 하는 로직이 있을 때, 해당 배열을 정렬하고 이분 탐색을 사용하면 $O(\log{N})$의 시간복잡도로 효율적으로 값을 찾을 수 있다.

동작 원리는 다음과 같다.

1. 초기 설정: 배열의 가장 왼쪽 인덱스인 `left`와 가장 오른쪽 인덱스인 `right`를 설정한다.
2. 중간값 계산: 현재 배열의 중간 인덱스 `mid`를 계산한다. 중간 인덱스는 `(left + right) / 2`로 계산된다.
3. 비교
   - 목표값과 중간값이 같을 때: 중간 인덱스 `mid`에 위치한 값이 목표값과 일치한다면 탐색을 종료하고, `mid`를 반환한다.
   - 목표값이 중간값보다 작을 때: 목표값이 중간값보다 작다면, 오른쪽 절반은 탐색할 필요가 없으므로 `right`를 `mid - 1`로 설정하여 왼쪽 절반만 탐색한다.
   - 목표값이 중간값보다 클 때: 목표값이 중간값보다 크다면, 왼쪽 절반은 탐색할 필요가 없으므로 `left`를 `mid + 1`로 설정하여 오른쪽 절반만 탐색한다.
4. 반복: 이 과정을 목표값을 찾거나 `left`가 `right`보다 커질 때까지 반복한다. 목표값을 찾지 못한 경우에는 -1 또는 배열의 범위에 해당하지 않는 숫자를 반환한다.

절반을 기준으로 목표값이 왼쪽인지 오른쪽인지를 판단하며 탐색을 진행하기 때문에, 탐색 진행마다 탐색 범위가 절반으로 줄어든다. 따라서 $O(\log{/n})$의 시간복잡도를 가진다.

이때 중요한 점은 이분탐색은 반드시 정렬된 배열에서만 가능하다.

```java
public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 3, 22, 6, 1, 30, 21, 9};
        int target = 6;

        // 배열 정렬
        Arrays.sort(arr);

        int result = binarySearch(arr, target);

        if (result != -1) {
            System.out.println("Target " + target + " found at index: " + result);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }
    }
}
```

만약 문제에서 배열의 형태가 아닌 리스트 형태가 주어진다면 Collections 라이브러리를 사용할 수 있다.

```java
public class BinarySearch {

    public static int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = list.get(mid);

            if (midVal == target) {
                return mid;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 3, 22, 6, 1, 30, 21, 9));
        int target = 6;

        // 리스트 정렬
        Collections.sort(list);

        int result = binarySearch(list, target);
        // Collections 라이브러리를 사용할 경우 다음과 같다.
        // int result = Collections.binarySearch(list, target);

        if (result != -1) {
            System.out.println("Target " + target + " found at index: " + result);
        } else {
            System.out.println("Target " + target + " not found in the array");
        }
    }
}
```

이진 탐색을 응용하면 특정 값의 위치 또는 그보다 크거나 작은 값의 위치를 쉽게 찾을 수 있다.

목표값 이상이 처음 나오는 위치를 lower_bound라 하고, 목표값 초과가 처음 나오는 위치를 upper_bound라고 한다.

```java
public static int lowerBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left < right) {
        int mid = (left + right) / 2;

        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}

public static int upperBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left < right) {
        int mid = (left + right) / 2;

        if (arr[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }

    return left;
}
```

위의 개념도 동일하게 정렬된 배열에서만 사용 가능하다.

### 이분탐색 예제 1

어떤 시스템은 하루 동안 수집한 데이터를 기준으로, 후속 요청에서 해당 데이터가 기존에 수집된 것인지 아닌지를 판별하는 기능을 제공해야 한다.
시스템은 다수의 테스트 케이스를 처리해야 하며, 각 테스트 케이스는 다음과 같은 구성으로 이루어진다.

- 첫 번째 목록에는 시스템이 이미 수집한 데이터들이 저장되어 있다.
- 두 번째 목록은 요청(query) 목록이며, 이 목록의 각 항목에 대해 시스템은 “존재함” 또는 “존재하지 않음”을 응답해야 한다.

입력

첫 줄에 테스트 케이스의 개수 T가 주어진다.
각 테스트 케이스는 다음과 같이 구성된다:

1. 수집 데이터 개수 N
2. N개의 정수 데이터 (정렬되지 않음)
3. 요청 데이터 개수 M
4. M개의 요청 정수 데이터

출력

- 각 테스트 케이스마다 요청된 M개의 숫자에 대해,
- 수집 데이터에 존재하면 "존재함"
- 존재하지 않으면 "존재하지 않음"
- 각 응답은 한 줄에 하나씩 출력

```java
public class BinarySearch {

    public static String check(int target, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) == target) {
                return "존재함";
            } else if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "존재하지 않음";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            List<Integer> data1 = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                data1.add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(data1);

            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                int query = Integer.parseInt(st.nextToken());

                sb.append(check(query, note1)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
```

이분 탐색을 이용하면 최적화 문제를 결정 문제로 변환할 수 있다.

이 방법은 주로 어떤 값을 최대화하거나 최소화하는 최적화 문제를 해결할 때 사용된다. 여기서 최적화 문제와 결정 문제는 다음과 같은 차이가 있다.

### 이분탐색 예제 2

N명의 아이가 사탕을 나눈다. 사탕은 M가지의 서로 다른 맛을 가진다. 각 아이는 한 가지 맛의 사탕만 가질 수 있고, 여러 맛의 사탕을 동시에 가지는 것은 불가능하다.

질투심은 가장 많은 사탕을 가져간 아이가 가지고 있는 사탕의 개수이다. 질투심을 최소화하도록 사탕을 나눠라.

N <= 1,000,000,000, 1 <= M <= 300,000

```java
public class BinarySearch {

    public static int n, m;
    public static int[] arr;


    // 조건을 만족하는지 검사
    // estimateVal: 현재 이분 탐색의 기준값
    public static boolean isValid(int estimateVal) {
        int count = 0;

        for (int i = 0; i < m; i++) {
            count += arr[i] / estimateVal;

            // 나머지가 존재하면 하나 더 필요함
            if (arr[i] % estimateVal != 0) count++;
        }

        return count <= n;
    }

    // 이분 탐색을 통해 최소 가능한 값 찾기
    public static int binarySearch() {
        int left = 1;
        int right = Arrays.stream(arr).max().getAsInt();

        int result = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isValid(mid)) {
                result = Math.min(result, mid); // 조건을 만족하므로 갱신
                right = mid - 1; // 더 작은 값이 가능한지 탐색
            } else {
                left = mid + 1; // 더 큰 값으로 이동
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch);
    }
}
```

이분탐색을 이용하면 최적화 문제를 결정 문제로 변환할 수 있다.

이 방법은 주로 어떤 값을 최대화하거나 최소화하는 최적화 문제를 해결할 때 사용된다. 여기서 최적화 문제와 결정 문제는 다음과 같은 차이가 있다.

- 최적화 문제: 어떤 값의 최댓값이나 최소값을 찾는 문제이다. 예를 들어 가장 적은 질투심을 갖게 사탕을 나누는 방법을 찾아라는 최적화 문제이다.
- 결정 문제: 어떤 조건을 만족하는지 여부를 확인하는 문제이다. 예를 들어, 주어진 질투심 값으로 사탕을 나눌 수 있는가라는 것은 결정 문제이다.

이 문제에서 최적화문제인 질투심을 최소화하려면 가장 많은 친구의 사탕 개수를 얼마나 줄일 수 있을까를 결정 문제인 주어진 질투심으로 모든 아이에게 사탕을 나눌 수 있는가로 변환해서 이분탐색으로 문제를 해결하는 것이다.

## 최대증가부분수열

최대증가부분수열(LIS)은 주어진 배열에서 순서를 유지하면서 순서가 오름차순으로 증가하는 가장 긴 부분 수열을 찾는 문제이다. 예를 들어, 배열 `[10, 20, 10, 30, 20, 50]`에서 가장 긴 증가하는 부분 수열은 `[10, 20, 30, 50]`이고, 그 길이는 4이다.

### LIS 예제 1

```java
public class LIS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] cnt = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            int maxValue = 0;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && maxValue < cnt[j]) maxValue = cnt[j];
            }

            cnt[i] = maxValue + 1;

            result = Math.max(result, cnt[i]);
        }

        System.out.println(result);
    }
}
```

cnt 배열은 각 위치에서 끝나는 가장 긴 증가하는 부분 수열의 길이를 저장한느 배열이다. i번째 숫자까지 포함하는 증가하는 부분 수열의 최대 길이를 저장한다.

이중 for 문을 사용해 각 원소마다 가장 긴 증가하는 부분 수열을 찾는다.

- `arr[j] < arr[i]`: 현재 수열에서 `a[j]`가 `a[i]`보다 작은 경우에만 증가하는 부분 수열이 될 수 있으므로 이 조건을 검사하낟.
- `maxValue < cnt[j]`: 현재까지 확인된 가장 긴 부분 수열의 길이를 갱신한다.
- `cnt[i] = maxValue + 1`: `a[i]`를 포함하는 가장 긴 부분 수열의 길이를 `cnt[i]`에 저장한다.
- `result = Math.max(result, cnt[i])`: 현재까지 찾은 가장 긴 부분 수열의 길이 `result`를 갱신한다.

이 코드의 시간 복잡도는 $O(N^{2})$이다.

### LIS 예제 2

만약 길이뿐만 아니라 수열을 출력하기 위해서는 어떻게 해야할까?

```java
public class LIS {

    private static void trace(int[] arr, int[] prev, int idx, List<Integer> path) {
        if (idx == -1) return;
        trace(arr, prev, prev[i], path);
        path.add(arr[idx]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] cnt = new int[n];
        int[] prev = new int[n];

        StringTokenizer st = new StringToeknizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(cnt, 1);
        Arrays.fill(prev, -1);

        int maxLen = 1;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && cnt[i] < cnt[j] + 1) {
                    cnt[i] = cnt[j] + 1;
                    prev[i] = j;
                }
            }

            if (cnt[i] > maxLen) {
                maxLen = cnt[i];
                maxIdx = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        trace(arr, prev, maxIdx, list);

        for (int num : list) {
            System.out.print(num + " ");
        }
    }
}
```

### LIS 예제 3

그렇다면 시간복잡도를 줄이기 위해서는 어떻게 해야할까?

```java
public class LIS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            // Collections.binarySearch는 삽입 위치를 음수로 반환한다.
            int pos = Collections.binarySearch(list, num);

            if (pos < 0) pos = -pos - 1; // Lower Bound 위치 계산

            if (pos == list.size()) {
                list.add(num); // 새로운 수 추가
            } else {
                list.set(pos, num); // 기존 위치 덮어쓰기
            }

            for (int val : list) {
                System.out.print(val + " ");
            }

            System.out.println();
        }

        System.out.println(list.size());
    }
}
```

위처럼 이분 탐색을 사용한다면 시간복잡도를 $O(N \log{N})$으로 해결할 수 있다.
