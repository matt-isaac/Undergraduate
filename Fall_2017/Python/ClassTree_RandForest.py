from sklearn.datasets import load_iris
from sklearn.metrics import confusion_matrix
from sklearn import tree
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
import random

digits_data = load_iris()
predictors = digits_data.data
target = digits_data.target

## Split Data
train_data, test_data, train_target, test_target = \
            train_test_split(predictors, target, test_size=0.8, random_state = random.randint(0,1000))

## Classification Tree
ctr = tree.DecisionTreeClassifier(random_state=random.randint(0,1000))
ctr_trained = ctr.fit(train_data, train_target)
ctr_accuracy = sum(ctr_trained.predict(test_data) == test_target) / float(len(target))
print'Classification Tree Accuracy: ', ctr_accuracy
ctr_predict_target = ctr_trained.predict(test_data)
ctr_cm = confusion_matrix(test_target, ctr_predict_target)
print 'Ctr Confusion Matrix:\n', ctr_cm


## Random Forest
rf = RandomForestClassifier(random_state=random.randint(0,1000));
rf_trained = rf.fit(train_data, train_target)
rf_accuracy = sum(rf_trained.predict(test_data) == test_target) / float(len(target))
print 'Random Forest Accuracy: ', rf_accuracy
rf_predict_target = rf_trained.predict(test_data)
rf_cm = confusion_matrix(test_target, rf_predict_target)
print 'Rf Confusion Matrix:\n', rf_cm
