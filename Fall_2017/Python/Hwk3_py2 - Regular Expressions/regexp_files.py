#-------------------------------------------------------------------------------
# Name:        regexp_files.py
# Author:      Matt Isaac
# A#:          A01515095
#-------------------------------------------------------------------------------
#!/usr/bin/python
import re
import os

## your regular expression should be placed into this variable
TRAJ_REGEXP = r'(tj)_(nc)_(\d+)_(\w+)_(d)_(\d+)_(aa)_(-?\d+)_(ase)_(-?\d+)_(ar)_(\d+)_(st)_(\d+)_(et)_(\d+).png'


def getMatchInfo(match):
    return ((match.group(2), int(match.group(3))),
                       ("t" + match.group(5), int(match.group(6))),
                       ("pd", match.group(4)),
                       (match.group(7), int(match.group(8))),
                       (match.group(9), int(match.group(10))),
                       (match.group(11), int(match.group(12))),
                       (match.group(13), int(match.group(14))),
                       (match.group(15), int(match.group(16)))
                       )


def parseTrajFiles(dirpath):
    traj_filename = []
    if(os.path.isfile(dirpath) != True):
        dircontents = os.listdir(dirpath)
        for filename in dircontents:
            rematch = re.match(TRAJ_REGEXP, filename)
            traj_filename.append((filename, getMatchInfo(rematch)))
    return traj_filename


def getInTrajList(traj_info_list):
    return [tup for tup in traj_info_list if tup[1][2][1] == 'in']


def getOutTrajList(traj_info_list):
    return [tup for tup in traj_info_list if tup[1][2][1] == 'out']


def getFlatTrajList(traj_info_list):
    return [tup for tup in traj_info_list if tup[1][2][1] == 'flat']


def filterTDByVal(traj_info_list, td=20):
    return [tup for tup in traj_info_list if tup[1][1][1] == td]


def filterTDByValRange(traj_info_list, tdlower=1, tdupper=1):
    return [tup for tup in traj_info_list if tup[1][1][1] <= tdupper and tup[1][1][1] >= tdlower]


def filterNCByVal(traj_info_list, nc=1):
    return [tup for tup in traj_info_list if tup[1][0][1] == nc]


def filterNCByValRange(traj_info_list, nclower=1, ncupper=1):
    return [tup for tup in traj_info_list if tup[1][0][1] >= nclower & tup[1][0][1] <= ncupper]


def filterAAByVal(traj_info_list, aa=1):
    return [tup for tup in traj_info_list if tup[1][3][1] == aa]


def filterAAByValRange(traj_info_list, aalower=1, aaupper=1):
    return [tup for tup in traj_info_list if tup[1][3][1] >= aalower & tup[1][3][1] <= aaupper]


def topNC(traj_info_list, topn=10):
    sortedlist = sorted(traj_info_list, key = lambda traj: traj[1][0][1], reverse=True)
    return sortedlist[0:topn]


def topAA(traj_info_list, topn=10):
    sortedlist = sorted(traj_info_list, key=lambda traj: traj[1][3][1], reverse=True)
    return sortedlist[0:topn]


if __name__ == '__main__':
      pass


'''
#############
# TEST CASES
#############

dirname = "D:/tmp"
trajlist = (parseTrajFiles(dirname))

print getInTrajList(trajlist)
print getOutTrajList(trajlist)
print getFlatTrajList(trajlist)
print filterTDByVal(trajlist, td=1)
print filterTDByValRange(trajlist, tdlower=0, tdupper=5)
print filterNCByVal(trajlist, nc=3)
print filterNCByValRange(trajlist, nclower=1, ncupper=4)
print filterAAByVal(trajlist, aa=-45)
print filterAAByValRange(trajlist, aalower=0, aaupper=70)
print topNC(trajlist, topn=2)
print topAA(trajlist, topn=4)
'''

