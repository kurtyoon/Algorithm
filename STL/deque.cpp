#include <bits/stdc++.h>
using namespace std;

int main(void) {
    // 앞뒤로 삽입, 삭제, 참조가 가능한 queue
    deque<int> dq; 

    // deque.push_front(value): 앞에 value 삽입
    dq.push_front(1); 

    // deque.push_back(value): 뒤에 value 삽입
    dq.push_back(2); 
    dq.push_back(3);

    // deque.front(): 맨 앞 요소 참조
    cout << dq.front() << "\n"; 

    // deque.back(): 맨 뒤 요소 참조
    cout << dq.back() << "\n"; 

    // deque.size(): deque의 크기 반환
    cout << dq.size() << "\n"; 

    // deque.pop_back(): 마지막 요소 삭제
    dq.pop_back(); 

    // deque.pop_front(): 첫 요소 삭제
    dq.pop_front();

    cout << dq.size() << "\n";
    return 0;
}