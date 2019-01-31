#!/usr/bin/python
import sys

###############################
# module: sort_temps.py
# Your Name
# Your A-Number
###############################

n = int(sys.argv[1])

i = 0
for line in sys.stdin.readlines():
    if i < n:
        sys.stdout.write(line)
    else:
        break
        
        
