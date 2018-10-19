Names 
Logan Stiles (lts010) and Kartikeya Sharma (ks061)

Primary Resources:
https://drive.google.com/file/d/1KSQ_7curVmEuemYg6AMsmNyPgsAljTjj/view (the homework PDF)
https://www.youtube.com/watch?v=QUTU4Y-bgbU&feature=youtu.be
https://en.wikipedia.org/wiki/Linear_separability
https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/
http://stevenmiller888.github.io/mind-how-to-build-a-neural-network-part-2/
Java 8 API
Geeksforgeeks.com
Old labs we completed
https://stackoverflow.com/questions/10433214/file-extension-for-a-serialized-object
https://www.tutorialspoint.com/java/java_serialization.htm
https://en.wikipedia.org/wiki/Strategy_pattern
https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html
https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html
https://docs.oracle.com/javase/8/docs/api/java/lang/System.html
https://towardsdatascience.com/activation-functions-neural-networks-1cbd9f8d91d6
https://en.wikipedia.org/wiki/Activation_function
https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm
https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/

Configuration File Format:
The first line will have the following numbers, in this order, separated by one 
space: the number of input neurons, the number of output neurons, the number of 
hidden layers, the number of neurons in each hidden layer, the highest 
acceptable SSE (sum of squared errors), and the maximum number of epochs. 
The lines after this will be edge weights, each line represents the edge 
weights needed to connect one layer to the next (i.e. the first line will 
be to connect layer one to layer two, the next line will connect layer two to
layer three, etc.) After the edge weights, there should be a line with just the word 
"THETAS," typed exactly like that without the double quotes. After this line, the 
remainder of the lines will represent theta values, each line representing the 
thetas that will be given to the neurons of a given layer
(i.e. the first line of thetas will be for the neurons in the first hidden layer layer, 
the second line of thetas will be for the neurons in the second hidden layer, etc.).
Note that the input layer does not have theta values; simply start listing the 
theta values for the first hidden layer (if no hidden layer exists, for the output layer).

Configuration File Template:
<number of input neurons> <number of output neurons> <number of hidden layers> <number of neurons in each hidden layer> <highest acceptable SSE> <maximum number of epochs>
<edge weight connecting from neuron 1 in input layer to neuron 1 in first hidden layer> <edge weight connecting from neuron 1 in input layer to neuron 2 in first hidden layer> <etc.> <edge weight connecting from neuron 2 in input layer to neuron 1 in first hidden layer> <edge weight connecting from neuron 2 in input layer to neuron 2 in first hidden layer> <etc.>
<edge weight connecting from neuron 1 in first hidden layer to neuron 1 in second hidden layer> <edge weight connecting from neuron 1 in first hidden layer to neuron 2 in second hidden layer> <etc.> <edge weight connecting from neuron 2 in first hidden layer to neuron 1 in second hidden layer> <edge weight connecting from neuron 2 in first hidden layer to neuron 2 in second hidden layer> <etc.>
<etc.>
THETAS
<theta of neuron 1 in first hidden layer> <theta of neuron 2 in first hidden layer> <etc.>
<theta of neuron 1 in second hidden layer> <theta of neuron 2 in second hidden layer> <etc.>
<etc.>

Other Notes:
- Because the homework explicitly says to keep the readme.txt here, we update the same
readme.txt for subsequent homeworks, as it appears that the readme is shared across all
homework.
- The Logger has static fields and methods; this design is intentional. The 
default constructor is overridden to be private so that it is private. The assumption 
here is that there will only be one Logger instance per process.
- The ConfigObject has a long parameter list, and it is intentional. This keeps the long
parameter list away from the NeuralNet, so a clean encapsulated data structure can be
passed into the NeuralNet instance.
- We interpreted "ANN-Name" at the bottom of pg. 5 of HW2 to be "ANN-<name of 
contributors>"
- We interpreted "Input values can be any number between 0 <= x <= 1 only." at
the bottom of pg. 4 of HW2 to truly mean throwing out any input sets that have values
that do not fall within the range [0,1].