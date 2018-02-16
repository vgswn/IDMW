#include <bits/stdc++.h>
#define pii pair<int,int>
using namespace std;
struct node
{
  bitset <1005> b;
};
struct node arr[1005];
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
int no_occur(vector <int> v,int noftr)
{
  bitset<1005> ans;
  ans.set();
  int i;
  for(i=0;i<v.size();i++)
  {
    ans=(ans&arr[v[i]].b);
  }
  return ans.count();
}
int noc(vector <int> a,vector <int> b,int k)
{
  int i;
  for(i=0;i<a.size()&&i<b.size()&&i<k;i++)
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
  bitset<1005> ans;
  ans.set();
  for(i=0;i<a.size();i++)
  {
    ans=(ans&arr[a[i]].b);
  }
  den=ans.count();
  for(i=0;i<b.size();i++)
  {
    ans=(ans&arr[b[i]].b);
  }
  num=ans.count();
  return (num*1.0)/den;
}
void generate_associations(vector < vector<int> >c3,int noftr,double mc)
{
  map <string,double> fs;
  int i,k,j,c=0;
  for(i=0;i<c3.size();i++)
  {
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
      //  cout<<vt[tmp]<<" ";
      }
      //cout<<endl;
      fans.push_back(vt);
    }


    for(j=0;j<fans.size();j++)
    {
      for(k=0;k<fans.size();k++)
      {
        double pd=conf(fans[j],fans[k],noftr);
        if(noc(fans[j],fans[k])&&pd>=mc)
        {
          int ti;
          string ts="";
          for(ti=0;ti<fans[j].size();ti++)
          {
          //  cout<<fans[j][ti]<<" ";
            int r=fans[j][ti],nr=0;
            while(r>0)
            {
              nr*=10;
              nr+=r%10;
              r/=10;
            }
            while(nr>0)
            {
              char ch=(nr%10)+48;
              ts=ts+ch;
              nr/=10;
            }
            if(fans[j].size()-1!=ti)
            {
                ts=ts+" ";
            }
          }
          int f1=0,f2=0;
          string trs="";
          //cout<<" -> ";
          for(ti=0;ti<fans[k].size();ti++)
          {
            //cout<<fans[k][ti]<<" ";
            int r=fans[k][ti],nr=0;
            while(r>0)
            {
              nr*=10;
              nr+=r%10;
              r/=10;
            }
            while(nr>0)
            {
              char ch=(nr%10)+48;
              trs=trs+ch;
              nr/=10;
            }
            if(fans[k].size()-1!=ti)
            {
                trs=trs+" ";
            }
          }
          fs[ts+" -> "+trs]=pd;
        }
      }
    }
  }
  cout<<"Association  and "<<"Confidence :- \n";
  for(map<string,double> :: iterator it=fs.begin();it!=fs.end();it++)
  {
    cout<<"[ "<<it->first<<" ] "<<it->second<<endl;
  }
}
int main()
{
  clock_t start;
  double duration;
  start = std::clock();
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
            arr[j].b.set(noftr);
          }
          k+=2;
        }
      }
    }
    i++;
  }
  double ms=0.1,mc=0.1;
  int msi,msc=0;
  for(msi=1;msi<=8;msi++,ms+=0.1)
  {
    mc=0.1;
    for(msc=1;msc<=6;msc++,mc+=0.1)
    {
      cout<<"\n\nSupport = "<<ms<<" Confidence = "<<mc<<endl;
      vector <int> l1;
      cout<<n<<" "<<noftr<<endl;
      for(i=1;i<=n;i++)
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
        vector < vector<int> > c2;
        for(i=0;i<l1.size();i++)
        {
          for(j=i+1;j<l1.size();j++)
          {
            vector <int> tp;
            tp.push_back(l1[i]);
            tp.push_back(l1[j]);
            c2.push_back(tp);
          }
        }
        vector < vector <int> > l2;
        for(i=0;i<c2.size();i++)
        {
          k=no_occur(c2[i],noftr);
          double s=(k*1.0)/noftr;
          //cout<<k<<" "<<s<<endl;
          if(s>=ms)
          {
            cout<<"[ ";
            for(j=0;j<c2[i].size();j++)
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
          for(i=0;i<l1.size();i++)
          {
            vector <int> tmp;
            tmp.push_back(l1[i]);
            c3.push_back(tmp);
          }
          generate_associations(c3,noftr,mc);
        }
        else
        {
          int tol,nk=3;
          for(tol=1;tol<=n;tol++,nk++)
          {
            vector < vector<int> > c3;
            for(i=0;i<l2.size();i++)
            {
              for(j=i+1;j<l2.size();j++)
              {
                if(noc(l2[i],l2[j],nk-2))
                {
                  vector <int> tmp;
                  for(k=0;k<l2[i].size();k++)
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
                    for(int idj=0;idj<tmp.size();idj++)
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
              generate_associations(l2,noftr,mc);
              break;
            }
            else
            {
              for(i=0;i<l2.size();i++)
              {
                l2[i].clear();
              }
              l2.clear();
              for(i=0;i<c3.size();i++)
              {
                l2.push_back(c3[i]);
              }
            }
          }
        }
      }
      cout<<"\n\n";
    }
  }
  duration = ( std::clock() - start ) / (double) CLOCKS_PER_SEC;
  cout<<"Total time : "<< duration <<'\n';
  return 0;
}
