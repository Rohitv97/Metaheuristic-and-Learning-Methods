from pandas import read_csv
import pandas as pd
from pandas import datetime
# from matplotlib import pyplot
from statsmodels.tsa.arima_model import ARIMA
from sklearn.metrics import mean_squared_error
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf

file_path = "ds.csv"
data = pd.DataFrame.from_csv('dset.csv')

num = data['x'].count()
X = []
i = 0
while i < 6:
    X.append(data.iloc[i,1])
    i += 1

# Split data for trainin and test 971 is number of data from previous year before 2015
# size = int(971)
train, test = X[0:5], X[5:]
history = [x for x in train]
# Predictions loop for each data in 2015
predictions = list()
for t in range(len(test)):
  # ARIMA hyperparameter configurations
	model = ARIMA(history, order=(0,1,1))
  # Train the model
	model_fit = model.fit(disp=0)
  # Forecast
	output = model_fit.forecast()
	yhat = output[0]
	predictions.append(yhat)
	obs = test[t]
	history.append(obs)
	print('predicted=%f, expected=%f' % (yhat, obs))
# Error calculation by using MSE
error = mean_squared_error(test, predictions)
print('Test MSE: %.3f' % error)
# Plot the result
# pyplot.plot(test)
# pyplot.plot(predictions, color='red')
# pyplot.show()
