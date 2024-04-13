#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 40;

bool che[MAX_N + 1];

// 해당 함수는 1000만 이상이면 사용하기 힘듦 -> 배열의 크기가 필요
vector<int> Eratosthenes(int max_n) {
    vector<int> v;

    for (int i = 2; i <= max_n; i++) {
        if (che[i]) continue;
        for (int j = 2 * i; j <= max_n; j += i) {
            che[j] = 1;
        }
    }

    for (int i = 2; i <= max_n; i++) if (che[i] == 0) v.push_back(i);
    return v;
}

bool Check(int n) {
    if (n <= 1) return 0;
    if (n == 2) return 1;
    if (n % 2 == 0) return 0;
    for (int i = 3; i * i <= n; i++) {
        if (n % i == 0) return 0;
    }
    return 1;
}

int main() {
    for (int i = 1; i <= 20; i++) {
        if (Check(i)) {
            cout << i << " is prime\n";
        }
    }
    return 0;
}