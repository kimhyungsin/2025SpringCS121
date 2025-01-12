---
title: "Homework 13: Single Points of Failure"
layout: default
---

# {{ page.title }}
The goal of this homework is to practice using undirected graphs.

## Instructions

We encourage you to **work with a partner**.

In this assignment, you will identify vertices and edges which serve as ["single points of failure" (SPOF)](https://en.wikipedia.org/wiki/Single_point_of_failure). Specifically, you will identify vertices and edges which, if removed, would partition the graph into multiple components.

For example, consider the map of Marblehead shown below:
{% include image.html src="map.png" alt="6 vertices superimposed over a map of Marblehead, MA" %}

Imagine that each vertex, labeled 0 to 5, represents a location of interest, and the edges represent roads between them. If vertices `3` or `4` were removed, the graph would be partitioned into two components. The graph could also be partitioned if either the `3 4` or `4 5` edges were removed.

In this assignment, you will complete a program, `SPOF.java`, which will find these single points of failure. `SPOF.java` should accept one argument, the name of a text file describing a graph. First, the text file contains the number of vertices, then the number of edges. Next, the file contains a list of edges, represented as pairs of vertices. Consider `marblehead.txt`, shown below:
```
6
7
0 1
0 3
1 2
1 3
2 3
3 4
4 5
```

When your program is finished, it should give the following output:
```
> java-algs4 SPOF marblehead.txt
SPOF vertices:
3
4

SPOF edges:
3 4
4 5
```

### Task 1: Download Starter Files
[spof.zip](spof.zip) contains the starter files for this assignment.

### Task 2: Implement SPOF.java
#### Load the Graph

Add code to the main method to create a `Graph` object from a text file. Refer to the `Graph(In in)` constructor of [the `Graph` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Graph.html). `GraphDOT`'s main method from [the Graphs lab]({{"/lab/graphs" | relative_url}}) may also be helpful.

**Hint:**
Since `Graph` has a nice `toString()` method, you can easily print the graph to verify that you loaded it correctly.

**Hint:**
You may need to include: `import edu.princeton.cs.algs4.Graph;`

**Note:**
One of the Gradescope tests will hide the details of any bugs in your code. To diagnose such bugs, create your own test data and unit tests.

#### Detect SPOF Vertices
Next, write code to detect and print single point of failure vertices. Here is some pseudocode:

- For each vertex:
  - Make a copy of the graph, excluding this vertex
  - Use [the CC class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/CC.html) to test whether the graph is still connected

#### Detect SPOF Edges
Next, write code to detect and print single point of failure edges. Here is some pseudocode:

- For each edge:
  - Make a copy of the graph, excluding this edge
  - Use [the CC class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/CC.html) to test whether the graph is still connected

**Hint:**
Consider creating an `Edge` class to represent each edge from the original graph.

**Hint:**
To avoid duplicate edges, consider storing each `Edge` in a set. Your `Edge` implementation can consider edges `0 1` and `1 0` to be equal, for example. Essentially, sets are symbol tables with just keys, not values (see [the "Sets" slides]({{"/slides/Sets.pdf" | relative_url}})). Multiple set implementations are available, so select the most appropriate:
- [The `java.util.HashSet` class](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashSet.html) from the Java standard library
- [The `java.util.TreeSet` class](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeSet.html) from the Java standard library
- [The `SET` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/SET.html) from the textbook authors, which [is implemented using `TreeSet`](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/SET.java.html)

To be stored in a `HashSet`, your `Edge` class should implement `equals()` and `hashCode()` methods.
To be stored in a `TreeSet`, your `Edge` class should implement `equals()` and `Comparable`.
Refer to [the "Hash Tables" slides]({{"/slides/34HashTables.pdf" | relative_url}}) for the `hashCode()` method recipe, and to [the "Symbol Tables" slides]({{"/slides/31ElementarySymbolTables.pdf" | relative_url}}) for recipes for the `Comparable` interface and the `equals()` method. Implementing `Comparable` for `Edge` is challenging, because it isn't obvious how to establish a total ordering between `Edges`, and without a total ordering, `Edges` may not be stored correctly in a `TreeSet`.

**Hint:**
A solution that doesn't use sets is also possible.

#### Handle Corner Cases
If the graph isn't connected to begin with, your code should print an error:
```
> java-algs4 SPOF island.txt
ERROR: Graph is not connected
```


## Submit
Upload the following files to **Gradescope**:

- SPOF.java
- **A completed readme.txt file,** including your names, etc.

For full credit, your code should be reasonably efficient (the autograder will check this).

Also, be sure to **indicate your group member on Gradescope**.
