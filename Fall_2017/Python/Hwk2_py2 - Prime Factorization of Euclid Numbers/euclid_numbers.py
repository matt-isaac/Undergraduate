#!/usr/bin/python

#########################################
## CS 3430: F2017: HW02: Euclid Numbers
## Matt Isaac
## A01515095
#########################################

import math


def average(x, y):
    return (x + y) / 2.0


def is_good_enough(n, g, error):
    return abs(g ** 2 - n) <= error


def next_guess(n, g):
    return average(g, float(n) / g)


def newton_sqrt_approx(n, g, error):
    if is_good_enough(n, g, error):
        return g
    else:
        ng = next_guess(n, g)
    return newton_sqrt_approx(n, ng, error)


def newton_sqrt(n):
    return newton_sqrt_approx(n, 1, 0.0001)


def is_prime(n):
    '''is_prime(n) ---> True if n is prime; False otherwise.'''
    if n < 2:
        return False
    elif (n == 2):
        return True
    else:
        for d in xrange(2, int(math.sqrt(n)) + 1):
            if n % d == 0:
                return False
        return True


def next_prime_after(p):
    '''computes next prime after prime p; if p is not prime, returns None.'''
    if not is_prime(p): return None
    prime_found = False
    num_to_check = p + 1
    while prime_found == False:
        if is_prime(num_to_check):
            return num_to_check
        else:
            num_to_check += 1

def prod(factors):
    product = 1
    if len(factors) == 0:
        return "empty list"
    while len(factors) != 0:
        product = product * factors.pop()
    return product

def euclid_number(i):
    '''euclid_number(i) --> i-th Euclid number.'''
    if i < 0: return None
    x = []
    for counts in xrange(0, i+1):
        if(counts == 0):
            prime = 2
            x.append(prime)
        else:
            prime = next_prime_after(prime)
            x.append(prime)
    return prod(x) + 1


def compute_first_n_eucs(n):
    '''returns a list of the first n euclid numbers.'''
    eucs = []
    for counts in xrange(0, n):
        eucs.append(euclid_number(counts))
    return eucs


def prime_factors_of(n):
    '''returns a list of prime factors of n if n > 1 and [] otherwise.'''
    if n < 2: return []
    factors = []
    complete = False
    prime = 2
    remainder = n
    while not complete:
        if is_prime(remainder):
            factors.append(remainder)
            complete = True
        elif remainder % prime == 0:
            factors.append(prime)
            remainder = remainder / prime
        else:
            prime = next_prime_after(prime)
    return factors


def tabulate_euc_factors(n):
    '''returns a list of 3-tuples (i, euc, factors).'''
    euc_factors = []
    first_n = compute_first_n_eucs(n + 1)
    count = 0
    for i in first_n:
        euc_factors.append((count, i, prime_factors_of(i)))
        count = count + 1
    return euc_factors


print tabulate_euc_factors(11)