#!/usr/bin/python

############ Problem 1 ###################
## module: process_temps.py
## Matt Isaac
## A01515095

'''
1) Pipeline:
more data/small_temp0.txt | python sort_temps.py dsc | python top_n.py 1

2) Pipeline:
more data/small_temp0.txt | python sort_temps.py asc | python top_n.py

'''
##########################################

from datetime import datetime
import re
import sys



## Put your regular expression into this variable
TEMP_REGEXP = r'(\d+)-(\d+)-(\d+)\s*(\d+):(\d+):(\d+)\s*([\d\.]+)'

## Code for debugging
#txt = '2017-05-02 16:21:11 26.875'
#print(re.search(TEMP_REGEXP, txt))

def comprehendDatetimeTempToops():
    tstmp = []
    for line in sys.stdin.readlines():
        match = re.search(TEMP_REGEXP, line)
        year = match.group(1)
        month = match.group(2)
        day = match.group(3)
        hour = match.group(4)
        minutes = match.group(5)
        seconds = match.group(6)
        ts = datetime.datetime(year, month, day, hour, minutes, seconds)
        tstmp.append(ts)

    sys.stdout.write([(timestamp, re.search(TEMP_REGEXP, line).group(7)) for timestamp in tstmp for line in sys.stdin.readlines()])
    
def writeDatetimeTempToops(toops):
    for line in sys.stdin.readlines():
        sys.stdout.write(str(line[0]) + '\t' + str(line[1]))

if __name__ == '__main__':
    toops = comprehendDatetimeTempToops()
    writeDatetimeTempToops(toops)





            

            
