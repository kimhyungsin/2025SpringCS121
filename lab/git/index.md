---
title: "Lab 16: Git"
layout: default
---

# {{ page.title }}
In this lab, you will perform several activities using the git version control system.

## Instructions
We encourage you to **work with a partner**.

### Activity 1: Contribute Using GitHub
Start by loading this website: <http://cs121.us.to>

The code for the website is stored in this git repository:
<https://github.com/ClarkuCSCI/csci121-ascii-art>

This website contains several examples of [ASCII art](https://en.wikipedia.org/wiki/ASCII_art). In this activity, you will contribute ASCII artwork of your choice to the website. Guidelines for contributing to the website are described in the project's README. If the guidelines are unclear, please ask the instructor for help.

Follow these steps to contribute to the website:
1. Choose an example of ASCII art you would like to contribute. For example, from these websites:
  - [ASCII Art Archive](https://www.asciiart.eu/)
  - [Text-Image.com](https://www.text-image.com/convert/ascii.html)
2. Log into your own GitHub account
3. On the repository's page, click the "Fork" button to create your own copy of the repository
4. It is possible to commit your changes to your fork's "main" branch, but it is better practice to commit to a feature branch. Follow these steps to create a new branch:
  - On your fork's page, click the "main" button to show the list of branches
  - Next, type the name of a new branch (e.g., `llama-art`)
  - Finally, click the "Create branch: llama-art from 'main'" link
5. Follow these steps to make changes to your branch:
  - Enter the `art` folder, then click "Add file," "Create new file"
  - Enter a name for your file (e.g., `llama.txt`)
  - Paste the ASCII art into this file
  - Note the default commit message -- it's okay to leave this as-is
  - Check that your change will be committed to your branch
  - Click "Commit new file"
  - Return to the top-level directory of your fork
  - Click on `art.js`
  - Click the pencil icon to "Edit this file"
  - Based on the examples, add the data about your ASCII art
  - Edit the commit message to describe your change (e.g, "Add llama details to art.js")
  - Check that your change will be committed to your branch
  - Click "Commit changes"
6. Follow these steps to open a pull request for your changes:
  - Return to the top-level directory of your fork
  - Click the "Compare & pull request" button
  - Carefully review the details shown on this page. You should see that you are requesting that the changes made to the branch on your fork be applied to the `main` branch of the original repository. Scrolling down, you can review the edits you made earlier.
  - Write a more descriptive title for your pull request (by default, it uses the name of your branch), and optionally a brief description
  - Click the "Create pull request" button
7. The instructor will review your changes. They may request further edits, or merge your changes into the original repository's `main` branch.

### Activity 2: Contribute Using a Local Client
Making edits directly on GitHub doesn't always make sense. For example, if you were contributing a bug fix for a Java program, you would want to test your edits on your local device before committing changes and opening a pull request.

For this activity, you will improve this Java program: 
<https://github.com/ClarkuCSCI/csci121-anagram>

The program is designed to test whether two strings are anagrams of each other. However, the program has at least one bug! If you are unfamiliar with anagrams, here is [Wikipedia's explanation](https://en.wikipedia.org/wiki/Anagram):
> An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once. For example, the word *anagram* itself can be rearranged into *nag a ram*, also the word *binary* into *brainy* and the word *adobe* into *abode*.

Follow these steps:
1. First, install a local git client. Consider choosing one of these options:
  - [GitHub Desktop](https://desktop.github.com): this option is very user-friendly
  - [GitHub IntelliJ Plugin](https://www.jetbrains.com/help/idea/github.html): having git integrated into your IDE can be very helpful! First, you will need to re-enable the Git and GitHub plugins in the IntelliJ preferences. After restarting IntelliJ, git-related functionality will be enabled.
  - [GitHub VS Code Plugin](https://code.visualstudio.com/docs/editor/github): this is a good option if you plan to use VS Code for programming after this course. I recommend following ["Step 2: Configure Git and VSCode" from these instructions](https://peterstory.me/projects/config_docker_windows.html).
  - [git CLI](https://training.github.com/downloads/github-git-cheat-sheet.pdf): this is more challenging, but will prepare you for the bonus task. The `git` command is pre-installed on macOS and on most Linux distros. On Windows, you can either use git from [Windows Subsystem for Linux (WSL)](https://learn.microsoft.com/en-us/windows/wsl/install), or using [Git BASH](https://gitforwindows.org).
2. On GitHub, create a fork of the `csci121-anagram` repo
3. Using your client, clone your fork of the website's repository to your local device
4. Using your client, create a new branch for your new changes
5. Diagnose and fix the bug in the `Anagram` class
  - **Hint:** The project includes unit tests which demonstrate the bug, so I recommend running those first
  - **Note:** Only spend a couple minutes trying to figure out the bug, since this isn't the main point of the activity. If you are stuck, ask for help!
6. Using your client, commit your changes to your local branch
7. Using your client, push your changes to your fork on GitHub
8. On GitHub, open a pull request for your changes
  - The instructor will review your changes, but won't merge them (since we will be using this activity in the future)

### Bonus Activity: Git Internals
In the second activity, you cloned a repository to your local device. A local git repository contains all the data associated with the repository (e.g., information on past commits, etc.). Git repositories use a hidden `.git` directory to store data like this. Using the terminal, you can easily inspect this directory:
```
> ls csci121-anagram/.git/
COMMIT_EDITMSG config         hooks          info           objects
HEAD           description    index          logs           refs
```

The `objects` subdirectory is of particular interest, because it stores commit, tree, and blob data:
```
> ls csci121-anagram/.git/objects/*/
csci121-anagram/.git/objects/02/:
2abfc18add614914927b6ddfa3df461141b892

csci121-anagram/.git/objects/03/:
58489d41224031e7667565da5a85141deed944

csci121-anagram/.git/objects/11/:
a54f6ba1d2033facb878730df2100d56d567c0

csci121-anagram/.git/objects/1a/:
f3227e2e1e67e9eabc0538b2d800f933143ee8

...
```

Each object is stored by its SHA-1 checksum (i.e., hash), with the first two characters of the hash used to name the subdirectory, and the rest of the hash used to name the file (this is for performance reasons, so files will be distributed across multiple directories). The `git cat-file` command can be used to inspect these objects. `git cat-file -t` will print the type of an object, and `git cat-file -p` will print the contents of the object. For example, inspecting the object described in the last line of the output shown above:
```
> git cat-file -t 1af3227e2e1e67e9eabc0538b2d800f933143ee8
blob
> git cat-file -p 1af3227e2e1e67e9eabc0538b2d800f933143ee8
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnagramTests {

    @Test
    void testAnagrams() {
        Assertions.assertTrue(Anagram.anagram("rat", "tar"));
        Assertions.assertTrue(Anagram.anagram("rat", "art"));
        Assertions.assertTrue(Anagram.anagram("creative", "reactive"));
    }

    @Test
    void testNonAnagrams() {
        Assertions.assertFalse(Anagram.anagram("alphabet", "cat"));
        Assertions.assertFalse(Anagram.anagram("coin", "buzz"));
        Assertions.assertFalse(Anagram.anagram("banana", "ban"));
    }

}
```

For this activity, you should find the hashes of the commits, trees, and/or blobs associated with your commit (i.e., the commit in which you fixed the bug).

**Hint:**
Start by running `git log`, which will give you the hash of your commit. Then, use `git cat-file -p COMMITHASH` to view the details of this commit, which includes the hash of the associated tree. Viewing the details of this tree, you can find associate blobs and other trees, etc.

## Submit
Complete the "Lab 16: Git" quiz on Canvas.

This lab will be graded for completion, as part of your attendance and participation grade. However, to be counted towards your grade, **you must share links to your pull requests.**

If you worked with a partner, **each of you should submit the quiz on Canvas,** since there isn’t an easy way to join groups on Canvas.

## Further Reading
- [How to Write a Git Commit Message](https://cbea.ms/git-commit/)
- [Trunk-based development](https://www.atlassian.com/continuous-delivery/continuous-integration/trunk-based-development)

{% comment %}
## Instructor Note

To reset the `csci121-ascii-art` repo after each lab, run:
```
git checkout -b S22-S1
git push
git checkout main
git reset --hard REF
git push --force
```
{% endcomment %}
