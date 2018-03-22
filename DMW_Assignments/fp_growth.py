import operator
class Tree(object):
    def __init__(self):
        self.child_name=[]
        self.child=[]
        self.name=''
        self.data = None
        self.parrent=None

def print_pattern_base(root,local_dic):
    if root.name=='Root':
        return
    else:
        if root.name not in local_dic:
            local_dic[root.name]=0
        print(root.name,end=' ')
        print_pattern_base(root.parrent,local_dic)

with open('input.txt') as f:
    x = f.readlines()
print('Transactions are : ')
inp={}
for i in range(len(x)):
    x[i]=x[i][:-1]
    print(x[i])
    x[i]=x[i].split('-')
    x[i]=x[i][1].split(',')
    for j in range(len(x[i])):
        if int(x[i][j]) not in inp.keys():
            inp[int(x[i][j])]=0
        inp[int(x[i][j])]=inp[int(x[i][j])]+1
        x[i][j]=int(x[i][j])

for i in inp.keys():
    if inp[i] <2:
        del inp[i]

new=sorted(inp.items(),key=operator.itemgetter(1))
new=new[::-1]

for i in range(len(new)):
    a,b=new[i]
    new[i]=a


#print(new)

root = Tree()
root.name='Root'
root.data=0
dic ={}

for i in range(len(x)):
    tmp=x[i]
    current=root
    for j in new:
        if not str(j) in dic.keys():
            dic[str(j)]=[]
        if j in tmp:
            if str(j) in current.child_name:
                #print('found ',str(j), 'a child of '+ current.name)
                for k in current.child:
                    if k.name==str(j):
                        current=k
                current.data=current.data+1

            else :
                #print('no child found ',str(j),current.name)
                current.child_name.append(str(j))

                temp=Tree()
                temp.name=str(j)
                temp.data=1
                dic[str(j)].append(temp)
                temp.parrent=current
                current.child.append(temp)
                current=temp

    current=root

desc=new[::-1]
global_subset=[]

print()
print()
print('Conditional Pattern Bases')

for j in desc:
    print()
    global_dic={}
    print('Base ', j,' : ')
    for k in dic[str(j)]:
        local_dic = {}

        if not k.parrent.name=='Root':
            print_pattern_base(k.parrent,local_dic)
            print('\t','{',k.data,'}')
            for i in local_dic.keys():
                local_dic[i]=k.data
                if i not in global_dic.keys():
                    global_dic[i]=0
            for ind in local_dic.keys():
                global_dic[ind] = global_dic[ind] + local_dic[ind]
    pattern = []
    temp_subset=[]
    local_subset=[]
    #print(global_dic)
    for index in global_dic.keys():
        if global_dic[index] >=2:
            pattern.append(int(index))
    #print(pattern)
    for m in range(1,(1 << len(pattern))):
        for n in range(len(pattern)):
            if m & (1 << n):
                temp_subset.append(pattern[n])
        temp_subset.append(j)
        if temp_subset not in global_subset:
            global_subset.append(temp_subset)
        local_subset.append(temp_subset)
        temp_subset=[]
print()
print('Frequent Itemset')
for index in global_subset:
    print(index)
