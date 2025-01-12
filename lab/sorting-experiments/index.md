---
title: "Lab 14: Sorting Experiments"
layout: default
---

# {{ page.title }}
In this lab, you will compare the performance of different sorting algorithms, using the textbook's implementations of the algorithms.

## Instructions
We encourage you to **work with a partner**.

Similar to [the "Arrays vs Linked Lists" lab]({{ '/lab/arrays-vs-linked-lists/' | relative_url }}), you will run experiments to determine the growth rate of different sorting algorithms. Specifically, your goal is to determine $$b$$ in this equation:

$$ \text{Running time} = a N^b $$

Where $$b = \text{log}_2(t_N \div t_{N/2})$$, with $$t_N$$ representing the time it takes to sort an array of size $$N$$, and $$t_{N/2}$$ representing the time it takes to perform the operation on an array half that size.

**Hint:**
A reminder from algebra class:
$$ \text{log}_2(X) = \frac{\text{ln}(X)}{\text{ln}(2)} $$ 

**Hint:**
The $$a N^b$$ formula **will not** describe the growth rate of all operations. If this is the case, simply plot a graph of Size and Time, and give your best guess for the growth rate based on what you learned in class.

### Example: Insertion Sort

Download [sorting.zip](sorting.zip), which contains the starter files for this assignment.

The starter files contain `SortingExperiment.java`, which you will use to test the performance of the sorting algorithms. `SortingExperiment` accepts three arguments:

- `ALGORITHM`: Which algorithm to use for sorting
  - Accepts `INSERTION`, `SELECTION`, `MERGE`, `QUICK`, or `HEAP`
  - `MYSTERY` and `SECRET` are also accepted, for the bonus task
- `ORDER`: The initial order of the integers to be sorted
  - Accepts `RANDOM`, `ASCENDING`, or `DESCENDING`
- `N`: The number of integers to sort
  - Accepts positive integers

Take a few minutes to **review the code** in `SortingExperiment.java`.

Try compiling then running the program. The program will show the number of seconds it took to sort the integers with the specified algorithm:
```
> java-algs4 SortingExperiment INSERTION RANDOM 1000
Sorted in 0.01 seconds
```

I ran the program multiple times, doubling the number of integers each time. I stopped running experiments once the time exceeded 10 seconds. I used the data to estimate the exponent $$b$$ in the growth rate equation, as shown in the third column of the table. 

| Size $$(N)$$ | Time $$(t_N)$$ | Log Ratio $$(b = \text{log}_2(t_N \div t_{N/2}))$$ |
|-------|--------|---------|
| 1000 | 0.01 | |
| 2000 | 0.02 | $$ \text{ln}(0.02 \div 0.01)/\text{ln}(2) \approx 1 $$ |
| 4000 | 0.04  | $$ \text{ln}(0.04 \div 0.02)/\text{ln}(2) \approx 1 $$ |
| 8000 | 0.11 | $$ \text{ln}(0.11 \div 0.04)/\text{ln}(2) \approx 1.46 $$ |
| 16000 | 0.40 | $$ \text{ln}(0.40 \div 0.11)/\text{ln}(2) \approx 1.86 $$ |
| 32000 | 1.67  | $$ \text{ln}(1.67 \div 0.40)/\text{ln}(2) \approx 2.06 $$ |
| 64000 | 8.20 | $$ \text{ln}(8.20 \div 1.67)/\text{ln}(2) \approx 2.30 $$ |
| 128000 | 40.48 | $$ \text{ln}(40.48 \div 8.20)/\text{ln}(2) \approx 2.30 $$ |

I used [this spreadsheet](https://docs.google.com/spreadsheets/d/1JZ53B3j1wzJpohNd0Db-mFcY5tPdChAKrouTl5ap-CI/edit?usp=sharing) to perform the log ratio calculations, and to generate a graph of the data.

{% include image.html src="scatterplot.svg" alt="Scatterplot for insertion sort" %}

Since the experiments that took less than a second did not have many [significant figures](https://en.wikipedia.org/wiki/Significant_figures), I ignored their results. The other experiments suggest that $$ b \approx 2 $$. This means that $$ \text{running time} = a N^2 $$, suggesting that insertion sort grows **quadratically**, $$\Theta(N^2)$$.

### Task 1: Benchmark Four Sorts

Your task is to **complete similar experiments and calculations** for four different sorts:

- Selection sort
- Mergesort
- Quicksort
- Heapsort

I recommend making a copy of [this spreadsheet](https://docs.google.com/spreadsheets/d/1JZ53B3j1wzJpohNd0Db-mFcY5tPdChAKrouTl5ap-CI/edit?usp=sharing) for each, to assist with the calculations and graphing. In each experiment, you should sort `RANDOM` integers (i.e., not `ASCENDING` or `DESCENDING`).

**Hint:** To avoid repetitive copy-pasting, consider modifying `SortingExperiment.java` to run multiple doubling experiments, stopping when the runtime exceeds a fixed amount of time (e.g., 60 seconds). Then, you can copy-paste everything at once into the spreadsheet.

**Hint:** To get more accurate timings, consider running multiple trials for each size, and printing the average. With more trials, your results will have greater precision, so it makes sense to print more digits in the fractional part. When running multiple trials, remove the fixed seed from `Random`'s constructor in `getData()`, to ensure that different data is used for each trial. 

After making these improvements, you can print your results like this:
```
> java-algs4 SortingExperiment INSERTION RANDOM 1000
N       Average Time (seconds)
1000    0.003
2000    0.002
4000    0.008
8000    0.033
16000   0.129
32000   0.751
64000   2.881
128000  15.384
256000  66.835
```

### Task 2: Conclusions
Based on your experiments, which sorting algorithm is fastest at sorting large arrays of random integers?

**Note:**
We only sorted random integers in these experiments. Your results might differ if you sorted different values.

**Note:**
We did not test sorting small arrays (e.g., ten or fewer items), but as we described in class, some sorts perform well when sorting a large number of items, but perform comparatively worse when sorting small arrays. As a result, some system sorts fall back to insertion sort when sorting small (sub-)arrays. However, in absolute terms, small arrays can be sorted quickly, so accurate benchmarking would require a tool like the [Java Microbenchmark Harness (JMH)](https://javadevcentral.com/jmh-benchmark-with-examples). 

### Bonus Task: Identify Sorts
The starter files include code for two sorts, in the `MYSTERY` and `SECRET` classes. The real names of these sorts are given somewhere on [the Wikipedia page for sorting algorithms.](https://en.wikipedia.org/wiki/Sorting_algorithm) Measure the growth rate of the `MYSTERY` and `SECRET` sorts, and try to identify the actual names of these sorts. You can also inspect the classes' source code.

## Submit
Complete the "Lab 14: Sorting Experiments" quiz on Canvas.

This lab will be graded for completion, as part of your attendance and participation grade. However, to be counted towards your grade, **you must answer the multiple choice questions correctly.** If you answer incorrectly, you can retake the quiz after a short time.

If you worked with a partner, **each of you should submit the quiz on Canvas,** since there isn't an easy way to join groups on Canvas.
