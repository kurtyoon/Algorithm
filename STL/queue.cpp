#include <bits/stdc++.h>
using namespace std;

// FIFO
queue<int> q;

int main(void) {
    // queue.push(value): value를 queue에 추가
    for (int i = 1; i <= 10; i++) q.push(i);

    // queue.size(): queue의 크기
    while (q.size()) {
        // queue.front(): 가장 앞에 있는 요소
        cout << q.front() << ' ';
        // queue.pop(): 가장 앞에 있는 요소 삭제
        q.pop();
    }

    return 0;
}