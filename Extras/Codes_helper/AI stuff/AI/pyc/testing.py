
"""
# A Python program to demonstrate common binary heap operations

# Import the heap functions from python library
from heapq import heappush, heappop, heapify


# heappop - pop and return the smallest element from heap
# heappush - push the value item onto the heap, maintaining
#             heap invarient
# heapify - transform list into heap, in place, in linear time

# A class for Min Heap
class MinHeap:
    # Constructor to initialize a heap
    def __init__(self):
        self.heap = []

    def parent(self, i):
        return (i - 1) / 2

    # Inserts a new key 'k' [pri,state,g]
    def insertKey(self, k):
        heappush(self.heap, k)

        # Decrease value of key at index 'i' to new_val

    # It is assumed that new_val is smaller than heap[i]
    def decreaseKey(self, old_val, new_val):
        i = -1;
        for num in enumerate(self.heap):
            if self.heap[num][1] == old_val:
                i = num
                break
        if new_val > self.heap[i][0]:
            return
        self.heap[i] = new_val
        while (i != 0 and self.heap[self.parent(i)][0] > self.heap[i][0]):
            # Swap heap[i] with heap[parent(i)]
            self.heap[i], self.heap[self.parent(i)] = (
                self.heap[self.parent(i)], self.heap[i])

    # Method to remove minium element from min heap
    def extractMin(self):
        return heappop(self.heap)
'''
    # This functon deletes key at index i. It first reduces
    # value to minus infinite and then calls extractMin()
    def deleteKey(self, i):
        self.decreaseKey(i, float("-inf"))
        self.extractMin()

    # Get the minimum element from the heap
    def getMin(self):
        return self.heap[0]
'''

# Driver pgoratm to test above function
heapObj = MinHeap()
heapObj.insertKey([2,'asd', 4])
heapObj.decreaseKey('asd', 5,)
print(heapObj.heap)
"""

import itertools, copy
from heapq import heappush, heappop


pq = []                         # list of entries arranged in a heap
entry_finder = {}               # mapping of tasks to entries
REMOVED = '<removed-task>'      # placeholder for a removed task
counter = itertools.count()     # unique sequence count


def add_task(task, g, priority=0):
    'Add a new task or update the priority of an existing task'
    if task in entry_finder:
        pr = entry_finder[task][0]
        print(entry_finder[task])
        print(priority, pr, sep = ' ')
        if (priority > pr):
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


add_task('a',112,461)
print(pq)
add_task('a',563,429)
print(pq)
add_task('a', 111, 500)
print(pq)

# For max heap, use min heap, and multiply priorities by -1.