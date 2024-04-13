#include <bits/stdc++.h>
using namespace std;

int a[3] = {1, 2, 3};
int n = 3, r = 3;

void PrintVector(vector<int> &v) {
    for (int i = 0; i < v.size(); i++) {
        cout << v[i] << ' ';
    }
    cout << '\n';
}

void Print() {
    for (int i = 0; i < r; i++) {
        cout << a[i] << ' ';
    }
    cout << '\n';
}

void MakePermutation(int n, int r, int depth) {
    if (r == depth) {
        Print();
        return;
    }

    for (int i = depth; i < n; i++) {
        swap(a[i], a[depth]);
        MakePermutation(n, r, depth + 1);
        swap(a[i], a[depth]);
    }

    return;
}

int main(void) {
    int a[3] = {1, 2, 3};
    vector<int> v;

    cout << "next permutation" << '\n'; // 오름차순 배열을 기반으로 순열 생성
    for (int i = 0; i < 3; i++) v.push_back(a[i]);

    do {
        PrintVector(v);
    } while (next_permutation(v.begin(), v.end()));

    cout << "\nprev permutation" << '\n'; // 내림차순 배열을 기반으로 순열 생성
    v.clear();

    for (int i = 2; i >= 0; i--) v.push_back(a[i]);

    do {
        PrintVector(v);
    } while (prev_permutation(v.begin(), v.end()));

    return 0;
}

// next_permutation()은 새로운 순열이 이전 순열보다 사전순으로 큰 경우 true를 반환