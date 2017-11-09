import copy

N = 4
#TODO: Make isGoal

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
            if solveNQdfs(board, col + 1):
                return True
            board[i][col] = 0
    return False


def solveNQbfs(board):
    queue = [[copy.deepcopy(board),0]]
    while queue:
        #print(queue)
        [st, lvl] = queue.pop()
        if lvl == N-1:
            return st
        for i in range(N):
            #print(st)
            if is_safe(st, i, lvl):
                st[i][lvl] = 1
                queue.insert(0, [copy.deepcopy(st) ,lvl+1])
                st[i][lvl] = 0
    return False


def print_board(board):
    if board == False:
        print("No soln")
        return
    for i in range(N):
        for j in range(N):
            print(board[i][j], end=' ')
        print()


board = [[0 for x in range(N)] for y in range(N)]
'''if solveNQdfs(board, 0) == False:
    print("Solution does not exist")
else :
    print_board(board)'''
print_board(solveNQbfs(board))

