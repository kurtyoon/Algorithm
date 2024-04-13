#include <bits/stdc++.h>
using namespace std;

/**
 * 최대 공약수
*/
int Gcd(int a, int b) {
    if (a == 0) return b;
    return Gcd(b % a, a);
}

/**
 * 최소 공배수
*/
int Lcm(int a, int b) {
    return (a * b) / Gcd(a, b);
}

int main(void) {
    int a = 10, b = 12;

    cout << Gcd(a, b) << '\n';
    cout << Lcm(a, b) << '\n';
    return 0;
}