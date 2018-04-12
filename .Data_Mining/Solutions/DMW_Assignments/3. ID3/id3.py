import math
import copy


class Tree(object):
    def __init__(self):
        self.child_name=[]
        self.child=[]
        self.name=''
        self.data = []
        self.parrent=None
        self.att_decided=''
        self.attr_decided_cat=''
        self.level=0
        self.d=''



def info_gain(list):
    s=sum(list)
    ans=0
    for i in list:
        if i==0:
            continue
        ans=ans+(-1*i*(math.log2(i/s)))/s
    return  ans


def entropy(list):
    s=0
    ans=0
    for i in list:
        s=s+sum(i)
        #print(i,sum(i))
    #print(s)
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


def cal(d):
    re={}
    for i in d:
        if i[-1] not in re.keys():
            re[i[-1]]=0
        re[i[-1]]=re[i[-1]]+1
    return re


def printf(root):
    if len(root.child)==0:
        print('Level: ',root.level,'Attribute :',root.att_decided,'Category :',root.attr_decided_cat,'Next level:',root.d)
        return

        return
    if root.level==0:
        print(root.name,root.d)
    else:
        print('Level: ',root.level,'Attribute :',root.att_decided,'Category :',root.attr_decided_cat,'Next level:',root.d)#,'Data Count',len(root.data))

    for i in root.child:
        printf(i)


def create_tree(node,list,c):
    count=cal(node.data)

    if len(count.keys())==1:
        pp=''
        for nn in count.keys():
            pp=nn

        newNode=Tree()
        newNode.level=c+1
        newNode.att_decided=name[-1]
        newNode.attr_decided_cat=pp
        node.child.append(newNode)
        node.child_name.append(newNode.name)
        node.d =name[-1]

        return

    if len(list)==0:
        newNode = Tree()
        newNode.level = c + 1
        newNode.att_decided = name[-1]
        newNode.attr_decided_cat =node.data[0][-1]
        node.child.append(newNode)
        node.child_name.append(newNode.name)
        node.d =name[-1]

        return

    entr=[]
    for i in list:
        info = []
        d=calculate(node.data,i)
        #print(d)

        for j in d.keys():
            #x = [0, 0,0,0]
            x=[0,0]
            p=0
            for k in d[j].keys():
                x[p]=d[j][k]
                p=p+1
            #print(x)
            info.append(copy.deepcopy(x))
        #print(info)
        entr.append((entropy(info),i))
    entr.sort()
    #print('Level ',c+1)
    #print('Creating new Node ')
    '''for ww in entr:
        print('Entropy= ',ww[0],'Name ',name[ww[1]])
    print(entr)'''
    neeew=entr.pop(0)
    list.remove(neeew[1])
    c=c+1
    node.d=name[neeew[1]]
    for i in uni_list[neeew[1]]:
        newData = []
        for da in node.data:
            if da[neeew[1]] == i:
                newData.append(da)
        if len(newData) != 0:
            newNode=Tree()
            newNode.att_decided=name[neeew[1]]
            newNode.data = newData

            newNode.attr_decided_cat=i
            newNode.level=c
            newNode.parrent=node
            node.child.append(newNode)
            node.child_name.append(i)
            lll=copy.deepcopy(list)
            create_tree(newNode,lll,c)


def check(root,dicc):

    while(True):
        cat = root.d
        #print(cat)
        ind = root.child_name.index(dicc[cat])
        if root.child[ind].d=='ans':

            print(root.child[ind].child[0].attr_decided_cat)
            break
        else:
            root=root.child[ind]





#name=['buying','maint','doors','persons','lug_boot','safety','ans']
name=['Outlook','Temp','Humidity','Wind','ans']
#with open('car.data.txt') as f:
with open('id3.txt')as f:
    data=f.readlines()
for x in range(len(data)):
    data[x]=data[x][:-1]
    data[x]=data[x].split(',')

li=[]
#with open('name.txt')as f:
with open('name_2.txt')as f:

    uni_list=f.readlines()


for x in range(len(uni_list)):
    uni_list[x]=uni_list[x][:-1]
    uni_list[x]=uni_list[x].split(',')
print(uni_list)

for i in range(len(data[0])-1):
    li.append(i)


root=Tree()
root.data=data
root.name='Root'

count=1
create_tree(root,li,0)
#printf(root)
m={}
m['Outlook']='Sunny'
m['Temp']='Hot'
m['Humidity']='High'
m['Wind']='Weak'
#check(root,m)
print('Enter number of inputs:')
n=input()
n=int(n)
for j in range(n):
    for i in name:
        if(i=='ans'):
            continue
        print('Input ',i,': ')
        m[i]=input()
    print('Answer:',sep=' ')
    check(root,m)
    print()



