import re

txt = '199.72.81.55 - - [01/Jul/1995:00:00:01 -0400] "GET /history/apollo/ HTTP/1.0" 200 6245'
txt2 = 'unicomp6.unicomp.net - - [01/Jul/1995:00:00:06 -0400] "GET /shuttle/countdown/ HTTP/1.0" 200 3985'

txt = '199.72.81.55 - - [01/Jul/1995:00:00:01 -0400] "GET /shuttle/countdown/ HTTP/1.0" 200 6245'
txt2 = 'unicomp6.unicomp.net - - [01/Jul/1995:00:00:06 -0400] "GET /shuttle/countdown/ HTTP/1.0" 200 3985'

REGEXP = r'(\d+\.\d+\.\d+\.\d+|\w+\d*\.\w+\.\w+)\s+(\W+\s+\W+)\s+(\[\d+\/\w+\/\d+\:\d+\:\d+\:\d+\s\-\d+\])\s+' \
         r'(\"\w+\s+\/[\w+\/]+\s+HTTP\/1\.0\")\s+(\d+)\s+(\d+)'

REGEXP2 = r'([\d\.\w-]+)\s+-\s+-\s+(\[[\d\w\s/:-\]+\])\s+("[\w\d\./]+")\s+([\d+-]+)\s+([\d+-])'


REGEXP3 = r'([\d\w\.-]+)\s+(-\s+-)\s+(\[\d+\/\w+\/\d+\:\d+\:\d+\:\d+\s\-\d+\])\s*("[\w\s\d\./]*")\s+([\d+-]*)\s+([\d+-]*)'

REGEXP3 = r'([\d\w\.-]+)\s+(-\s+-)\s+(\[\d+\/\w+\/\d+\:\d+\:\d+\:\d+\s\-\d+\])\s*("[\w\s\d\./]*")\s+([\d+-]*)\s+([\d+-]*)'

match = re.search(REGEXP3,txt)
match2 = re.search(REGEXP3, txt2)

print(match)
print(match.group(1))
print(match.group(2))
print(match.group(3))
print(match.group(4))
print(match.group(5))
print(match.group(6))

print(match2)
print(match2.group(1))
print(match2.group(2))
print(match2.group(3))
print(match2.group(4))
print(match2.group(5))
print(match2.group(6))

'''
print(match.group(2))
print(match.group(3))
print(match.group(4))
print(match.group(5))
print(match.group(6))


print(match2)
print(match2.group(1))
print(match2.group(2))
print(match2.group(3))
print(match2.group(4))
print(match2.group(5))
print(match2.group(6))
'''