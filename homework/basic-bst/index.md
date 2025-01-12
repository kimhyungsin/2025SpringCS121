---
title: "Homework 12: Basic Binary Search Tree"
layout: default
---

# {{ page.title }}
The goal of this homework is to practice:

- Working with binary search trees (BSTs)
- Implementing Iterable
- Writing recursive methods

## Instructions

This is an **individual** assignment. 

Start by downloading [bst.zip](bst.zip), which contains the starter files for this assignment.

### Task 1: Read BasicBST.java

For this assignment, you will be adding code to `BasicBST.java`. `BasicBST.java` includes a collection of completed methods copied from the textbook's `algs4` library's [BST.java](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BST.java.html).

Start by **reading the code** in `BasicBST.java`.

### Task 2: Implement Key height()
You will notice that `BasicBST` has several `height()` methods. You will be completing the method with this signature:
```
/**
 * Returns the height of the specified key in the BasicBST tree.
 * if the key does not exist in the tree, return -1;
 * if the key is in a leaf node, return 0.
 * For a non-leaf node, its height is one more than its
 * taller child.
 *
 * @return the height of the specified key in the BasicBST tree
 */

public int height(Key key) {
    // TODO Implement this method
    return -1;
}
```

**Hint**:
Refer to the implementation of the other `height()` methods for ideas about how to implement this version.

If your code is working, the main method should give the following output:
```
java-algs4 BasicBST < TinyInput.txt
Tree printed in level order, showing keys and heights:
S 5
E 4
X 0
A 1
R 3
C 0
H 2
M 1
L 0
P 0

Tree printed in order, showing keys and heights:
A 1
C 0
E 4
H 2
L 0
M 1
P 0
R 3
S 5
X 0
...
```

### Task 3: Implement leaves()
`BasicBST` also includes several methods that return `Iterable` objects. You should complete the following method:

```
/**
 * Returns all keys of the leaves in the BasicBST as an {@code Iterable}.
 * If the tree is empty, return an iterable with no key in it.
 *
 * @return all keys in the leaves of the BasicBST, in the order from
 * the smallest leaf key to the largest leaf key
 */


public Iterable<Key> leaves() {
    Queue<Key> queue = new Queue<Key>();
    // TODO: write code to add the leaf keys to the queue
    return queue;
}
```

**Hint**:
Refer to the implementation of `levelOrder()` and `keys()` for ideas about how to implement `leaves()`.

If your code is working, the main method should give the following output:
```
java-algs4 BasicBST < TinyInput.txt
...
Leaf keys printed in order: 
C L P X 
```

### Hints

- Consider writing a `toString()` method, so you can visualize the nodes in the BST
- Consider writing unit tests for `height()` and `leaves()`. You can also test using the main method, but you should test using more than just `TinyInput.txt`. Consider creating and testing with text files containing:
  - All characters in the alphabet
  - Short words
  - A paragraph of text

## Submit

Upload the following files to **Gradescope**:

- BasicBST.java
- **A completed readme.txt file** containing hours spent on the assignment, etc.
