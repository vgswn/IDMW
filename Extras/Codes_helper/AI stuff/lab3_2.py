from collections import deque

global N
N = 4

global q

def isSafe(board, row, col):
    for i in range(col):
        if board[row][i] == 1:
            return False
    for i,j in zip(range(row,-1,-1), range(col, -1, -1)):
        if board[i][j] == 1:
            return False
    for i,j in zip(range(row,N, 1), range(col, -1, -1)):
        if board[i][j] == 1:
            return False

    return True

def solveNQdfs(board, col):
    if col >= N:
        return True
 
    for i in range(N):
        if isSafe(board, i, col):
            board[i][col] = 1
            if solveNQdfs(board, col+1) == True:
                return True
            board[i][col] = 0
    return False

def solveNQbfs(board,row,col):
    if isGoal(board):
        return True

    for i in range
    
    


def printBoard(board):
    for i in range(N):
        for j in range(N):
            print(board[i][j],end=' ')
        print()


def solveNQ():
    board = [ [0 for x in range(N)] for y in range(N)]
    if solveNQdfs(board, 0) == False:
    #if solveNQbfs(board, 0, 0) == False:
        print ("Solution does not exist")
        return False
    printBoard(board)
    return True

solveNQ()
