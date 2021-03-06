Names 
Logan Stiles (lts010) and Kartikeya Sharma (ks061)

Primary Resources
https://drive.google.com/file/d/1KSQ_7curVmEuemYg6AMsmNyPgsAljTjj/view (the homework PDF)
https://www.youtube.com/watch?v=QUTU4Y-bgbU&feature=youtu.be
https://en.wikipedia.org/wiki/Linear_separability
https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/
http://stevenmiller888.github.io/mind-how-to-build-a-neural-network-part-2/
Java 8 API
Geeksforgeeks.com
Old labs we completed

Configuration File Format
The first line will have the following numbers, in this order, separated by one 
space: the number of input neurons, the number of output neurons, the number of 
hidden layers, the number of neurons in each hidden layer, and the highest 
acceptable SSE (sum of squared errors). The lines after this will be edge 
weights, each line represents the edge weights needed to connect one layer to 
the next (i.e. the first line will be to connect layer one to layer two, 
the next line will connect layer two to layer three, etc.)
After the edge weights, there should be a line with just the word "THETAS," 
typed exactly like that without the double quotes. After this line, the 
remainder of the lines will represent theta values, each line representing the 
thetas that will be given to the neurons of a given layer
(i.e. the first line of thetas will be for the neurons in the first hidden layer layer, 
the second line of thetas will be for the neurons in the second hidden layer, etc.).
Note that the input layer does not have theta values; simply start listing the 
theta values for the first hidden layer (if no hidden layer exists, for the output layer).

Configuration File Template:
<number of input neurons> <number of output neurons> <number of hidden layers> <number of neurons in each hidden layer> <highest acceptable SSE>
<edge weight connecting from neuron 1 in input layer to neuron 1 in first hidden layer> <edge weight connecting from neuron 1 in input layer to neuron 2 in first hidden layer> <etc.> <edge weight connecting from neuron 2 in input layer to neuron 1 in first hidden layer> <edge weight connecting from neuron 2 in input layer to neuron 2 in first hidden layer> <etc.>
<edge weight connecting from neuron 1 in first hidden layer to neuron 1 in second hidden layer> <edge weight connecting from neuron 1 in first hidden layer to neuron 2 in second hidden layer> <etc.> <edge weight connecting from neuron 2 in first hidden layer to neuron 1 in second hidden layer> <edge weight connecting from neuron 2 in first hidden layer to neuron 2 in second hidden layer> <etc.>
<etc.>
THETAS
<theta of neuron 1 in first hidden layer> <theta of neuron 2 in first hidden layer> <etc.>
<theta of neuron 1 in second hidden layer> <theta of neuron 2 in second hidden layer> <etc.>
<etc.>