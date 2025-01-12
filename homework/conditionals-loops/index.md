---
title: "Homework 2: Conditionals and Loops"
layout: default
---

# {{ page.title }}

The goal of this homework is to continue practicing programming in Java. In particular, you will practice:

- Using Java loops, conditionals, and arrays
- Debugging Java code

## Instructions

This is an **individual** assignment. Complete the three tasks listed below.

### Task 1: Reading and Exercises

* Read and do the exercises for "Java Data Types," "Conditionals," "Loops and Iteration" in [Java for Python Programmers](https://runestone.academy/runestone/books/published/java4python/Java4Python/toctree.html).
* Read "Week 1: Loops" in the [Java Style Guide](http://bit.ly/126StyleGuideS2021).
* Refer to the [Java Programming Cheatsheet](https://introcs.cs.princeton.edu/java/11cheatsheet/)

### Task 2: Implement Four Programs

Start by downloading and unzipping [hw2.zip](hw2.zip), which contains the files you will need for this assignment. Open the hw2 project folder in IntelliJ, and create your Java files in this project folder.

#### Bits.java

Write a program `Bits.java` that takes an integer command-line argument `n` and uses a `while` loop to compute the number of times you need to divide `n` by 2 until it is strictly less than 1. Print the error message "Illegal input" if `n` is negative.

Examples:
```
> java-introcs Bits 0
0

> java-introcs Bits 1
1

> java-introcs Bits 2
2

> java-introcs Bits 4
3

> java-introcs Bits 8
4

> java-introcs Bits 16
5

> java-introcs Bits 1000
10

> java-introcs Bits -23
Illegal input
```

**Note:**
This program computes the number of bits in the binary representation of n, which also equals $$1+\text{floor}(\text{log}_2 n)$$ when $$n$$ is positive. This quantity arises in information theory and the analysis of algorithms.

#### NoonSnooze.java

Write a program `NoonSnooze.java` that takes an integer command-line argument representing the number of minutes, `snooze`, that have elapsed since 12:00pm (noon) and prints the resulting time. Assume a 12-hour clock (with 'am' and 'pm'). **You must not use loops**. You may assume that the value of snooze is a non-negative integer.

**Hints:**
- Use integer division (`/`) and integer remainder (`%`) operators to help compute the new values for the hours and minutes.
- Use the integer remainder (`%`) operator to determine if the clock should read am or pm.
- Use conditionals (`if`) to help format the output.

**Exercises:**
Before writing code, it is good practice to try solving the problem by hand. Try manually performing the calculations for the following values using only integer division (`/`) and integer remainder (`%`): 0, 9, 15, 60, 719, 720, 721, 1440, 1441, ..., 5039, 5040, 5041

Examples:

```
> java-introcs NoonSnooze 50
12:50pm

> java-introcs NoonSnooze 100
1:40pm

> java-introcs NoonSnooze 721
12:01am

> java-introcs NoonSnooze 11111
5:11am
```


#### RandomWalker.java

A drone begins flying aimlessly, starting at Jonas Clark Hall (0, 0). At each time step, the drone flies one meter in a random direction, either north, east, south, or west, with probability 25%. How far will the drone be from Jonas Clark Hall after n steps? This process is known as a *two-dimensional random walk*.

{% include image.html src="RandomWalker.png" alt="Random walk example on a grid" %}

Write a program `RandomWalker.java` that takes an integer command-line argument `n` and simulates the motion of a random walk for `n` steps. Print the location at each step (including the starting point), treating the starting point as the origin (0, 0). Finally, print the square of the final Euclidean distance from the origin.

**Hint:**
First, think about which variables you need to maintain. You certainly need to parse and store the command-line argument n. You also need to store the current location (x, y) of the random walker. What should be the type of the variables x and y? What should be their initial values? To choose a random direction, consider using the idiom from the `RandomInt` program [from the slides]({{'/slides/CS.1.Basics.pdf' | relative_url}}) to generate a random integer in a given range.

Examples:

```
> java-introcs RandomWalker 10
(0, 0)
(0, -1)
(0, 0)
(0, 1)
(0, 2)
(-1, 2)
(-2, 2)
(-2, 1)
(-1, 1)
(-2, 1)
(-3, 1)
squared distance = 10

> java-introcs RandomWalker 20
(0, 0)
(0, 1)
(-1, 1)
(-1, 2)
(0, 2)
(1, 2)
(1, 3)
(0, 3)
(-1, 3)
(-2, 3)
(-3, 3)
(-3, 2)
(-4, 2)
(-4, 1)
(-3, 1)
(-3, 0)
(-4, 0)
(-4, -1)
(-3, -1)
(-3, -2)
(-3, -3)
squared distance = 18
```

#### RandomWalkers.java

Write a program `RandomWalkers.java` that takes two integer command-line arguments `n` and `trials`. In each of `trials` independent experiments, simulate a random walk of `n` steps and compute the squared distance. Output the *mean squared distance* (the average of the trials squared distances). 						

**Hint:**
1. Copy the code (in `main`) from `RandomWalker.java` to `RandomWalkers.java`.
2. Which additional variables do you need?
  - You certainly need to read and store the command-line arguments `n` and `trials`. 
  - In addition to the current location (x, y) of the random walker, you need an accumulator variable, say `totalSquaredDistance`, that stores the total sum of squared distances so far.
  - Nest the loop inside an outer loop that repeats `trials` times and add code to update `totalSquaredDistance` after each time through the outer loop.

Examples:

```
> java-introcs RandomWalkers 100 10000
mean squared distance = 101.446

> java-introcs RandomWalkers 100 10000
mean squared distance = 99.1674

> java-introcs RandomWalkers 200 1000  
mean squared distance = 195.75

> java-introcs RandomWalkers 400 2000      
mean squared distance = 383.12      
                               	 
> java-introcs RandomWalkers 800 5000      
mean squared distance = 811.8264  
                                	 
> java-introcs RandomWalkers 1600 100000      
mean squared distance = 1600.13064
```

**Note:**
This process is a discrete version of a natural phenomenon known as Brownian motion. It serves as a scientific model for an astonishing range of physical processes, from the dispersion of ink flowing in water, to the formation of polymer chains in chemistry, to cascades of neurons firing in the brain.

### Task 3: Analyze your results

Edit the text file named `readme.txt` that is a narrative description of your work. 

As `n` increases, we expect the random walk to end up farther and farther away from the origin. But how much farther? Use `RandomWalkers` to formulate a hypothesis as to how the mean squared distance grows as a function of `n`.

What is the proportional relationship between the number of steps `n` of the random walk and the mean squared distance? By relationship, we mean something like:

$$\text{mean squared distance} = 126 n^9 $$

(Although of course this is incorrect -- the real answer is simpler!)

Briefly justify your answer based on computational experiments. Describe the experiments and list several data points. Provide your answer in your `readme.txt` file.

**Hint:**
Run a series of experiments keeping the number of trials fixed, but **changing n** - try large values for n (e.g., 100,000)

**Hint:**
Run a series of experiments keeping n fixed but **changing the number of trials** - try large values for trials (e.g., 100,000)


## Submit

Upload these files to Gradescope:

* Your four .java files:
  * Bits.java
  * NoonSnooze.java
  * RandomWalker.java
  * RandomWalkers.java
* **A completed readme.txt file,** including your analysis, etc.

## Acknowledgements

Thanks to Princeton's COS126 materials, which served as a basis for this assignment.
{% comment %}
https://docs.google.com/document/d/1wOtj125KMAuZtrmjQU3hjht6MIJZUHbEKQz3q4V4FZ0/edit#
{% endcomment %}
