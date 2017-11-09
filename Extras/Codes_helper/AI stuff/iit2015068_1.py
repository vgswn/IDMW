import itertools, copy, math
from heapq import heappush, heappop


pq = []                         # list of entries arranged in a heap
entry_finder = {}               # mapping of tasks to entries
REMOVED = '<removed-task>'      # placeholder for a removed task
counter = itertools.count()     # unique sequence count


def add_task(task,g, priority=0):
    'Add a new task or update the priority of an existing task'
    if task in entry_finder:
        pr = entry_finder[task][0]
        if priority > pr:
            return
        remove_task(task)
    count = next(counter)
    entry = [priority, count, task,g]
    entry_finder[task] = entry
    heappush(pq, entry)


def remove_task(task):
    'Mark an existing task as REMOVED.  Raise KeyError if not found.'
    entry = entry_finder.pop(task)
    entry[-1] = REMOVED


def pop_task():
    'Remove and return the lowest priority task. Raise KeyError if empty.'
    while pq:
        priority, count, task, g = heappop(pq)
        if task is not REMOVED:
            del entry_finder[task]
            return task, g
    raise KeyError('pop from an empty priority queue')


def contains(task):
    return task in entry_finder


def is_empty():
    for entry in pq:
        if entry[-1] != '<removed-task>':
            return False
    return True


N = 3

def calc_h(tmp1,tmp2):
    sum = 0
    for i in range(9):
        if (tmp1[i] == '@'):
            continue
        i1 = int(i / 3)
        j1 = i - 3 * i
        tmp = list(tmp2).index(tmp1[i])
        i2 = int(tmp / 3)
        j2 = tmp - 3 * i
        sum = sum + int(math.fabs(i1-i2))+int(math.fabs(j1-j2))
    return sum


def ucs_search(initial, goal,visited):
    add_task(copy.deepcopy(initial), 0)
    visited.add(copy.deepcopy(initial))
    while not is_empty():
        element, _priority = pop_task()
        if element == goal:
            return True
        tmp = list(element).index("@")
        i = int(tmp / 3)
        j = tmp - 3 * i
        # move left -> cost increase 5
        if j > 0:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * i + j - 1]
            tmp[3 * i + j - 1] = "@"
            tmp = "".join(tmp)
            h = calc_h(tmp, goal)
            if (tmp not in visited) and (not contains(tmp)):
                visited.add(copy.deepcopy(tmp))
                add_task(copy.deepcopy(tmp), _priority + 1,_priority + 1 + h)
            elif contains(tmp):
                add_task(copy.deepcopy(tmp), _priority + 1, _priority + 1 + h)

        # move right -> 6 increase
        if j < N - 1:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * i + j + 1]
            tmp[3 * i + j + 1] = "@"
            tmp = "".join(tmp)
            h = calc_h(tmp, goal)
            if (tmp not in visited) and (not contains(tmp)):
                visited.add(copy.deepcopy(tmp))
                add_task(copy.deepcopy(tmp), _priority + 1, _priority + 1 + h)
            elif contains(tmp):
                add_task(copy.deepcopy(tmp), _priority + 1, _priority + 1 + h)
        # move up -> 2 increase
        if i > 0:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * (i - 1) + j]
            tmp[3 * (i - 1) + j] = "@"
            tmp = "".join(tmp)
            h = calc_h(tmp, goal)

            if (tmp not in visited) and (not contains(tmp)):
                visited.add(copy.deepcopy(tmp))
                add_task(copy.deepcopy(tmp), _priority + 1, _priority + 1 + h)
            elif contains(tmp):
                add_task(copy.deepcopy(tmp), _priority + 1, _priority + 1 + h)
        # move down -> 1 increase
        if i < N - 1:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * (i + 1) + j]
            tmp[3 * (i + 1) + j] = "@"
            tmp = "".join(tmp)
            h = calc_h(tmp, goal)
            if (tmp not in visited) and (not contains(tmp)):
                visited.add(copy.deepcopy(tmp))
                add_task(copy.deepcopy(tmp), _priority + 1, _priority + 1 + h)
            elif contains(tmp):
                add_task(copy.deepcopy(tmp), _priority + 1, _priority + 1 + h)
    return False


print("Enter start state row-wise,without any space(denote blank by @: ")
initial = input()
print("Enter goal state row-wise,without any space(denote blank by @: ")
goal = input()
visited = set()
print(calc_h(initial, goal))
ucs_search(initial, goal,visited)

