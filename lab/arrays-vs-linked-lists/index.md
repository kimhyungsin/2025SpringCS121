---
title: "Lab 9: Arrays vs Linked Lists"
layout: default
---

# {{ page.title }}
The goal of this lab is to practice analyzing the performance of data structures. Specifically, you will compare the performance characteristics of Java Arrays and LinkedLists.

## Instructions

We encourage you to **work with a partner**.

### Task 1: Review Array and LinkedList

For the Array and LinkedList data types, I have include sample code for:

- Random initialization
- Retrieving an element at an index
- Retrieving the index of an element
- Inserting an element into the middle

Carefully review the example code below -- be sure to ask questions if you don't understand parts of it.

#### Array

You are probably most familiar with Java Arrays. They can be defined using literals, and support the square-bracket indexing notation. Notice that we import `java.util.Arrays` and `java.lang.reflect.Array` to get access to some helper methods. We need `java.util.ArrayList` for the element insertion.

```
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Array;

// Shortcut initialization, using literals
Double [] d1 = {1.0, 1.1, 1.2, 1.3};
// Alternative initialization
Double [] d2 = new Double[4];
d1[0] = 1.0;
d1[1] = 1.1;
d1[2] = 1.2;
d1[3] = 1.3;

// Retrieving an element at an index, using square-brackets
System.out.println(d1[1]);
// Retrieving an element at an index, using a method
System.out.println(Array.get(d1, 1));

// Sorting the array (this is needed, or else the binary search won't work)
Arrays.sort(d1);

// Retrieving the index of an element
System.out.println(Arrays.binarySearch(d1, 1.1));

// Insert an element into the middle. This is complicated because the length
// of an Array is fixed when it is initialized, so we must:
// 1. Copy the Array into an ArrayList
// 2. Perform the insertion there
// 3. Convert the ArrayList back into an Array
// It's okay if you find this confusing - it is confusing!
ArrayList<Double> d1_temp = new ArrayList<Double>(Arrays.asList(d1));
d1_temp.add(d1.length / 2, 2.0);
d1 = d1_temp.toArray(new Double[5]);
```

Documentation:

- [java.util.ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)
- [java.util.Arrays](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Arrays.html)
- [java.lang.reflect.Array](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflect/Array.html)

#### LinkedList

We talked about linked lists in lecture. Java's implementation is of a "doubly-linked" list, but otherwise it is quite similar to what we discussed.

```
import java.util.Comparator;
import java.util.LinkedList;

// Initialization
LinkedList<Double> d = new LinkedList<Double>();
d.addLast(1.0);
d.addLast(1.1);
d.addLast(1.2);
d.addLast(1.3);

// Retrieving an element at an index
System.out.println(d.get(1));

// Sorting the list
d.sort(Comparator.naturalOrder());

// Retrieving the index of an element
System.out.println(d.indexOf(1.1));

// Insert an element into the middle
d.add(d.size() / 2, 2.0);
```

Documentation: 
- [java.util.Comparator](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html)
- [java.util.LinkedList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html)

{% comment %}
#### ArrayList
```
import java.util.ArrayList;

// Initialization
ArrayList<Double> d = new ArrayList<Double>();
d.add(1.0);
d.add(1.1);
d.add(1.2);
d.add(1.3);

// Retrieving an element at an index
System.out.println(d.get(1));

// Retrieving the index of an element
System.out.println(d.indexOf(1.1));

// Insert an element into the middle
TODO
```
Documentation:
- [java.util.ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)
{% endcomment %}

### Task 2: Complete ArrayExperiments.java

First, download the starter files: [arrays-vs-linked-lists.zip](arrays-vs-linked-lists.zip)

Next, you will complete the `ArrayExperiments.java` program. This program should calculate how long it takes to perform different operations using Arrays.

ArrayExperiments accepts two parameters: the `size` of the Array and the number of experiment `trials` to run. It should run the number of trials specified, then print the average time taken to run a single trial. The program is mostly complete, but there is one `TODO`, which indicates code you will need to write.

Carefully read the `ArrayExperiments.java`, and make sure you understand how it works. Noticed that we use `System.nanoTime` to time the operations -- this provides greater precision than the `Stopwatch` class provided by the textbook authors. We need this extra precision because some of the operations we are timing are extremely fast.

```
import java.util.Arrays;

public class ArrayExperiments {

    public static Double[] initialize(int size) {
        // An array of random doubles
        Double[] d = new Double[size];
        for (int i = 0; i < size; i++) {
            d[i] = StdRandom.uniform();
        }
        return d;
    }

    public static long retrieve_by_index(Double[] d) {
        int i = (int) (StdRandom.uniform() * d.length);
        long startTime = System.nanoTime();
        Double r = d[i];
        return (System.nanoTime() - startTime);
    }

    public static long retrieve_by_element(Double[] d) {
        Double r = d[(int) (StdRandom.uniform() * d.length)];
        long startTime = System.nanoTime();
        // TODO Find element r in Array d using Arrays.binarySearch()
        return (System.nanoTime() - startTime);
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        long trials = Long.parseLong(args[1]);

        Double[] d = initialize(size);

        System.out.println("Timing retrieval by index...");
        long index_retrieval = 0;
        for (long t = 0; t < trials; t++) {
            index_retrieval += retrieve_by_index(d);
        }
        System.out.println(
                "Retrieval by index: " + (index_retrieval / ((double) trials))
                        + " nanoseconds on average");

        System.out.println("Sorting array...");
        Arrays.sort(d);

        System.out.println("Timing retrieval by element...");
        long element_retrieval = 0;
        for (long t = 0; t < trials; t++) {
            element_retrieval += retrieve_by_element(d);
        }
        System.out.println(
                "Retrieval by element: " + element_retrieval / ((double) trials)
                        + " nanoseconds on average");

    }
}
```

When the program is finished, it should work like this:

```
> java-introcs ArrayExperiments 500000 10000000
Timing retrieval by index...
Retrieval by index: 30.5145226 nanoseconds on average
Sorting array...
Timing retrieval by element...
Retrieval by element: 467.0542783 nanoseconds on average
```

The program already supports timing "retrieval of an element from a random index". You simply need to finish the `retrieve_by_element` method, which will time "retrieving the index of a random element".

### Task 3: Write LinkedListExperiments.java

Next, you will write `LinkedListExperiments.java`. This program will be very similar to `ArrayExperiments.java`, expect that it will use LinkedLists instead of Arrays. We recommend modifying a copy of `ArrayExperiments.java` to use LinkedList operations.

### Task 4: Estimate Running Time and Runtime Complexity

Next, you will run experiments to determine the growth rate of the operations on Arrays and LinkedLists. Specifically, your goal is to determine $$b$$ in this equation:

$$ \text{Running time} = a N^b $$

Where $$b = \text{log}_2(t_N \div t_{N/2})$$, with $$t_N$$ representing the time it takes to perform the operation on an Array or LinkedList of size $$N$$, and $$t_{N/2}$$ representing the time it takes to perform the operation on a data structure half that size.

**Hint**: A reminder from algebra class:

$$ \text{log}_2(X) = \frac{\text{ln}(X)}{\text{ln}(2)} $$ 

**Hint**: The $$a N^b$$ formula may not describe the growth rate of all operations. If this is the case, try plotting a graph of Size and Time, and simply give your best guess for the growth rate.

#### Array: Retrieval of an element by index

Here are my results for the retrieval of an element by index, calculated using 10,000,000 trials.

I ran the program multiple times, doubling the size of the data structure each time. I used the data to estimate the exponent $$b$$ in the growth rate equation, as shown in the third column of the table.

| Size $$(N)$$ | Time $$(t_N)$$ | Log Ratio $$(b = \text{log}_2(t_N \div t_{N/2}))$$ |
|-------|--------|---------|
| 10000 | 30.6725031 | ... |
| 20000 | 29.9520132 | $$\text{ln}(29.9520132 \div 30.6725031)/\text{ln}(2) \approx -0.03429 \approx 0 $$ |
| 40000 | 30.8575394 | ... |
| 80000 | 30.5059546 | ... |
| 160000 | 30.5368424 | ... |
| 320000 | 31.28404 | ... |
| 640000 | 30.8744206 | $$\text{ln}(30.8744206 \div 31.28404)/\text{ln}(2) \approx -0.01901 \approx 0 $$ |
| 1280000 | 31.1663318 | ... |
| 2560000 | 31.0351684 | ... |
| 5120000 | 31.1552376 | ... |
| 10240000 | 31.493183 | $$\text{ln}(31.493183 \div 31.1552376)/\text{ln}(2) \approx 0.01556 \approx 0 $$ |

You are welcome to use [this spreadsheet](https://docs.google.com/spreadsheets/d/1TFKAb4XjhayMaDQlacycgZ_r0gWurKGg_2fZFMn0D6E/edit?usp=sharing) to perform the log ratio calculations, and to generate a graph of the data. If you use the spreadsheet, inspect the forumula in the third column, so you understand how $$b$$ is calculated.

{% include image.html src="spreadsheet.png" alt="A spreadsheet with size, time, and log ratio columns, populated with data about retrieval of an element by index from an array. The spreadsheet contains a scatterplot of size and time, showing a linear relationship." %}

My experiments suggest that:

$$ b \approx 0 $$

$$ \text{Running time} = a N^0 = a $$

That is, retrieval of an element by index is a constant order operation. Based on the data, it seems that $$a \approx 30$$ nanoseconds. So:

$$ \text{Running time} \approx 30 \text{ nanoseconds} $$

Now, this constant may differ from one computer to another. So we are more interested in the Big Theta runtime complexity, which is:

$$ \Theta(1) $$

Your task is to **complete similar experiments and calculations** for the following:

- Array: Retrieval of an element's index
- LinkedList: Retrieval of an element's index
- LinkedList: Retrieval of an element by index

### Bonus Task: Inserting an element
If you finish early, you should modify `ArrayExperiments.java` and `LinkedListExperiments.java` to perform an additional experiment: testing the time it takes to insert an element into the **beginning** and **middle** of each data structure.


## Submit
Complete the "Lab 9: Arrays vs Linked Lists" quiz on Canvas. You should submit screenshots showing:

- The code for your `ArrayExperiments` class
- The code for your `LinkedListExperiments` class

This lab will be graded for completion, as part of your attendance and participation grade. However, to be counted towards your grade, **you must answer the multiple choice questions correctly.** If you answer incorrectly, you can retake the quiz after a short time.

If you worked with a partner, **each of you should submit the quiz on Canvas,** since there isn't an easy way to join groups on Canvas.
