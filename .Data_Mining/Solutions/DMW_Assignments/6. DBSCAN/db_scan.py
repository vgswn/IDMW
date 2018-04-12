import random
import math
import matplotlib.pyplot as plt
def dis(a,b):
    return math.sqrt(((a[0]-b[0])*(a[0]-b[0]))+((a[1]-b[1])*(a[1]-b[1])))
def epsi_dis_circle(data,li,epsilon):
    neigh=[]
    for i in data:
        if dis(i,li)  <= epsilon:
            neigh.append(i)
    return neigh
def find_points(list):
    s=0
    for i in list:
        s=s+len(i)
    return s
with open('DBSCAN.txt') as f:
    data=f.readlines()
data.remove(data[0])
for i in range(len(data)):
    data[i]=data[i][:-1]
    data[i]=data[i].split(' ')
    for j in range(len(data[i])):
        data[i][j]=float(data[i][j])
print(len(data),data)
epsilon=20
min_neigh=100

visited=[]
member=[]
for i in range(len(data)):
    visited.append(0)
    member.append(0)
Cluster=[]
dicc={}
ind=-1
ll=0
for plpl in range(len(data)):
    ll+=1
    #list= random.sample(data,1)
    list=data[plpl]
    if visited[data.index((list))]==1:
        continue
    #print('MMMMMMMMMMMMMMMMMMMMMMMMMMMM',member.count(1),len(Cluster),visited.count(1))

    #print(visited[data.index(list[0])])
    visited[data.index(list)]=1
    E_neigh=epsi_dis_circle(data,list,epsilon)
    if len(E_neigh) >= min_neigh:
        ind+=1
        #print('ind',ind)
        Cluster.append([])
        #print(find_points(Cluster))
        k=0
        while(True):
        #for k in range(len(E_neigh)):
            if k==len(E_neigh):
                break
            #print(k,len(E_neigh))
            if visited[data.index(E_neigh[k])] ==0:
                visited[data.index(E_neigh[k])]=1
                neig = epsi_dis_circle(data,E_neigh[k], epsilon)
                if len(neig) >= min_neigh:
                    for p in neig:
                        if p not in E_neigh:
                            if member[data.index(p)]==0:
                                E_neigh.append(p)
            if member[data.index(E_neigh[k])]==0:
                member[data.index(E_neigh[k])]=1
                Cluster[ind].append(E_neigh[k])
                dicc[tuple(E_neigh[k])]=ind
            k += 1

    if 0 in visited == False:
        print(0 in visited)
        break
print('Success: Cluster Formed :')
for i in range(len(Cluster)):
    print('Cluster Number : ',i,' Number of Points : ',len(Cluster[i]))
print('Plotting....')
color = ['b', 'g', 'r', 'c', 'm', 'y', 'k', 'w']
for i in range(len(Cluster)):
    a=[]
    b=[]
    for j in range(len(Cluster[i])):
       plt.plot(Cluster[i][j][0],Cluster[i][j][1], marker='o', color=color[i])
#plt.show()
plt.savefig('db_scan'+str(epsilon)+'_'+str(min_neigh)+'.png')
print('Success')