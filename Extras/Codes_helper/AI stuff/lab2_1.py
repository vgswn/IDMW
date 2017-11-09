import random
import time
import threading


class RandomDirt (threading.Thread):
    def __init__(self, matrix, m, n):
        threading.Thread.__init__(self)
        self.matrix = matrix
        self.m = m
        self.n = n

    def run(self):
        while True:
            x = random.randint(0, m - 1)
            y = random.randint(0, n - 1)
            matrix[x][y] = 'dirty'
            time.sleep(2)

def is_clean(matrix, m, n):
    for i in range(m):
        for j in range(n):
            if matrix[i][j] == 'dirty':
                return False
    return True

def simple_reflex_agent(location, status, m, n):
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


# taking and processing input
m, n = input().split(" ")
m = int(m)
n = int(n)
matrix = [['clean' if random.randint(0, 100) / 100.0 <= 0.7 else 'dirty' for x in range(int(n))] for y in range(int(m))]
print(matrix)
'''
# start generating dirt
thread = RandomDirt(matrix, m, n)
thread.start()
'''

#start our vaccumm cleaner
i = random.randint(0, m - 1)
j = random.randint(0, n - 1)
while True:
    if is_clean(matrix, m, n):
        print ("Area Cleaned")
        break
    ans = simple_reflex_agent([i, j], matrix[i][j], m, n)
    print(ans)
    if ans == 'cleaned' :
        matrix[i][j] = 'clean'
    elif ans == 'moved left':
        j = j - 1
    elif ans == 'moved right':
        j = j + 1
    elif ans == 'moved up':
        i = i - 1
    elif ans == 'moved down':
        i = i + 1
    time.sleep(.500)
