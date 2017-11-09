import copy

N = 4

def isGoal(board):
    cnt = 0
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1:
                if is_safe(board, i, j):
                    cnt = cnt+1
    if (cnt == N):
        return True
    else:
        return False

def find(l, elem):
    for row, i in enumerate(l):
        try:
            column = i.index(elem)
        except ValueError:
            continue
        return row, column
    return -1


def is_safe(board, row, col):
    for i in range(col):
        if board[row][i] == 1:
            return False
    for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
        if board[i][j] == 1:
            return False
    for i, j in zip(range(row, N, 1), range(col, -1, -1)):
        if board[i][j] == 1:
            return False

    return True


def solveNQdfs(board, col):
    if col >= N:
        return True

    for i in range(N):
        if is_safe(board, i, col):
            board[i][col] = 1
            if solveNQdfs(board, col + 1) == True:
                return True
            board[i][col] = 0
    return False


def solveNQbfs(board):
    queue = [[copy.deepcopy(board),0]]
    while queue != []:
        #print(queue)
        [st, lvl] = queue.pop()
        print(st)
        if isGoal(st):
            return st
        for i in range(N):
            #print(st)
            if is_safe(st, i, lvl):
                st[i][lvl] = 1
                queue.insert(0, [copy.deepcopy(st) ,lvl+1])
                st[i][lvl] = 0
    return False



def print_board(board):
    for i in range(N):
       for j in range(N):
           print(board[i][j], end=' ')
       print()


board = [[0 for x in range(N)] for y in range(N)]
print("Using DFS")
if solveNQdfs(board, 0) == False:
    print("Solution does not exist")
else :
    print_board(board)
print("Using BFS: ")
#solveNQbfs(board)

