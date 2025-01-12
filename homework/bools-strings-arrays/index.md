---
title: "Homework 3: Booleans, Strings, and Arrays"
layout: default
---

# {{ page.title }}

The goal of this homework is to familiarize you with programming in Java. In particular, you will practice:

* Using booleans, Strings, and Arrays
* Reading from standard input
* Reading from files


## Instructions

### Task 1: Reading and Exercises

- Read Algorithms, chapter 1, "Input and output" (pages 36-45) and "Static methods" (pages 22-27)
- Read and do the exercises in [Java For Python Programmers](https://runestone.academy/runestone/books/published/java4python/Java4Python/toctree.html), "Java Data Types: Input / Output / Scanner" and "Defining Classes in Java"
  - In assignments, I recommend using the Algorithms textbook's `StdIn` and `In` classes instead of Java's built-in `Scanner` class. However, it is useful to learn about `Scanner`, since `Scanner` is widely-used in real-world programs.

### Task 2: Coding Practice

To practice using boolean expressions and branching statements, do the following [CodingBat](https://codingbat.com/java) problems:

- [Sleep in](https://codingbat.com/prob/p187868)
- [Monkey Trouble](https://codingbat.com/prob/p181646)
- [Parrot Trouble](https://codingbat.com/prob/p140449)
- [posNeg](https://codingbat.com/prob/p159227)

### Task 3: Implement Two Programs

This is an **individual** assignment.

Start by downloading the [hw3.zip starter project](hw3.zip). Then create two programs:

#### Caesar.java

Julius Caesar sent secret messages to Cicero using a scheme that is now known as a *Caesar cipher*. Each letter is replaced by the letter `k` positions ahead of it in the alphabet (and you wrap around if needed).

The table below gives the Caesar cipher when `k = 3`:

```
Original: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
Caesar:   D E F G H I J K L M N O P Q R S T U V W X Y Z A B C
```

For example the message "VENI, VIDI, VICI" is converted to "YHQL, YLGL, YLFL".

Write a program `Caesar.java` that takes a command-line argument `k` and applies a Caesar cipher with shift = `k` to a sequence of letters read from **standard input**. If a character is not an uppercase letter (e.g., if the character is a symbol or a lowercase letter), simply print it back out. The program should read from standard input until it encounters the newline character `'\n'` (i.e., the users presses "Return"). Then, it should print out the shifted text.

**Hint:**
Use [the `StdIn` methods](https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdIn.html) we discussed in class. To use libraries supplied by the textbook authors, you can use either the LIFT menu, or the `javac-introcs` and `java-introcs` commands to compile and run your code.

**Hint:**
You can use type casting to convert characters to their ASCII values, and vice-versa. For example, if `c` is a `char` variable storing `'A'`, `(int) c` will convert it to `65`. Casts can be used to go in the opposite direction as well. Use [this reference for ASCII code values](https://www.asciitable.com). Representing characters as numbers makes it easy to do math on them.

**Hint:**
This is how your program should work when it is finished.

First, you run the program, supplying the shift:
{% include image.html src="Caesar1.png" alt="Step 1: Enter CLI args" max-width="250px" %}

Next, the program will start running, but nothing should appear:
{% include image.html src="Caesar2.png" alt="Step 2: Wait for the program to run" %}

However, you can type. Type the input to the program:
{% include image.html src="Caesar3.png" alt="Step 3: Type input to standard input" %}

Nothing should happen until you press "Return" or "Enter" on your keyboard. Then, your program should print the shifted output:
{% include image.html src="Caesar4.png" alt="Step 4: Press return, and output should appear" %}

#### QueensChecker.java

A permutation of n integers in the range 0 to n-1 corresponds to a placement of queens on an n-by-n chessboard so that no two queens are in the same row or column.

Write a program `QueensChecker.java` that takes a filename as its only command-line argument. Using the permutation encoded in this file, the program should determine whether or not the permutation corresponds to a valid placement of queens so that no two are in the same **row**, **column**, or **diagonal**. The program should print "true" if the permutation is valid, or "false" otherwise.

As an example, assume test1.txt has the following content: `4 1 3 0 2`. These numbers correspond to the queens’ positions below, with each number specifying a different queen's position. This is a legal placement, so the program should print "true".

```
> java-introcs QueensChecker test1.txt
* * * Q *
* Q * * *
* * * * Q
* * Q * *
Q * * * *
true
```

Take a moment to carefully compare this board with the corresponding test input. Assume you write code to store the queen positions in a 1D array, `q`. How does the length of `q` correspond to the dimensions of the board? How do the array element values, `q[i]`, `i=0, ..., last`, correspond to the queens' positions?

For full-credit, your program should:
- Print whether the permutation is valid
- Print a board representing the placement of the queens, matching the output shown above

**Hint:**
Create a 2D array representing the queens’ positions, and use this to print out the board. Make sure the orientation matches the board in the example.

**Hint:**
It is possible to check whether the placement is valid using just the 1D array of length n representing the input permutation (e.g., `q`, as described above).

**More hints:**
To determine whether setting `q[i]` conflicts with `q[j]` for i < j.
- If `q[i]` equals `q[j]`: two queens are placed in the same row
- If `q[i] - q[j]` equals `j - i`: two queens are on same major diagonal
- If `q[j] - q[i]` equals `j - i`: two queens are on same minor diagonal

**Hint:**
This code will allow you to read the input filename into the `q` array. The `In()` class is supplied by the textbook authors, and works similarly to the `StdIn` library we covered in lecture.
```
In in = new In(args[0]);
int[] q = in.readAllInts();
```

## Submit

Upload the following files to **Gradescope**:

* Your two .java files:
  * Caesar.java
  * QueensChecker.java
* **A completed readme.txt file,** including your name, etc.
