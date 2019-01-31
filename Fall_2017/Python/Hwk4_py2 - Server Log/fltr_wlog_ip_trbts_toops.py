#!/usr/bin/python

import sys
import re

REGEXP = r'(\d+\.\d+\.\d+\.\d+|\w+\d*\.\w+\.\w+)\s+(\W+\s+\W+)\s+(\[\d+\/\w+\/\d+\:\d+\:\d+\:\d+\s\-\d+\])\s+' \
         r'(\"\w+\s+\/[\w+\/]+\s+HTTP\/1\.0\")\s+(\d+)\s+(\d+)'

for line in sys.stdin.readlines():
    match = re.search(REGEXP, line)
    toop = (match.group(1), match.group(6))
    sys.stdout(toop)

                     


