import math
import copy
from operator import itemgetter

def lsprint(ls):
    for l in ls:
        print(l)

def sqrtls(ls):
    for i in xrange(0, len(ls)):
        ls[i] = math.sqrt(ls[i])
    return ls

def lsremove(ls,n):
    #for l in ls:
    #    if l == n:
    #       ls.remove(n)    
    for x in xrange(0, len[ls]):
        if ls[x] == n:
            ls.remove(x)

def sqrtlsnew(ls):
    lsnew = []
    for l in ls:
        lsnew.append(math.sqrt(l))
    return lsnew

def lsremovenew(ls, x):
    copyls = copy.copy(ls)
    if x in copyls:
        copyls.remove(x)
    return copyls

def isprime(n):
    divisible = False
    for x in xrange(2,n):
        if n%x == 0:
            divisible == True
        else:
            divisible == False
    return divisible

def colsum(mat, n):
    return sum([x[n] for x in mat])

def charjoiner(begin, end, sep):
    return sep.join([str(x) for x in xrange(begin, end)])


import sys

m = sys.argv(1)
n = sys.argv(2)

def __name__ = '__main__':
    if sys.argv(1) == "iter":
        return fib_iter(n)
    else if sys.argv(1) == "recurse":
        return fib_rec(n)

def fib_rec(n):
    if n == 0 or n == 1: return n
    else:
        return fib_rec(n-2) + fibrec(n-1)

import re



def studentInfo(file_path):
    regexp = r'(\w+)\t(\w+)\tA(\d+)\t([@\w\d\.]*)'
    with open(file_path, 'r') as infile:
        return[(re.search(line, regexp)).group(1) for line in infile]
    

