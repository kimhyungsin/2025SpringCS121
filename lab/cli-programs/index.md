---
title: "Lab 13: Command-line Programs"
layout: default
---

# {{ page.title }}
The goal of this lab is to practice combining simple command-line programs using UNIX-syle pipes. You will also practice using symbol tables.

## Instructions

We encourage you to **work with a partner**.

Download [cli.zip](cli.zip), which contains the starter files for this assignment.

### Task 0: Setup

This assignment requires a Unix-style command-line environment. If you are running Linux or macOS, you are all set. But if you are running Windows, you have a couple options:

- Use a teammate's computer running Linux or macOS
- Follow [these instructions](https://docs.microsoft.com/en-us/windows/wsl/install) to install [Windows Subsystem for Linux (WSL)](https://docs.microsoft.com/en-us/windows/wsl/)
  - You may also need to follow steps 1 and 2 to [install the textbook command-line tools](https://lift.cs.princeton.edu/java/linux/) in the WSL environment
  - If unzipping the command-line tools fails, install the `unzip` command by running: `sudo apt update` then `sudo apt install zip`

**Hint:**
For those using WSL: suppose you downloaded the starter files to your Downloads folder in Windows. To locate the starter files in the WSL command-line, you need to change directory to that folder within the WSL command-line. For example:
```
$ cd /mnt/c/Users/peter/Downloads/cli
```
Notice how the Windows filesystem is accessed from the WSL command-line: each Windows drive (e.g., `c`) is mounted in the `/mnt` directory.

### Task 1: Background

- Refer to Algorithms chapters 3.1 and 3.4 for information about symbol tables and hash tables
- Refer to the ["Symbol Tables"]({{ '/slides/31ElementarySymbolTables.pdf' | relative_url }}) and ["Hash Tables"]({{ '/slides/34HashTables.pdf' | relative_url }})  lecture slides
- Read the information about Unix commands below

#### Unix Commands

There are many different ways to use a computer to solve a problem. Suppose you want to count the number of occurrences of different words in a book, perhaps to identify the most and least common words. You might:

- Write a program that accepts a text file as a command-line argument, counts the number of each word's occurrence, then prints the result
- Use a [word frequency counter website](http://www.writewords.org.uk/word_count.asp)
- Combine multiple command-line programs to achieve the same result

This final approach will be the focus of this lab, in order to demonstrate the influential [Unix Philosophy](https://en.wikipedia.org/wiki/Unix_philosophy) of software development. Multiple programs can be combined by connecting each program's STDOUT (standard out) to the next program's STDIN (standard in), forming a "pipeline."

Here is an example that illustrates this approach, using the `dickens.txt` file, which is included with the starter files:
```
It was the age of foolishness, it was the age of wisdom.
It was the best of times, it was the worst of times.
It was the epoch of belief, it was the epoch of incredulity.
It was the season of darkness, it was the season of light.
It was the spring of hope, it was the winter of despair.
```

Word counts can be calculated by combining three simple command-line programs:

- `tr` Performs substitutions on STDIN, printing results to STDOUT
- `sort` Sorts STDIN, printing to STDOUT
- `uniq` Prints the number of identical lines seen on STDIN to STDOUT. Relies on STDIN being sorted.

Here is the program pipeline and output. **Run this pipeline on your computer, and ensure you get the same result.**

```
$ tr " " "\n" < dickens.txt | tr -d "[:punct:]" | tr "[:upper:]" "[:lower:]" | sort | uniq -c
   2 age
   1 belief
   1 best
   1 darkness
   1 despair
   2 epoch
   1 foolishness
   1 hope
   1 incredulity
  10 it
   1 light
  10 of
   2 season
   1 spring
  10 the
   2 times
  10 was
   1 winter
   1 wisdom
   1 worst
```

Observe how we use `tr` to replace spaces with newlines (ensuring each word is on its own line), to remove punctuation, and to replace uppercase characters with lowercase ones. Next, we use `sort` and `uniq` to count the number of occurrences of each word. **Try removing commands in the pipeline, and observe how this affects the results**.

**Note:**
We use `< dickens.txt` to connect `dickens.txt` to the first program in the pipeline's STDIN.

**Note:**
`\n` is the newline or linefeed (LF) character. On macOS, Linux, and other Unix-based operating systems, `\n` is typed when you press "return" or "enter" on your keyboard. Things are slightly different (by default) on Windows, with two characters typed when you press "return" or "enter" on your keyboard: `\r\n`, which is referred to as CR/LF. On Windows, it is best practice to switch to your text editor to use the LF style line endings (`\n` only).

**Note:**
The leading `$` before the command simply indicates that the commands are run on a Unix shell. So if you try to copy-paste this example, you shouldn't copy the `$`.

For this lab, you will be writing two simple command-line programs, which can be combined to achieve a similar result.

### Task 2: Replace.java

This program should accept two arguments, `find` and `replace`, indicating the text to be found, and what it should be replaced with. It should read STDIN line-by-line, perform the substitutions, and output the results to STDOUT.

Examples:
```
$ java-introcs Replace was is < dickens.txt
It is the age of foolishness, it is the age of wisdom.
It is the best of times, it is the worst of times.
It is the epoch of belief, it is the epoch of incredulity.
It is the season of darkness, it is the season of light.
It is the spring of hope, it is the winter of despair.

$ java-introcs Replace "\\p{Punct}" "" < dickens.txt
It was the age of foolishness it was the age of wisdom
It was the best of times it was the worst of times
It was the epoch of belief it was the epoch of incredulity
It was the season of darkness it was the season of light
It was the spring of hope it was the winter of despair

$ java-introcs Replace " " "\\n" < dickens.txt
It
was
the
age
of
...

```

**Hint:**
You should use `String`'s `replaceAll()` method. If you read [the documentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#replaceAll(java.lang.String,java.lang.String)) you will see that `replaceAll()` accepts a "[regular expression](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html#sum)" as its first parameter. This makes `replaceAll()` more complicated to use, but is helpful because it allows us to easily replace all kinds of punctuation, as shown in the example above.

**Hint:**
You may have noticed the ``\\p`` in the second example. The `\` character (backslash) is commonly used as an escape character: it gives the subsequent character special meaning. Two backslashes used together (``\\``) represent the `\` character itself, rather than anything special. This is important, because in the second example we want the `Replace` program to get `\p{Punct}` (as described in the documentation for [regular expressions](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html#sum)). If we didn't use two backslashes, the command-line environment itself might interpret `\p` as something special, preventing the `\p` from reaching the `Replace` program. Determining the correct number of escape characters to use can be tricky, so we've given the following lines of code to help you parse the command-line arguments:

```
String find = args[0];
String replace = args[1].replace("\\n", "\n");
```

Notice that this isn't a perfect solution, since it only "unescapes" newline characters. A more general solution might use the 3rd party [`escapeJava()` method from Apache's StringEscapeUtils](https://commons.apache.org/proper/commons-lang/javadocs/api-2.6/org/apache/commons/lang/StringEscapeUtils.html#escapeJava(java.lang.String)).

**Note**:
You can also debug your program by typing input yourself, instead of redirecting a file to STDIN. When supplying input interactively, each time you press "Return" or "Enter", your program should process another line of input. Your program should not exit until it encounters the "end of file" character. On macOS and Linux, you can type this with "Control+D", or on Windows "Control+Z" then "Enter".


### Task 3: Count.java

This program should read STDIN line-by-line, using a symbol table to count how many times each line has been seen. When STDIN is empty, it should print the number of each line's occurrences to STDOUT.

Example, with `fruit.txt` as input:
```
$ java-introcs Count < fruit.txt
2 apple
1 apple banana carrot
1 carrot
```

You can use any of these symbol table implementations in your code:
- [The `java.util.HashMap` class](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html) from the Java standard library
- [The `ST` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/ST.html) from the textbook authors
- [The `SeparateChainingHashST` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/SeparateChainingHashST.html) from the textbook authors
- [The `LinearProbingHashST` class](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/LinearProbingHashST.html) from the textbook authors

**Hint:**
To achieve the behavior we want, you should convert each line to lowercase before storing it in the symbol table, making the count case-insensitive.

**Hint:**
Think carefully about what should be the "keys" to the symbol table, and what should be the "values."

### Task 4: Combine your programs

With both programs working, you can combine them to achieve the functionality we want:

```
$ java-introcs Replace " " "\\n" < dickens.txt | java-introcs Replace "\\p{Punct}" "" | java-introcs Count
10 was
1 winter
1 best
2 epoch
10 it
1 hope
1 wisdom
10 the
1 spring
2 times
1 incredulity
1 foolishness
1 light
10 of
2 season
1 worst
1 darkness
1 belief
1 despair
2 age
```

The order may differ, but otherwise your output should match the example above. Of course, we get the same result as the example which used `tr`, `sort`, and `uniq`.

## Submit

Upload the following files to **Gradescope**:

* Replace.java
* Count.java
* **A completed readme.txt file,** including your names, etc.

Also, be sure to **indicate your group member on Gradescope**.
