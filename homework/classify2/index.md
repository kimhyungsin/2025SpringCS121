---
title: "Homework 5: Image Classification, Part 2"
layout: default
---

# {{ page.title }}

As part of this homework, you will implement perceptron learners. The goal of this homework is to familiarize you with programming in Java. You will practice:

- Applying object-oriented programming concepts
- Implementing machine learning algorithms

## Instructions

### Task 1: Reading

- Read Algorithms, chapter 1.2, "Data Abstraction" to learn the basics of object-oriented programming.
- Read the information about machine learning below.

#### Background

A "perceptron" is a simplified model of a biological neuron. It is a function that takes a vector $$x = x_0, x_1,\ldots , x_{n-1}$$ of $$n$$ real numbers as input and outputs (or predicts) either +1 or -1. A perceptron is characterized by a vector $$w = w_0, w_1, \ldots , w_{n-1}$$ of $$n$$ real numbers known as the weight vector. The perceptron computes the weighted sum:

$$ S = w_0 \times x_0 + w_1 \times x_1 + \ldots + w_{n-1} \times x_{n-1} $$

and outputs the sign of that number.

{% include image.html src="sum.png" alt="A weight sum of the input vector results in a prediction" %}

A perceptron addresses the binary classification problem, in which we seek to classify input vectors into one of two classes. By convention, we use the labels +1 (positive) and -1 (negative) to denote the two classes. For the handwritten digit application, a perceptron will be trained to predict +1 for images that correspond to a target digit; and -1 otherwise. The input vector x holds the grayscale values of each pixel in an image; the weight vector w is pre-computed by a process described later.

A single perceptron can be used for the *binary* classification problem. For a *multiclass* classification problem with m classes, we create an array of m perceptrons, each solving its own binary classification problem. For our handwritten digit application, each perceptron solves a binary classification problem of the form "*does the image correspond to the digit 6?*"

{% include image.html src="multi.png" alt="Multiple perceptrons are needed in the multiclass classification problem" %}

We train each perceptron independently and make predictions by distilling the results from the m perceptrons, following a process we describe later.

#### Approach

How do we determine the values of the weight vector w so that the perceptron makes accurate predictions? The core idea is to use the training data of known
input-output pairs to incrementally refine the weights. Specifically, we initialize the weights to 0 and then process the labeled inputs one at a time. When we process a labeled input, there are three possibilities:

1. *Correct prediction:* the perceptron predicts the correct label (+1 or -1) for the given input vector x . In this case, we leave w unchanged.
2. *False positive:* the given input vector x is labeled -1 but the perceptron predicts +1. In this case, we adjust w as follows:
  - for each $$i$$ : $$w^{'}_{i} = w_{i} - x_{i}$$
3. *False negative*: the given input vector x is labeled +1 but the perceptron predicts -1. In this case, we adjust w as follows:
  - for each $$i$$ : $$w^{'}_{i} = w_{i} + x_{i}$$

The perceptron algorithm is one of the earliest and simplest examples of a supervised learning algorithm.

Here is an example trace of the perceptron algorithm using 4 labeled inputs, each of length n = 3:

{% include image.html src="update.png" alt="Updates to the perception weights based on incorrect predictions" %}

In a multiclass classification problem, we train multiple perceptrons independently and make predictions by distilling the results from the m perceptrons.

- *Multiclass training:* initialize the weight vector of each of the m perceptrons to be zero and process the labeled training inputs one at a time. To train the perceptrons on an input vector x with multi-class label j (0 to m - 1):
  - Train perceptron j on input vector x with the label = +1
  - Train the other m - 1 perceptrons on input vector x with the label = -1
  - That is, when training perceptron j , we treat an input vector labeled j as a positive example and an input vector with any other label as a negative example.
- *Multiclass prediction:* to make a prediction for an input vector x, compute the weighted sum for each of the m perceptrons on that input. The multiclass prediction is the index of the perceptron with the largest weighted sum. Intuitively, each perceptron with a positive weighted sum predicts that x is a positive example for its class; the perceptron with the largest weighted sum makes that prediction with the most intensity, so we use that perceptron for the prediction.

This *one-vs-all* strategy decomposes a multiclass classification task into m binary classification tasks. In computer science, this decomposition is known as a *reduction*; this particular kind of reduction is used all over machine learning.

Here is an example of a multi-perceptron with m = 2 classes and n = 3.

{% include image.html src="multipredict.png" alt="Updates to the perception weights based on incorrect predictions, for the multiclass problem" %}


### Task 2: Back Up MultiPerceptron.class

In the first part of this assignment, you used our implementation of `MultiPerceptron` which we provided as a pre-compiled `MultiPerceptron.class` file. For the second part of this assignment, we will continue to work in the same project folder.

As part of this assignment, you will be implementing `MultiPerceptron.java` yourself. However, when you compile this .java file, our `MultiPerceptron.class` file will be erased! Make a copy of `MultiPerceptron.class` somewhere else on your computer, so you will not lose it. This will be helpful when you get to Task 5, Integration Testing.

### Task 3: Implement Perceptron.java

This is an **individual** assignment.

Create a data type that represents a perceptron by implementing the following API:

```
public class Perceptron {

    // Creates a perceptron with n inputs.
    public Perceptron(int n)

    // Returns the number of inputs n.
    public int numberOfInputs()

    // Returns the weighted sum of the weight vector and x.
    public double weightedSum(double[] x)

    // Predict the label (+1 or -1) of input x.
    public int predict(double[] x)

    // Trains this perceptron on the labeled (+1 or -1) input x.
    public void train(double[] x, int label)

    // Returns a string representation of this perceptron.
    public String toString()

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args)
}
```

We recommend representing a `Perceptron` using two instance variables: an integer `n` and an array of double-precision floating-point numbers `weights[]`.

Here is some more information about the required behavior:
- `Perceptron(int n)` The constructor creates an array of n weights and initializes them to 0. Assume `n >= 1`.
- `double weightedSum(double[] x)` The weighted sum of the input vector x is:
$$ w_0 \times x_0 + w_1 \times x_1 + \ldots + w_{n-1} \times x_{n-1} $$
- `int predict(double[] x)` Returns +1 if the weighted sum is positive and -1 if it is negative (or zero).
- `void train(double[] x, int label)` Checks whether the perceptron correctly classifies the labeled input x and updates the weight vector accordingly. Assume that the argument label is either +1 or -1.
- `String toString()` Returns a string representation of the weight vector, with the individual weights separated by commas and enclosed in parentheses. For example: `(2.0, 1.0, -1.0, 5.0, 3.0)`
- *Corner cases:* You may assume that the arguments to the constructor and instance methods are valid. For example, you may assume that the label is either +1 or -1, the input vector x is of length n, and `n >= 1`.

#### Code Style

For both `Perceptron.java` and the upcoming `MultiPerceptron.java`, you must:

- Provide a comment describing the purpose of every method, using the names of the argument variables in your description.
- Comment all instance variables.
- Write code that conforms to the specified APIs. If a method has a different signature or does not behave as specified, you will lose a substantial number of points. Producing undocumented side effects (such as printing to standard output in any of the constructor or instance methods) is also an API violation. You may not add public methods to the API; however, you may add *private* methods (which are accessible only in the class in which they are declared).

#### Hints

- First, implement the `Perceptron` constructor `Perceptron(int n)`, and the method `int numberOfInputs()`.
  - Test by writing code in the main method that instantiates a few `Perceptron` objects and prints the number of inputs for each object.
- Next, implement the `String toString()` method.
  - Test by writing code in the main method to print the various `Perceptron` objects. What should the output be for a newly instantiated `Perceptron` object?
- Next, implement the `double weightedSum(double[] x)` method.
  - Test by writing code in the main method to print the result of invoking the `weightedSum` method on the various `Perceptron` objects (using, of course, appropriately sized arrays).
- Next, implement the `int predict(double[] x)` method.
- Finally, implement the `void train(double[] x, int label)` method. Note that `train` should call `predict`.
- Test everything by using the code in the Testing section below and by submitting to Gradescope. Do not move onto `MultiPerceptron` until `Perceptron` is working properly.

#### Testing

This code trains a `Perceptron` using four input vectors of length three. This code can be run from your main method.

```
double[] training1 = {  5.0, -4.0,  3.0 };  // yes
double[] training2 = {  2.0,  3.0, -2.0 };  // no
double[] training3 = {  4.0,  3.0,  2.0 };  // yes
double[] training4 = { -6.0, -5.0,  7.0 };  // no

int n = 3;
Perceptron perceptron = new Perceptron(n);
StdOut.println(perceptron);
perceptron.train(training1, +1);
StdOut.println(perceptron);
perceptron.train(training2, -1);
StdOut.println(perceptron);
perceptron.train(training3, +1);
StdOut.println(perceptron);
perceptron.train(training4, -1);
StdOut.println(perceptron);
```

If your code is correct, it should print:

```
(0.0, 0.0, 0.0)
(5.0, -4.0, 3.0)
(5.0, -4.0, 3.0)
(5.0, -4.0, 3.0)
(11.0, 1.0, -4.0)
```

### Task 4: Implement MultiPerceptron.java

Create a data type that represents a perceptron by implementing the following API:

```
public class MultiPerceptron {

    // Creates a multi-perceptron object with m classes and n inputs.
    public MultiPerceptron(int m, int n)

    // Returns the number of classes m.
    public int numberOfClasses()

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs()

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x)

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label)

    // Returns a string representation of this MultiPerceptron.
    public String toString()

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args)
}
```

We recommend representing a `MultiPerceptron` using three instance variables: an integer `m` (for the number of classes), an integer `n` (for the number of inputs), and an array of Perceptron objects `perceptrons[]`.


Here is some more information about the required behavior:

- `MultiPerceptron(int m, int n)` The constructor creates an array of m perceptrons, each with n inputs and all weights initialized to 0. Assume m >= 1 and n >= 1.
- `String toString()` Returns a string representation of the perceptrons, with the string representations of the perceptrons separated by commas and enclosed in parentheses. Here is an example with m = 4 and n = 3: `((-10.0, -8.0, 5.0), (3.0, 4.0, 5.0), (6.0, 4.0, -2.0), (1.0, 9.0, -10.0))`
- `int predictMulti(double[] x)` Return the label corresponding to the perceptron with the greatest weighted sum (between 0 and m - 1). If two (or more) perceptrons tie for the largest weighted sum, return the index of any such perceptron.
- `void trainMulti(double[] x, int label)` Refer to the description of "Multiclass training" above. A call to this method should result in calls to each perceptron's `train` method.
- *Corner cases:* You may assume that the arguments to the constructor and instance methods are valid. For example, you may assume that the argument label is an integer between 0 and m - 1, `x[]` is an array of length n, and m >= 1 and n >= 1.

#### Hints

- First, implement the `MultiPerceptron` constructor, `MultiPerceptron(int m, int n)`, and the methods, `int numberOfClasses()` and `int numberOfInputs()`.
  - Test by writing code in the main method that instantiates a few `MultiPerceptron` objects and prints the number of classes and inputs for each object.
- Next, implement the `String toString()` method.
  - Test by writing code in the main method to print the various `MultiPerceptron` objects. What should the output be for a newly instantiated `MultiPerceptron` object?
- Next, implement the `int predictMulti(double[] x)` method.
  - When trying to find the `Perceptron` with the greatest weighted sum, note that weighted sums can be negative.
- Next, implement the `void trainMulti(double[] x, int label)` method.
- Test everything by using the code in the Testing section (below) and by submitting to Gradescope.

#### Testing

This code trains a `MultiPerceptron` using four input vectors of length three. This code can be run from your main method.

```
int m = 2;
int n = 3;

double[] training1 = {  5.0, -4.0,  3.0 };
double[] training2 = {  2.0,  3.0, -2.0 };
double[] training3 = {  4.0,  3.0,  2.0 };
double[] training4 = { -6.0, -5.0,  7.0 };

MultiPerceptron perceptron = new MultiPerceptron(m, n);
StdOut.println(perceptron);
perceptron.trainMulti(training1, 1);
StdOut.println(perceptron);
perceptron.trainMulti(training2, 0);
StdOut.println(perceptron);
perceptron.trainMulti(training3, 1);
StdOut.println(perceptron);
perceptron.trainMulti(training4, 0);
StdOut.println(perceptron);
```

If your code is correct, it should print:
```
((0.0, 0.0, 0.0), (0.0, 0.0, 0.0))
((0.0, 0.0, 0.0), (5.0, -4.0, 3.0))
((2.0, 3.0, -2.0), (5.0, -4.0, 3.0))
((-2.0, 0.0, -4.0), (5.0, -4.0, 3.0))
((-8.0, -5.0, 3.0), (11.0, 1.0, -4.0))
```

### Task 5: Integration Testing

In Part 1 of this assignment you implemented a client, `ImageClassifier`, which used our implementation of `MultiPerceptron`. Now that you have implemented your version of `MultiPerceptron` and (`Perceptron`), you can test your client with your own `MultiPerceptron` implementation.

Compile and run `ImageClassifier.java`, which will now use your implementation of `MultiPerceptron`. Do you get the same error rates as reported when using our implementation?

### Task 6: Analysis

Modify your `ImageClassifier.java` from Part 1 so that in addition to printing the misclassified image filename, you also print the label associated with the filename and the incorrectly predicted digit. For example:

```
jar:file:digits.jar!/testing/9/4560.png label: 9 predicted: 4
```

Run the following experiment (you may wish to redirect the standard output to a file):

```
java-introcs ImageClassifier digits-training60K.txt digits-testing1K.txt
```

1. What digit is misclassified the most frequently (i.e., which label has the most false-negatives)?
2. For this digit, what are the top two digits that your `MultiPerceptron` incorrectly predicts?
3. Examine some of these misclassified images. Provide an explanation for what might have caused these misclassifications.

Provide your answers in a new `readme_part2.txt` file, containing the following:

```
Programming Assignment: Image Classification: Part 2


/**********************************************************************
 * Approximate number of hours to complete this assignment            *
 **********************************************************************/

Number of hours:


/**********************************************************************
 * Analysis: 
 * 1. What digit is misclassified the most frequently? 
 * 2. For this digit, what are the top two digits that your 
 *    MultiPerceptron incorrectly predicts?
 * 3. Examine some of these misclassified images. Provide an
      explanation for what might have caused these misclassifications.
 **********************************************************************/

1. Most frequently misclassified digit:


2. Top two digits misclassified are:


3. Explanation:


/**********************************************************************
 *  Did you receive help from classmates, past students, or
 *  anyone else? If so, please list their names.  ("A Sunday lab TA"
 *  or "Office hours on Thursday" is ok if you don't know their name.)
 **********************************************************************/

Yes or no?


/**********************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 **********************************************************************/

Yes or no?


/**********************************************************************
 *  List any other comments here.                                     
 **********************************************************************/


```

## Submit

Upload the following files to **Gradescope**:

* Perceptron.java
* MultiPerceptron.java
* **A completed readme_part2.txt file,** including your analysis, etc.

## Further Reading (Recommended)

**When was the perceptron algorithm first discovered?**
It was proposed in 1958 by Frank Rosenblatt, an American psychologist. It is one of the earliest machine-learning algorithms. At the time, researchers were overly optimistic about its short-term potential and grossly underestimated the formidable challenge of building intelligent machines.

**What is the significance of the perceptron algorithm?**
It is a really simple, beautiful algorithm that, nevertheless, can do something interesting.

- The perceptron algorithm is one of the most fundamental algorithms in an area of ML called online learning (learning from samples one at a time).
- The perceptron algorithm is closely related to the support-vector machines algorithm, another fundamental ML algorithm.
- The perceptron algorithm has some beautiful theoretical properties. For example, if the training data set is linearly separable (i.e., there exists some weight vector that correctly classifies all of the training examples), and if we cycle through the training data repeatedly, the perceptron algorithm will eventually (and provably) find such a weight vector.
- Perceptrons are a building block in neural networks. In neural networks, many perceptrons (or other artificial neurons) are connected in a network architecture, in which the outputs of some neurons are used as the inputs to other neurons. Training multi-layer neural networks requires a more sophisticated algorithm to adjust the weights, such as gradient descent.

**Does the perceptron algorithm produce a different weight vector depending on the order in which it processes the training examples?**
Yes. We've randomized the training data. It would be a bad idea, for example, to process all of the handwritten 0s before all of the handwritten 6s.

**Any intuition for why the perceptron algorithm works?**
Geometrically, you can view each input vector x as a point in $$R^n$$ (n-dimensional real space) and the weight vector w as a hyperplane through the origin. The goal of the perceptron algorithm is to find a hyperplane that separates the positive examples from the negative examples. Using vector terminology, the weighted sum is known as the dot product; its sign determines on which side of the hyperplane the point lies.

During training, when we encounter a point x that is on the wrong side of the hyperplane (i.e., a false positive or negative), we update the weight vector, thereby rotating the hyperplane slightly. After the rotation, x is either on the correct side of the hyperplane or, if not, at least a bit closer to the hyperplane (in terms of Euclidean distance).

{% include image.html src="hyperplane.png" alt="Samples shown on a 2D coordinate system, with the weight vector through the origin." %}

**How can I improve accuracy of the perceptron algorithm?**
Here are some ideas:
- **Multiclass perceptron**. Instead of training all m perceptrons on each input vector, when there is a prediction error (multiclass perceptron predicts i but correct label is j ) , train only two perceptrons: train perceptron i (with label -1) and perceptron j (with label +1).
- **Smoothed convergence**. Adjust the weights with a fraction of +1 or -1 for correct or incorrect predictions (this helps with a smoother convergence) and iterate over the training step multiple times, each time training the perceptron with the same set of training data (randomized in order).
- **Random starting weights**. Normalize the Features array data to have values between 0 and 1 (divide the values with 255) and initialize the perceptron weights to random values (with uniform random or gaussian random to be less than 1 and on average 0).
- **Averaged perceptron**. Instead of using the last weight vector, take the average of the weight vectors that are computed along the way.
- **Incorporate more features**. Instead of using the feature vector $$x_0 , x_1 , \ldots , x_{n-1}$$ create additional features. In particular, for each pair of features $$x_i$$ and $$x_j$$ , create a new feature
$$x_{ij} = x_i \cdot x_j$$. You could also keep going, adding not just pairs of features, but also triples, etc. This can significantly improve accuracy, but it becomes prohibitively expensive in terms
of computation.

See this [paper for additional ideas](http://rob.schapire.net/papers/FreundSc98.pdf), including the kernel trick and the voted-perceptron algorithm.

**I noticed that the weights are always integral throughout the perceptron algorithm. Why is this?**
Adding or subtracting an integer to an integer yields an integer. The weights are always adjusted by either adding or subtracting the input vector; for the image classification problems we consider in this assignment, the input vector elements are integers between 0 and 255 (grayscale values).

**As a perceptron is trained with more and more data, the weights increase in absolute value. Is this to be expected?**
Yes. This implies that the algorithm makes smaller and smaller relative changes to the weights as it learns from more and more input-output pairs.

**Why not use the testing data for training?**
We seek a model that can make good predictions for unseen input-output pairs. If we use the testing data when training, the algorithm could "memorize" the input-output pairs. While this might achieve higher accuracy on those inputs, the results may not generalize to unseen inputs. This is referred to as "overfitting" the data.

**What is the difference between supervised and unsupervised learning?**
In supervised learning, the algorithm has access to a training data for which we know the correct labels. These labels act as a teacher supervising the learning process. In unsupervised learning, the training data is unlabeled, so there are no correct answers; instead, the goal may be to cluster similar inputs together.

**How to recognize my own handwritten digits?**
Use the same technique but must be careful to size-normalize and center the images, as is done in the [MNIST training data](https://en.wikipedia.org/wiki/MNIST_database).

**Can ML algorithms be trained to classify alphabetic and mathematical symbols?**
Yes. Here are web apps that let you draw and find the most similar [Unicode characters](https://shapecatcher.com) or [LaTeX symbols](https://detexify.kirelabs.org/classify.html).

**Any games based on classifying hand-drawn images?**
Yes. Try [Quick, Draw](https://quickdraw.withgoogle.com/), which uses neural networks and the world's largest doodling data set.

**What is the best performing algorithm for classifying handwritten digits using the MNIST dataset?**
The current champion uses convolution neural networks and achieves a 99.79% accuracy rate on the MNIST testing database consisting of 10,000 images. Here are the 21 incorrect predictions:

{% include image.html src="incorrect.png" alt="Incorrect predictions from the state-of-the-art classifier for MNIST." %}

There is not much room for improvement; indeed, some of the errors appear to be due to incorrectly labeled (or ambiguous) inputs.

## Acknowledgements

Thanks to Princeton's COS126 materials, which served as a basis for this assignment.
