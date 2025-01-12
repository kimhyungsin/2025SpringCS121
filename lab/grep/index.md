---
title: "Lab 3: Simple Grep"
layout: default
---

# {{ page.title }}
In this lab, you will create a simplified implementation of the `grep` command-line utility. Your program will allow you to search through input text for lines matching a target string.

## Instructions
We encourage you to **work with a partner**.

Start by downloading [grep.zip](grep.zip), which contains the starter files for this assignment.

Create your own `Grep` class which supports searching through input text for a target string. Your program should support input from either standard input (i.e., STDIN) or from a file. The number of arguments supplied should determine where input is read from. When your program finds an input line matching the target string, it should print the matching line.

For example, searching through `the-raven.txt` for the string `raven`:
```
> java-introcs Grep raven the-raven.txt
In there stepped a stately raven of the saintly days of yore;
"Though thy crest be shorn and shaven, thou," I said, "art sure no craven,
Ghastly grim and ancient raven wandering from the Nightly shore,
But the raven, sitting lonely on the placid bust, spoke only
```

Or searching through `the-raven.txt` using standard input:
```
> java-introcs Grep raven < the-raven.txt
In there stepped a stately raven of the saintly days of yore;
"Though thy crest be shorn and shaven, thou," I said, "art sure no craven,
Ghastly grim and ancient raven wandering from the Nightly shore,
But the raven, sitting lonely on the placid bust, spoke only
```

Or searching through standard input typed by the user:
```
> java-introcs Grep word
Hello there!
Hello?
Can you read the words I am typing?
Can you read the words I am typing?
```
Note that only the last line was repeated by the program, because it contained the string `word`.

**Hint:** To check whether a string contains another string, use [the `String` class's `contains()` method](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#contains(java.lang.CharSequence)).

**Note**:
When reading from standard input, each time you press "Return" or "Enter", `Grep` should process another line of input. `Grep` should not exit until it encounters the "end of file" character. On macOS and Linux, you can type this with "Control+D", or on Windows "Control+Z" then "Enter".

**Hint:** We usually use [the `StdIn` class](https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdIn.html) to read from standard input. However, to read from a file, you will need to use [the `In` class](https://introcs.cs.princeton.edu/java/stdlib/javadoc/In.html). Since the `In` class supports reading from either standard input or from a file, your code will be simpler if you only use the `In` class. You can create an `In` object that reads from standard input with:
```
In input = new In();
```
Then, you can read data using the same `StdIn` methods we discussed in lecture, but written as `input.readInt()`, `input.readDouble()`, etc. Refer to [the documentation for the `In` class](https://introcs.cs.princeton.edu/java/stdlib/javadoc/In.html) to learn how to read from a file.

## Submit

Upload the following files to **Gradescope**:

* Grep.java
* **A completed readme.txt file,** including your names, etc.

Also, be sure to **indicate your group member on Gradescope**.

## Learning Goals
- Practice using STDIN and file-based I/O
- Practice reading Java documentation
