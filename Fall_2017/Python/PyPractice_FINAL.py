## Python Practice - Final Exam

# Lecture 20: Intro to Machine Learning

## PDF's
## Generate random normal PDF

import numpy as np
import matplotlib.pyplot as plt

##mu = 0.5
##sigma = 0.2
##values = np.random.normal(mu, sigma, 1000)
##plt.hist(values,50)
##plt.show()
##
##from scipy.stats import norm
##
##x1 = np.arange(-3,3,0.001)
##plt.plot(x1, norm.pdf(x1))
##plt.show()

#################
## NumPy basics##
#################

a = np.array([i for i in xrange(15)])
print 'a: ', a
a2 = np.array([1,2,3,4,5])
print 'a2: ', a2

print 'a2 dimensions: ', a2.ndim # number of dimensions (rank)
print 'a2 shape: ', a2.shape # size of array in each dimension
print 'a2 size: ', a2.size # total number of elements in array
print 'ar type: ', a2.dtype # type of elements in array

# can use np.arange - similar to range/xrange.
a3 = np.arange(10) # creates array with elements 0 through 9
a4 = np.arange(10, 40, 2) #np.arange(from, to, by) doesn't include 20

# create multi-dimensional arrays
a5 = a4.reshape(5,3) #(5 rows, 3 columns)
print 'a5: \n', a5

# fill array with ones
aOnes = np.ones((2,3,5), dtype = np.int32) #np.ones(sheet, row, col)

# NOTE: for np arrays A and B, A*B is element-wise multiplication.
# A.dot(B) is matrix multiplication

# Sum over rows and cols
a5.sum(axis = 0) # sums over columns
a5.sum(axis = 1) # sums over rows

# Summary statistics
np.mean(a5) # mean
np.std(a5) # standard deviation
np.var(a5) # variance

# Boolean indexing
a6 = np.array([1,2,3,4,5,6,7,8,9,10])
print 'Bool index: ', a6 % 2 == 0 # returns an array of True/False values
print 'Results of Bool index: ', a6[a6 % 2 == 0] # returns array with values that meet condition

##################
## SciPy Basics ##
##################

import scipy as sp
import sys

# data = sp.genfromtxt(sys.argv[1], delimiter ='\t') # read tab delimited file (filename, delimiter)
# x = x[~sp.isnan(y)] # get rid of nan's
# y = y[~sp.isnan(y)]

####################
## MatPlot Basics ##
####################

# x = data[:,0] # first column
# y = data[:,1] # second column

# plt.plot(x,y) # line plot
# plt.scatter(x,y) # scatter plot
# plt.xlabel('xlab')
# plt.ylabel('ylab')
# plt.title('myTitle')
# plt.grid()
# plt.show()


# Lecture 21: Correlation, Covariance, and Curve Fitting

# np.corrcoef(x,y) # correlation coefficient (r)
# np.cov(x,y) # estimates covariance matrix

web_traffic = sp.genfromtxt('web_traffic.tsv', delimiter = '\t')
x = web_traffic[:,0]
y = web_traffic[:,1]

if sp.sum(sp.isnan(y)) > 0:
    x = x[~sp.isnan(y)]
    y = y[~sp.isnan(y)]

plt.scatter(x,y)
plt.xlabel('Time')
plt.ylabel('Hits/Hour')
plt.grid()
plt.title('Server Hits per Hour')
plt.show()

# fit a curve
# Calculate model error:
def error(f,x,y):
    return sp.sum((f(x) - y) ** 2)

# pcoeffs, error, rank, sv, rcond = sp.polyfit(x,y,pd,full = True) #pd = polynomial degree
f1 = sp.poly1d(sp.polyfit(x,y,1)) # obtain model function for plotting/evaluating
sp.linspace(0,1,2) # sp.linspace(start, stop, n) - generates an array of n values from start to stop
f2 = sp.poly1d(sp.polyfit(x,y,2)) # quadratic polyfit


plt.scatter(x,y)
plt.title('Server Hits per Hour')
plt.xlabel('Time')
plt.ylabel('Hits/Hour')
xvals = sp.linspace(0, x[-1], 1000)
plt.plot(xvals, f1(xvals), linewidth = 4, color = 'b')
plt.plot(xvals, f2(xvals), linewidth = 4, color = 'r')
plt.legend(['d=%d' % f1.order, 'd=%d' % f2.order], loc = 'upper left')
plt.show()

pcoeffs, error, rank, sv, rcond = sp.polyfit(x, y, 2, full = True)
print 'Quadratic Model Coeffs: %s' % pcoeffs

# Lecture 22 - Conditional Probability, Train and Test data sets

from numpy import random
random.seed(0)
peopleInAgeGroup = {20:0, 30:0, 40:0, 50:0}
purchasesInAgeGroup = {20:0, 30:0, 40:0, 50:0}
numOfPurchases = 0
numOfPeople = 10000

##for x in xrange(numOfPeople):
##    ageGroup = random.choice([20, 30, 40, 50, 60, 70])
##    purchaseProb = float(ageGroup) / 100
##    peopleInAgeGroup[ageGroup] += 1
##    if(random.random() < purchaseProb:
##       numOfPurchases += 1
##       purcasesInAgeGroup[ageGroup] += 1

# Lecture 23 - Classifier for Iris data set

# read in iris data
from sklearn.datasets import load_iris
iris_data = load_iris()
# iris_data.feature_names # array of strings
# iris_data.data          # array of data items (feature vectors)
# iris_data.target_names  # array of strings (names of species - classes)
# iris_data.target        # array of target numbers

def print_data_items_classified_as(data, target_name):
    target_names = data.target_names
    data_items = data.data
    target = data.target
    t = target_names.tolist().index(target_name)
    target_items = data_items[target == t]
    print 'All data items classified as', target_name
    print target_items

def get_all_feature_values_for_target(data, tn, fn):
    data_items = data.data
    target = data.target
    feature_vals = feature_vals[target == tn,fn]

def save_feature_vs_feature_plot_as_figure(png_path):
    data_items = data.data
    target_names = data.target_names
    
    sepal_length = data_items[:,0]
    sepal_width = data_items[:,1]
    petal_length = data_items[:,3]
    petal_width = data_items[:,4]

    plt.scatter(sepal_length, sepal_width)

    plt.scatter(sepal_length, petal_length)

    plt.scatter(sepal_length, petal_width)

    plt.scatter(sepal_width, petal_length)

    plt.scatter(sepal_width, petal_width)

    plt.scatter(petal_length, petal_width)

    return
   
def min_max_spec(data, tn, fn):
    data_items = data.data
    target_items = data.target
    feature_names = data.feature_names
    target_names = data.target_names

    flower_names = target_names[tn]

    petal_lengths = data_items[:,2]
    is_setosa = (flower_names == 'setosa')
    max_setosa = petal_lengths[is_setosa].max()
    min_setosa = petal_lengths[~is_setosa].min()

    print 'Max petal length for setosas: ', max_setosa
    print 'Min petal length for non-setosas: ', min_setosa

# Lecture 24 & 25 - Decision Trees

# Train/Test split
from sklearn.model_selection import train_test_split
import random

data_items = iris_data.data
target = iris_data.target

train_data, test_data, train_target, test_target = \
            train_test_split(data_items, target, test_size = 0.8, random_state=random.randint(0,1000))

# train and save the tree
clf = tree.DecisionTreeClassifier(random_state=0) # construct decision tree
dtr = clf.fit(train_data, train_target) # train decision tree
tree.export_graphviz(dtr, out_file='decisionTree.dot') # save diagram
print(sum(dt.predict(test_data) == test_target)/float(len(test_target)))

# Cross Validation

from sklearn.model_selection import cross_val_predict
cross_val = cross_val_predict(decisionTree, data_items, target, cv=10) # 10 fold
acc = sum(cross_val==target)/float(len(target))
print acc

# Confusion Matrices

from sklearn.metrics import confusion_matrix
classifier = tree.DecisionTreeClassifier(random_state = 0)
dt = classifier.fit(train_data, train_target)
test_predict = dt.predict(test_data)
cm = confusion_matrix(test_target, test_predict)

# Lecture 26 - Random Forests
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
import random
import sys

iris_data = load_iris()
iris_items = iris_data.data
iris_target = iris_data.target

def random_forest(data, target):
    train_data, test_data, train_target, test_target = \
                train_test_split(data, target)
    rf = RandomForestClassifier(n_estimators = random.randint(10,20))
    rf_fit = rf.fit(train_data, train_target)
    rf_pred = rf_fit.predict(test_target)
    cm = confusion_matrix(test_target, test_predict)
    
random_forest(iris_items, target)
