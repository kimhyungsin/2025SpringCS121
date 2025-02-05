---
title: "Homework 10: Shuffles, part 1"
layout: default
---

# {{ page.title }}
The goal of this homework is to practice:

- Using exceptions
- Thinking about order of growth while writing code

## Instructions

We encourage you to **work with a partner**.

Start by downloading [shuffles.zip](shuffles.zip), which contains the starter files for this assignment.

### Card.java and ArrayDeck.java

In this part of the assignment you will create classes to represent a deck of playing cards that can be shuffled. A standard deck of playing cards consists of 52 cards. Each card is one of four suits: Clubs, Diamonds, Hearts, and Spades. There are 13 cards of each suit, each with its own rank: Ace, 2, 3, .., 9, 10, Jack, Queen, King. We will represent each card by an abbreviated rank and suit, such as "2C" for the two of Clubs, "JH" for the Jack of Hearts, etc. Refer to the tables below for a full set of abbreviations.

| Rank  | Abbreviation |
|-------|--------------|
| Ace   | A            |
| 2     | 2            |
| 3     | 3            |
| ...   | ...          |
| 10    | 10           |
| Jack  | J            |
| Queen | Q            |
| King  | K            |

| Suit     | Abbreviation |
|----------|--------------|
| Clubs    | C            |
| Diamonds | D            |
| Hearts   | H            |
| Spades   | S            |

In a brand new, factory-sealed deck of playing cards, the cards are placed in order of suit (Clubs, Diamonds, Hearts, then Spades) and, within each suit, in order of rank from Ace to King (A, 2, 3, ..., 9, 10, J, Q, then K).

```
AC 2C 3C 4C 5C 6C 7C 8C 9C 10C JC QC KC AD 2D 3D 4D 5D 6D 7D 8D 9D 10D JD QD KD AH 2H 3H 4H 5H 6H 7H 8H 9H 10H JH QH KH AS 2S 3S 4S 5S 6S 7S 8S 9S 10S JS QS KS
```

The starter files contains three classes for this problem. You will complete some methods in Part 1 of the assignment, but others in Part 2.
- `Card.java`: represents a playing card, with a rank and suit. The constructor has been completed for you.
  - In Part 1, you will complete the `toString()` method
  - In Part 2, you will complete:
    - `equals()`
    - `hashCode()`
- `Deck.java`: an interface describing operations that can be performed on a deck of playing cards. **Do not edit this file.**
- `ArrayDeck.java`: an implementation of the `Deck` interface. It should store `Card` objects in an array.
  - In Part 1, you will complete:
    - `ArrayDeck(int maxRank)` constructor
    - `toString()`
    - `size()`
    - `copy()`
    - `peekTop()`
    - `outShuffle()`
    - `inShuffle()`
  - In Part 2, you will complete:
    - `equals()`
    - `iterator()`

The functionality and time performance requirements of the methods are specified in **the comment sections**.

#### Constructor
As we mentioned before, a standard deck has 52 cards. However, it will be easier to develop your code using smaller decks of cards. For this reason, `ArrayDeck`'s constructor has a `maxRank` parameter. This constructor should create decks of `4 * maxRank` cards in factory order. For example, invoking `new ArrayDeck(3)` should create a deck of 12 cards in the following order:
```
AC 2C 3C AD 2D 3D AH 2H 3H AS 2S 3S
```

#### Shuffling
`ArrayDeck` must have two shuffling methods: `outShuffle` and `inShuffle`.

The "faro out shuffle" splits a deck in half, interleaves the cards of the two halves, and leaves the original top card at the top and the original bottom card at the bottom. As an example, using the 12-card deck described above, an out shuffle would conceptually look like this:
```
Index:           0  1  2  3  4  5  6  7  8  9 10 11
Unshuffled:     AC 2C 3C AD 2D 3D AH 2H 3H AS 2S 3S

1st half:       AC    2C    3C    AD    2D    3D
2nd half:          AH    2H    3H    AS    2S    3S

Result:         AC AH 2C 2H 3C 3H AD AS 2D 2S 3D 3S
Original Index:  0  6  1  7  2  8  3  9  4 10  5 11
```

The "faro in shuffle" is slightly different. It splits a deck in half, interleaves the cards of the two halves, and moves the original top card to second and the original bottom card to second from the bottom. As an example, using a 12-card deck, an in shuffle would conceptually look like this:
```
Index:           0  1  2  3  4  5  6  7  8  9 10 11
Unshuffled:     AC 2C 3C AD 2D 3D AH 2H 3H AS 2S 3S

1st half:          AC    2C    3C    AD    2D    3D
2nd half:       AH    2H    3H    AS    2S    3S

Result:         AH AC 2H 2C 3H 3C AS AD 2S 2D 3S 3D
Original Index:  6  0  7  1  8  2  9  3 10  4 11  5
```

For more information, refer to [the Wikipedia page for the Faro shuffle](https://en.wikipedia.org/wiki/Faro_shuffle).

In Part 2 of the assignment, we will write code to find the number of out-shuffles and in-shuffles needed to bring a deck back to its factory order, as well as the shortest sequences of out-shuffles and in-shuffles needed to bring each card of a deck to the top of the deck.


## Submit
Upload the following files to **Gradescope**:

- Card.java
- ArrayDeck.java
- **A completed readme.txt file,** including your names, etc.

Also, be sure to **indicate your group member on Gradescope**.
