#include <bits/stdc++.h>
using namespace std;

// multiset은 중복되는 요소를 삽입할 수 있는 set이다. 마찬가지로 자동 정렬된다.
multiset<int> s;

int main(void) {
    // set은 고유한 요소만 저장한다. 중복된 값은 제거되며, 자동 정렬된다.
    set<pair<string, int>> st;

    st.insert({"test", 1});
    st.insert({"test", 1});
    st.insert({"test", 1});
    st.insert({"test", 1});
    cout << st.size() << '\n';

    set<int> st2;
    st2.insert(2);
    st2.insert(1);
    st2.insert(2);

    for (auto it : st2) {
        cout << it << '\n';
    }

    for (int i = 5; i >= 1; i--) {
        s.insert(i);
        s.insert(i);
    }
    for (int it : s) cout << it << ' ';
    cout << '\n';
    return 0;
}