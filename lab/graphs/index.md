---
title: "Lab 15: Graphs"
layout: default
---

# {{ page.title }}
In this lab, you will write code to convert graph data into the format used by [the Graphviz tool.](https://graphviz.org)

## Instructions
We encourage you to **work with a partner**.

Start by downloading [graphs.zip](graphs.zip), which contains the starter files for this assignment.


### Task 1: Install Graphviz on your device
Graphviz is a tool for visualizing graphs.

- First, [install Graphviz by following the instructions for your platform](https://graphviz.org/download/)
  - On macOS, install `graphviz-gui` via MacPorts
  - On Windows, use the latest 64-bit EXE installer. Accept the option to add Graphviz to your PATH.
- Next, use Graphviz to render `starter-files.dot`. You should see something like the image below.
  - On macOS, open `starter-files.dot` using the "Graphviz" app installed in `/Applications/MacPorts/`
  - On Windows, use the `dot` command in the IntelliJ terminal to save a PNG image of the graph using:
  ```
  dot -Tpng starter-files.dot -o starter-files.png
  ```
- Familiarize yourself with [Graphviz's DOT language](https://en.wikipedia.org/wiki/DOT_(graph_description_language)) by comparing the data in `starter-files.dot` to the graph rendered by Graphviz.

{% include image.html src="starter-files.svg" alt="A graph showing the starter files. The 'graphs' vertex is connected to the '.idea', '.lift', 'clusters.txt', etc. vertices." %}

**Note:** Spend at most 15 minutes trying to install Graphviz. If you cannot install Graphviz on either partner's device, you can use [an online Graphviz renderer.](https://edotor.net)

### Task 2: Complete GraphDOT.java
The `GraphDOT` class contains the unfinished `getDOT()` method. When completed, it should convert a [Java `Graph` object](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Graph.html) into [Graphviz DOT data](https://en.wikipedia.org/wiki/DOT_(graph_description_language)), suitable for rendering in Graphviz. 

`GraphDOT`'s main method is already completed. The main method reads `Graph` data in the format described in the documentation for the [`Graph` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Graph.html) from either STDIN or from a file argument, and uses `getDOT()` to print Graphviz data to STDOUT.

For example, `clusters.txt` contains `Graph` data, with 10 vertices and edges:
```
10
10
1 2
3 4
4 5
5 3
6 7
6 8
6 9
7 8
7 9
8 9
```

When the `GraphDOT` class is completed, it should convert `clusters.txt` into Graphviz data. Notice that each edge is printed once, and vertices with degree zero are printed without associated edges:
```
> java-algs4 GraphDOT clusters.txt
graph {
  "0"
  "1" -- "2"
  "3" -- "5"
  "3" -- "4"
  "4" -- "5"
  "6" -- "9"
  "6" -- "8"
  "6" -- "7"
  "7" -- "9"
  "7" -- "8"
  "8" -- "9"
}
```

Redirect the Graphviz data to a new file, `clusters.dot`:
```
> java-algs4 GraphDOT clusters.txt > clusters.dot
```

Open `clusters.dot` with Graphviz, which should display:
{% include image.html src="clusters.svg" alt="Four clusters of fully connected vertices. The clusters contain one, two, three, and four vertices each, respectively." %}

**Note:** When running `GraphDOT` from the command-line, you may get the message:
```
Exception in thread "main" java.lang.NoClassDefFoundError: StdOut
```
This happens because the `java-algs4` command can't locate the `StdOut` class. The `java-introcs` command can locate `StdOut`, but won't be able to locate the `Graph` class! To resolve the issue, simply use `System.out.println()` instead of `StdOut.println()`.

### Task 3: Generate New Graphs
We have included the `GenerateGraph` class, which you can use to generate graphs using the single-argument methods of [the `GraphGenerator` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html). In particular, all of these methods should work:

- [`binaryTree()`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html#binaryTree-int-)
- [`complete()`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html#complete-int-)
- [`cycle()`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html#cycle-int-)
- [`path()`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html#path-int-)
- [`star()`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html#star-int-)
- [`tree()`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html#tree-int-)
- [`wheel()`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html#wheel-int-)

For example, to randomly generate a wheel with ten vertices, run:
```
> java-algs4 GenerateGraph wheel 10 | java-algs4 GraphDOT > wheel.dot
```

Open `wheel.dot` in Graphviz, and you should see something similar to:
{% include image.html src="wheel-dot.svg" alt="A wheel, improperly visualized as a hierarchical graph drawing." %}

Although the visualization below depicts an equivalent graph, its layout makes the graph's structure more interpretable:
{% include image.html src="wheel-neato.svg" alt="The same wheel, more clearly visualized using the 'spring model' offered by the neato layout." %}

Graphviz offers a variety of [different layout engines.](https://graphviz.org/docs/layouts/) By default, the `dot` layout is used, which attempts to [draw the graph heirarchically.](https://en.wikipedia.org/wiki/Layered_graph_drawing) However, this is not appropriate for all types of graphs. To change the layout engine, add a `layout` statement within your graph block. For example, to use the `neato` layout:
```
graph {
  layout="neato"
  "0" -- "2"
  "0" -- "9"
...
```

Referring to [the list of Graphviz layout engines](https://graphviz.org/docs/layouts/), match each graph type with the most appropriate layout. In your testing, generate graphs with ten vertices.

| Graph Type  | Ideal Layout Engine |
|-------------|---------------------|
| Binary Tree |                     |
| Complete    |                     |
| Cycle       |                     |
| Path        |                     |
| Star        |                     |
| Tree        |                     |
| Wheel       | neato               |

{% comment %}
Potential solutions:

- binaryTree: dot, neato, fdp, sfdp, twopi
- complete: circo
- cycle: neato, sfdp, circo
- path: neato, sfdp, twopi
- star: neato, sfdp
- tree: neato, twopi
- wheel: neato, sfdp
{% endcomment %}

**Note:** In general, you should choose layouts that minimize the number of [intersecting edges](https://en.wikipedia.org/wiki/Planar_graph).

**Note:** If you're using [the online Graphviz renderer](https://edotor.net), you can change the layout engine using the "Engine" menu.

### Bonus Tasks

If you finish early, try using the optional attributes of the layout engines to further improve your graph visualizations.

Also, try writing code to generate other types of graphs using [the `GraphGenerator` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/GraphGenerator.html). In particular, try the methods that accept more than one parameter.

## Submit
Upload the following files to **Gradescope**:

- GraphDOT.java
- **A completed readme.txt file,** including your names, etc.
  - Also, match the graph types to the ideal layout engines!

Finally, be sure to indicate your group member on Gradescope.

## Learning Goals
- Practice using graphs in programs
- Practice visualizing graphs
