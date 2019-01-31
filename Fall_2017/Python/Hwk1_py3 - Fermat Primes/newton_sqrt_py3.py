#-------------------------------------------------------------------------------
# Name:        newton_sqrt_py3.py
# Author:      Matt Isaac
# A#:          A01515095
#-------------------------------------------------------------------------------
def average(x,y):
    return (x+y)/2.0

def is_good_enough(n, g, error):
    return abs(g**2 - n) <= error

def next_guess(n, g):
    return average(g, n/g)

def newton_sqrt_approx(n,g,error):
    if is_good_enough(n,g,error):
        return g
    else:
        ng = next_guess(n,g)
    return newton_sqrt_approx(n,ng,error)

def newton_sqrt(n):
    return newton_sqrt_approx(n,1,0.0001)