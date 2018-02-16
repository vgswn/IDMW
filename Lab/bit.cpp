#include <bits/stdc++.h>
using namespace std;
int main()
{
    bitset <10> b1(0),b2;
    b1.set(1);
    cout<<b2<<" "<<b2.count()<<endl;
    int t=102;
    stringstream ss;
    ss << t;
    string s=ss.str();
    vector <int> v;
    cout<<s<<endl;
    ss.clear();
    int t1=211;
    ss<<t1;
    s=ss.str();
    cout<<s<<endl;
    return 0;
}
