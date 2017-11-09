
class Stack:
    def __init__(self):
        self.stk = []
	
    def isEmpty(self):
        return self.stk == []
	
    def push(self, data):
        self.stk.insert(0,data)
	
    def pop(self):
        if self.isEmpty():
            return "Stack empty"
        else :
            return self.stk.pop(0)
	
    def peek(self):
        return self.stk[0]
	
    def size(self):
        return len(self.stk)
	
s = Stack()
s.push(1)
s.push(2)
s.push(20)
print(s.pop())
