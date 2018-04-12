import random
import matplotlib.pyplot as plt
def distance(a,b):
    return abs(a[0]-b[0])+abs(b[1]-a[1])
def k_means(k,list):
    a=0
    part=[]
    for i in range(k):
        part.append([])
        #part[i].append(list[i])
    #print(part)
    for x in data:
        dist=[]
        for j in range(len(list)):
            dist.append((distance(x,list[j]),j))
        dist.sort()
        ind=dist[0][1]
        part[ind].append(x)
    newList=[]
    for i in range(len(part)):
        Xi=0
        Yi=0
        for j in range(len(part[i])):
            Xi=Xi+part[i][j][0]
            Yi=Yi+part[i][j][1]
        Xi=Xi/len(part[i])
        Yi=Yi/len(part[i])
        newList.append((Xi,Yi))
    #print('list:',list)
    #print('newList',newList)
    #print('part:',part)
    if(list.__eq__(newList)):
        return part,newList
    else:
        newpart,a=k_means(k,newList)
    return newpart,a,



with open('K-means dataset.txt') as f:
    data=f.readlines()

data=data[1:]
for i in range(len(data)):
    data[i]=data[i][:-1]
    data[i]=data[i].split(' ')
    data[i][0]=float(data[i][0])
    data[i][1] = float(data[i][1])
k=5
list= random.sample(data,k)
#list=[(4,4),(12,12)]
fin,lisst=k_means(k,list)
print(fin)
clors = ['blue' , 'green' , 'red'  , 'orange' , 'brown' , 'cyan' , 'yellow']
mark = ['o' , 'x' , '^' , 'h' , '<' , 'd' , '+' , 's']
for i in range(len(fin)):
    a=[]
    b=[]
    for j in range(len(fin[i])):
       plt.plot(fin[i][j][0],fin[i][j][1], marker='x', color=clors[i])
for i in range(len(lisst)):
    plt.plot(lisst[i][0],lisst[i][1], marker='o', color='black')
    print(lisst[i][0],lisst[i][1])

#plt.show()
plt.savefig('K_means'+str(k)+'.png')