# Python program to implement a
# single neuron neural network

# import all necessery libraries
from numpy import exp, array, random, dot, tanh
import math

# Class to create a neural
# network with single neuron
class NeuralNetwork():

    def __init__(self):

        # Using seed to make sure it'll
        # generate same weights in every run
        random.seed(1)

        # 3x1 Weight matrix
        self.weight_matrix = 2 * random.random((1, 1)) - 1

    # tanh as activation fucntion
    def tanh(self, x):
        return tanh(x)
        # return (1/(1+exp(-x)))

    # derivative of tanh function.
    # Needed to calculate the gradients.
    def tanh_derivative(self, x):
        return 1.0 - tanh(x) ** 2
        # return (x*(1-x))

    # forward propagation
    def forward_propagation(self, inputs):
        return self.tanh(dot(inputs, self.weight_matrix))

    # training the neural network.
    def train(self, train_inputs, train_outputs,
                            num_train_iterations):

        # Number of iterations we want to
        # perform for this set of input.
        for iteration in range(num_train_iterations):
            output = self.forward_propagation(train_inputs)

            # Calculate the error in the output.
            error = train_outputs - output

            # multiply the error by input and then
            # by gradient of tanh funtion to calculate
            # the adjustment needs to be made in weights
            adjustment = dot(train_inputs.T, error *
                             self.tanh_derivative(output))

            # Adjust the weight matrix
            self.weight_matrix += adjustment

# Driver Code
if __name__ == "__main__":

    neural_network = NeuralNetwork()

    print ('Random weights at the start of training')
    print (neural_network.weight_matrix)

    train_inputs = array([[1849.76],[1850.59],[1851.95],[1853.84],[1856.26],[1859.45],[1860.54],[1860.7],[1861.52],[1862.04]])
    train_outputs = array([[517.45,514.68,510.18,503.92,495.93,485.36,411.2,412.18,416.26,471.84]]).T

    neural_network.train(train_inputs, train_outputs, 10)

    print ('New weights after training')
    print (neural_network.weight_matrix)

    # Test the neural network with a new situation.
    print ("Testing network on new examples ->")
    print (neural_network.forward_propagation(array([1856.2])))
