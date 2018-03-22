#include <bits/stdc++.h>
using namespace std;
#define pii pair <int ,int > 
struct trie
{
    int val;
    int cnt;
    struct trie *c[105];
    struct trie *par;
};
struct node
{
  bitset <1005> b;
};
struct node arr[1005];
vector <struct trie *> adj[105];
vector < vector <pii> > trans;
set < pair < vector <int> ,int > > freq_item;
int count_item[105];
int count_of_itemset(vector <int> v)
{
    bitset <1005> ans;
    ans.set();
    int i;
    for(i=0;i<v.size();i++)
    {
        ans=ans&arr[v[i]].b;
    }
    return ans.count();
}
int cmp(pii a,pii b)
{
    if(a.second!=b.second)
    {
        return a.second > b.second;
    }
    else
    {
        return a.first < b.first;
    }
}
struct trie * create(int x,struct trie *par)
{
    struct trie *tmp=(struct trie *)malloc(sizeof(struct trie));
    tmp->val=x;
    int i;
    for(i=1;i<=100;i++)
    {
        tmp->c[i]=NULL;
    }
    tmp->cnt=0;
    tmp->par=par;
    return tmp;
}
struct trie * insert(vector <int> d,struct trie *root)
{
    struct trie *tmp=root;
    int i;
    for(i=0;i<d.size();i++)
    {
        if(tmp->c[d[i]]==NULL)
        {
            tmp->c[d[i]]=create(d[i],tmp);
            adj[d[i]].push_back(tmp->c[d[i]]);
        }
        tmp=tmp->c[d[i]];
        (tmp->cnt)++;
    }
    return root;
}
int main()
{
    ifstream fp;
    fp.open("fpinp.txt",ios::in);
    string s;
    vector < vector <int> > tmpv;
    int tmp_count[105];
    vector <pii> items;
    int i,j,k,l,x,no_of_trans=0,no_of_items=0;
    while(!fp.eof())
    {
        getline(fp,s);
        //cout<<s<<endl;
        vector <int> v;
        no_of_trans++;
        l=s.size();
        x=0;
        for(i=0;i<l;i++)
        {
            if(s[i]==' ')
            {
                arr[x].b.set(no_of_trans);
                no_of_items=max(no_of_items,x);
                count_item[x]++;
                v.push_back(x);
                x=0;
            }
            else
            {
                x*=10;
                x+=s[i]-'0';
            }
        }
        arr[x].b.set(no_of_trans);
        no_of_items=max(no_of_items,x);
        count_item[x]++;
        v.push_back(x);
        tmpv.push_back(v);
    }
    //cout<<(arr[1].b&arr[2].b).count()<<endl;
    float min_sup=.3;
    int min_sup_count=2;//ceil(.3*no_of_trans);
    for(i=0;i<tmpv.size();i++)
    {
        vector <pii> v;
        for(j=0;j<tmpv[i].size();j++)
        {
            v.push_back(make_pair(tmpv[i][j],count_item[tmpv[i][j]]));
        }
        sort(v.begin(),v.end(),cmp);
        trans.push_back(v);
    }
    cout<<"\nTranscations with items sorted acc to their counts in descending order are - \n";
    for(i=0;i<trans.size();i++)
    {
        for(j=0;j<trans[i].size();j++)
        {
            cout<<trans[i][j].first<<" "<<trans[i][j].second<<"   ";
        }
        cout<<endl;
    }
    cout<<endl;
    struct trie *root;
    root=create(-1,NULL);
    for(i=0;i<trans.size();i++)
    {
        vector <int> v;
        for(j=0;j<trans[i].size();j++)
        {
            if(trans[i][j].second>=min_sup_count)
            {
                v.push_back(trans[i][j].first);
            }
        }
        root=insert(v,root);
    }
    for(i=1;i<=no_of_items;i++)
    {
        items.push_back(make_pair(i,count_item[i]));
    }
    sort(items.begin(),items.end(),cmp);
    cout<<"Conditional Pattern Base - \n";
    for(i=items.size()-1;i>=0;i--)
    {
        for(j=1;j<=no_of_items;j++)
        {
            tmp_count[j]=0;
        }
        vector < vector <pii> > paths;
        //cout<<items[i].first<<"   "<<adj[items[i].first].size()<<endl;
        int cnt_last=0;
        for(j=0;j<adj[items[i].first].size();j++)
        {
            vector <pii> v;
            struct trie *tmp=adj[items[i].first][j];
            while(tmp->val!=-1)
            {
                if(cnt_last==0)
                {
                    cnt_last=tmp->cnt;
                }
                v.push_back(make_pair(tmp->val,tmp->cnt));
                tmp_count[tmp->val]+=cnt_last;
                tmp=tmp->par;
            }
            reverse(v.begin(),v.end());
            paths.push_back(v);
        }
        cout<<items[i].first<<"   ";
        set <int> st;
        for(j=0;j<paths.size();j++)
        {
            if(paths[j].size()>1)
            {
                cout<<" { ";
                for(k=0;k<paths[j].size()-1;k++)
                {
                    if(tmp_count[paths[j][k].first]>=min_sup_count)
                    {
                        st.insert(paths[j][k].first);
                    }
                    cout<<paths[j][k].first<<" ";
                }
                cout<<" : "<<paths[j][k].second<<" } ";
            }
        }
        cout<<endl;
        set <int> :: iterator it1;
        vector <int> v;
        for(it1=st.begin();it1!=st.end();it1++)
        {
            v.push_back(*it1);
            //cout<<*it<<" ";
        }
        for(j=1;j<=(1<<v.size());j++)
        {
            vector <int> tmp_v1;
            for(k=0;k<v.size();k++)
            {
                if(j&(1<<k))
                {
                    tmp_v1.push_back(v[k]);
                }
            }
            tmp_v1.push_back(items[i].first);
            if(count_of_itemset(tmp_v1)>=min_sup_count)
            {
                sort(tmp_v1.begin(),tmp_v1.end());
                freq_item.insert(make_pair(tmp_v1,count_of_itemset(tmp_v1)));
            }
        }
    }
    cout<<endl;
    cout<<"Frequent Itemsets using FP tree are - \n";
    set < pair < vector <int> ,int > > :: iterator it;
    for(it=freq_item.begin();it!=freq_item.end();it++)
    {
        cout<<"[ ";
        vector <int> v=(*it).first;
        for(i=0;i<v.size();i++)
        {
            cout<<v[i]<<" ";
        }
        cout<<" ] ";
        cout<<(*it).second<<endl;
    }
    cout<<endl;
    return 0;
}