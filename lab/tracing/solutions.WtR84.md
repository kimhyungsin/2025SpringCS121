---
title: "Lab 4 Solutions: Tracing Programs"
layout: default
---

# {{ page.title }}

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

#### Solution

{% include image.html src="cs121_tracing_task1.svg" alt="Fibonacci recursion tree" %}

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

#### Solution

| F[0] | F[1] | F[2] | F[3] | F[4] | F[5] | i     |
|------|------|------|------|------|------|-------|
| 0    | 0    | 0    | 0    | 0    | 0    |       |
| **0**| 0    | 0    | 0    | 0    | 0    |       |
| 0    | **1**| 0    | 0    | 0    | 0    |       |
| 0    | 1    | 0    | 0    | 0    | 0    | **2** |
| 0    | 1    | **1**| 0    | 0    | 0    | 2     |
| 0    | 1    | 1    | 0    | 0    | 0    | **3** |
| 0    | 1    | 1    | **2**| 0    | 0    | 3     |
| 0    | 1    | 1    | 2    | 0    | 0    | **4** |
| 0    | 1    | 1    | 2    | **3**| 0    | 4     |
| 0    | 1    | 1    | 2    | 3    | 0    | **5** |
| 0    | 1    | 1    | 2    | 3    | **5**| 5     |
| 0    | 1    | 1    | 2    | 3    | 5    | **6** |

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
### Task 3.1
Trace the code and draw the recursion tree for `F(5)`

{% include image.html src="cs121_tracing_task3.1.svg" alt="Recursive Dynamic Fibonacci call tree" %}

### Task 3.2
Keep all the statements except change `memo[n] = F(n-1) + F(n-2);` to `memo[n] = F(n-2) + F(n-1);`. This just switches the positions for `F(n-1)` and `F(n-2)`. Now trace the slightly changed code and draw the new recursion tree for `F(5)`.

{% include image.html src="cs121_tracing_task3.2.svg" alt="Recursive Dynamic Fibonacci call tree" %}

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

#### Solutions

| Amount  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|---------|---|---|---|---|---|---|---|---|---|
| 1 (i=1) | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| 4 (i=2) | 0 | 1 | 2 | 3 | 1 | 2 | 3 | 4 | 2 |
| 5 (i=3) | 0 | 1 | 2 | 3 | 1 | 1 | 2 | 3 | 2 |

$$ c(i,j) = \begin{cases} 0 & \text{if } j = 0 \\
\frac{j}{d_1}               & \text{if } i = 1 \text{ and } j \% d_1 = 0 \\
\infty                      & \text{if } i = 1 \text{ and } j \% d_1 \neq 0 \\
\infty                      & \text{if } j < 0 \\
\text{min}\Big(c(i - 1, j), 1 + c(i, j - d_i)\Big) & \text{otherwise} \\
\end{cases} $$

| Amount  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|---------|---|---|---|---|---|---|---|---|---|
| 2 (i=1) | 0 | ∞ | 1 | ∞ | 2 | ∞ | 3 | ∞ | 4 |
| 4 (i=2) | 0 | ∞ | 1 | ∞ | 1 | ∞ | 2 | ∞ | 2 |
| 5 (i=3) | 0 | ∞ | 1 | ∞ | 1 | 1 | 2 | 2 | 2 |

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

#### Solution

{% include image.html src="cs121_tracing_task5.svg" alt="g(2) recursion tree" %}
