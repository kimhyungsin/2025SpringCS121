---
title: "Lab 8: Unit Testing"
layout: default
---

# {{ page.title }}
The goal of this lab is to practice creating unit tests for your code. Unit tests will help you develop code more efficiently by quickly identifying bugs.

Although course assignments usually won't required you to create unit tests, unit testing will make it easier to write programs, especially as assignments become more complicated.

## Instructions

In this lab, you will write unit tests to help develop your code for [the Traveling Salesperson Problem (TSP) homework]({{'/homework/tsp/' | relative_url}}). If you are working on the TSP homework with a partner, you should work on this lab together. If you are working on the homework individually, you should work on this lab individually.

### Task 1: Create Tour.java
If you haven't already, spend a few minutes reviewing the instructions for [the Traveling Salesperson Problem (TSP) homework]({{'/homework/tsp/' | relative_url}}).

Next, create `Tour.java`, based on the method signatures described in the assignment. Write just enough code to get `Tour.java` to compile. For example:
```
// Returns the length of this tour.
public double length() {
    return -1.0;
}
```
Notice that we return `-1.0`, which will never be correct, to make it clear that this method hasn't been implemented yet.

### Task 2: Run ExampleTests
First, open [the Traveling Salesperson Problem (TSP) starter files]({{'/homework/tsp/tsp.zip' | relative_url}}) in IntelliJ.

Next, open the "Run" menu, select "Run ...", then select "All in COS 126" or "ExampleTests". This will recompile your code, then run the tests in the `ExampleTests` class.

{% include image.html src="runalltests.png" alt="Run All in COS 126" max-width="250px" %}

The bottom of the IntelliJ window should show that all the tests passed.

{% include image.html src="allpassed.png" alt="Tests passed: 1 of 1 test" %}

### Task 3: Create tests for your own code
Your assignment is to write unit tests for your homework code. I recommend creating a `TourTests` class in the `test` directory.

You should write unit tests using JUnit 5 Jupiter (version 5.4.2). You may find it helpful to consult `ExampleTests`, [the slides]({{'/slides/UnitTesting.pdf' | relative_url}}), and the resources linked below:

- [JUnit 5 User Guide](http://junit.org/junit5/docs/current/user-guide/)
- [JUnit 5 API Javadoc](https://junit.org/junit5/docs/5.4.2/api/) in particular, [the list of available assertions](https://junit.org/junit5/docs/5.4.2/api/org/junit/jupiter/api/Assertions.html)
- [Modern Best Practices for Testing in Java](https://phauer.com/2019/modern-best-practices-testing-java/)

**Note:** It's okay to write tests before implementing the `Tour` class -- this is known as [test-driven development (TDD).](https://en.wikipedia.org/wiki/Test-driven_development) In this case, your tests should fail until you finish implementing `Tour`.

**Hints:**
- Examples of program output in the homework instructions can serve as a basis for tests
- To test my code thoroughly, I created the following tests:
  - `emptySize()`
  - `squareSize()`
  - `emptyLength()`
  - `squareLength()`
  - `emptyToString()`
  - `squareToString()`
  - `insertNearest()`
  - `insertSmallest()`
- It can sometimes be hard to test code unless you refactor it first. For example, moving potentially buggy code into static methods, so unit tests can call that code directly, instead of having to initialize objects and call methods on those objects.
- If the option to run your new test class (e.g., `TourTests`) doesn't appear in the "Run ..." dialogue, contact the instructor for help -- you may need to edit the project configuration

## Submit
Complete the "Lab 8: Unit Testing" quiz on Canvas. You should submit a screenshot showing:

- The code for your unit tests
- The results of running your unit tests

This lab will be graded for completion, as part of your attendance and participation grade. However, to be counted towards your grade, **you must write and run at least one unit test.**

If you worked with a partner, **each of you should submit the quiz on Canvas,** since there isn't an easy way to join groups on Canvas.

{% comment %}

## Enabling Unit Testing in a Project

1. In the project tab, right-click on the project name, and select New -> Package. Name the new package `test`. This just creates a folder named `test` in your project directory.
2. Open File -> "Project Structure", then navigate to "Project Settings" -> "Modules". Under "Sources", click on the `test` folder you created. Then click "Tests" to designate it as a Tests folder.
3. Create an `ExampleTests` class in the `test` folder.
4. This test can't be executed yet, because the JUnit package isn't available. So download it (perhaps via Maven), add the `.jar` files to the `.lift` directory. Next, under "Project Settings" add those `.jar` files to the COS 126 library.
5. You should now be able to execute the `ExampleTests` using the "Run" menu.

{% endcomment %}
