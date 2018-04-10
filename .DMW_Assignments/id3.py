import math
import copy
class Tree(object):
    def __init__(self):
        self.child_name=[]
        self.child=[]
        self.name=''
        self.data = []
        self.parrent=None
        self.dicti=dict()
        self.info_gain=0

'''buying: vhigh, high, med, low. 
maint: vhigh, high, med, low. 
doors: 2, 3, 4, 5more. 
persons: 2, 4, more. 
lug_boot: small, med, big. 
safety: low, med, high.'''


def info_gain(list):
    s=sum(list)
    #print(s)
    ans=0
    for i in list:
        if i==0:
            return 0
        ans=ans+(-1*i*(math.log2(i/s)))/s
        #print(ans)
    return  ans
def entropy(list):
    s=0
    ans=0
    for i in list:
        s=s+sum(i)
    for i in list:
        ans=ans+(sum(i)*info_gain(i))/s
    return ans
def gain(a,b):
    return a-b
def calculate(list,ind):
    x={}
    for i in list:
        if i[ind] not in x:
            x[i[ind]]={}
        if  i[-1] not in x[i[ind]].keys():
            x[i[ind]][i[-1]]=0
        x[i[ind]][i[-1]]=x[i[ind]][i[-1]]+1

    return x
def cal(data):
    re={}
    for i in data:
        #print(i[-1])
        if i[-1] not in re.keys():
            re[i[-1]]=0
        re[i[-1]]=re[i[-1]]+1
    print('dsfgbreasfdg',re)
    return re


def printf(root):
    if len(root.child)==0:
        return
    print('Node = ',root.name)
    #print('childs = ',root.child_name)
    for i in root.child:
        printf(i)
def create_tree(node,list,count):
    #count = count_T(node.data)
    count=cal(node.data)

    if len(count.keys())==1:
        return
    if len(list)==0:

        #print('return ',root.name)
        return

    entr=[]
    for i in list:
        info = []
        d=calculate(node.data,i,)
        #print(d)
        x=[0,0,0,0]
        for j in d.keys():
            p=0
            for k in d[j].keys():
                x[p]=d[j][k]
                p=p+1
            #print(j,x)
            info.append(x)
        entr.append((entropy(info),i))
    entr.sort()
    neeew=entr.pop(0)
    #print(neeew[1],uni_list[neeew[1]])
    list.remove(neeew[1])

    #node.name=name[neeew[1]]+'->'+node.name
    node.name = str(neeew[1]) + '->' + node.name

    for i in uni_list[neeew[1]]:

        newNode=Tree()
        #newNode.name=name[neeew[1]]+'->'+i
        newNode.name=str(neeew[1])+'->'+i
        newNode.parrent=node
        node.child.append(newNode)
        node.child_name.append(newNode.name)
        #print(newNode.name)
        lll=copy.deepcopy(list)
        newData = []
        for da in data:
            if da[neeew[1]] == i:
                newData.append(da)
        newNode.data = newData
        create_tree(newNode,lll,count)









name=['buying','maint','doors','persons','lug_boot','safety']
with open('car.data.txt') as f:
    data=f.readlines()
for x in range(len(data)):
    data[x]=data[x][:-1]
    data[x]=data[x].split(',')
#print(data)

li=[]
with open('name.txt')as f:
    uni_list=f.readlines()
for x in range(len(uni_list)):
    uni_list[x]=uni_list[x][:-1]
    uni_list[x]=uni_list[x].split(',')
#print(uni_list)
for i in range(len(data[0])-1):
    li.append(i)

root=Tree()
root.data=data

count=1
create_tree(root,li,count)
printf(root)
print(count)
