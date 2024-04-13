#include <bits/stdc++.h>
using namespace std;

list<int> a;

void print(list <int> a) {
    for (auto it : a) cout << it << ' ';
    cout << '\n';
}

int main(void) {
    // list.push_back(value); list의 뒤에서부터 value 삽입
    for (int i = 1; i <= 3; i++) a.push_back(i);
    print(a);

    // list.push_front(value): list의 앞에서부터 value 삽입
    for (int i = 1; i <= 3; i++) a.push_front(i);
    print(a);

    // list.insert(idx, value): idx 번째에 value 삽입
    auto it = a.begin(); it++;
    a.insert(it, 1000);
    print(a);

    // list.erase(idx): list의 idx번째 요소 삭제
    it = a.begin(); it++;
    a.erase(it);
    print(a);

    // list.pop_front(): list의 첫번째요소 삭제
    a.pop_front();
    print(a);

    // list.pop_back(): list의 마지막요소 삭제
    a.pop_back();
    print(a);

    // list.front(): list의 맨 앞 요소 참조
    // list.back(): list의 마지목 요소 참조
    cout << a.front() << " : " << a.back() << '\n';

    // list.clear(): 모든 요소 삭제
    a.clear();
    print(a);

    return 0; 
}