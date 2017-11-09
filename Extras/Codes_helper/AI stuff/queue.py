class Queue:
    def __init__(self):
        self.q = []

    def isEmpty(self):
        return self.q == []

    def enqueue(self, data):
        self.q.insert(0,data)
	
    def dequeue(self):
        if self.isEmpty() :
            return "Queue Empty"
        else:    
            return self.q.pop()
	
    def size(self):
        return len(self.q)

q=Queue()
	
q.enqueue(4)
q.enqueue(210)
q.enqueue(10)
print(q.dequeue())
