---
title: "Lab 7: Bug Hunt"
layout: default
---

# {{ page.title }}
In this lab, you will identify and fix bugs in several programs.

## Instructions
We encourage you to **work with a partner**.

Start by downloading [bug-hunt.zip](bug-hunt.zip), which contains the starter files for this assignment.

Each of the programs has at least one bug. To identify the bugs:
- Read through the program's code
- Try running the program with different inputs
- Read stack traces carefully. Stack traces are the messages displayed if the program crashes.
- Add print statements to show variable values
- Compare the program's output to calculations you perform by hand
- Trace the program by hand (e.g., by drawing a call tree)

### CodeCount.java
This program should return the number of times that the string `co*e` appears anywhere in the given string. Note that `*` is the wildcard character, so any character should be accepted in this place. For example, `code`, `cone`, and `cope` should all count, but `CODE` (uppercase) shouldn't count. For example:
```
> java-introcs CodeCount code
1
> java-introcs CodeCount aaaCODEcodebbb
1
> java-introcs CodeCount codebbbcone
2
```

**Hint:** If you can't find the bug in the program, write code to test `countCode()` on randomly generated strings until the program crashes. This technique is known as ["fuzz testing."](https://en.wikipedia.org/wiki/Fuzzing)

### PairStar.java
Given a string, this program should recursively compute a new string where identical chars that are adjacent in original string are separated from each other by a `*`. For example:
```
> java-introcs PairStar hello
hel*lo
> java-introcs PairStar xxyy
x*xy*y
```

**Note:** Don't try to fix the program by avoiding recursive programming!

### Turtle.java
This interactive program allows you to control the movement of a virtual turtle. For example:
```
> java-introcs Turtle
Initial coordinates: (0.00, 0.00)
Turn how many degrees? 0
Move how far? 10
Updated coordinates: (10.00, 0.00)
Turn how many degrees? -90
Move how far? 10
Updated coordinates: (10.00, -10.00)
```

**Note:** The program won't exit until it encounters the "end of file" character. On MacOS and Linux, you can type this with "Control+D", or on Windows "Control+Z" then "Enter".

**Note:** If you try to run Turtle before compiling it, you may accidentally open the textbook authors' Turtle class, which opens the Standard Draw window.

## Submit
Upload the following files to **Gradescope**:

- CodeCount.java
- PairStar.java
- Turtle.java
- **A completed readme.txt file,** including your names, etc.
  - Also, describe each of the bugs you discovered!

Finally, be sure to indicate your group member on Gradescope.

## Learning Goals
- Practice debugging
- Practice recursive programming

## Acknowledgements
Some of these programs were based on [CodingBat](https://codingbat.com/java).
