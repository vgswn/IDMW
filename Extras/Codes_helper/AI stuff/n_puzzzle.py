import copy


N = 3
initial = '12345@678'
goal = '@23145678'
queue = []
visited = set()
visited.add(copy.deepcopy(initial))
queue.insert(0, copy.deepcopy(initial))


def add_chk(s, x):
    return len(s) != (s.add(x) or len(s))


def dfs_search(i, j, dire):
    global initial
    print("State : "+initial)
    if goal == initial:
        return True
    # move left->3
    if j > 0 and dire != 4:
        initial = list(initial)
        initial[3*i+j] = initial[3*i+j - 1]
        initial[3*i+j - 1] = "@"
        initial = "".join(initial)
        if add_chk(visited, copy.deepcopy(initial)):
            return dfs_search(i, j - 1, 3)
    # move right->4
    elif j < N - 1 and dire != 3:
        initial = list(initial)
        initial[3*i+j] = initial[3*i+j + 1]
        initial[3*i+j + 1] = "@"
        initial = "".join(initial)
        if add_chk(visited, copy.deepcopy(initial)):
            return dfs_search(i, j + 1, 4)
    # move up->1
    elif i > 0 and dire != 2:
        initial = list(initial)
        initial[3*i+j] = initial[3*(i - 1) + j]
        initial[3*(i - 1) + j] = "@"
        initial = "".join(initial)
        if add_chk(visited, copy.deepcopy(initial)):
            return dfs_search(i - 1, j, 1)
    # move down->2
    elif i < N - 1 and dire != 1:
        initial = list(initial)
        initial[3*i+j] = initial[3*(i + 1)+j]
        initial[3*(i + 1)+j] = "@"
        initial = "".join(initial)
        if add_chk(visited, copy.deepcopy(initial)):
            return dfs_search(i + 1, j, 2)

    return False


def bfs_search():
    while queue != []:
        element = queue.pop()
        if element == goal:
            return True
        print(element)
        tmp = list(element).index("@")
        i = int(tmp / 3)
        j = tmp - 3 * i
        # move left
        if j > 0:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * i + j - 1]
            tmp[3 * i + j - 1] = "@"
            tmp = "".join(tmp)
            if add_chk(visited, copy.deepcopy(tmp)):
                queue.insert(0, copy.deepcopy(tmp))
        # move right
        if j < N - 1:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * i + j + 1]
            tmp[3 * i + j + 1] = "@"
            tmp = "".join(tmp)
            if add_chk(visited, copy.deepcopy(tmp)):
                queue.insert(0, copy.deepcopy(tmp))
        # move up
        if i > 0:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * (i - 1) + j]
            tmp[3 * (i - 1) + j] = "@"
            tmp = "".join(tmp)
            if add_chk(visited, copy.deepcopy(tmp)):
                queue.insert(0, copy.deepcopy(tmp))
        # move down
        if i < N - 1:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * (i + 1) + j]
            tmp[3 * (i + 1) + j] = "@"
            tmp = "".join(tmp)
            if add_chk(visited, copy.deepcopy(tmp)):
                queue.insert(0, copy.deepcopy(tmp))
    return False
if dfs_search(1, 2, 0):
#if bfs_search():
    print("Success")
else:
    print ("Failure")
