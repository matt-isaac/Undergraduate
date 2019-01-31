#-------------------------------------------------------------------------------
# Name:        fermat_primes_py2.py
# Author:      Matt Isaac
# A#:          A01515095

#-------------------------------------------------------------------------------
from newton_sqrt_py2 import newton_sqrt
import math
import random

def expmod(b, e, m):
    if e == 0:
        return 1;
    elif(e % 2 == 0):
        x = expmod(b, e/2, m)
        return (x*x) % m
    else:
        return((b*expmod(b,e-1,m)) % m)

def fermat_test(n):
    if(n == 1):
        return False
    if(n < 4):
        return True
    a = random.choice(xrange(2, n-1, 1))
    return expmod(a, n, n) == a

def is_fermat_prime(n, num_times):
    if(num_times == 0):
        return True
    elif(fermat_test(n)):
        return is_fermat_prime(n, num_times - 1)
    else:
        return False

def is_prime(n):
    if n < 2:
        return False
    elif (n == 2):
        return True
    else:
        for d in xrange(2, int(newton_sqrt(n)) + 1):
            if n % d == 0:
                return False
        return True


def sum_of_fermat_primes(lower, upper, num_times):
    prime_sum = 0
    for i in xrange(lower, upper, 1):
        if(is_fermat_prime(i, num_times)):
            prime_sum += i
    return prime_sum


def sum_of_primes(lower, upper):
    prime_sum = 0
    for i in xrange(lower, upper, 1):
        if(is_prime(i)):
            prime_sum += i
    return prime_sum


print sum_of_primes(0,500)
print sum_of_fermat_primes(0, 500, 300)















