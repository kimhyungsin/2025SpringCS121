---
title: "Homework 1: Hello, World"
layout: default
---

# {{ page.title }}

The goal of this homework is to familiarize you with programming in Java. In particular, you will practice:

* Developing with IntelliJ
* Using command-line arguments
* Using Java data types
* Using Java's Math class

## Instructions

### Task 1: Install IntelliJ On Your PC

Click the link below that matches your operating system, then follow steps 0 to 5 to install and use IntelliJ. **However, download this zip file ([hw1.zip](hw1.zip)) instead of the files linked in Step 1** -- you will open the "hw1" folder instead of the "hello" folder.

* [macOS](https://lift.cs.princeton.edu/java/mac/)
* [Windows](https://lift.cs.princeton.edu/java/windows/)
* [Linux](https://lift.cs.princeton.edu/java/linux/)

Note that the LIFT installer includes IntelliJ as well as several course-required libraries. So you must follow these instructions even if you installed IntelliJ prior to this course.

**Take screenshots of the Barnsley fern and colliding disks drawing windows** -- you will include them with your submission. If you can run these programs, that shows your programming environment is configured correctly, which will be important for future assignments.

**Note:** If you have an Apple Silicon Mac, IntelliJ may feel slow, because the LIFT installer includes the Intel version of IntelliJ. To speed things up, you can download and use [the Apple Silicon version of IntelliJ](https://www.jetbrains.com/idea/download/other.html). However, you should start by running the LIFT installer, since you still need the course-required libraries.

{% comment %}
**Note to instructors:**
Download the LIFT installers before class, in case they are too slow to download from Princeton.
{% endcomment %}

### Task 2: Reading and Exercises

* Read and do the exercises for "Preface," "Introduction," "Why learn another programming language?," "Let's look at a Java program", and "Java Data Types" in [Java for Python Programmers](https://runestone.academy/runestone/books/published/java4python/Java4Python/toctree.html).
* Read "Overview" through "Week 0: Hello World" in the [Java Style Guide](http://bit.ly/126StyleGuideS2021).
* Briefly familiarize yourself with the [Java Programming Cheatsheet](https://introcs.cs.princeton.edu/java/11cheatsheet/).


### Task 3: Implement Five Programs

Note that this is an **individual** assignment, and should be completed according to the collaboration policy described in [the syllabus]({{'/syllabus/' | relative_url}}).

#### HelloWorld.java

Write a program `HelloWorld.java` that prints the text "Hello, World".

**Hint:**
By following the instructions in Task 1, you should have already finished this program!


#### HiFour.java

This exercise demonstrates the use of the `String` data type and command-line arguments. Write a program `HiFour.java` that takes four first names as command-line arguments and prints a proper sentence with the names in **the reverse** of the order given.

Examples:

```
> java-introcs HiFour Alice Bob Carol Dave
Hi Dave, Carol, Bob, and Alice.

> java-introcs HiFour Alejandro Bahati Chandra Deshi
Hi Deshi, Chandra, Bahati, and Alejandro.
```

**Hint:**
The command line arguments can accessed in the `args` array.

**Hint:**
If you see the following message when running HiFour:
`Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 0`
you should check whether you forget to type a command-line argument when you executed the program.

<!-- Note: The textbook doesn't introduce CLI args, so I should mention them in lecture -->


#### Ordered.java

This exercise demonstrates the use of the `int` and `boolean` data types. Write a program `Ordered.java` that takes three integer command-line arguments, `x`, `y`, and `z`. **Define** a boolean variable whose value is `true` if the three values are either in strictly ascending order (`x` < `y` < `z`) or in strictly descending order (`x` > `y` > `z`), and `false` otherwise. Then, print this boolean value. You **must not** use any if statements (or switch statements).

Examples:

```
> java-introcs Ordered 10 17 49
true

> java-introcs Ordered 49 17 10
true

> java-introcs Ordered 10 49 17
false
```

**Hint:**
The command-line arguments (`args`) are stored as `String` variables, but to compare numeric values you should use `Integer` variables. Review ["Parsing command-line arguments" in the Java Cheatsheet](https://introcs.cs.princeton.edu/java/11cheatsheet/#CommandLineArguments) for examples of type conversions.

**Hint:**
If `b` is a `boolean` variable, then `System.out.println(b)` will print `true` or `false`, according to its value.


#### GreatCircle.java

This exercise demonstrates the use of the `double` data type and Java’s [Math](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html) library. The great circle distance is the shortest distance between two points on the surface of a sphere if you are constrained to travel along the surface. Write a program `GreatCircle.java` that takes four double command-line arguments `x1`, `y1`, `x2`, and `y2` (the latitude and longitude, in **degrees**, of two points on the surface of the earth) and prints the great circle distance (in nautical miles) between them. Use the following formula, which is derived from the spherical law of cosines:

$$ \text{distance in degrees} = \text{arccos}\Big(\text{sin}(x_1) \text{sin}(x_2) + \text{cos}(x_1) \text{cos}(x_2) \text{cos}(y_1-y_2)\Big)$$

$$ \text{distance in nautical miles} = 60 \times \text{distance in degrees} $$ 

This formula uses degrees, whereas Java's trigonometric functions use radians. Use `Math.toRadians()` and `Math.toDegrees()` to convert between the two. For reference, a nautical mile (approximately 1.151 regular miles) is 1/60 of a degree of an arc along a meridian of the Earth, and this is the reason for the 60 in the formula.

Examples:

```
> java-introcs GreatCircle 40.35 74.65 48.87 -2.33  	// Princeton to Paris
3185.1779271158425 nautical miles

> java-introcs GreatCircle 48.87 -2.33 40.35 74.65  	// Paris to Princeton
3185.1779271158425 nautical miles
```

**Hint:**
The [official Java reference for the Math class](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html) describes the methods you will need for this program. 

**Hint:**
Your output for GreatCircle may match the sample output in the assignment specification, except in the very last digit or two. Why is there this tiny discrepancy? Check whether you multiplied by 60 (to convert the arc to nautical miles) before or after you converted the angle from radians to degrees. Computers work with limited precision, so algebraically equivalent formulas can produce slightly different answers. We typically ignore such tiny discrepancies when grading.

#### RGBtoCMYK.java

This exercise demonstrates the use of  type conversions. Several different formats are used to represent color. For example, the primary format for LCD displays, digital cameras, and web pages — known as the RGB format — specifies the level of red (R), green (G), and blue (B) on an integer scale from 0 to 255 inclusive. The primary format for publishing books and magazines — known as the CMYK format — specifies the level of cyan (C), magenta (M), yellow (Y), and black (K) on a real scale from 0.0 to 1.0 inclusive.

Write a program `RGBtoCMYK.java` that converts from RGB format to CMYK format. Your program must take three integer command-line arguments, red, green, and blue; print the RGB values; then print the equivalent CMYK values using the following mathematical formulas:

$$\text{white} = \text{max}\Big\{ \frac{\text{red}}{255}, \frac{\text{green}}{255}, \frac{\text{blue}}{255} \Big\}$$

$$\text{cyan} = \Big(\text{white} - \frac{\text{red}}{255} \Big) \div \text{white} $$

$$\text{magenta} = \Big(\text{white} - \frac{\text{green}}{255} \Big) \div \text{white} $$

$$\text{yellow} = \Big(\text{white} - \frac{\text{blue}}{255} \Big) \div \text{white} $$

$$\text{black} = 1 - \text{white} $$

CMYK is a subtractive color space, because its primary colors are subtracted from white light to produce the resulting color: cyan absorbs red, magenta absorbs green, and yellow absorbs blue.

You **may not** use `if` statements on this assignment, but you may assume that the command-line arguments are not all simultaneously zero.

For full credit, your programs must not only work correctly for all valid inputs, but they should be easy to read.

Examples:

```
// indigo
> java-introcs RGBtoCMYK 75 0 130
red     = 75
green   = 0
blue    = 130

cyan    = 0.423076923076923
magenta = 1.0
yellow  = 0.0
black   = 0.4901960784313726

// Clark scarlet
> java-introcs RGBtoCMYK 204 0 0
red     = 204
green   = 0
blue    = 0

cyan    = 0.0
magenta = 1.0
yellow  = 1.0
black   = 0.19999999999999996
```

**Hint:**
Recall that `Math.max(x, y)` returns the maximum of `x` and `y`.

**Hint:**
In Java, dividing an integer by another integer always results in an integer. However, dividing an integer by a floating-point number results in a floating-point number.


## Submit

Upload these files to Gradescope:

* A screenshot of the Barnsley fern
* A screenshot of the colliding disks
* Your five .java files:
  * HelloWorld.java
  * HiFour.java
  * Ordered.java
  * GreatCircle.java
  * RGBtoCMYK.java
* **A completed readme.txt file,** including your name, etc.

## Acknowledgements

Thanks to Princeton's COS126 materials, which served as a basis for this assignment.
{% comment %}
https://docs.google.com/document/d/1GJGzr5CoMzm1Cd6T8Gaex6kWJzM2ijHJAJLBcNCGV1k/edit#
{% endcomment %}
