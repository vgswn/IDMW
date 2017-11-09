import random, time, queue

def RULE_MATCH(matrix, x, y, n, m, visited_cell):

    d = queue.PriorityQueue()
    if x-1 >=0 and visited_cell[x-1][y]==0:
        if matrix[x-1][y]=='Dirty':
            d.put([1+(-5),'UP'])
        else :
            d.put([1,'UP'])
    if x+1< n and visited_cell[x+1][y]==0 :
        if matrix[x+1][y]=='Dirty':
            d.put([2+(-5),'DOWN'])
        else :
            d.put([2,'DOWN'])
    if y-1 >=0 and visited_cell[x][y-1]==0 :
        if matrix[x][y-1]=='Dirty':
            d.put([3+(-5),'LEFT'])
        else :
            d.put([3,'LEFT'])
    if y+1 < m and visited_cell[x][y+1]==0:
        if matrix[x][y+1]=='Dirty':
            d.put([4+(-5),'RIGHT'])
        else :
            d.put([4,'RIGHT'])
    if d.empty() :
        flag = 0
        while flag == 0:
            r = random.randrange(4)
            if r == 0 and x - 1 >= 0:
                flag = 1
                return 'UP'
            elif r == 1 and x + 1 <= n:
                flag = 1
                return 'DOWN'
            elif r == 2 and y - 1 >= 0:
                flag = 1
                return 'LEFT'
            elif r == 3 and y + 1 <= m:
                flag = 1
                return 'RIGHT'
    x=d.get()
    return x[1]



def SIMPLE_REFLEX_AGENT(matrix, x, y, count, n, m, visited_cell):
    if matrix[x][y] == 'Dirty':
        matrix[x][y] = 'Clean'
        print("Sucked at ", x, y)


    return RULE_MATCH(matrix, x, y, n, m, visited_cell)


def vacuum_cleaner(matrix, x, y, count, n, m, visited_cell):
    if count == (m + 1) * (n + 1):
        return
    if visited_cell[x][y] == 0:
        count = count + 1

    visited_cell[x][y] = 1
    action = SIMPLE_REFLEX_AGENT(matrix, x, y, count, n, m, visited_cell)
    if action == 'UP':
        print("Going Up")
        vacuum_cleaner(matrix, x - 1, y, count, n, m, visited_cell)

    elif action == 'DOWN':
        print("Going Down")
        vacuum_cleaner(matrix, x + 1, y, count, n, m, visited_cell)
    elif action == 'LEFT':
        print("Going Left")
        vacuum_cleaner(matrix, x, y - 1, count, n, m, visited_cell)
    elif action == 'RIGHT':
        print("Going Right")
        vacuum_cleaner(matrix, x, y + 1, count, n, m, visited_cell)


        
    '''
    moves = ['moved left', 'moved right', 'moved down', 'moved up']
    if status == 'dirty':
        return 'cleaned'
    else:
        while True:
            x = random.randint(0, 3)
            # print(x)
            if x == 0:
                if location[1] == 0:
                    continue
            elif x == 1:
                if location[1] == n-1:
                    continue
            elif x == 2:
                if location[0] == m-1:
                    continue
            elif x == 3:
                if location[0] == 0:
                    continue
            break
        return moves[x]

'''
# taking and processing input
m, n = input().split(" ")
m = int(m)
n = int(n)
matrix = [['clean' if random.randint(0, 100) / 100.0 <= 0.7 else 'dirty' for x in range(int(n))] for y in range(int(m))]
print(matrix)
visited = [ [0 for x in range(n)] for y in range(m)]

#start our vaccumm cleaner
i = random.randint(0, m - 1)
j = random.randint(0, n - 1)
count = 0
print("Started from "+ str(i)+", "+str(j))
vacuum_cleaner(matrix, i, j, count, n - 1, m - 1, visited)
print("Cleaned the area")
