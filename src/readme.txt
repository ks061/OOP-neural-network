Names 
Logan Stiles (lts010) and Kartikeya Sharma (ks061)

Primary Resources
https://drive.google.com/file/d/1KSQ_7curVmEuemYg6AMsmNyPgsAljTjj/view (The homework PDF)
https://www.youtube.com/watch?v=QUTU4Y-bgbU&feature=youtu.be
https://en.wikipedia.org/wiki/Linear_separability
https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/
http://stevenmiller888.github.io/mind-how-to-build-a-neural-network-part-2/
Java 8 API
Geeksforgeeks.com
Old labs we completed

Configuration File Format
The first line will have the following numbers, in this order, seperated by one space: the number of inputs, the number of outputs, the number of hidden layers, the number of neurons per each hidden layer, and the highest acceptable SSE
The lines after this will be edge weights, each line represents the edge weights needed to connect one layer to the next
(i.e. the first line will be to connect layer one to layer two, the next line will connect layer two to layer three, etc.)
After the edge weights there should be a line with just the word "THETAS", typed exactly like that
Then after that line the rest of the lines will be thetas, each line representing the thetas that will be given to the neurons of a given layer
(i.e. the first line of thetas will be for the neurons in layer one, the second line of thetas will be for the neurons in layer two, etc.)