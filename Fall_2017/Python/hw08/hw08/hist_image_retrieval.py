#!/usr/bin/python

import argparse
import cv2
import sys
import os
import cPickle as pickle
from matplotlib import pyplot as plt
from os.path import basename

################################
# module: hist_image_retrieval.py
# author:  your name and A-Number
# description: persistent image retriever
# to run:
# $python hist_image_retrieval.py -ip food_test/img01.JPG -clr rgb -hist rgb_hist8.pck -bin 8 -sim correl
#
# the output should print the matches for the top 3 images and display the input image
# and the top 3 matches in 4 matplotlib figures.
# images/123461762.JPG --> 0.966425720752
# images/123476552.JPG --> 0.934419667638
# images/123465049.JPG --> 0.903088250132
# match 1 images/123461762.JPG
# match 2 images/123476552.JPG
# match 3 images/123465049.JPG
#################################

ap = argparse.ArgumentParser()
ap.add_argument('-ip', '--imgpath', required = True, help = 'image path')
ap.add_argument('-hist', '--hist', required = True, help = 'hist index file')
ap.add_argument('-bin', '--bin', required = True, help = 'hist bin size')
ap.add_argument('-sim', '--sim', required = True, help = 'hist similarity')
ap.add_argument('-clr', '--clr', required = True, help = 'color space: rgb/hsv')
args = vars(ap.parse_args())

inimg = cv2.imread(args['imgpath'])
bin_size = int(args['bin'])
# compute the histogram of inimg and save it in inhist
inhist = None
# normalize and flatten the inhist into a feature vector
inhist_vec = None

# get the similarity metric string from the command line parameter.
hist_sim = args['sim']

HIST_INDEX = None

def hist_correl_sim(norm_hist1, norm_hist2):
  # compute correlation similarity b/w normalized and flattened histograms
  pass

def hist_chisqr_sim(norm_hist1, norm_hist2):
  # compute chi square similarity b/w normalized and flattened histograms
  pass

def hist_intersect_sim(norm_hist1, norm_hist2):
  # compute intersection similarity b/w normalized and flattened histograms
  pass

def hist_bhatta_sim(norm_hist1, norm_hist2):
  # compute bhattacharyya similarity b/w normalized and flattened histograms
  pass

# compute the topn matches using the value saved in hist_sim above.
def compute_hist_sim(inhist_vec, hist_index, topn=3):
  # your code
  pass

def show_images(input_image, match_list):
  # show 4 images in matplotlib figures
  pass
 
if __name__ == '__main__':
  with open(args['hist'], 'rb') as histfile:
    HIST_INDEX = pickle.load(histfile)
  sim_list = compute_hist_sim(inhist_vec, HIST_INDEX)
  for imagepath, sim in sim_list:
    print(imagepath + ' --> ' + str(sim))
  show_images(inimg, sim_list)


