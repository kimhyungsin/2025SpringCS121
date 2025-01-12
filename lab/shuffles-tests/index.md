---
title: "Lab 12: Unit Tests for Shuffles"
layout: default
---

# {{ page.title }}
In this lab, you will write unit tests for the ["Shuffles, part 1"]({{'/homework/shuffles1/' | relative_url}}) homework.

If you are working on this homework with a partner, you should work on this lab together. If you are working on the homework individually, you should work on this lab individually.

## Instructions
You are developing two classes for the homework: `Card` and `ArrayDeck`. You should create corresponding test classes for each of these (i.e., `CardTests`, and `ArrayDeckTests`).

You should write unit tests using JUnit 5 Jupiter (version 5.4.2). You may find it helpful to consult `ExampleTests`, [the slides]({{'/slides/UnitTesting.pdf' | relative_url}}), and the resources linked below:

- [JUnit 5 User Guide](http://junit.org/junit5/docs/current/user-guide/)
- [JUnit 5 API Javadoc](https://junit.org/junit5/docs/5.4.2/api/) in particular, [the list of available assertions](https://junit.org/junit5/docs/5.4.2/api/org/junit/jupiter/api/Assertions.html)
- [Modern Best Practices for Testing in Java](https://phauer.com/2019/modern-best-practices-testing-java/)

You should **write at least one unit test for each of the methods described in [the assignment.]({{'/homework/shuffles1/' | relative_url}})** For example, you should write at least one test for `Card`'s `toString` method. In some tests, it may make sense to use multiple assertions. Together, your tests should exercise all the functionality described in the assignment. To fully test my code, I wrote 8 tests, with 16 assertions total.

**Hint:**
JUnit can test that code throws exceptions appropriately. To do this, JUnit uses "lambda expressions." Here is an example of an assertion that uses a lambda expression:
```
Assertions.assertThrows(java.lang.IllegalArgumentException.class,
                        () -> {new ArrayDeck(0);});
```
The second argument to `assertThrows()` is a lambda expression. This lambda expression describes the code that should be run to cause the `IllegalArgumentException` to be thrown (i.e., `new ArrayDeck(0);`). Lambda expressions allow you to pass code as an argument.

**Note:**
If the option to run your new test class (e.g., `CardTests`) doesn't appear in the "Run ..." dialogue, contact the instructor for help -- you may need to edit the project configuration.

## Submit
Complete the "Lab 12: Unit Tests for Shuffles" quiz on Canvas. You should submit a screenshot showing:

- The code for your unit tests
- The results of running your unit tests

This lab will be graded for completion, as part of your attendance and participation grade. However, to be counted towards your grade, you must write and run **unit tests for both classes.**

If you worked with a partner, **each of you should submit the quiz on Canvas,** since there isn’t an easy way to join groups on Canvas.

{% comment %}

## Enabling Unit Testing in a Project

1. In the project tab, right-click on the project name, and select New -> Package. Name the new package `test`. This just creates a folder named `test` in your project directory.
2. Open File -> "Project Structure", then navigate to "Project Settings" -> "Modules". Under "Sources", click on the `test` folder you created. Then click "Tests" to designate it as a Tests folder.
3. Create an `ExampleTests` class in the `test` folder.
4. This test can't be executed yet, because the JUnit package isn't available. So download it (perhaps via Maven), add the `.jar` files to the `.lift` directory. Next, under "Project Settings" add those `.jar` files to the COS 126 library.
5. You should now be able to execute the `ExampleTests` using the "Run" menu.

{% endcomment %}
