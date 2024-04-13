#include <bits/stdc++.h>
using namespace std;

int n = 5, k = 3, a[5] = {1, 2, 3, 4, 5};

void Print(vector<int> b) {
    for (int i : b) cout << i << ' ';
    cout << '\n';
}

void Combination(int start, vector<int> b) {
    if (b.size() == k) {
        Print(b);
        return ;
    }

    for (int i = start + 1; i < n; i++) {
        b.push_back(i);
        Combination(i, b);
        b.pop_back();
    }

    return ;
}

int main() {
    vector<int> b;

    Combination(-1, b);
    return 0;
}