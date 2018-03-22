#include <bits/stdc++.h>
#define pii pair<int,int>
using namespace std;
struct node
{
    bitset <100005> b;
};
struct node arr[100005];
map <string,double> fs;
int noc(vector <int> a,vector <int> b)
{
    int i,j;
    for(i=0; i<a.size(); i++)
    {
        for(j=0; j<b.size(); j++)
        {
            if(a[i]==b[j])
            {
                return 0;
            }
        }
    }
    return 1;
}
int no_occur(vector <int> v,int noftr)
{
    bitset<100005> ans;
    ans.set();
    int i;
    for(i=0; i<v.size(); i++)
    {
        ans=(ans&arr[v[i]].b);
    }
    return ans.count();
}
int noc(vector <int> a,vector <int> b,int k)
{
    int i;
    for(i=0; i<a.size()&&i<b.size()&&i<k; i++)
    {
        if(a[i]!=b[i])
        {
            return 0;
        }
    }
    return 1;
}
double conf(vector <int> a,vector <int> b,int noftr)
{
    int i,num=0,den=0;
    bitset<100005> ans;
    ans.set();
    for(i=0; i<a.size(); i++)
    {
        ans=(ans&arr[a[i]].b);
    }
    den=ans.count();
    for(i=0; i<b.size(); i++)
    {
        ans=(ans&arr[b[i]].b);
    }
    num=ans.count();
    return (num*1.0)/den;
}
int super_set(vector <int> a,vector <int> b)
{
    int i,ans=0,j;
    for(i=0; i<b.size(); i++)
    {
        int flag=0;
        for(j=0; j<a.size(); j++)
        {
            if(a[j]==b[i])
            {
                flag=1;
                break;
            }
        }
        if(!flag)
        {
            return 0;
        }
    }
    if(a.size()>b.size())
    {
        return 1;
    }
    return 0;
}
void generate_associations(vector < vector<int> >c3,int noftr,double mc,int lev)
{
    int i,k,j,c=0;
    for(i=0; i<c3.size(); i++)
    {
        int tmp;
        for(k=1; k<(1<<c3[i].size())-1; k++)
        {
            vector <int> vt,av;
            for(tmp=0; tmp<c3[i].size(); tmp++)
            {
                if(k&(1<<tmp))
                {
                    vt.push_back(c3[i][tmp]);
                }
                else
                {
                    av.push_back(c3[i][tmp]);
                }
            }
            double pd=conf(vt,av,noftr);
            if(noc(av,vt)&&pd>=mc&&av.size()>0&&vt.size()>0)
            {
                string ts="";
                for(j=0; j<vt.size(); j++)
                {
                    stringstream ss;
                    ss<<vt[j];
                    ts=ts+ss.str()+" ";
                }
                string trs="";
                for(j=0; j<av.size(); j++)
                {
                    stringstream ss;
                    ss<<av[j];
                    trs=trs+ss.str()+" ";
                }
                fs[ts+" -> "+trs]=pd;
            }
        }
    }
}
int main()
{
    clock_t start;
    double duration;
    start = std::clock();
    map <string,int> mp;
    string filename;
    cin>>filename;
    ifstream fp(filename.c_str());
    int n=0,i=1,j,cnt=0,k,noftr=0;
    while(!fp.eof())
    {
        string s;
        getline(fp,s);
        noftr++;
        j=0;
        for(i=0; i<s.size(); i++)
        {
            if(s[i]==' ')
            {
                n=max(n,j);
                arr[j].b.set(noftr);
                j=0;
            }
            else
            {
                j*=10;
                j+=s[i]-'0';
            }
        }
        if(j>0)
        {
            arr[j].b.set(noftr);
            n=max(n,j);
        }
    }
    vector < vector < vector<int> > > freq_item;
    double ms=0.1,mc=0.1;
    cin>>ms>>mc;
    {
        cout<<"\n\nSupport = "<<ms<<" Confidence = "<<mc<<endl;
        vector <int> l1;
        cout<<n<<" "<<noftr<<endl;
        for(i=1; i<=n; i++)
        {
            double s=(arr[i].b.count()*1.0)/noftr;
            if(s>=ms)
            {
                cout<<"[ "<<i<<" ] "<<arr[i].b.count()<<endl;
                l1.push_back(i);
            }
        }
        if(l1.size()==0)
        {
            cout<<"No element has support greater than "<<ms<<endl;
        }
        else
        {
            for(i=0; i<l1.size(); i++)
            {
                vector < vector <int> > ivv;
                vector <int> iv11;
                iv11.push_back(l1[i]);
                ivv.push_back(iv11);
                freq_item.push_back(ivv);
            }
            vector < vector<int> > c2;
            for(i=0; i<l1.size(); i++)
            {
                for(j=i+1; j<l1.size(); j++)
                {
                    vector <int> tp;
                    tp.push_back(l1[i]);
                    tp.push_back(l1[j]);
                    c2.push_back(tp);
                }
            }
            vector < vector <int> > l2;
            for(i=0; i<c2.size(); i++)
            {
                k=no_occur(c2[i],noftr);
                double s=(k*1.0)/noftr;
                //cout<<k<<" "<<s<<endl;
                if(s>=ms)
                {
                    cout<<"[ ";
                    for(j=0; j<c2[i].size(); j++)
                    {
                        cout<<c2[i][j]<<" ";
                    }
                    cout<<" ] ";
                    cout<<k<<endl;
                    l2.push_back(c2[i]);
                }
            }
            if(l2.size()==0)
            {
                vector < vector<int> > c3;
                for(i=0; i<l1.size(); i++)
                {
                    vector <int> tmp;
                    tmp.push_back(l1[i]);
                    c3.push_back(tmp);
                }
                generate_associations(c3,noftr,mc,1);
            }
            else
            {
                int tol,nk=3;
                for(tol=1; tol<=n; tol++,nk++)
                {
                    generate_associations(l2,noftr,mc,nk-1);
                    freq_item.push_back(l2);
                    vector < vector<int> > c3;
                    for(i=0; i<l2.size(); i++)
                    {
                        for(j=i+1; j<l2.size(); j++)
                        {
                            if(noc(l2[i],l2[j],nk-2))
                            {
                                vector <int> tmp;
                                for(k=0; k<l2[i].size(); k++)
                                {
                                    tmp.push_back(l2[i][k]);
                                }
                                tmp.push_back(l2[j][l2[j].size()-1]);
                                sort(tmp.begin(),tmp.end());
                                int ck=no_occur(tmp,noftr);
                                double s=(ck*1.0)/noftr;
                                if(s>=ms)
                                {
                                    cout<<"[ ";
                                    for(int idj=0; idj<tmp.size(); idj++)
                                    {
                                        cout<<tmp[idj]<<" ";
                                    }
                                    cout<<" ] ";
                                    cout<<ck<<endl;
                                    c3.push_back(tmp);
                                }
                            }
                        }
                    }
                    if(c3.size()==0)
                    {
                        break;
                    }
                    else
                    {
                        for(i=0; i<l2.size(); i++)
                        {
                            l2[i].clear();
                        }
                        l2.clear();
                        for(i=0; i<c3.size(); i++)
                        {
                            l2.push_back(c3[i]);
                        }
                    }
                }
            }
        }
        cout<<"Association  and "<<"Confidence :- \n";
        for(map<string,double> :: iterator it=fs.begin(); it!=fs.end(); it++)
        {
            cout<<"[ "<<it->first<<" ] "<<it->second<<endl;
        }
        cout<<"\n\n";
    }
    cout<<"Closed Frequent itemsets are :-\n";
    for(i=0; i<freq_item.size(); i++)
    {
        for(j=0; j<freq_item[i].size(); j++)
        {
            int flag=1;
            for(k=i+1; k<freq_item.size(); k++)
            {
                int j1;
                for(j1=0; j1<freq_item[k].size(); j1++)
                {
                    if(super_set(freq_item[k][j1],freq_item[i][j])&&(no_occur(freq_item[k][j1],noftr)==no_occur(freq_item[i][j],noftr)))
                    {
                        //  cout<<"yipeee\n";
                        flag=0;
                        break;
                    }
                }
                if(!flag)
                {
                    break;
                }
            }
            if(flag)
            {
                cout<<"[ ";
                for(k=0; k<freq_item[i][j].size(); k++)
                {
                    cout<<freq_item[i][j][k]<<" ";
                }
                cout<<" ]\n";
            }
        }
    }
    duration = ( std::clock() - start ) / (double) CLOCKS_PER_SEC;
    cout<<"Total time : "<< duration <<'\n';
    return 0;
}
