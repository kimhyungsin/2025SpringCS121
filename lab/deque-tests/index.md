---
title: "Lab 10: Unit Tests for Deque"
layout: default
---

# {{ page.title }}
In this lab, you will write unit tests for [the Deque homework assignment]({{'/homework/deque/' | relative_url}}).

If you are working on the homework with a partner, you should work on this lab together. If you are working on the homework individually, you should work on this lab individually.

## Instructions
You should write unit tests that exercise `Deque`'s functionality. I recommend creating a `DequeTests` class in the `test` directory.

You should write unit tests using JUnit 5 Jupiter (version 5.4.2). You may find it helpful to consult `ExampleTests`, [the slides]({{'/slides/UnitTesting.pdf' | relative_url}}), and the resources linked below:

- [JUnit 5 User Guide](http://junit.org/junit5/docs/current/user-guide/)
- [JUnit 5 API Javadoc](https://junit.org/junit5/docs/5.4.2/api/) in particular, [the list of available assertions](https://junit.org/junit5/docs/5.4.2/api/org/junit/jupiter/api/Assertions.html)
- [Modern Best Practices for Testing in Java](https://phauer.com/2019/modern-best-practices-testing-java/)

**Hint:**
To compile `Deque` before you've finished implementing it, you can return `null` from methods that return generics. For example:
```
public Item removeFirst() {
    return null;
}
```

**Hint:**
To test my code thoroughly, I created the following tests:
- `isEmpty()`
  - Tests that `isEmpty()` returns `true` for a new `Deque`
- `zeroSize()`
  - Tests that `size()` returns `0` for a new `Deque`
- `addFirst()`
  - Calls `addFirst()` a few times, then checks `isEmpty()` and `size()`
- `addLast()`
  - Calls `addLast()` a few times, then checks `isEmpty()` and `size()`
- `removeFirst()`
  - Calls `addFirst()` and `addLast()` a few times, then checks `removeFirst()`, `isEmpty()`, and `size()`
- `removeLast()`
  - Calls `addFirst()` and `addLast()` a few times, then checks `removeLast()`, `isEmpty()`, and `size()`
- `iterator()`
  - Calls `addFirst()`, `addLast()`, `removeFirst()`, and `removeLast()` a few times, then checks that the iterator's `hasNext()` and `next()` methods return the expected values

**Hint:**
JUnit can test that code throws exceptions appropriately. To do this, JUnit uses "lambda expressions." We haven't discussed lambda expressions before, and we won't use them much in the course. Here is an example of an assertion that uses a lambda expression:
```
Assertions.assertThrows(java.util.NoSuchElementException.class,
                        () -> {deque.removeLast();});
```
The second argument to `assertThrows()` is a lambda expression. This lambda expression describes the code that should be run to cause the `NoSuchElementException` to be thrown (i.e., `deque.removeLast();`). Essentially, lambda expressions allow you to define an unnamed method. This makes it easy to pass methods as parameters to other methods.

**Note:**
If the option to run your new test class (e.g., `DequeTests`) doesn't appear in the "Run ..." dialogue, contact the instructor for help -- you may need to edit the project configuration

## Submit
Complete the "Lab 10: Unit Tests for Deque" quiz on Canvas. You should submit a screenshot showing:

- The code for your unit tests
- The results of running your unit tests

This lab will be graded for completion, as part of your attendance and participation grade. However, to be counted towards your grade, you must write and run **at least three unit tests.**

If you worked with a partner, **each of you should submit the quiz on Canvas,** since there isn’t an easy way to join groups on Canvas.

{% comment %}

## Enabling Unit Testing in a Project

1. In the project tab, right-click on the project name, and select New -> Package. Name the new package `test`. This just creates a folder named `test` in your project directory.
2. Open File -> "Project Structure", then navigate to "Project Settings" -> "Modules". Under "Sources", click on the `test` folder you created. Then click "Tests" to designate it as a Tests folder.
3. Create an `ExampleTests` class in the `test` folder.
4. This test can't be executed yet, because the JUnit package isn't available. So download it (perhaps via Maven), add the `.jar` files to the `.lift` directory. Next, under "Project Settings" add those `.jar` files to the COS 126 library.
5. You should now be able to execute the `ExampleTests` using the "Run" menu.

{% endcomment %}
