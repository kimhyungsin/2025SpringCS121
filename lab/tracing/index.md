---
title: "Lab 4: Tracing Programs"
layout: default
---

# {{ page.title }}
The goal of this lab is to practice tracing the execution of recursive and dynamic programs.

## Instructions

**We encourage you to work with a partner**. It is probably easiest to write your answers on paper.

### Task 1: Trace Naïve Fibonacci

Consider the following naïve recursion code for Fibonacci numbers:

```
public static long F(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    return F(n-1) + F(n-2);
}
```

Trace the code and draw the recursion tree for `F(5)`. 

As an example of a recursion tree, consider the `ruler(4)` recursion tree from lecture:

{% include image.html src="ruler.png" alt="Ruler recursion tree" %}

### Task 2: Trace Dynamic Fibonacci
Consider the following dynamic programming code for Fibonacci numbers:

```
long[] F = new long[n+1];
F[0] = 0;
F[1] = 1;
for (int i = 2; i <= n; i++)
    F[i] = F[i-1] + F[i-2];
```

Trace the code for `n` being 5, and write down the values of the elements in array `F`.

### Task 3: Trace Recursive Dynamic Fibonacci

Consider the following recursion code with memoization for Fibonacci numbers.

```
static long[] memo = new long[100];
public static long F(int n)
{
    if (n == 0) return 0;
    if (n == 1) return 1;
    if (memo[n] == 0)
        memo[n] = F(n-1) + F(n-2);
    return memo[n];
}
```

1. Trace the code and draw the recursion tree for `F(5)`
2. Keep all the statements except change `memo[n] = F(n-1) + F(n-2);` to `memo[n] = F(n-2) + F(n-1);`. This just switches the positions for `F(n-1)` and `F(n-2)`. Now trace the slightly changed code and draw the new recursion tree for `F(5)`.

### Task 4: Dynamic Coin Change

Consider the dynamic programming code for coin change:

$$ c(i,j) = \begin{cases} 0 & \text{if } j = 0 \\
\frac{j}{d_1}               & \text{if } i = 1 \\
\infty                      & \text{if } j < 0 \\
\text{min}\Big(c(i - 1, j), 1 + c(i, j - d_i)\Big) & \text{otherwise} \\
\end{cases} $$

1. Draw the 2D array `C[i, j]`, and trace the algorithm for a change amount of `8` and a coin set `{1, 4, 5}`.
2. The mathematical definition above for the function $$c(i, j)$$ has a problem for the base case $$i = 1$$. It works when the first coin value $$d_1$$ is 1. But if $$d_1$$ is not 1, the base case needs to be changed.
  - Consider a change amount of `8` and the coin set `{2, 4, 5}`. Trace using the definition above for $$c(i, j)$$ to identify the problem.
  - Try to fix the problem by changing the $$c(i, j)$$ definition for the base case $$i = 1$$. Check your fix by tracing your revised definition with an amount of `8` and the coin set `{2, 4, 5}`.

### Task 5: Mutually Recursive Functions

Consider the following pair of mutually recursive functions: 

```
public static int f(int n) {
    if (n == 0) return 0;
    return f(n-1) + g(n-1);
}

public static int g(int n) {
    if (n == 0) return 0;
    return g(n-1) + f(n);
}
```

What is the value of `g(2)`?

{% comment %}
I doubt students will finish early. But if they do, I can re-add this:

Trace [Permuations.java](https://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html)
{% endcomment %}


## Submit

Create a .pdf file and upload it to Gradescope. It should contain:

* Your responses to the questions above
* A filled-in version of the information below:

```
/******************************************************************************
 ***   You and your partner's name, if any.                                 ***
 ******************************************************************************/


/******************************************************************************
 ***   Do you attest that this work is your own, in accordance with the     ***
 ***   statement on academic integrity in the syllabus?                     ***
 ******************************************************************************/

Yes or no? 


/******************************************************************************
 ***   List any other comments here.                                        ***
 ******************************************************************************/

```

This will only be graded for completion, as part of your attendance and participation grade.
