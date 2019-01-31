#!/usr/bin/python

import sys
import re

REGEXP = r'(\d+\.\d+\.\d+\.\d+|\w+\d*\.\w+\.\w+)\s+(\W+\s+\W+)\s+(\[\d+\/\w+\/\d+\:\d+\:\d+\:\d+\s\-\d+\])\s+' \
         r'(\"\w+\s+\/[\w+\/]+\s+HTTP\/1\.0\")\s+(\d+)\s+(\d+)'

statuscode = sys.argv[1]
sys.stdout.write([line for  line in sys.stdin if re.search(REGEXP, line).group(6) == statuscode])