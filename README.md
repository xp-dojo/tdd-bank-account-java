# Bank account kata

## Overview

This session will be fun.  That's the primary objective, have fun and code ... that's it!  In this session we will talk for a little bit about why XP practices are important and then we can write some code practicing two of the most fundamental ones.  The code will be super easy, this is not about writing complicated code ... quite the opposite.  We will get pairs of you to build really simply test driven code. It will make you rethink how you write code from now on.  For those of you after more of a challenge, we have included some optional Object Oriented (calisthenics) rules to apply.

Come in pairs or make new friends on the day.  Please all __bring your own laptop__ (we dont have any to share) with your favourite IDE installed (we will have prepared using IntelliJ and trust that Eclipse will work).


## What will this kata teach us

Two key things to learn / practice in this Kata:

1. Test Driven Development
1. Pair programming

### Test Driven Development

Test driven development is based on the principles of test-first development (where you write the test first) but goes an extra step to actually driving the code using the IDE.  The basic cycle follows the __<span style="color: red">Red</span> -> <span style="color: green">Green</span> -> <span style="color: blue">Refactor</span>__ model:

 - __Red__: write a failing test.  Write a test that describes (think documentation) what the function you are writing actually does.  Likely this will not even compile (this is fine, not compiling IS a failing test).
 - __Green__: now write enough of an implementation to make the test pass.  You should write the simplest code possible to make the code pass and resist the urge to write more than is actually needed.  Consider the [YAGNI](https://martinfowler.com/bliki/Yagni.html) principle.
 - __Refactor__: now we re-read the code and make sure that this is good enough to push to the _world at large_.  You should ask yourself at this point how the next person that reads this code will experience it.

As a walkthrough consider applying TDD as a two stage process, the first phase writes the API in the test __as it should be__ (write the code you would like someone else to have written for you).  In this case the compiler _IS_ the failing test, you rewrite it until you are happy and then to make it go green you use the IDE to create the classes and methods as per the test (dont type them, let the IDE do the work).  The next phase of the cycle implements the methods to get the unit tests passing, followed by the refactoring to complete the RGR cycle described above.  The key message here is that you should consider the compiler failing as a failing test to allow you to get it green (alt+enter until it all compiles).

### Pair programming

There are many different ways to do pair programming, the most common model is the Driver-Navigator model.  For this kata, try and follow as below for simplicity.  There are two roles in this model (you should switch often to keep it interesting):

 - __The Driver__ is the person wiring the code (test driven) and implementing.  The Driver should be explaining what they are doing in a running monologue so the Navigator understands the direction taken and can assess it (also it keeps the Navigator engaged).
 - __The Navigator__ is the person observing and thinking about the big picture.  The best Navigators are those that ask __why?__ often to check that we __build the right thing, and build the thing right__.  For this kata, the Navigator is checking that they are really test driving (using the IDE) and that the code __does what it says and says what it does__.  When applying object calisthenics the Navigator should be checking that the Nine rules are not being broken.  Correction and learning is the key here.


## Requirements to drive the kata

 - I can Deposit and Withdraw money from accounts
 - I can Transfer amounts between accounts (if I have the funds)
 - I can print out an Account balance (date, amount, balance)  
 - I can print a statement of account activity (statement)  
 - I can apply Statement filters (just deposits, withdrawal, date)


## Going the extra mile

### Using object calisthenics (bold ones are MUST)

See the links below for more details on these:

1. __One level of indentation per method__
1. __Don’t use the ELSE keyword__
1. __Wrap all primitives and Strings__
1. __No getters/setters/properties__
1. __First class collections__
1. One dot per line
1. Don’t abbreviate
1. Keep all entities small (50 lines)
1. No classes with more than two instance variables

### Additional information

- Originated from Sandro Mancuso and the LSCC
-  [Object Calisthenics pdf](http://www.cs.helsinki.fi/u/luontola/tdd-2009/ext/ObjectCalisthenics.pdf)
-  Original idea for the kata: [How Object-Oriented Are You Feeling Today?](https://www.slideshare.net/KrzysztofJelski/how-object-oriented-are-you-feeling-today) - Krzysztof Jelski (Session on the Software Craftsmanship UK 2011 conference)
-  Object Calisthenics (full book), Jeff Bay in: The ThoughtWorks Anthology.
Pragmatic Bookshelf 2008