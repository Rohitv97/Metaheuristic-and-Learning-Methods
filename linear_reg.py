
"""
TODO:
# 1. Place the Advertising.csv into /data folder
# download it from http://www-bcf.usc.edu/~gareth/ISL/Advertising.csv
#
# 2. Quick Analysis and Visualization
#
# 3. Train linear regression model
#
# 4. Show the Learned estimators/coeficients
#
# 5. Model Evaluation
"""

import numpy as np
import pandas as pd
# import model
from sklearn.linear_model import LinearRegression
from sklearn.cross_validation import train_test_split
# import module to calculate model perfomance metrics
from sklearn import metrics


data_path = "dset.csv" # or load the dataset directly from the link
# # data_link = "http://www-bcf.usc.edu/~gareth/ISL/Advertising.csv"

data = pd.read_csv(data_path, index_col=0)

# create a Python list of feature names
feature_names = ['x']

# use the list to select a subset of the original DataFrame
X = data[feature_names]

# vehicle_y
y = data.y

# Splitting X and y into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y,train_size = 5, test_size=1, random_state=1)


print(len(X_train))

# Linear Regression Model
linreg = LinearRegression()

# fit the model to the training data (learn the coefficients)
linreg.fit(X_train, y_train)

# make predictions on the testing set
y_pred = linreg.predict(X_test)

print(linreg.predict(59))

# compute the RMSE of our predictions
print(np.sqrt(metrics.mean_squared_error(y_test, y_pred)))
