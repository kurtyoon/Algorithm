#include <bits/stdc++.h>
using namespace std;

int a[3] = {1, 2, 3};
int a2[] = {1, 2, 3, 4};
int a3[10];
array<int, 10> a4; // a3은 c스타일, a4는 std스타일

int main(void) {
    for (int i = 0; i < 3; i++) cout << a[i] << ' ';
    cout << '\n';

    for (auto i : a) cout << i << ' ';
    cout << '\n';

    for (int i = 0; i < 4; i++) cout << a2[i] << ' ';
    cout << '\n';

    for (auto i : a2) cout << i << ' ';
    cout << '\n';

    for (int i = 0; i < 10; i++) a3[i] = i;
    for (auto i : a3) cout << i << ' ';
    cout << '\n';

    return 0;
}