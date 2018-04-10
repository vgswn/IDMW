with open('DATASET.csv', newline='') as f:
    data=f.readlines()
for i in range(len(data)):
    data[i]=data[i][:-1]
mapp={}
lisst=data[0].split(',')
for i in range(len(lisst)):
    mapp[i]=lisst[i]
#print(mapp)
data_dict={}
data_dict['T']=0
data_dict['F']=0
for i in range(len(data)):
    if i==0:
        continue;

    row=data[i].split(',')
    if row[-1]=='T':
        data_dict['T']=data_dict['T']+1
    else:
        data_dict['F']=data_dict['F']+1
    #print(ro)
    for j in range(len(row)):
        if j==0 or j==len(row)-1:
            continue
        #print(row[j])
        if j not in data_dict.keys():
            tmp={}
            data_dict[j]=tmp
        temp=data_dict[j]
        if row[j] not in temp:
            temp[row[j]]=[]
            temp[row[j]].append(0)
            temp[row[j]].append(0)
            #temp[row[j]]=(0,0)
        x=temp[row[j]]
        if row[-1]=='T':
            x[0]=x[0]+1
        elif row[-1]=='F':
            x[1]=x[1]+1
        temp[row[j]]=x
        data_dict[j]=temp

for key in data_dict.keys():
    print(key,':',data_dict[key])
    


test=input().split(',')
print(test)

prob={}
for x in range(len(test)):
    if x==0:
        continue
    temp=data_dict[x]
    p=temp[test[x]]
    prob[x]=[]
    prob[x].append(p[0]/(p[0]+p[1]))
    prob[x].append(1-prob[x][0])
t=1
f=1
for i in prob.keys():
    print(test[i],mapp[i],t,f)
    tmp=prob[i]
    print(tmp)
    t=t*tmp[0]
    f=f*tmp[1]
t=t*data_dict['T']
f=f*data_dict['F']

print(t,f)

