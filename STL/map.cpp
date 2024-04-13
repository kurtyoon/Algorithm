#include <bits/stdc++.h>
using namespace std;

// map은 정렬이 되며, 레드블랙트리를 기반으로 탐색, 삽입, 삭제에 O(logN)이 걸린다.
// unordered_map은 정렬이 안되며, 해시 테이블 기반으로 탐색, 삽입, 삭제에 평균적으로 O(1), 최악의 경우 O(N)이 걸린다.
map<string, int> m;
string a[] = {"first", "second", "third"};

int main(void) {
    // insert({key, value}): map에 {key, value} 삽입
    for (int i = 0; i < 3; i++) {
        m.insert({a[i], i + 1});
        m[a[i]] = i + 1;
    }

    // map에 해당 키가 존재하지 않는 경우 0으로 초기화 (int)
    // map에 해당 키가 없는지 확인하고 싶고, 초기값이 0으로 할당되지 않아야 하는 상황에는 find 사용
    cout << m["component"] << '\n';

    // map[key] = value: key에 해당하는 value 할당
    m["component"] = 4; // 대괄호로 수정 가능

    // map[key]: key를 기반으로 map의 요소 참조
    cout << m["component"] << '\n';

    // map.size(): map 요소의 개수 반환
    cout << m.size() << '\n';

    // map.erase(key): 해당 key에 해당하는 요소 삭제
    m.erase("component");

    // find(key): map에서 key를 가진 요소를 찾아 이터레이터 반환, 못찾을 경우 map의 end() 이터레이터 반환
    auto it = m.find("component");
    if (it == m.end()) {
        cout << "Not found\n";
    }

    m["component"] = 100;

    it = m.find("component");
    if (it != m.end()) {
        cout << (*it).first << " : " << (*it).second << '\n';
    }

    for (auto it : m) {
        cout << (it).first << " : " << (it).second << '\n';
    }

    for (auto it = m.begin(); it != m.end(); it++) {
        cout << (*it).first << " : " << (*it).second << '\n';
    }

    // map.clear(): map의 요소 전체 삭제
    m.clear();
    return 0;
}