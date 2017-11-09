import copy


N = 4
pq = []


def push(priority, element, lvl):
    pq.append([priority, element, lvl])


def pop():
    mini = 99999

    for item in pq:
        if item[0] < mini:
            mini = item[0]
            [elt, lvl] = [item[1], item[2]]
    pq.remove([mini, elt, lvl])
    return [mini, elt, lvl]


def contains(elt):
    for item in pq:
        if item[1] == elt:
            return True
    return False


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


def isGoal(board):
    c = 0
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1 and is_safe(board, i, j):
                c = c+1
    return True if c == N else False


def print_board(board):
    if board == False:
        print("No soln")
        return
    for i in range(N):
        for j in range(N):
            print(board[i][j], end=' ')
        print()


def solveNQucs(board):
    push(0, copy.deepcopy(board), 0)
    while pq:
        # print(queue)
        _priority, st, lvl = pop()
        print(st, _priority)
        if isGoal(st):
            return st
        for i in range(N):
            if is_safe(st, i, lvl):
                st[i][lvl] = 1
                push(_priority+lvl+i, copy.deepcopy(st), lvl+1)
                st[i][lvl] = 0
    return False

board = [[0 for x in range(N)] for y in range(N)]
solveNQucs(board)