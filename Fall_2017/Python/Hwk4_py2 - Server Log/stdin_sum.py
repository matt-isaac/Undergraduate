#!/usr/bin/python
## print sum of integers in STDIN
import sys
sys.stdout.write(str(sum([int(x) for x in sys.stdin.readlines()])) + '\n')
sys.stdout.flush()


