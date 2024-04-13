#include <bits/stdc++.h>
using namespace std;

/**
 * split string function
*/
vector<string> split(string input, string delimiter) {
    vector<string> result;
    long long pos = 0;
    string token = "";

    // input에서 delimiter를 찾을 때 까지
    while ((pos = input.find(delimiter)) != string::npos) {
        token = input.substr(0, pos); // 해당 pos까지 부분 문자열 추출
        result.push_back(token); // 추출한 문자열 삽입
        input.erase(0, pos + delimiter.length()); // 삭제
    }
    result.push_back(input);
    return result;
}

/**
 * faster split string function
*/
vector<string> split(const string& input, string delimiter) {
    vector<string> result;
    auto start = 0;
    auto end = input.find(delimiter);

    while (end != string::npos) {
        result.push_back(input.substr(start, end - start));
        start = end + delimiter.size();
        end = input.find(delimiter, start);
    }

    result.push_back(input.substr(start));
    return result;
}