import itertools, copy
from heapq import heappush, heappop

pq = []  # list of entries arranged in a heap
entry_finder = {}  # mapping of tasks to entries
REMOVED = '<removed-task>'  # placeholder for a removed task
counter = itertools.count()  # unique sequence count


def init():
    global pq
    global entry_finder
    pq = []
    entry_finder = {}


def add_task(task, g, priority=0):
    'Add a new task or update the priority of an existing task'
    if task in entry_finder:
        pr = entry_finder[task][0]
        if priority > pr:
            return
        remove_task(task)
    count = next(counter)
    entry = [priority, count, task, g]
    entry_finder[task] = entry
    heappush(pq, entry)


def remove_task(task):
    'Mark an existing task as REMOVED.  Raise KeyError if not found.'
    entry = entry_finder.pop(task)
    entry[-2] = REMOVED


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



graph = {'Oradea': [('Zerind', 71), ('Sibiu', 151)],
         'Zerind': [('Oradea', 71), ('Arad', 75)],
         'Arad': [('Zerind', 75), ('Sibiu', 140), ('Timisoara', 118)],
         'Timisoara': [('Arad', 118), ('Lugoj', 111)],
         'Lugoj': [('Timisoara', 111), ('Mehadia', 70)],
         'Mehadia': [('Lugoj', 70), ('Drobeta', 75)],
         'Drobeta': [('Mehadia', 75), ('Craiova', 120)],
         'Sibiu': [('Oradea', 151), ('Arad', 140), ('Fagaras', 99), ('Rimnicu Vilcea', 80)],
         'Rimnicu Vilcea': [('Sibiu', 80), ('Craiova', 146), ('Pitesti', 97)],
         'Fagaras': [('Sibiu', 99), ('Bucharest', 211)],
         'Pitesti': [('Rimnicu Vilcea', 97), ('Craiova', 138), ('Bucharest', 101)],
         'Giurgiu': [('Bucharest', 90)],
         'Bucharest': [('Fagaras', 211), ('Pitesti', 101), ('Giurgiu', 90)],
         'Craiova': [('Drobeta', 120), ('Rimnicu Vilcea', 146), ('Pitesti', 138)]}

def printf(src,dest, parent):
    print('>',parent[src],sep=' ', end = '')
    if( parent[src] != dest):
        printf(parent[src],dest,parent)



def ucs(src, dest, parent):
    add_task(src, 0, 0)
    explored = set()
    parent[src] = 'EXIT'
    while pq:
        node, g = pop_task()
        # print(node, g, SLDip[node],g+SLDip[node], sep = ' ')
        if node == dest:
            return g
        explored.add(node[0])
        for state in graph[node]:
            # print(state, SLDip[state[0]], sep = ' ')
            # print(src, g+state[1], g+state[1]+SLDip[state[0]], sep= " ")
            if state[0] not in explored and (not contains(node[0])):
                add_task(state[0], g + state[1], g + state[1])
                parent[node] = state[0]
            elif contains(node[0]):
                add_task(state[0], g + state[1], g + state[1])
                parent[node] = state[0]


path = {}
print("Source: ")
src = input()
print("Destination: ")
dest = input()
g = ucs(src,dest, path)
print(src, end='')
printf(src,dest,path)
print(',',g, sep=' ')



