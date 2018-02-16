#include <bits/stdc++.h>
#define pii pair<int,int>
using namespace std;
int noi[1005];
int item_count[1005];
int tran[1005][1005];
int noc(vector <int> a,vector <int> b)
{
  int i,j;
  for(i=0;i<a.size();i++)
  {
    for(j=0;j<b.size();j++)
    {
      if(a[i]==b[j])
      {
        return 0;
      }
    }
  }
  return 1;
}
double conf(vector <int> a,vector <int> b,int noftr)
{
  int i,j,flag=0,num=0,den=0;
  for(i=1;i<=noftr;i++)
  {
    flag=1;
    for(j=0;j<a.size();j++)
    {
      if(!tran[i][a[j]])
      {
        flag=0;
        break;
      }
    }
    den+=flag;
  }
  for(i=1;i<=noftr;i++)
  {
    flag=1;
    for(j=0;j<a.size();j++)
    {
      if(!tran[i][a[j]])
      {
        flag=0;
        break;
      }
    }
    for(j=0;j<b.size();j++)
    {
      if(!tran[i][b[j]])
      {
        flag=0;
        break;
      }
    }
    num+=flag;
  }
  for(i=0;i<a.size();i++)
  {
  //  cout<<a[i]<<" ";
  }
//  cout<<den<<" ****** \n";
  return (num*1.0)/den;
}
int main()
{
  map <string,int> mp;
  ifstream fp("FILE1.txt");
  int n,i=1,j,cnt=0,k,noftr=0;
  while(!fp.eof())
  {
    string s;
    getline(fp,s);
    if(i==1)
    {
      n=0;
      for(j=0;j<s.size();j++)
      {
        n*=10;
        n+=s[j]-'0';
      }
    }
    else if(i==2)
    {
      string ts="";
      for(j=0;j<s.size();j++)
      {
        if(s[j]==' ')
        {
          continue;
        }
        if(s[j]==',')
        {
          mp[ts]=++cnt;
          ts="";
        }
        else
        {
          ts=ts+s[j];
        }
      }
    }
    else
    {
      k=0;
      if(s.size()>0)
      {
        noftr++;
        for(j=1;j<=n;j++)
        {
          if(s[k]=='1')
          {
            item_count[j]++;
            tran[noftr][j]++;
          }
          k+=2;
        }
      }
    }
    i++;
  }
  double ms,mc;
  cin>>ms>>mc;
  vector <int> l1;
  cout<<n<<" "<<noftr<<endl;
  for(i=1;i<=n;i++)
  {
    double s=(item_count[i]*1.0)/noftr;
    if(s>=ms)
    {
      cout<<i<<" "<<item_count[i]<<endl;
      l1.push_back(i);
    }
  }
  vector <pii> c2;
  for(i=0;i<l1.size();i++)
  {
    for(j=i+1;j<l1.size();j++)
    {
      c2.push_back(make_pair(l1[i],l1[j]));
    }
  }
  vector <pii> l2;
  for(i=0;i<c2.size();i++)
  {
    k=0;
    for(j=1;j<=noftr;j++)
    {
      if(tran[j][c2[i].first]&&tran[j][c2[i].second])
      {
        k++;
      }
    }
    double s=(k*1.0)/noftr;
    if(s>=ms)
    {
      cout<<c2[i].first<<" "<<c2[i].second<<" "<<k<<" *** "<<endl;
      l2.push_back(c2[i]);
    }
  }
  vector < vector<int> > c3;
  for(i=0;i<l2.size();i++)
  {
    for(j=i+1;j<l2.size();j++)
    {
      if(l2[i].first==l2[j].first)
      {
        vector <int> tmp;
        tmp.push_back(l2[i].first);
        tmp.push_back(l2[i].second);
        tmp.push_back(l2[j].second);
        sort(tmp.begin(),tmp.end());
        c3.push_back(tmp);
      }
    }
  }
  for(i=0;i<c3.size();i++)
  {
    for(j=0;j<c3[i].size();j++)
    {
      cout<<c3[i][j]<<" ";
    }
    cout<<endl;
    vector < vector<int> > fans;
    int tmp;
    for(k=1;k<(1<<c3[i].size())-1;k++)
    {
      vector <int> vt;
      for(tmp=0;tmp<c3[i].size();tmp++)
      {
          if(k&(1<<tmp))
          {
            vt.push_back(c3[i][tmp]);
          }
      }
      for(tmp=0;tmp<vt.size();tmp++)
      {
        cout<<vt[tmp]<<" ";
      }
      cout<<endl;
      fans.push_back(vt);
    }
    for(j=0;j<fans.size();j++)
    {
      for(k=0;k<fans.size();k++)
      {
        if(noc(fans[j],fans[k])&&conf(fans[j],fans[k],noftr)>=mc)
        {
          int ti;
          for(ti=0;ti<fans[j].size();ti++)
          {
            cout<<fans[j][ti]<<" ";
          }
          cout<<" -> ";
          for(ti=0;ti<fans[k].size();ti++)
          {
            cout<<fans[k][ti]<<" ";
          }
          cout<<endl;
        }
      }
    }
  }
  return 0;
}
