#!/usr/bin/python

###############################
# module: sort_temps.py
# Your Name
# Your A-Number
###############################

import re
import sys
from operator import itemgetter

## Put your regular expression into this variable.
DATETIME_REGEXP = r'([\d-]+)\s*([:\d]+)\s*([\d\.]+)'

def writeDateTimeTempToops(toops):
    for tupe in toops:
        sys.stdout.write(str(tupe[0]) + '\t' + str(tupe[1] + '\t' + str[2]))

def sortDateTimeTempToops(sort_type):
    tupes = []
    if sort_type == 'asc':
        for line in sys.stdin.readlines():
            match = re.search(DATETIME_REGEXP, line)
            tupes.append((match.group(1), match.group(2), match.group(3)))
        sort_tupes = sorted(tupes, key = itemgetter(2))
        return sort_tupes

    elif sort_type == 'dsc':
        for line in sys.stdin.readlines():
            match = re.search(DATETIME_REGEXP, line)
            tupes.append((match.group(1), match.group(2), match.group(3)))
        sort_tupes = sorted(tupes, key = itemgetter(2), reverse = True)
        return sort_tupes

    else:
        return("Invalid sort type")

if __name__ == '__main__':
    toops = sortDateTimeTempToops(sys.argv[1])
    writeDateTimeTempToops(toops)




            

            
