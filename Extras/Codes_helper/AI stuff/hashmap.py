class HashMap:
    def __init__(self):
        self.hashMap = {}
        
    def insert(self,key,value):
        self.hashMap[key] = value
        
    def get(self,key):
        return self.hashMap.get(key,"Not Present")
           
    def delete(self,key):
        del self.hashMap[key]

hm = HashMap()

hm.insert(1,'a')
hm.insert(2,'b')
print(hm.get(2))
hm.delete(2)
print(hm.get(2))
