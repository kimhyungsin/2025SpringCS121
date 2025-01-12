---
title: "Lab 11: Expressions"
layout: default
---

# {{ page.title }}
In this lab, you will use stacks to convert postfix expressions to fully parenthesized infix expressions.

## Instructions
We encourage you to **work with a partner**.

First, download [expressions.zip](expressions.zip), which contains the starter files for this assignment.

Next, develop an `Expression` class with the following methods:

```
public class Expression {
    // Given a valid postfix expression, return its corresponding
    // fully parenthesized infix expression
    public static String postfixToInfix(String postfix)

    // Given a postfix expression on STDIN, print the corresponding
    // fully parenthesized infix expression to STDOUT.
    // Exit when STDIN is empty.
    public static void main(String[] args)
}
```

When finished, `Expression` should work like this:
```
> java-algs4 Expression
5 18 3 + * 2 -
( ( 5 * ( 18 + 3 ) ) - 2 )

> java-algs4 Expression
1
1
1 2 +
( 1 + 2 )
1 2 3 + -
( 1 - ( 2 + 3 ) )
```

For this assignment, we only consider `+`, `-`, `*`, and `/` operations on **positive integers**.

The following formats should be used for the `postfix` parameter and the String returned by the `postfixToInfix()` method:
- **The `postfix` parameter**: there is one space between each pair of adjacent terms, but no whitespace before the first one or after the last one. Here is an example: `"5 18 3 + * 2 -"`
- **The returned infix expression**: you can choose to either omit spaces between the terms (e.g., `"((5*(18+3))-2)"`), or to include spaces (e.g.`"( ( 5 * ( 18 + 3 ) ) - 2 )"`)

You should use [the textbook's `Stack` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Stack.html) to build the infix expression. [The "Stacks and Queues" slides]({{'/slides/CS.12.StacksQueues.pdf' | relative_url}}) show how to *evaluate* postfix expressions, but *converting* postfix to infix is slightly different. The example below shows the process of building an infix expression:

{% include image.html src="expression_example.svg" alt="Building an infix expression using a stack. When the plus operator is encountered, two items are popped, a plus sign is placed between then, they are surrounded by parentheses, and the result is pushed onto the stack." %}

**Hint:**
Start by developing unit tests for the `postfixToInfix()` method, so you can implement `postfixToInfix()` before writing the `main()` method.

**Hint**:
Given a `postfix` string with the above-described format, you can use `postfix.split(" ")` to split the given postfix string into an array of strings, such as `"5"`, `"18"`, ..., `"-"`. You can process this array of strings to create the corresponding infix expression.

**Hint**:
Each time you press "Return" or "Enter", `Expression` should process another line of input. `Expression` should not exit until it encounters the "end of file" character. On MacOS and Linux, you can type this with "Control+D", or on Windows "Control+Z".

## Submit
Upload the following files to **Gradescope**:

- Expression.java
- **A completed readme.txt file,** including your names, etc.

Also, be sure to **indicate your group member on Gradescope**.

## Learning Goals
- Practice using stacks
- Practice using standard input
