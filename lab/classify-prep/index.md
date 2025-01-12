---
title: "Lab 6: Preparation for the Image Classification Project"
layout: default
---

# {{ page.title }}
The goal of this lab is prepare you for the Image Classification Project (i.e., Homework 5).

## Instructions

This lab should be done **individually**.

### Task 1: Rotate.java

In class, we discussed a program that would flip an image upside down:

```
Picture source = new Picture(args[0]);
int width  = source.width();
int height = source.height();
Picture target = new Picture(width, height);
for (int col = 0; col < width; col++)
   for (int row = 0; row < height; row++)
      target.set(col, height-row-1, source.get(col, row));
target.show();
```

Using this code as a starting point, create a program `Rotate.java` that will instead rotate an image by 90 degrees. Use [classify.zip]({{ '/homework/classify1/classify.zip' | relative_url }}) from the "Image Classification" assignment as your starter project.

**Hint:**
Review the ["Picture" data type](https://introcs.cs.princeton.edu/java/11cheatsheet/#Picture), particularly the `get` and `set` methods.

**Hint:**
`mandrill.png` isn't square.

{% include image.html src="mandrill.png" alt="Original mandrill image" max-width="150px" %}
{% include image.html src="mandrill90.png" alt="Mandrill image, rotated 90 degrees" max-width="149px" %}

### Task 2: Review Homework Instructions

Carefully read all of the information about [Homework 5: Image Classification, Part 1]({{ '/homework/classify1/' | relative_url }}).

Write down answers to:

1. What is the image classification problem?
2. What is the machine learning process described in the documentation?
3. What are the inputs to `ImageClassifier.java`?
4. What methods must you write? 

**Please _don't_ discuss how you would solve the problem or how you would program the methods, etc.** The "how" part is your assignment and needs to be done by you, with guidance **only from the course staff**, if needed. Please contact us early if you have questions or need help.


## Before You Leave

- Ensure your `Rotate.java` program can rotate `mandrill.png`
- Write down your answers to the four questions in Task 2
- Ask any remaining questions you have about Homework 5

This lab won't be graded, due to the Wellness Day on Friday.

{% comment %}
## Submit

Create a .pdf file and upload it to Gradescope. It should contain:

* Your copy-pasted `Rotate.java` code
* A filled-in version of the information below:

```
/******************************************************************************
 ***   What is the image classification problem?                            ***
 ******************************************************************************/


/******************************************************************************
 ***   What is the machine learning process described in the documentation? ***
 ******************************************************************************/


/******************************************************************************
 ***   What are the inputs to ImageClassifier.java?                         ***
 ******************************************************************************/


/******************************************************************************
 ***   What methods must you write?                                         ***
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
{% endcomment %}