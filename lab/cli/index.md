---
title: "Lab 2: Command-Line Interface"
layout: default
---

# {{ page.title }}
In this lab, you will practice using [the UNIX command-line interface (CLI)](https://en.wikipedia.org/wiki/Unix_shell). You will use the CLI to access a remote server, on which you will run several commands in order to discover a secret. Future assignments will assume familiarity with the CLI, so ask questions if anything is unclear!

## Instructions
Each student must complete all of the tasks described below. You should **try to work individually**, though if you get stuck you may ask other students, the TAs, or instructor for help.

### Task 1: Claim a Username and Password
You must use a unique username to access the remote server (e.g., `user1`). Usernames and passwords are projected and listed on the signup sheet. Write your name on the signup sheet to claim a username. Then, write your username and password down somewhere, so I don't have to project them for the entire lab.

### Task 2: Access the Server via SSH
The [Secure Shell (SSH)](https://en.wikipedia.org/wiki/Secure_Shell) allows you to securely access the CLI of another computer. After connecting via SSH, all data transferred between your device and the device you are connected to is encrypted for security.

If your device runs macOS or Linux, you can simply open the Terminal app, then type the following command. `PORT` will vary based on your lab section, and `USERNAME` is the username you claimed in Task 1.
```
ssh -p PORT USERNAME@cs121.us.to
```
Then type your password when prompted.

If your device runs Windows, I recommend [installing Windows Subsystem for Linux (WSL)](https://docs.microsoft.com/en-us/windows/wsl/install), which will allow you to open a Linux terminal on your PC. From the Linux terminal, you can run the `ssh` command. Alternatively, you could try using the `ssh` command built in to Windows, by running it directly from PowerShell. Another option is to install and use [the PuTTY utility](https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html).

**Note:**
When you type your password, you won't see it displayed (i.e., "echoed") on the screen. This is for security, to thwart [shoulder surfing](https://en.wikipedia.org/wiki/Shoulder_surfing_(computer_security)).

**Note:**
Before SSH was created, people connected to remote devices using a command called [Telnet](https://en.wikipedia.org/wiki/Telnet). However, Telnet doesn't encrypt data, which would make it easy for hackers to see everything transferred between connected devices, including your password. For this reason, Telnet is rarely used today.

### Task 3: Practice Using UNIX Commands
Now that you have access to the remote server's CLI, you can run different programs, or commands, on the server. There are many [standard UNIX commands](https://en.wikipedia.org/wiki/List_of_Unix_commands), which you will find on (almost) any macOS or Linux device. Here are some basic commands, which you will use today. **Practice using them**, and ask if you have any questions.

#### `pwd` print working directory
When using the CLI, it important to keep track of which directory (AKA folder) you are "in." You are always "in" a particular directory, and this directory is referred to as your "working directory." It is important to keep track of your working directory, since the effect of commands varies based on your working directory.

The `pwd` command shows your current working directory. For example:
```
$ pwd
/home/user1
```
After typing `pwd` and pressing "Return," `/home/user1` is printed. This tells me that I am within the `user1` directory, which is located within the `home` directory, which is located at the root of the filesystem. All directories are either immediate or indirect descendents of the root directory, `/`. Conceptually, directories are [organized as a tree](https://en.wikipedia.org/wiki/Unix_filesystem).

When you first SSH to a server, you usually start in your "home directory." Typically, each user is assigned a unique home directory, often named the same as the user themselves, where they are allowed to add, edit, and delete files. For security, such operations are often restricted in other directories on the server. 

**Note:**
The `$` sign used in the example above simply indicates your shell prompt. It is used to distinguish between input (things you type) and output (things printed by commands). As such, you should **not** type the `$` sign in the examples we give. The `$` is displayed as part of the "shell prompt." Everything you type on the CLI is parsed by a shell program, which can run other programs based on what you type. For this lab, you will be interacting with the `bash` shell, but [many different shells exist](https://en.wikipedia.org/wiki/Comparison_of_command_shells). Personally, I prefer to use [the fish shell](https://fishshell.com) on my own devices, but `bash` is much more widely used, which is why we are using it in this lab.

#### `ls` list directory contents
Directories can contain other directories and files. To list the names of all the directories and files within the working directory, simply type:
```
$ ls
```
If nothing is printed, that means that the current directory is either empty, or contains only hidden directories or files.

To show hidden files, type:
```
$ ls -al
total 20
drwxr-xr-x 2 user1 user1 4096 Jan  3 18:26 .
drwxr-xr-x 1 root  root  4096 Jan  3 18:26 ..
-rw-r--r-- 1 user1 user1  220 Apr  4  2018 .bash_logout
-rw-r--r-- 1 user1 user1 3771 Apr  4  2018 .bashrc
-rw-r--r-- 1 user1 user1  807 Apr  4  2018 .profile
```
The `a` tells `ls` to print "all" directory entries. The `l` tells `ls` to print using the "long" format. As you can see, there are several hidden files in our home directory. However, we won't further concern ourselves with hidden files in this lab.

To list the contents of another directory, type:
```
$ ls -a /home
.       user12  user18  user23  user29  user34  user4   user45  user50
..      user13  user19  user24  user3   user35  user40  user46  user6
shared  user14  user2   user25  user30  user36  user41  user47  user7
user1   user15  user20  user26  user31  user37  user42  user48  user8
user10  user16  user21  user27  user32  user38  user43  user49  user9
user11  user17  user22  user28  user33  user39  user44  user5
```
So you can see that typing `ls` without the name of a directory lists the contents of the current working directory, whereas if you give the name of a directory, the contents of that directory will be listed.

**Note:** The first thing you type on the command line is the name of the command itself (e.g., `ls`). Everything that follows is an "argument" to the command. This is analogous to how you might pass arguments, or parameters, to a function when programming. Often, `-` is used to prefix arguments, which allows the command to distinguish between configuration flags (e.g., the `-a` flag tells `ls` to print even hidden files and directories) and positional arguments (e.g., the name of a directory).

#### `cd` change the working directory
As mentioned earlier, when you first log into a device, your initial working directory is typically your home folder. However, it is useful to be able to change your working directory. You can do this using the `cd` command, as shown in the examples below.
```
$ pwd
/home/user1
$ cd /
$ pwd
/
$ cd ~
$ pwd
/home/user1
$ cd ..
$ pwd
/home
$ cd user1
$ pwd
/home/user1
```
As shown in the examples, you can either type the path of a directory, or use a special directory name. `~` corresponds to your home directory. `..` corresponds to the parent directory.

#### `cp` copy files
The `cp`, or copy, command allows you to make a copy of a file or directory. For example:
```
$ cp .profile my_profile_backup
```
First, you specify the source file or directory. Next, you specify the destination file or directory.

#### `man` an interface to the reference manuals
Even the basic UNIX commands have many advanced capabilities. Certainly, it is possible to Google search for documentation for these commands. However, it is better to read the manuals included with the operating system you are using, since subtle differences exist between different operating systems.

To read the documentation for a command, type:
```
$ man cp
```
You can scroll through the documentation using the arrow keys. You can search by typing `/term`, then "Return." You can exit by typing `q`. The `man` command actually uses the `more` command, so advanced navigation options are described in the documentation for `more`:
```
$ man more
```

#### `echo` write to standard output
The `echo` command is quite simple: it simply repeats all arguments you pass to it. For example:
```
$ echo Hello World!
Hello World!
```

`echo` is particularly useful for printing the values of "environment variables." Command-line programs can be configured using both parameters and "environment variables." For example, the `HOME` variable specifies your home directory:
```
$ echo $HOME
/home/user1
```
`HOME` and other environment variables can be accessed by any program you run from the CLI.

One of the most important environment variables is `PATH`:
```
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games
```
Each time you type the name of a command, your shell searches the directories listed in `PATH` for programs that have that name. If you list the contents of these directories, you will see the names of many different commands.

Typically, most commands you run will be stored in a directory included in `PATH`. However, it is also possible to run commands that aren't in `PATH`. To do this, simply specify the path to the command. For example, the following would run a program named `my_program`, located in the current working directory:
```
$ ./my_program
```
Note that `.` is short for the current working directory, but you could also write out the full path to the program (e.g., `/home/user1/my_program`).

#### Additional commands
Here are some additional commands you should be aware of. We have either previously discussed them, or they are not essential to this lab:
- `javac` compile .java files
- `java` run java programs
- `cat` print files
- `more` display files on a page-by-page basis
- `rm` remove files or directories
- `find` find files
- `grep` search file(s) for a pattern

You should also be aware of command-line text editors, which allow you to edit files from the CLI. Popular editors include [nano](https://en.wikipedia.org/wiki/GNU_nano), [Vim](https://en.wikipedia.org/wiki/Vim_(text_editor)), and [Emacs](https://en.wikipedia.org/wiki/Emacs).

### Task 3: Compile and Run `Hint` Program
Now that you have some familiarity with the basics of the CLI, you will perform a simple "scavenger hunt."

First, you should compile and run the `Hint` Java program. The source code for the program is located in the `/home/shared` directory.
1. Copy the source code into your home directory
2. Compile the program
3. Run the program

### Task 4: Run the Secret Program
The `Hint` program will tell you where the secret program is. Run the secret program, which will reveal your secret word.

### Bonus Tasks

#### Find the ASCII Cat
Somewhere on the server is a text file containing [ASCII art](https://en.wikipedia.org/wiki/ASCII_art) depicting a cat. Use the `grep` command to locate the file, and the `cat` command to display the file's contents.

**Hint:** The ASCII art is credited to "Felix Lee."

**Hint:** Review the man page for `grep`. If you're still unsure how to use `grep`, search online for examples of using `grep`.

**Hint:** Sometimes `grep` can seem to hang. This may happen when it is reading from standard input, since `grep` will wait for you to type something. If you are searching through the entire filesystem, `grep` can also hang when it is reading very large files. In particular, you should avoid searching through [the `/proc` directory](https://www.linux.com/news/discover-possibilities-proc-directory/), because `/proc` contains special types of files. You can exit the currently running program by typing `Control+C`.

#### Determine Remote System Details
What kind of computer is `cs121.us.to`? Is it [an Intel-based server or workstation](https://www.dell.com/en-us/shop/dell-poweredge-servers/sc/servers), [a Raspberry Pi](https://www.raspberrypi.com/products/), or something else?

What operating system is `cs121.us.to` running? Is it running macOS, [a Linux distribution](https://en.wikipedia.org/wiki/Linux), [a BSD-based OS](https://en.wikipedia.org/wiki/Berkeley_Software_Distribution), or something else? 


## Submit
Complete the "Lab 2: Command-Line Interface" quiz on Canvas. You will submit:

* The username you claimed for this lab
* The output of the secret program
* Any recommendations for improving this lab in the future

This lab will be graded for completion, as part of your attendance and participation grade.

## Acknowledgements
Domain name provided by [Free DNS](https://freedns.afraid.org/).
