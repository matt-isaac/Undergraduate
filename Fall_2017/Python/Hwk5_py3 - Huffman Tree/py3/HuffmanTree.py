#!/usr/bin/python3

######################################
## module: HuffmanTreeNode.py
## Your Name
## Your A-Number
######################################

from HuffmanTreeNode import HuffmanTreeNode
    
class HuffmanTree(object):
    def __init__(self, root=None):
        self.__root = root

    def getRoot(self):
        return self.__root

    def encodeSymbol(self, s):
        if not s in self.__root.getSymbols():
            raise Exception('Unknown symbol')
        else:
            ## your code here
	    pass

    def encodeText(self, txt):
        ## your code here
        pass
     
    def decode(self, bin_string):
        ## your code here
        pass
    
    @staticmethod
    def mergeTwoNodes(htn1, htn2):
        #print 'Merging', str(htn1), str(htn2)
        symbols = set(htn1.getSymbols())
        for i in htn2.getSymbols():
            symbols.add(i)
        n = HuffmanTreeNode(symbols=symbols, weight=htn1.getWeight() + htn2.getWeight())
        n.setLeftChild(htn1)
        n.setRightChild(htn2)
        return n

    @staticmethod
    def findTwoLowestWeightNodes(list_of_nodes):
        ## your code here
        pass

    @staticmethod
    def displayListOfNodes(list_of_nodes):
        for n in list_of_nodes:
            print str(n)

    @staticmethod
    def fromListOfHuffmanTreeNodes(list_of_nodes):
        if len(list_of_nodes) == 0:
            raise Exception('Cannot construct from empty list of nodes')
        elif len(list_of_nodes) == 1:
            return HuffmanTree(root=list_of_nodes[0])
        else:
            ## your code here
            pass

    @staticmethod
    def freqMapToListOfHuffmanTreeNodes(freq_map):
        return [HuffmanTreeNode(symbols=set([item[0]]), weight=item[1]) for item in freq_map.items()] 


    
