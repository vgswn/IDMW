import itertools, copy
from heapq import heappush, heappop


pq = []                         # list of entries arranged in a heap
entry_finder = {}               # mapping of tasks to entries
REMOVED = '<removed-task>'      # placeholder for a removed task
counter = itertools.count()     # unique sequence count


def add_task(task, priority=0):
    'Add a new task or update the priority of an existing task'
    if task in entry_finder:
        remove_task(task)
    count = next(counter)
    entry = [priority, count, task]
    entry_finder[task] = entry
    heappush(pq, entry)


def remove_task(task):
    'Mark an existing task as REMOVED.  Raise KeyError if not found.'
    entry = entry_finder.pop(task)
    entry[-1] = REMOVED


def pop_task():
    'Remove and return the lowest priority task. Raise KeyError if empty.'
    while pq:
        priority, count, task = heappop(pq)
        if task is not REMOVED:
            del entry_finder[task]
            return task, priority
    raise KeyError('pop from an empty priority queue')


def contains(task):
    return task in entry_finder


def is_empty():
    for entry in pq:
        if entry[-1] != '<removed-task>':
            return False
    return True


N = 3
initial = '12345@678'
goal = '@23145678'
visited = set()
visited.add(copy.deepcopy(initial))


def ucs_search():
    add_task(copy.deepcopy(initial), 0)
    while not is_empty():
        element, _priority = pop_task()
        print(element,end=" ")
        print(_priority)
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
            if (tmp not in visited) and (not contains(tmp)):
                visited.add(copy.deepcopy(tmp))
                add_task(copy.deepcopy(tmp), _priority + 5)
            elif contains(tmp):
                add_task(copy.deepcopy(tmp), _priority + 5)

        # move right -> 6 increase
        if j < N - 1:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * i + j + 1]
            tmp[3 * i + j + 1] = "@"
            tmp = "".join(tmp)
            if (tmp not in visited) and (not contains(tmp)):
                visited.add(copy.deepcopy(tmp))
                add_task(copy.deepcopy(tmp), _priority + 6)
            elif contains(tmp):
                add_task(copy.deepcopy(tmp), _priority + 6)
        # move up -> 2 increase
        if i > 0:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * (i - 1) + j]
            tmp[3 * (i - 1) + j] = "@"
            tmp = "".join(tmp)
            if (tmp not in visited) and (not contains(tmp)):
                visited.add(copy.deepcopy(tmp))
                add_task(copy.deepcopy(tmp), _priority + 2)
            elif contains(tmp):
                add_task(copy.deepcopy(tmp), _priority + 2)
        # move down -> 1 increase
        if i < N - 1:
            tmp = list(element)
            tmp[3 * i + j] = tmp[3 * (i + 1) + j]
            tmp[3 * (i + 1) + j] = "@"
            tmp = "".join(tmp)
            if (tmp not in visited) and (not contains(tmp)):
                visited.add(copy.deepcopy(tmp))
                add_task(copy.deepcopy(tmp), _priority + 1)
            elif contains(tmp):
                add_task(copy.deepcopy(tmp), _priority + 1)
    return False


if ucs_search():
    print("Success")
else:
    print ("Failure")