import copy

initial = [3, 3, 'L']
final = [0, 0, 'R']


visited = set()
queue = []
visited.add(copy.deepcopy(initial))
queue.insert([copy.deepcopy(initial), None])


def add_chk(s, x):
    return len(s) != (s.add(x) or len(s))

def bfs_soln():
    while queue != []
        [st, parent] = queue.pop()
        if ( st[2]=='R'):
            missi = st[0]
            canni = st[1]
            # 2M 0C
            if missi > 2:
                missi = missi - 2
                if is_safe(missi, canni, 'R'):
                    queue.insert(0, [[missi, canni, 'L'], copy.deepcopy(st)])
            if canni > 2:
                canni = canni - 2

        else:
            missi = 3 - st[0]
            canni = 3 - st[1]
