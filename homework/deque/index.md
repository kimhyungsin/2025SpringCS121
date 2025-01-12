---
title: "Homework 9: Deque"
layout: default
---

# {{ page.title }}
The goal of this homework is to implement the "deque" data structure. You will practice:
- Implementing a data structure
- Using generics
- Using exceptions
- Implementing the Iterable interface
- Thinking about order of growth while writing code

## Instructions
We encourage you to **work with a partner**.

### Task 1: Reading
Ensure you are familiar with the material we covered in lecture about:

- Linked lists
- Queues
- Stacks
- Generics
- Inheritance
- Exceptions

If not, review the recommended readings, especially Algorithms, chapter 1.3.

### Task 2: Download Starter Files

[deque.zip](deque.zip) contains the starter files for this assignment.


### Task 3: Implement Deque.java

A "deque" (pronounced "deck") is a double-ended queue. A deque is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure. You should create a generic data type `Deque` that implements the following API:

```
public class Deque<Item> implements Iterable<Item> {

    // construct an empty deque
    public Deque()

    // is the deque empty?
    public boolean isEmpty()

    // return the number of items on the deque
    public int size()

    // add the item to the front
    public void addFirst(Item item)

    // add the item to the back
    public void addLast(Item item)

    // remove and return the item from the front
    public Item removeFirst()

    // remove and return the item from the back
    public Item removeLast()

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args)

}
```

Java contains data structures that support these operations (e.g., [`LinkedList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html), [`ArrayList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)). For this assignment, **you cannot use these data structures**, or the assignment will be far too easy. You should only import two classes: [`java.util.Iterator`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Iterator.html) and [`java.util.NoSuchElementException`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/NoSuchElementException.html). **You will not receive credit** for a submission that uses a class like the Java Standard Library's [`LinkedList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html). Instead, you should use a nested Node class, similar to [the TSP assignment.]({{ '/homework/tsp/' | relative_url }})

#### Exceptions

Throw the specified exceptions for the following corner cases:

- Throw `IllegalArgumentException` if the client calls either `addFirst()` or `addLast()` with a `null` argument.
- Throw `java.util.NoSuchElementException` if the client calls either `removeFirst()` or `removeLast()` when the deque is empty.
- Throw `java.util.NoSuchElementException` if the client calls the `next()` method in the iterator when there are no more items to return.

**Hint:**
Use this syntax to throw an exception:
```
throw new IllegalArgumentException("A message describing the problem");
```
Of course, you should surround the exception with appropriate conditional logic, so it is only thrown when there is an actual problem.

#### Testing

We recommend writing JUnit tests to check every public method. Alternatively, you can add code to your `main()` method to verify that methods work as prescribed (e.g., by printing results to standard output).

#### Performance requirements

Your implementation must achieve the following worst-case performance requirements:

- A deque containing n items must use at most 48n + 192 bytes of memory, not including the memory for the items themselves.
- Each deque operation (including construction) must take constant time.
- Each iterator operation (including construction) must take constant time.

In summary:

| Operation                 | Big Theta     |
|---------------------------|---------------|
| Non-iterator operations   | $$\Theta(1)$$ |
| Iterator constructor      | $$\Theta(1)$$ |
| Other iterator operations | $$\Theta(1)$$ |

<br>

| Memory Usage              | Big Theta     |
|---------------------------|---------------|
| Deque                     | $$\Theta(n)$$ |
| Iterator                  | $$\Theta(1)$$ |

## Submit

Upload the following files to **Gradescope**:

* Deque.java
* **A completed readme.txt file,** including your names, etc.

Also, be sure to **indicate your group member on Gradescope**.

## Acknowledgements

Thanks to Princeton's COS126 materials, which served as a basis for this assignment.

{% comment %}
https://www.cs.princeton.edu/courses/archive/spring21/cos226/assignments/queues/specification.php
{% endcomment %}
