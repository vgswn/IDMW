import itertools, copy
from heapq import heappush, heappop


pq = []                         # list of entries arranged in a heap
entry_finder = {}               # mapping of tasks to entries
REMOVED = '<removed-task>'      # placeholder for a removed task
counter = itertools.count()     # unique sequence count


def add_task(task, g, priority=0):
    'Add a new task or update the priority of an existing task'
    if task in entry_finder:
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



SLDip = {'Arad':366,
         'Bucharest':0,
         'Craiova': 160,
         'Drobeta': 242,
         'Eforie' : 161,
         'Fagaras' : 176,
         'Giurgiu':77,
         'Hirsova':151,
         'Iasi': 226,
         'Lugoj': 244,
         'Mehadia': 241,
         'Neamt': 234,
         'Oradea': 380,
         'Pitesti': 100,
         'Rimnicu Vilcea': 193,
         'Sibiu': 253,
         'Timisoara': 329,
         'Urziceni': 80,
         'Vaslui': 199,
         'Zerind': 374}

graph = {'Oradea': [('Zerind', 71), ('Sibiu',151)],
         'Zerind': [('Oradea', 71), ('Arad', 75)],
         'Arad': [('Zerind', 75), ('Sibiu', 140), ('Timisoara', 118)],
         'Timisoara': [('Arad', 118), ('Lugoj', 111)],
         'Lugoj': [('Timisoara', 111), ('Mehadia', 70)],
         'Mehadia': [('Lugoj', 70), ('Drobeta', 75)],
         'Drobeta': [('Mehadia', 75), ('Craiova',120)],
         'Sibiu': [('Oradea', 151), ('Arad', 140), ('Fagaras', 99), ('Rimnicu Vilcea', 80)],
         'Rimnicu Vilcea': [('Sibiu', 80), ('Craiova', 146), ('Pitesti', 97)],
         'Fagaras': [('Sibiu', 99), ('Bucharest', 211)],
         'Pitesti': [('Rimnicu Vilcea', 97), ('Craiova', 138), ('Bucharest', 101)],
         'Giurgiu': [('Bucharest', 90)],
         'Bucharest': [('Fagaras',211), ('Pitesti', 101), ('Giurgiu', 90), ('Urziceni',85)],
         'Urziceni': [('Bucharest', 85), ('Hirsova', 98), ('Vaslui', 142)],
         'Vaslui': [('Urziceni', 142), ('Iasi', 92)],
         'Iasi': [('Vaslui', 92), ('Neamt', 87)],
         'Neamt': [('Iasi', 87)],
         'Hirsova': [('Urziceni', 98), ('Eforie', 86)],
         'Eforie': [('Hirsova', 86)],
         'Craiova': [('Drobeta', 120), ('Rimnicu Vilcea', 146), ('Pitesti', 138)]}





def a_star(src, dest):
    global pq
    add_task(src, 0, SLDip[src])
    explored = set()
    while pq:
        node, g = pop_task()

        if node == dest:
            print(node, g , sep=" ")
            print("Success")
            break
        explored.add(node[0])
        for state in graph[node]:
            #print(state)
            #print(src, g+state[1], g+state[1]+SLDip[state[0]], sep= " ")
            if state[0] not in explored or contains(node[0]):
                add_task(state[0], g+state[1], g+state[1]+SLDip[state[0]])
            elif contains(node[0]):
                add_task(state[0], g+state[1], g+state[1]+SLDip[state[0]])
    pq = []
        


for i in graph.keys():
    print(i)
    a_star(i,'Bucharest')
    

