import copy


def agent(loc, dirt):
    que = []
    visited = [0,0,0,0]
    print(dirt)
    que.insert(0, copy.deepcopy([loc,dirt,' ']))
    while que:
        loc, dirt, move = que.pop()
        print(move, end= ' ')
        if dirt[0]==0 and dirt[1]==0 and dirt[2] == 0 and dirt[3] == 0:
            return '10'
        if loc == '00' :
            visited[0] = 1
            if dirt[0] == '1':
                print('> S', end = ' ')
                dirt[0] = '0'
            if visited[1]!= 1:
                que.insert(0, copy.deepcopy(['01', dirt, '> R']))
            if visited[2]!=1:
                que.insert(0, copy.deepcopy(['10', dirt, '> D']))
        if loc == '01':
            visited[1] = 1
            if dirt[1] == '1':
                print('> S', end = ' ')
                dirt[1] = '0'
            if visited[0]!=1:
                que.insert(0, copy.deepcopy(['00', dirt, '> L']))
            if visited[3] != 1:
                que.insert(0, copy.deepcopy(['11', dirt, '> D']))
        if loc == '10':
            visited[2] = 1
            if dirt[2] == '1':
                print('> S', end = ' ')
                dirt[2] = '0'
            if visited[0]!=1:
                que.insert(0, copy.deepcopy(['00', dirt, '> U']))
            if visited[3] != 1:
                que.insert(0, copy.deepcopy(['11', dirt, '> R']))
        if loc == '11':
            visited[3] = 1
            if dirt[3] == '1':
                print('> S', end = ' ')
                dirt[3] = '0'
            if visited[1] != 1:
                que.insert(0, copy.deepcopy(['01', dirt, '> U ']))
            if visited[2] != 1:
                que.insert(0, copy.deepcopy(['10', dirt, '> L']))
    return loc


print("Enter location: ")
loc = input()
print("Enter dirt without any special character(Eg - 0110): ")
dirt = input()
dirt = list(dirt)
r = agent(loc, dirt)
print()
print("Final State: ",r,"0000", sep= " ")
