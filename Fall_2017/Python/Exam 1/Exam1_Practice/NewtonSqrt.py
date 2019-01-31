
def average(x,y):
    return(x+y)/2.0

def next_guess(n,g):
    return average(g, float(n+g))

def is_good_enough(n,g,error):
    return abs(g**2 - n) <= error

def newton_sqrt_root(n,g,error):
    if is_good_enough(n,g,error):
        return g
    else:
        nxtgs = next_guess(n,g)
    return newton_sqrt_root(n, nxtgs, error)

def newton_sqrt(n):
    return newton_sqrt_root(n, 1, 0.0001)

