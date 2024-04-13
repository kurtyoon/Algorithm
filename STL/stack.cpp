#include <bits/stdc++.h>
using namespace std;

// LIFO, 삽입 & 삭제에 O(1), 탐색에 O(n) 
stack<string> stk;

int main(void) {
    // stack.push(value): value를 stack에 추가
    stk.push("1");
    stk.push("2");
    stk.push("3");
    stk.push("4");
    stk.push("5");
    stk.push("6");

    // stack.size(): stack의 크기
    while (stk.size()) {
        // stack.top(): 가장 마지막(상위)에 있는 요소
        cout << stk.top() << '\n';
        // stack.pop(): 가장 마지막(상위)에 있는 요소를 삭제
        stk.pop();
    }

    return 0;
}