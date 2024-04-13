#include <bits/stdc++.h>
using namespace std;

vector<int> v;
vector<int> v2(5, 100); // 5개의 요소를 담는 vector 선언 후 100으로 채우기
vector<int> v3{10, 20, 30, 40, 50};
vector<vector<int>> v4;
vector<vector<int>> v5(10, vector<int>(10, 0));
vector<int> v6[10];

int main(void) {
    // vector.push_back(): vector의 뒤에서부터 요소를 더함
    for (int i = 0; i <= 10; i++) v.push_back(i);
    for (auto a : v) cout << a << ' ';
    cout << '\n';

    // vector.pop_back(): vector의 맨 뒤의 요소를 지움
    v.pop_back();
    for (auto a : v) cout << a << ' ';
    cout << '\n';

    // vector.erase(): erase(position)일 경우 한 요소, erase(from, to)의 경우 [from, to)의 범위 삭제
    v.erase(v.begin(), v.begin() + 3);
    for (auto a : v) cout << a << ' ';
    cout << '\n';

    // find(from, to, value): [from, to)에서 value를 찾음
    auto a = find(v.begin(), v.end(), 100);
    if (a == v.end()) cout << "not found" << '\n';

    // fill(from, to, value): vector를 value로 값 할당
    fill(v.begin(), v.end(), 10);
    for (auto a : v) cout << a << ' ';
    cout << '\n';

    // vector.clear(): vector 모든 요소 삭제
    v.clear();
    for (auto a : v) cout << a << ' ';
    cout << '\n';

    return 0; 
}
