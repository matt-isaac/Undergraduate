#!/usr/bin/python

###############################
# module: ttc4x4.py
# Matt Isaac
# A01515095
###############################

import random


def isWinForX(ttc_board):
    winforx = False
    col = 0
    for x in ttc_board:
        if sum([x[col] for x in ttc_board]) == 4:
            winforx = True
        if sum(x) == 4:
            winforx = True
        col = col+1
    return winforx



def isWinForO(ttc_board):
    winforo = False
    col = 0
    for x in ttc_board:
        if sum([x[col] for x in ttc_board]) == 8:
            winforo = True
        if sum(x) == 8:
            winforo = True
        col = col+1
    return winforo

def isDraw(ttc_board):
    winforx = isWinForO(ttc_board)
    winforo = isWinForO(ttc_board)
    if winforx == True & winforo == True:
        return True
    else:
        return False


def generateRandom4x4TTCBoard():
    board = \
    [
        [],
        [],
        [],
        []
    ]
    count = 0
    for r in xrange(4):
        for c in xrange(4):
            board[r][c] = random.randint(1,2)

def isValid4x4TTCBoard(board):
    for row in board:
        if sum(row) < 4:
            return False
        else:
            return True

def generateRandom4x4TTCBoards(num_boards):
    boardls = []
    for x in xrange(num_boards):
        boardls.append(generateRandom4x4TTCBoard())
    return boardls




def countWinsAndDraws(boards):
    ## your code
    pass

    



    





