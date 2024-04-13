#include <bits/stdc++.h>
using namespace std;

struct Point {
    int y, x;
    Point(int y, int x) : y(x), x(x) {}
    Point() { y = -1; x = -1; }
    bool operator < (const Point & a) const {
        return x < a.x;  // 우선순위큐에 커스텀 정렬을 넣는 경우 반대로 넣어야 한다.
    }
};

priority_queue<int, vector<int>, greater<int> > pq; // 오름차순
priority_queue<int> pq2; // 내림차순
priority_queue<int, vector<int>, less<int> > pq3; // 내림차순
priority_queue<Point> pq4; // 구조체를 사용하는 우선순위 큐

int main(void) {
    for (int i = 5; i >= 1; i--) {
        pq.push(i);
        pq2.push(i);
        pq3.push(i);
    }

    while (pq.size()) {
        cout << pq.top() << " : " << pq2.top() << " : " << pq3.top() << '\n';
        pq.pop(); pq2.pop(); pq3.pop();
    }

    pq4.push({1, 1});
    pq4.push({2, 2});
    pq4.push({3, 3});
    pq4.push({4, 4});
    pq4.push({5, 5});
    pq4.push({6, 6});
    cout << pq4.top().x << '\n';

    return 0;
}
