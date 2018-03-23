with open('car.data.txt') as f:
    x = f.readlines()
for i in range(len(x)):
    x[i]=x[i][:-1]
    x[i]=x[i].split(',')
#print(x)
data=[]
for i in x:
    new=i
    temp=new.pop()
    #print(temp,new)
    data.append((new,temp))
print(data)
