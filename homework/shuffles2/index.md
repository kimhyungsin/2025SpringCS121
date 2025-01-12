---
title: "Homework 11: Shuffles, part 2"
layout: default
---

# {{ page.title }}
In this homework you will practice:

- Implementing `equals()` and `hashCode()`
- Using HashMaps
- Using Queues
- Using LinkedLists
- Implementing a breadth-first search algorithm

## Instructions

We encourage you to **work with a partner**.

Start by downloading [Shuffling.java](Shuffling.java), which contains starter code for this assignment. You should add it to the IntelliJ project you used in [part 1 of this assignment]({{'/homework/shuffles1/' | relative_url}}).

### Finish Card.java
You should implement the `equals()` and `hashCode()` methods in `Card.java`.

**Ensure your code is correct before proceeding.** You can test your code using a `main()` method, or ideally [a unit testing class]({{'/lab/shuffles-tests/' | relative_url}}). Be aware that incorrect implementation of these methods can cause [strange behavior](https://stackoverflow.com/questions/57343069/how-does-containskey-really-work-in-java-when-working-with-map) if a `Card` is used as a key in a `HashMap`.

**Hint:**
Follow the recipe for `equals()` given in [the "Symbol Tables" lecture]({{'/slides/31ElementarySymbolTables.pdf' | relative_url}}), and follow the recipe for `hashCode()` given in [the "Hash Tables" lecture]({{'/slides/34HashTables.pdf' | relative_url}}).

### Finish ArrayDeck.java
You should implement the `equals()` and `iterator()` methods in `ArrayDeck.java`.
 
**Ensure your code is correct before proceeding.** You can test your code using a `main()` method, or ideally [a unit testing class]({{'/lab/shuffles-tests/' | relative_url}}).

**Hint:**
Review the `Iterable` interface described in [the "Advanced Java, part 1" lecture]({{'/slides/AdvancedJava1.pdf' | relative_url}}).

### Implement Shuffling.java
You will complete the following methods in `Shuffling.java`:
- `minOutShuffles()`
- `minInShuffles()`
- `computeDeckSequence()`
- `findShortestShuffles()`

You should implement them in the listed order.

#### minOutShuffles() and minInShuffles()
If you repeatedly shuffle a deck of cards using "out shuffles" or "in shuffles," it will eventually return to its original order. These methods should return the number times a deck must be shuffled to return it to its original order. Note that the full method signature for `minOutShuffles()` is:
```
public static int minOutShuffles(int maxRank)
```
The `maxRank` parameter allows this method to work for different sized decks.

Your code for `minInShuffles()` should be very similar to your code for `minOutShuffles()`.

If your code is working correctly, the main method should show the following results:

```
> java-introcs Shuffling 10
Minimum Numbers of Shuffles
  maxRank    Min Out Shuffles  Min In Shuffles  
  1          2                 4                
  2          3                 6                
  3          10                12               
  4          4                 8                
  5          18                6                
  6          11                20               
  7          18                28               
  8          5                 10               
  9          12                36               
  10         12                20               
...
```

#### computeDeckSequence()
This method will perform a sequence of "out" and "in" shuffling operations on a deck. The full method signature is:
```
public static Queue<Deck> computeDeckSequence(Deck deck, String shuffleSequence)
```
The `deck` parameter is a `Deck`, which represents the initial state of the deck, before shuffling operations have been performed.

The `shuffleSequence` parameter is a `String`, representing a sequence of shuffling operations, where `"I"` represents an "in shuffle" and `"O"` represents an "out shuffle".

Finally, notice that the return type `Queue` is [the Java Standard Library's `Queue` interface](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html), **not the `Queue` class in the textbook's `algs4` library.**

If your code is working correctly, you should see the following results:
```
> java-introcs Shuffling 10
...
Shuffle Sequences
  maxRank=1, shuffleSequence="OO"
    AC AD AH AS 
    AC AH AD AS 
    AC AD AH AS 
  maxRank=2, shuffleSequence="IIIIII"
    AC 2C AD 2D AH 2H AS 2S 
    AH AC 2H 2C AS AD 2S 2D 
    AS AH AD AC 2S 2H 2D 2C 
    2S AS 2H AH 2D AD 2C AC 
    2D 2S AD AS 2C 2H AC AH 
    2C 2D 2H 2S AC AD AH AS 
    AC 2C AD 2D AH 2H AS 2S 
  maxRank=1, shuffleSequence="OIOI"
    AC AD AH AS 
    AC AH AD AS 
    AD AC AS AH 
    AD AS AC AH 
    AC AD AH AS 
...
```
Notice that performing two "out shuffles" on a deck with four cards (`maxRank=1`) returns the deck to its original state, which corresponds to the result from `minOutShuffles(1)`. Also, notice that it is possible to find combinations of in and out shuffles that return the deck to its original state, as shown in the third example above.

#### findShortestShuffles()
The full method signature for `findShortestShuffles()` is:
```
public static HashMap<Card, String> findShortestShuffles(int maxRank)
```

For a factory-ordered deck determined by the specified `maxRank`, this method will consider each card. For each card, it will find the shortest shuffling sequence needed to bring that card to the top of the factory-ordered deck. It will store these shuffling sequences in a `HashMap`, where each `Card` is a key and a `String` representing that card's shortest shuffling sequence is the corresponding value. The returned `HashMap` should contain the shortest shuffling sequence for each card in the deck.

Refer to the Java Standard Library documentation as you write your code:
- [`HashMap` class](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html)
  - **Note:** `HashMap` is an implementation of a hash table, a type of symbol table. The ["Hash Tables"]({{ '/slides/34HashTables.pdf' | relative_url }}) and ["Symbol Tables"]({{ '/slides/31ElementarySymbolTables.pdf' | relative_url }}) slides may be helpful.
- [`Queue` interface](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html)
- [`LinkedList` class](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html)

Here are solutions for the problem with `maxRank=1` , where `'O'` and `'I'` stand for "out shuffle" and "in shuffle," respectively:

| Card | Shortest Sequence |
|------|-------------------|
| AC   | `""`              |
| AD   | `"OI"`            |
| AH   | `"I"`             |
| AS   | `"II"`            |


Let's take a closer look at how these sequences move each card to the top of the deck. Note that when `maxRank=1`, the original deck sequence is: `AC AD AH AS`.

- `AC` is already at the top of the deck, so no shuffling operations are needed
- `AD` (and all the other cards) does not start at the top of the deck, so some shuffling operations will be needed. Applying the sequent `"OI"` will means applying and "out shuffle" followed by an "in shuffle". This will tranform the deck as follows:
  - `AC AD AH AS` becomes
  - `AC AH AD AS` after applying an "out shuffle", which becomes
  - `AD AC AS AH` after applying an "in shuffle," leaving `AD` on the top
- `AH` will be on the top after the sequence `"I"`
  - `AC AD AH AS` becomes
  - `AH AC AS AD` after applying an "in shuffle," leaving `AH` on the top
- `AS` will be on the top after the sequence `"II"`
  - `AC AD AH AS` becomes
  - `AH AC AS AD` after applying an "in shuffle"
  - `AS AH AD AC` after applying an "in shuffle," leaving `AS` on the top

Implementing this method is somewhat challenging, so we will include a detailed explanation. The main idea is to use a queue to systematically explore the decks that can be created using combinations of "in shuffles" and "out shuffles." If you explore the possible decks using a "breadth-first search," then the **first time** you see a card on the top of a deck, you will have found the shortest shuffling sequence for that card, and you can use the `HashMap` to record the sequence for that card.

First, you should define a private inner class to bundle a `Deck` and a `String`. Each `DeckShufflePair` object will represent a sequence of "in shuffles" and "out shuffles" (`shuffle`) that brings a deck from the original factory order to `deck`'s order.
```
private static class DeckShufflePair {
    private final Deck deck;
    private final String shuffle;
    public DeckShufflePair(Deck deck, String shuffle) {
        this.deck = deck;
        this.shuffle = shuffle;
    }
}
```

We will use `(deck, sequence)` as the notation to represent a pair. For example `(AC AD AH AS, "")` is a pair, indicating the deck `AC AD AH AS` can be reached with an empty sequence (i.e.m no shuffle is needed). Here are two more example pairs `(AC AH AD AS, "O")` and `(AH AC AS AD, "I")`, representing the decks that can be reached by one "out shuffle" or one "in shuffle," respectively.

`DeckShufflePair` will be the element type of our queue. We will define a local variable, called `queue`, of the type `Queue<DeckShufflePair>`, and we will use Java’s `LinkedList` to construct it (since a `LinkedList` can behave like a `Queue`).

Of course, we will also need a `HashMap<Card, String>`, since this is our return type. We will use this `HashMap` to save the solution. It will store each `Card` in the deck as a key. For each `Card`, the corresponding value will be a `String` representing the shortest shuffling sequence needed to put this card on top of the deck. We create a local variable, called `shortestShuffles`, for this. We will use `Card: String` to represent the information in this hash table (e.g. `AC: ""`, `AH: "I"`).

Below is the process for solving the problem with `maxRank=1`. It illustrates the general algorithm for solving the problem with an arbitrary `maxRank`.

##### Initialization
- `queue = (AC AD AH AS, "")`, representing a factory-order deck and no shuffling
- `shortestShuffles = (AC: "")`, since the card `AC` is at the top without any shuffling

We will use a while loop to repeat the computation describe below until a shortest shuffling sequence is found for each card. In each round of the loop body execution, we will dequeue the `DeckShufflePair` at the front of `queue`, and add two more pairs to the end of `queue`, reflecting the fact that, from the dequeued pair, we can generate two additional pairs by performing an "in shuffle" and, separately, an "out shuffle". As we do this, we will keep track of the shortest sequence for each `Card`.

Next, we explain several rounds of the loop's execution, using our example of `maxRank=1`.

##### Round 1
Do a dequeue on `queue` to get `(AC AD AH AS, "")`, and `queue` becomes empty. We will refer to the deck and the string in the dequeued pair as the "dequeued deck" and the "dequeued sequence."

Some pseudo-code:
- If the top card of the dequeued deck is already a key in `shortestShuffles`, this means that the shortest sequence for the top card has already been found
  - So we don't change `shortestShuffles`
- Otherwise, we have just found the shortest sequence for the top card!
  - So we add the top card and the dequeued sequence to `shortestShuffles`

In this round, the top card is `AC`, which is already a key in `shortestShuffles`, so we don't change `shortestShuffles`.

Next, we make two copies of the dequeued deck `AC AD AH AS`:
- With the first copy, we perform an "in shuffle." We create a `DeckShufflePair` with this copy and the dequeued sequence (`""`) concatenated with `"I"`, forming `(AH AC AS AD, "I")`. This indicates that this deck can be reached from the original, factory-order deck with one "in shuffle." Finally, we add this `DeckShufflePair` to `queue`. Now our queue has one item: `queue = (front) (AH AC AS AD, "I")`
- With the second copy, we perform an "out shuffle." We create a `DeckShufflePair` with this copy and the dequeued sequence (`""`) concatenated with `"O"`, forming `(AC AH AD AS, "O")`. This indicates that this deck can be reached from the original, factory-order deck with one "out shuffle." Finally, we add this `DeckShufflePair` to `queue`. Now our queue has one item: `queue = (front) (AH AC AS AD, "I"), (AC AH AD AS, "O")`

##### Round 2
Do a dequeue on `queue` to get `(AH AC AS AD, "I")`, leaving `queue` with only `(AC AH AD AS, "O")`.

The top of the dequeued deck `AH AC AS AD` is `AH`, which isn't yet in `shortestShuffles`. This means we hadn't already found a shortest sequence for `AH`, so the dequeued shuffling sequence is the shortest sequence for bringing this card to the top. We add this top card and the dequeued sequence to `shortestShuffles`. Now shortestShuffles includes the following card-string associations: `shortestShuffles = (AC: ""), (AH: "I")`

Next, we make two copies of the dequeued deck `AH AC AS AD`:
- With the first copy, we perform an "in shuffle." We create a `DeckShufflePair` with this copy and the dequeued sequence (`"I"`) concatenated with `"I"`, forming `(AS AH AD AC, "II")`. This indicates that this deck can be reached from the original, factory-order deck with two "in shuffles." Finally, we add this `DeckShufflePair` to `queue`. Now our queue has two items: `queue = (front) (AC AH AD AS, "O"), (AS AH AD AC, "II")`
- With the second copy, we perform an "out shuffle." We create a `DeckShufflePair` with this copy and the dequeued sequence (`"I"`) concatenated with `"O"`, forming `(AC AH AD AS, "IO")`. This indicates that this deck can be reached from the original, factory-order deck with an "in shuffle" followed by an "out shuffle." Finally, we add this `DeckShufflePair` to `queue`. Now our queue has three items: `queue = (front) (AC AH AD AS, "O"), (AS AH AD AC, "II"), (AH AS AC AD, "IO")`

You may have noticed that `queue` grows in size with each execution of the while loop.

##### Round 3
No shortest sequence found.

##### Round 4
Shortest sequence found for `AS`.

##### Round 5
No shortest sequence found.

##### Round 6
Rounds 3, 4, 5, and 6 will work similarly to Rounds 1 and 2. In Round 6, we will find the shortest sequence for `AD`, which will mean that we have found the shortest sequence for all cards. At this point, the while loop can be exited. Programmatically, you can do this by checking the size of `shortestShuffles`.

When `maxRank=1`, we'll know we have all the shortest sequences when `shortestShuffles` contains four card-string associations. Specifically, `shortestShuffles` will contain `shortestShuffles = (AC: ""), (AH: "I"), (AS: "II"), (AD: "OI")`

##### Testing
If your code is working correctly, the main method should show the following results:
```
> java-introcs Shuffling 10
...
Shortest Shuffles
  maxRank=1
    Card  Shuffle Sequence
    AC    
    AS    II
    AD    OI
    AH    I
  maxRank=2
    Card  Shuffle Sequence
    2S    III
    2C    OOI
    AC    
    AS    II
    2D    OII
    AD    OI
    2H    IOI
    AH    I
...
```


{% comment %}
And here are solutions for the problem with `maxRank=2`:

| Card | Shortest Sequence |
|------|-------------------|
| AC   | `""`              |
| 2C   | `"OOI"`           |
| AD   | `"OI"`            |
| 2D   | `"OII"`           |
| AH   | `"I"`             |
| 2H   | `"IOI"`           |
| AS   | `"II"`            |
| 2S   | `"III"`           |
{% endcomment %}

## Submit

Upload the following files to **Gradescope**:

- Card.java
- ArrayDeck.java
- Shuffling.java
- **A completed readme_part2.txt file,** containing the information shown below

Also, be sure to **indicate your group member on Gradescope**.

```
Programming Assignment: Shuffles: Part 2


/**********************************************************************
 * All teammates' names                                               *
 **********************************************************************/


/**********************************************************************
 * Approximate number of hours to complete this assignment            *
 **********************************************************************/

Number of hours for 1st TEAMMATE's NAME:

Number of hours for 2nd TEAMMATE's NAME:


/**********************************************************************
 *  Did you receive help from classmates, past students, or
 *  anyone else? If so, please list their names.  ("A Sunday lab TA"
 *  or "Office hours on Thursday" is ok if you don't know their name.)
 **********************************************************************/

Yes or no?


/**********************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 **********************************************************************/

Yes or no?


/**********************************************************************
 *  List any other comments here.                                     
 **********************************************************************/


```

