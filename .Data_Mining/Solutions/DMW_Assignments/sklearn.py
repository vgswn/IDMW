import numpy as np
import matplotlib.pyplot as plt
import sklearn.cluster as cls


data =[]
noofpoints =0
nooffeatures =0
fp =open("DBSCAN.txt" ,"r")
# fp=open("small.txt","r") c= 0
for line in fp:
    if c == 0:
        x = line.split(' ')
        no_of_points, no_of_features = int(x[0]), int(x[1])
    else:
        x = line.split(' ')
        data.append([float(x[0]), float(x[1])])
    c = c + 1

dbs = cls.DBSCAN(eps=20, min_samples=100)
dbs.fit(data)

print(dbs.fit_predict(data))

color = ['b', 'g', 'r', 'c', 'm', 'y', 'k', 'w']
cn = dbs.fit_predict(data)

s = set()
for i in cn:
    s.add(i)
k = len(s)
c = 0
for i in s:
    v = []
    co = color[min(c, 7)]
    c = c + 1
    for j in range(len(data)):
        if cn[j] == i:
            v.append(data[j])
    print(v)
    plt.plot([i[0] for i in v], [i[1] for i in v], co + 'o')
plt.show()


