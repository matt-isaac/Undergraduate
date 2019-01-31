#!/usr/bin/python

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
            if s in self.__root.leftChild:
                # add a 0
                HuffmanTree.encodeSymbol(HuffmanTreeNode.getLeftChild(self))
                pass
            else:
                # add a 1.
                HuffmanTree.encodeSymbol(HuffmanTreeNode.getRightChild(self))
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
        sorted_list = sorted(list_of_nodes, key = HuffmanTreeNode.getWeight(), reverse = True)
        return sorted_list[0], sorted_list[1]

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
            two_lowest = HuffmanTree.findTwoLowestWeightNodes(list_of_nodes)
            HuffmanTree.mergeTwoNodes(two_lowest[0], two_lowest[1])
            return HuffmanTree.fromListOfHuffmanTreeNodes(list_of_nodes)

    @staticmethod
    def freqMapToListOfHuffmanTreeNodes(freq_map):
        return [HuffmanTreeNode(symbols=set([item[0]]), weight=item[1]) for item in freq_map.items()] 


    
