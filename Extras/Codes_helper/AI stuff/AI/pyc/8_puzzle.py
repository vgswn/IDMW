import copy

global N
global initial
global goal
N = 3
initial = [[1, 2, 3], [4, 5, -1], [6, 7, 8]]
goal = [[-1, 2, 3], [1, 4, 5], [6, 7, 8]]
global q
q = []
q.insert(0, copy.deepcopy(initial))
global visited
visited = [[0 for x in range(N)] for y in range(N)]
visited[1][2] = 1

"""
def soln(i, j, dire):
    # print(initial)
    # print(goal)
    if goal == initial:
        return True
    # move left
    elif j > 0 and dire != 4:
        initial[i][j] = initial[i][j - 1]
        initial[i][j - 1] = -1
        return soln(i, j - 1, 3)
    # move right
    elif j < N - 1 and dire != 3:
        initial[i][j] = initial[i][j + 1]
        initial[i][j + 1] = -1
        return soln(i, j + 1, 4)
    # move up
    elif i > 0 and dire != 2:
        initial[i][j] = initial[i - 1][j]
        initial[i - 1][j] = -1
        return soln(i - 1, j, 1)
    # move down
    elif i < N - 1 and dire != 1:
        initial[i][j] = initial[i + 1][j]
        initial[i + 1][j] = -1
        return soln(i + 1, j, 2)
    else:
        return False

"""
def find(l, elem):
    for row, i in enumerate(l):
        try:
            column = i.index(elem)
        except ValueError:
            continue
        return row, column
    return -1


def bfsSoln():
    while q != []:
        a = q.pop()
        if a == goal:
            return True
        else:
            i, j = find(a, -1)
            if j > 0 and visited[i][j - 1] = 0:


if soln(1, 2, 0):
    print("Success")
else:
    print("Failed")