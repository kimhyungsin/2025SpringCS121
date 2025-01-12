---
title: "Homework 7: Traveling Salesperson Problem"
layout: default
---

# {{ page.title }}

As part of this homework, you will learn about the well-known "traveling salesperson problem" (TSP). The goal of this homework is to familiarize you with programming in Java and with data structures. You will practice:

- Implementing circular linked lists
- Implementing two TSP algorithms

## Instructions

This is a **group assignment**. We encourage you to work with one other student.

Download and unzip the [tsp.zip](tsp.zip), which contains the files you will need for this assignment.

### Task 1: Reading

- Read Algorithms, chapter 1.3, "Linked lists" (pages 142-146)
- Read the background information below, including the information about your approach

#### Background

Given n points, the goal of the traveling salesperson problem is to find the shortest path that visits every point once and returns to the starting point. We call an ordering of n points a **tour**.

Consider the two four-point tours shown below. Both tours visit the same set of points; however they take different paths. The tour on the left uses the ordering $$A \rightarrow B \rightarrow C \rightarrow D \rightarrow A$$, while the tour on the right uses $$A \rightarrow B \rightarrow D \rightarrow C \rightarrow A$$. A traveling salesperson would prefer the tour on the left, as it minimizes the distance they would have to travel.

{% include image.html src="path_lengths.png" alt="Two differing paths, depicted in a cartesian coordinate system, and their differing lengths" %}

Note that the importance of the TSP does not arise from an overwhelming demand of salespeople to minimize their travel length, but rather from a wealth of other applications such as vehicle routing, circuit board drilling, VLSI design, robot control, X-ray crystallography, machine scheduling, and computational biology.

You will implement two greedy heuristics to find good, but not optimal, solutions to the traveling salesperson problem. We ask you to use greedy heuristics to estimate the solution because TSP is a notoriously difficult combinatorial optimization problem. In principle, you can enumerate all possible tours (n factorial) and pick the shortest one; in practice, the number of possible tours is so staggeringly large that this approach is inapplicable. No one so far knows of an efficient method that can find the shortest possible tour for large n. Instead many methods have been studied that seem to work well in practice, even though they do not guarantee to produce the best possible tour. Such methods are called heuristics.

#### Approach

We provide a `Point` data type in the project folder. Use this data type to represent a single (x, y) coordinate.

```
public class Point

    // Creates the point (x, y).
    public Point(double x, double y)

    // Returns the Euclidean distance between the two points.
    public double distanceTo(Point that)

    // Draws this point to standard drawing.
    public void draw()

    // Draws the line segment between the two points.
    public void drawTo(Point that)

    // Returns a string representation of this point.
    public String toString()
```

You will create a `Tour` data type that represents the sequence of points visited in a TSP tour. Represent the tour as a circular linked list of nodes, with one node for each point in the tour. Recall that in a circular linked list, the last node contains a pointer back to the first node, including a circular linked list that contains only one node.

Your main task is to implement two greedy heuristics: the **nearest neighbor** and **smallest increase** insertion heuristics for building a tour incrementally, one point at a time. Start with a one-point tour (from the first point back to itself), and iterate the following process until there are no points left:
- *Nearest neighbor heuristic*: Read in the next point and add it to the current tour after the point to which it is closest. If there is more than one point to which it is closest, insert it after the first such point you discover.
- *Smallest increase heuristic*: Read in the next point and add it to the current tour after the point where it results in the least possible increase in the tour length. If there is more than one point where this occurs, insert it after the first such point you discover.

For example, suppose our current tour contains the following four points that are stored in a circular linked list as $$A \rightarrow B \rightarrow C \rightarrow D \rightarrow A$$. 

{% include image.html src="TSP1.png" alt="Initial tour, between points A, B, C, and D" max-width="250px" %}

The next point input is E, located at (5, 6). Depending on the heuristic, point E will be incorporated into the existing tour in one of two ways. Nearest neighbor will visit E after point C, as C is the closest existing point to E. Smallest increase will determine that visiting E after point B results in the smallest overall increase in the length of the tour.

{% include image.html src="TSP2.png" alt="Point E will be added to tour differently, depending on whether the nearest neighbor or smallest increase heuristic is used" %}

To find the nearest neighbor to a node, loop through the linked list and maintain a reference to the closest node seen. After checking all the locations, insert the new point into the linked list after the reference node.

To find the smallest increase, your code should loop through your linked list and consider how the tour length is impacted by inserting your current point into every index. Maintain a reference to the best position to insert the node, and insert the current point into the linked list after the reference node.

Here is an example of showing the calculations needed for performing one insert using the Smallest Increase heuestic on a 4-point tour. The blue node E represents the new point to insert. 

{% include image.html src="TSP3.png" alt="Considering how to incorporate E into the existing path" max-width="250px" %}

The figure below shows how you can find the 5-point tour with the smallest increase heuristic. Notice that there will be 4 possible tours with E, depending on where to insert it.  All 4 tours create two new pointers (depicted in red) and remove one pointer. To find the tour with the smallest increase, calculate which insertion point minimizes the change in the tour length after the insertion (i.e. delta length) and insert your node there. 

{% include image.html src="TSP4.png" alt="Calculating the path length for all possible insertion points" %}

### Task 2: Implement Tour.java

Create a `Tour` data type that represents the sequence of points visited in a TSP tour. Represent the tour as a circular linked list of `Nodes`, one for each point in the tour. Each `Node` contains two references: one to the associated `Point` and the other to the next `Node` in the tour. Each constructor must take constant time. All instance methods must take time linear (or better) in the number of points currently in the tour. 

To represent a node, within `Tour.java` define a nested class Node:
```
private class Node {
    private Point p;
    private Node next;
}
```

Your `Tour` data type must implement the following API. You **must not** add public methods to the API; however, you may add private instance variables or methods (which are only accessible in the class in which they are declared).

```
public class Tour

    // Creates an empty tour.
    public Tour()

    // Creates the 4-point tour a->b->c->d->a (for debugging).
    public Tour(Point a, Point b, Point c, Point d)

    // Returns the number of points in this tour.
    public int size()

    // Returns the length of this tour.
    public double length()

    // Returns a string representation of this tour.
    public String toString()

    // Draws this tour to standard drawing.
    public void draw()

    // Inserts a point using the nearest neighbor heuristic.
    public void insertNearest(Point p)

    // Inserts a point using the smallest increase heuristic.
    public void insertSmallest(Point p)

    // Tests this class by calling all constructors and instance methods.
    public static void main(String[] args)
```

#### Constructors
For the default constructor, initialize the instance variables to represent an empty list. In the 4-point constructor, add the given points into the circular linked list ordered as $$A \rightarrow B \rightarrow C \rightarrow D \rightarrow A$$.

**Hint!**
Use the 4-point example described above as a test case in `main()`.
```
public static void main(String[] args) {
    // define 4 points as corners of a square
    Point a = new Point(1.0, 1.0);
    Point b = new Point(1.0, 4.0);
    Point c = new Point(4.0, 4.0);
    Point d = new Point(4.0, 1.0);

    // create the tour a -> b -> c -> d -> a
    Tour squareTour = new Tour(a, b, c, d);
}
```

#### size()
Returns the number of points in the current `Tour`, which is equivalent to the number of nodes in the linked list. The size of an empty `Tour` is 0. You can loop through your circular linked list using a do-while loop:
```
Node current = start;
do {
    // do things with current Node
    current = current.next;
} while (current != start);
```

**Hint!**
Test the `size() `method by calling the `size()` method for the square tour you created in `main()`, which has four points.
```
// print the size to standard output
int size = squareTour.size();
StdOut.println("# of points = " + size);
```

#### length()
Returns the length of the current `Tour`, which is equivalent to the distance of the path through all the points in the linked list. Use the `distanceTo()` method of the `Point` data type to find the distance between successive points. The length of an empty `Tour` is 0.0.

**Hint!**
Test the `length()` method on the 4-point tour. The length should be 12.0.
```
// print the tour length to standard output
double length = squareTour.length();
StdOut.println("Tour length = " + length);
```

#### toString()
Returns a string containing the points, separated by newline characters, by calling the `toString()` method for each point, starting with the first point in the tour. An empty Tour must be represented as an empty String ("").

**Hint!**
Test the toString() method on the 4-point tour in main():
```
// print the tour to standard output
StdOut.println(squareTour);
```

You should get the following output:
```
(1.0, 1.0)
(1.0, 4.0)
(4.0, 4.0)
(4.0, 1.0)
```

#### draw()
Draws the `Tour` to standard drawing by calling the `drawTo()` method for each pair of consecutive points. It must produce no other output. If the `Tour` is empty, then nothing is drawn to standard drawing.

**Hint!**
Remember to set your x and y scale. For debugging the square tour, add to `main()`:
```
StdDraw.setXscale(0, 6);
StdDraw.setYscale(0, 6);
```


#### insertNearest()
Inserts the given point into the current tour using the *insert nearest heuristic*.

**Hint!**
Test `insertNearest()` by inserting various points into the 4-point tour. Create a `Point E` at coordinate (5.0, 6.0), as in the example graphs provided earlier in the documentation. In `main()`, add the following test code and check if the drawing looks as expected.
```
Point e = new Point(5.0, 6.0);
squareTour.insertNearest(e);
squareTour.draw();
```


#### insertSmallest()
Inserts the given point into the current tour using the *smallest neighbor heuristic*.

**Hint!**
Test `insertSmallest()` similarly to how you tested the `insertNearest()` method.


#### Input Files

We have included many input files for testing. The input files begin with two integers width and height, followed by pairs of x- and y-coordinates. All x-coordinates will be real numbers between 0 and width; all y-coordinates will be real numbers between 0 and height.

Here are several small input files:

- tsp0.txt
- tsp1.txt
- ...
- tsp8.txt

And some larger inputs:

- tsp10.txt
- tsp100.txt
- ...
- tsp85900.txt
- usa13509.txt
- circuit1290.txt

For more input files, see [the TSPLIP dataset](http://elib.zib.de/pub/mp-testdata/tsp/tsplib/tsp/index.html).

#### Testing

A good debugging strategy for most programs is to test your code on inputs that you can easily solve by hand. Start with 1- and 2-point problems. Then, do a 4-point problem. Choose the data so that it is easy to work through the code by hand. Draw pictures. If your code does not do exactly what your hand calculations indicate, determine where they differ. Use the `StdOut.println()` method to trace.

After implementing `Tour.java`, use the client program `NearestInsertion.java`, which reads points from standard input; runs the nearest neighbor heuristic; prints the resulting tour, its length, and its number of points to standard output; and draws the resulting `Tour` to standard drawing. `SmallestInsertion.java` is analogous but runs the smallest increase heuristic. For example:
```
> java-introcs NearestInsertion < tsp6a.txt
(50.0, 450.0)
(50.0, 50.0)
(550.0, 100.0)
(550.0, 400.0)
(900.0, 50.0)
(900.0, 450.0)

Tour length = 2947.4685
Number of points = 6
```
{% include image.html src="nearest.png" alt="Visualization of the nearest insertion tour of the tsp6a.txt data" %}

```
> java-introcs SmallestInsertion < tsp6a.txt
(50.0, 450.0)
(550.0, 400.0)
(900.0, 450.0)
(900.0, 50.0)
(550.0, 100.0)
(50.0, 50.0)

Tour length = 2512.0943
Number of points = 6
```
{% include image.html src="smallest.png" alt="Visualization of the smallest insertion tour of the tsp6a.txt data" %}

If your `Tour.java` code is correct, you should get the same results as our code for these examples:

| Data File       | "Nearest" Tour Length | "Smallest" Tour Length |
|-----------------|-----------------------|------------------------|
| tsp6a.txt       | 2947.4685             | 2512.0943              |
| usa13509.txt    | 77449.9794            | 45074.7769             |
| circuit1290.txt | 25029.7905            | 14596.0971             |


{% comment %}
### Task 3: Analysis 
In your `readme.txt`, estimate the running time (in seconds) of your program as a function of the number of points `n`. You should use the client program `TSPTimer.java` to help you estimate the running time as a function of the input size `n`. `TSPTimer` takes a command-line argument `n`, runs the two heuristics on a random input of size `n`, and prints how long each took. Run the two heuristics for `n = 1000`, and repeatedly double `n` until the execution time exceeds 60 seconds.

In order to get consistent timing results:
- For each `n`, run `TSPTimer` more than once, and record the median of your results.
- Execute with the `-Xint` flag. The `-Xint` flag turns off various compiler optimizations, which helps normalize and stabilize the timing data that you collect. For example:

```
> java-introcs -Xint TSPTimer 1000
Tour length = 26338.42949015926
Nearest insertion:  0.056 seconds

Tour length = 15505.745750759515
Smallest insertion:  0.154 seconds
```

**Hint!**
You many find it helpful to refer to the formula we used in [Lab 5]({{'/lab/lab5' | relative_url }}) (see "Task 4: Estimate Running Time").

**Hint!**
If `TSPTimer` takes over 60 seconds for `n = 1000`, your code is too slow, and must have a bug. For `n = 1000`, my code finishes in less than a second. If your code is too slow, **your Gradescope submission will time out**.
{% endcomment %}

## Submit
Upload the following files to Gradescope:

* Tour.java
* **A completed readme.txt file,** including responses to the questions

Also, be sure to **indicate your group member on Gradescope**.

## Further Reading (Recommended)

### Improved TSP

Here are some ideas for finding a better TSP tour. None of the methods guarantees to find an optimal tour, but they often lead to good tours in practice.

- **Crossing edges.** If two edges in a tour cross, you can decrease the length of the tour by replacing the pair that crosses with a pair that does not cross.
- **Farthest insertion.** It is just like the smallest increase insertion heuristic described in the assignment, except that the cities need not be inserted in the same order as the input. Start with a tour consisting of the two cities that are farthest apart. Repeat the following:
  - For each city not in the tour, consider the shortest distance from that city to a city already in the tour. Among all cities not in the tour, select the city whose distance is largest.
  - Insert that city into the tour in the position where it causes the smallest increases in the tour distance.
  - You will have to store all of the unused cities in an appropriate data structure, until they get inserted into the tour. If your code takes a long time, your algorithm probably performs approximately $$n^3$$ steps. If you're careful and clever, this can be improved to $$n^2$$ steps.
- **Node interchange local search.** Run the original greedy heuristic (or any other heuristic). Then repeat the following:
  - Choose a pair of cities.
  - Swap the two cities if this improves the tour. For example, if the original greedy heuristic returns 1-5-6-2-3-4-1, you might consider swapping 5 and 3 to get the tour 1-3-6-2-5-4-1.
  - Writing a function to swap two nodes in a linked list provides great practice with coding linked lists. Be careful: it can be a little trickier than you might first expect (e.g., make sure your code handles the case when the two cities occur consecutively in the original tour).
- **Edge exchange local search.** Run the original greedy heuristic (or any other heuristic). Then repeat the following:
  - Choose a pair of edges in the tour, say 1-2 and 3-4.
  - Replace them with 1-3 and 2-4 if this improves the tour.
  - This requires some care, as you will have to reverse the orientation of the links in the original tour between nodes 3 and 2 so that your data structure remains a circular linked list. After performing this heuristic, there will be no crossing edges in the tour, although it need not be optimal.

### Links 

- Here's a [13,509-point problem](https://www.cs.princeton.edu/courses/archive/spring20/cos126/assignments/tsp/tsp13509.gif) that contains each of the 13,509 cities in the continental US with a population over 500. The [optimal solution](https://www.cs.princeton.edu/courses/archive/spring20/cos126/assignments/tsp/tsp13509-sol.jpg) was discovered in 1998 by Applegate, Bixby, Chvatal, and Cook using theoretical ideas from linear and integer programming. The "record" for the largest TSP problem ever solved exactly is a [85,900-point](http://www.math.uwaterloo.ca/tsp/pla85900/index.html) instance that arose from microchip design in the 1980s. It took over 136 CPU-years to solve.
- Here's an excellent and very accessible [book](http://www.amazon.com/Pursuit-Traveling-Salesman-Mathematics-Computation/dp/0691152705) about the TSP.
- Here's a nice pictorial survey of the [history of the TSP problem](http://www.math.uwaterloo.ca/tsp/).
- Some folks even use the TSP to create and sell art. Check out [Bob Bosch's page](http://www.oberlin.edu/math/faculty/bosch/tspart-page.html). You can even [make your own TSP artwork](http://www.oberlin.edu/math/faculty/bosch/making-tspart-page.html).

## Acknowledgements

Thanks to Princeton's COS126 materials, which served as a basis for this assignment.

{% comment %}
https://docs.google.com/document/d/1yZiP_ri3FSYH1EGC_3oXVP9YeKidMBmN9PM42RPdNzY/edit#heading=h.6g1jckuo2qe2
{% endcomment %}