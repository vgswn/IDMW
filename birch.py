class Tree(object):
    def __init__(self):
        self.child_name=[]
        self.child=[]
        self.name=''
        self.data = None
        self.parrent=None
with open('Dataset_birch.txt') as f:
    data=f.readlines()
print(data)
for i in range(len(data)):
    data[i]=data[i][:-1]
    data[i]=data[i].split(' ')
    data[i][0]=int(data[i][0])
    data[i][1] = int(data[i][1])
print(data)