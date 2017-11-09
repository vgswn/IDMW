import functools
def cmpp(a,b):
    if a[0]!=b[0]:
        return a[0]-b[0]   #ascending order
    else:
        return b[1]-a[1]  #descending order
n=[[3,4],[1,2],[1,4],[3,65]]
n=sorted(n,key=functools.cmp_to_key(cmpp))
print(n)
d=[[1,2],[3,4]]
s=tuple(tuple(x) for x in d)
for i in s:
    print(i)
print(s)
