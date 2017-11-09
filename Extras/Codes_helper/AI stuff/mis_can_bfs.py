class state:
    def __init__(self,c,m,s):
        self.nc=c
        self.nm=m
        self.side=s
    def __hash__(self):
        return hash((self.nc,self.nm,self.side))

    def __eq__(self, other):
        if not isinstance(other, type(self)): return NotImplemented
        return self.nc==other.nc and  self.nm==other.nm and  self.side==other.side
    def prin(self):
        print(self.nc,self.nm,self.side,sep=" ")
class queue:
    def __init__(self):
        self.arr=[]
    def enqueue(self,x):
        self.arr.append(x)
    def front(self):
        return self.arr[0]
    def dequeue(self):
        self.arr=self.arr[1:]
    def isempty(self):
        if len(self.arr)==0:
            return 1
        else:
            return 0
    def prin(self):
        for i in enumerate(self.arr):
            print(i)

se = set()
parent = []
def display(s):
    f=0
    for sublist in parent:
        if sublist[1] == s:
            display(sublist[0])
            f=1
            if s.side == 0:
                print(s.nc,s.nm,sep=" ",end="      ")
                print(3-s.nc,3-s.nm,sep=" ")
            else:
                print(3-s.nc, 3-s.nm, sep=" ", end="      ")
                print(s.nc, s.nm, sep=" ")
            break;
    if f ==0:
        print("3 3      0 0")

def  cross(s):
    q=queue()
    q.enqueue(s)
    while(q.isempty()==0):
        s=q.front()
        q.dequeue()
        #s.prin()
        if s.nc==3 and s.nm==3 and s.side == 1:
            display(s)
            print()
        else :
            if s.nc >= 1:
                s1 = state((3 - s.nc) + 1, (3 - s.nm), 1 - s.side)
                if s1 not in se:
                    se.add(s1)
                    if not ((s1.nc == 2 and s1.nm == 1) or (s1.nm == 2 and s1.nc <= 1) or (s1.nc == 3 and s1.nm == 1)):
                        parent.append([s, s1])
                        q.enqueue(s1)

            if s.nc >= 2:
                s1 = state((3 - s.nc) + 2, (3 - s.nm), 1 - s.side)
                if s1 not in se:
                    se.add(s1)
                    if not ((s1.nc == 2 and s1.nm == 1) or (s1.nm == 2 and s1.nc <= 1) or (s1.nc == 3 and s1.nm == 1)):
                        parent.append([s, s1])
                        q.enqueue(s1)

            if s.nm >= 1:
                s1 = state((3 - s.nc), (3 - s.nm) + 1, 1 - s.side)
                if s1 not in se:
                    se.add(s1)
                    if not ((s1.nc == 2 and s1.nm == 1) or (s1.nm == 2 and s1.nc <= 1) or (s1.nc == 3 and s1.nm == 1)):
                        parent.append([s, s1])
                        q.enqueue(s1)

            if s.nm >= 2:
                s1 = state((3 - s.nc), (3 - s.nm) + 2, 1 - s.side)
                if s1 not in se:
                    se.add(s1)
                    if not ((s1.nc == 2 and s1.nm == 1) or (s1.nm == 2 and s1.nc <= 1) or (s1.nc == 3 and s1.nm == 1)):
                        parent.append([s, s1])
                        q.enqueue(s1)

            if s.nm >= 1 and s.nc >= 1:
                s1 = state((3 - s.nc) + 1, (3 - s.nm) + 1, 1 - s.side)
                if s1 not in se:
                    se.add(s1)
                    if not ((s1.nc == 2 and s1.nm == 1) or (s1.nm == 2 and s1.nc <= 1) or (s1.nc == 3 and s1.nm == 1)):
                        parent.append([s, s1])
                        q.enqueue(s1)


s=state(3,3,0)
se.add(s)
cross(s)
