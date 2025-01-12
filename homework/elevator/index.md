---
title: "Homework 8: Elevator Simulation"
layout: default
---

# {{ page.title }}
In this homework, you will implement the `Elevator` and `Floor` clases for the elevator simulation project. You should use the textbook's [Stack](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Stack.html) and [Queue](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Queue.html) classes in your code.

## Instructions
We encourage you to **work with a partner**.

### Task 1: Review the Starter Files
Download [elevator.zip](elevator.zip), which contains the starter files for this assignment.

Next, review the provided classes:
- `Building`
- `Elevator`
- `ElevatorException`
- `Floor`
- `ManualOperator`
- `Passenger`

All the classes except the `Elevator` and `Floor` classes have been completed for you. After implementing `Elevator` and `Floor`, you can interact with the simulation by running the `ManualOperator` class.

Initially, there should be 14 people waiting on the 1st and 5th floors, and the elevator should be empty. Note that people are represented by their destinations, and the elevator's position is represented by an `E`:
```
> java-introcs ManualOperator
Options: (p)rint state, (a)scend, (d)escend, (l)oad, (u)nload
> p
5   1 1 1 1 1 2 3 4 2 3 4 2 3 4 
4   
3   
2   
1 E 5 5 5 5 5 2 3 4 2 3 4 2 3 4 
Elevator: 
```

After loading the elevator on the 1st floor, ascending a floor, then unloading on the 2nd floor, several people have made it to their destination:
```
> l
> a
> u
> p
5   1 1 1 1 1 2 3 4 2 3 4 2 3 4 
4   
3   
2 E 
1   4 2 3 4 
Elevator: 3 4 3 5 5 5 5 5 
```

**Note:** The provided classes have dependencies on each other:
- `Building` uses: `Floor` and `Elevator`
- `Elevator` uses: `Floor`, `Passenger`, and `ElevatorException`
- `Floor` uses: `Passenger`
- `ManualOperator` uses: `Building`, `Elevator`, `Floor`, `Passenger`, and `ElevatorException`
- Whereas `Passenger` and `ElevatorException` don't use any of the other classes

As a result, the classes must be compiled in a specific order. For example, this ordering will always work: `Passenger`, `ElevatorException`, `Floor`, `Elevator`, `Building`, then `ManualOperator`. Alternatively, you can use IntelliJ's "Build Project" option, which will automatically compile the classes in the proper order.

### Task 2: Implement Floor.java
On each floor, passengers wait in line for the elevator. The first person to arrive in line should be the first person to board the elevator when it arrives.

Refer to the `Floor` class's method comments for additional implementation details.

Before implementing `Floor`, the building visualization won't show the passengers:
```
> java-introcs ManualOperator
Options: (p)rint state, (a)scend, (d)escend, (l)oad, (u)nload
> p
5   Floor@30c7da1e
4   Floor@5b464ce8
3   Floor@57829d67
2   Floor@19dfb72a
1   Floor@17c68925
Elevator: Elevator@7e0ea639
```

After implementing `Floor`, the building visualization should show the passengers:
```
> java-introcs ManualOperator
Options: (p)rint state, (a)scend, (d)escend, (l)oad, (u)nload
> p
5   1 1 1 1 1 2 3 4 2 3 4 2 3 4 
4   
3   
2   
1   5 5 5 5 5 2 3 4 2 3 4 2 3 4 
Elevator: Elevator@2d6e8792
```

**Hint:** Consider whether a [Stack](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Stack.html) or [Queue](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Queue.html) is appropriate for keeping track of the passengers on each `Floor`. Use the data structures provided by the textbook authors, instead of the versions included in the Java standard library.

**Hint:** Override the default `toString()` method and depict the passengers waiting for the elevator. It will be easiest if you use the `toString()` method of the data structure you're using.

**Hint:** Create unit tests for `Floor`.

### Task 3: Implement Elevator.java
The elevator should initially start on the 1st floor, and can travel to any of the floors in the building by moving one floor at a time. If the elevator operator tries to travel to a non-existent floor, an `ElevatorException` should be raised.

The elevator should have a limited capacity for passengers. The elevator should be filled from back to front, so that the first person to enter the elevator should be the last person to exit the elevator when they disembark.

Refer to the `Elevator` class's method comments for additional implementation details.

After implementing `Elevator`, test that you can use the `ManualOperator` class to transport all the passengers to their destinations.

**Hint:** Consider whether a [Stack](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Stack.html) or [Queue](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Queue.html) is appropriate for keeping track of the passengers in the `Elevator`. Use the data structures provided by the textbook authors, instead of the versions included in the Java standard library.

**Hint:** Override the default `toString()` method and depict the passengers riding in the elevator. It will be easiest if you use the `toString()` method of the data structure you're using.

**Hint:** Create unit tests for `Elevator`.

{% comment %}
Possible extensions:
- Have button state, door state for the elevator
- Have students develop scheduling algorithms for the elevator
- Use event-driven programming so passengers can enter when the door opens
{% endcomment %}

## Submit
Upload the following files to **Gradescope**:

- Floor.java
- Elevator.java
- **A completed readme.txt file,** including your names, etc.

Also, be sure to indicate your group member on Gradescope.

## Learning Goals
- Practice using stacks and queues
- Practice using exceptions
- Practice debugging
