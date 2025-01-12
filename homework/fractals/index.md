---
title: "Homework 4: Drawing Fractals"
layout: default
---

# {{ page.title }}
In this homework, you will draw a fractal tree. [Fractals](https://en.wikipedia.org/wiki/Fractal) are recursive geometric patterns, which occur in nature, mathematics, and engineering.

## Instructions

We encourage you to **work with a partner**.

### Task 1: Download Starter Files
[fractals.zip](fractals.zip) contains the starter files for this assignment.

### Task 2: Implement Tree.java
You will use the textbook's [StdDraw library](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdDraw.html) to draw your fractal tree. The only method you need to use is the `StdDraw.line()` method, but other methods may be helpful for debugging or customizing your tree.

To draw a tree, you should implement the `drawBranch()` method, which will recursively call itself:
```
/**
 * Draws a tree branch, and potentially sub-branches as well.
 *
 * @param x     the x-coordinate of the base of this branch
 * @param y     the y-coordinate of the base of this branch
 * @param size  the length of this branch
 * @param angle the angle of this branch
 */
public static void drawBranch(double x, double y, double size, double angle)
```

Your main method should contain:
```
public static void main(String[] args) {
    drawBranch(0.5, 0, 0.2, Math.PI / 2);
    StdDraw.save("tree.png");
}
```

After you've implemented `drawBranch()`, this `main()` method should draw the tree so its base is at the bottom-center of the canvas (i.e., at coordinates `(0.5, 0)`), with a trunk length of 0.2, pointing upwards. Then, the drawing is saved to the file `tree.png`.

The end result should look like one of these examples:
{% include image.html src="tree3.png" alt="A fractal tree." max-width="350px" %}
{% include image.html src="tree1.png" alt="A fractal tree." max-width="350px" %}
{% include image.html src="tree2.png" alt="A fractal tree." max-width="350px" %}

I recommend following these steps to implement `drawBranch()`:
1. First, draw a simple branch, without any recursive calls. This branch should be just a straight line. It should start at the appropriate coordinates, be of the appropriate length, and point in the direction specified by the angle.
  - You should use `Math.sin()` and `Math.cos()` to calculate the endpoint of the branch, based on the angle. [Remember your Trigonometric ratios!](https://en.wikipedia.org/wiki/Trigonometry#Trigonometric_ratios)
2. Next, add two recursive calls to `drawBranch()` to create two smaller branches. These branches should angle to the left and right.
  - Be sure to include a base case, or else you will make an infinite number of recursive calls! The `size` parameter can be used to define the base case: you don't want to draw extremely small branches.
  - Since `Math.sin()` and `Math.cos()` work with radians, it may be easier to write your code with radians. Refer to the unit circle for conversions between degrees and radians:

{% include image.html src="unit-circle.png" alt="A unit circle showing conversions between degrees and radians." max-width="500px" %}
<!-- Public domain image from: https://commons.wikimedia.org/wiki/File:Unit_circle_angles_color.svg -->

**Hint:**
Consider annotating your output with a grid, dividing the drawing area into 10 x 10 squares. This will help you compare your program's output to calculations you do by hand.
{% include image.html src="tree3-annotated.png" alt="A fractal tree, drawn on a 10 x 10 grid." max-width="350px" %}

**Hint:**
When debugging with print statements, print the angles as degrees. You can use `Math.toDegrees()`.

**Note:**
If you finish early, customize your tree. Consider adding color or variable branch width. However, don't make changes that would violate [self-similarity](https://en.wikipedia.org/wiki/Self-similarity).
{% include image.html src="tree3-colored.png" alt="A fractal tree with brown branches and green leaves." max-width="350px" %}

## Submit
**For full-credit:**
- Your code must call itself recursively
- At least five recursive calls should be visible in your output (i.e., there should be at least five branches from the tree's trunk to its leaves)
- Your output should exhibit [self-similarity](https://en.wikipedia.org/wiki/Self-similarity), as in the examples above

Upload the following files to **Gradescope**:

- Tree.java
- tree.png, showing your code's output
- **A completed readme.txt file,** including your names, etc.

Also, be sure to indicate your group member on Gradescope.

## Learning Goals

- Practice recursive programming
- Practice using Java drawing libraries
- Practice debugging

