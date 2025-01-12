---
title: "Lab 5: Recursive Grep"
layout: default
---

# {{ page.title }}
In this lab, you will create a version of the `grep` command-line utility that supports recursively searching for files matching a target string.

## Instructions
We encourage you to **work with a partner**. If you worked with a partner on [the Simple Grep lab]({{'/lab/grep/' | relative_url}}), **work with the same partner on this assignment.** You can reuse some of your code from [the Simple Grep lab]({{'/lab/grep/' | relative_url}}) in this assignment.

Start by downloading [recursive-grep.zip](recursive-grep.zip), which contains the starter files for this assignment.

Explore the `poems` subdirectory. Notice that the text files containing the poems are stored in subdirectories by author and download source.

Your task is to create a `RecursiveGrep` class which supports recursively searching within a directory to find files which match a target string. When your program finds a line matching the target string, it should print the name of the file along with the matching line.

For example, searching through `poems` directory for the string `dream`:
```
> java-introcs RecursiveGrep dream poems
poems/project-gutenberg/alexander-pushkin/the-roussalka.txt The dreamy wave she vanished under.
poems/public-domain-poetry/charlotte-bronte/life.txt Life, believe, is not a dream
poems/public-domain-poetry/charlotte-bronte/the-wood.txt We'll seek a couch of dreamless ease;
poems/public-domain-poetry/edgar-allan-poe/the-raven.txt Doubting, dreaming dreams no mortals ever dared to dream before;
poems/public-domain-poetry/edgar-allan-poe/the-raven.txt And his eyes have all the seeming of a demon's that is dreaming,
```

You should implement the `searchFile()` and `findFiles()` methods:
```
/**
 * Print lines within the file that match the target string.
 *
 * @param target the string we are searching for
 * @param file   the text file we are searching within
 */
public static void searchFile(String target, File file)
```
```
/**
 * Find and search within files for lines matching the target string.
 *
 * @param target    the string we are searching for
 * @param directory the directory we are searching within
 */
public static void findFiles(String target, File directory)
```

Your main method should contain:
```
public static void main(String[] args) {
    findFiles(args[0], new File(args[1]));
}
```

Before you start coding, decide:
1. Which operations will form your **recursive step** (i.e., they need to be performed recursively)?
2. Which operations will form your **base case** (i.e., they don't need to be performed recursively)?

**Hint:**
To read from a file, you can use [the textbook's `In` class](https://introcs.cs.princeton.edu/java/stdlib/javadoc/In.html). To find files you will need to use [the Java Standard Library's `File` class](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html). In particular, read the documentation for the methods `listFiles()`, `isDirectory()`, and `isFile()`.

**Hint:**
If IntelliJ doesn't add it automatically, you may need to add this import statement to the top of your `RecursiveGrep.java` file to use the `File` class:
```
import java.io.File;
```

## Submit

Upload the following files to **Gradescope**:

- RecursiveGrep.java
- **A completed readme.txt file,** including your names, etc.
  - Also, respond to: "Describe your program's recursive step and base case."

Finally, be sure to indicate your group member on Gradescope.

## Learning Goals
- Practice using file-based I/O
- Practice reading Java documentation
- Practice recursive programming
