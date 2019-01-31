#!/usr/bin/python3
import sys
import re
n = int(sys.argv[1])
count = 0
for line in sys.stdin.readlines():
    if count < n:
        sys.stdout.write(line)
        count += 1
    else:
        break
sys.stdout.flush()

        
        
