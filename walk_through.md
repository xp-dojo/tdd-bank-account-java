# Worked Example

----
## Overview

This branch contains an example of how we can implement the initial **Deposit** requirement using TDD.
It is illustrative only, and is by no means the only way to implement the requirement.

Each incremental change is documented in this file and recorded with a commit, so you can follow the commit history,
and/or read through this file to follow along.

---
## Steps

We start with an empty project and no code.

We want to implement the deposit behaviour.
Since we're doing TDD we need to start with a test, so we create the `AccountTest`.
We believe our first test should ensure that depositing an amount increases the account balance by the same amount, so we name our test case accordingly.

We're going to need a class to represent our account. We decide we'll call it `Account`, so we call the constructor of this non-existent class in our test.

We now have a compilation failure in our test, which counts as a failing test, so we fix it by creating the `Account` class.

We realise we need to store this `Account` object in order to use it, so we assign it to a variable.

We begin to write the deposit method, using an `int` data type to represent the amount deposited.
We know we can change the data type later if required so we keep it simple for now.

At this point we realise we can only verify the deposit behaviour if we can also check the balance afterwards.
But that's two behaviours, *depositing* and *checking balances*! And we like our tests to test one thing.
We pause for a second and wonder if there's a simpler test we can write...?
We decide there is... what if we simply check the balance of a new account instead?
We change the test to reflect this simpler scenario, renaming the test case and calling the non-existent `balance()` method.
But what should the balance of a new account be...? How about zero!

We implement `balance()` to return `-1`.
Our test should fail so we run and check that it does.
We also confirm that it fails for the right reason: we expected `0` and got `-1`.

The simplest way to make this test pass is to always return `0` instead, so we do that.
We run our test and watch it pass.

We've now been through the first couple of steps of the TDD cycle, *red* and *green*, so we consider if there's anything to *refactor*.
Our `Account` class is about as short and simple as it can get. But we can also refactor our test code.
On reflection we can be more explicit when constructing the `Account` object.
We can express our *intent* more clearly by replacing the constructor with a *factory method*.
We call this method `emptyAccount()` to reflect the fact that new accounts should be empty, i.e. they should have a balance of zero.
Our test code is now even clearer, and we've also made our production code more explicit that we can only create empty accounts.
We rerun our test to ensure that we haven't changed the behaviour (i.e. broken our test).

Now we return to our original test case: depositing an amount, which should increase the balance by that amount.
We now know that we can start with an empty account, so we don't need to compare the balance before and after the deposit.
Instead we can simply deposit to an empty account and ensure that the final balance is equal to the deposited amount.

We create the `deposit()` method, but we leave the implementation empty for now.
We run our tests and see our new test fail. It expects the balance to be `10` but the balance was `0`.
This is precisely what we expected so we can now safely implement the method, having seen our test go *red*.

We have a failing test so now we need to write the production code to make it pass.
But before we rush headlong into a full implementation, we remember that we should write the minimum amount of code to make the test pass.
We think for a second... what's the least amount of code we could write to make the test pass?
We decide we'll need to store the balance at least, so we refactor the existing `balance()` method, extracting a field, which we also call `balance`.
We run the tests to ensure that our original balance test still passes, and that our new deposit test still fails for the same reason as before.

Now we can use our new field in the `deposit()` method.
But we're still wondering... what's *the simplest thing that could possibly work* to make our test pass...?
Eureka! What if we simply set the balance to `10`?!
We do this, then we run our test and watch it pass!

We recognise at this point that, although our tests pass, our implementation won't work in all scenarios.
Reflecting on this, we realise what it means... we're missing a test!
So we write another test case, using *triangulation* to zero in on our complete solution ("*as the tests get more specific the code gets more generic*").
"Isn't that lucky?" we think, noting that if we'd jumped straight to the solution we would have missed a valuable test case.
We decide that the next simplest failing test would involve multiple deposits, so we add that test. 
We run it and sure enough it fails!

We decide that the simplest thing we can do now is to increment the `balance` field by the `amount` of the `deposit()`.
So that's what we do, before running our tests and watching them all pass!
We're now confident that we have a solid implementation of the *deposit* and *balance* functionality, and that it's all covered by tests!

At this point we take a step back. Earlier on we decided to use an `int` data type to represent our balance and the amount we deposit.
Looking at the public interface of our `Account` we can see that it talks about accounts, balances, deposits and amounts.
All of these would make sense if we were discussing the code with our business user/customer.
But what is an `int`? Would our business user/customer understand this? Even if they would, should they have to?
We think back to when we first learned about [Domain-Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design).
Accounts, balances, deposits and amounts all seem like domain concepts.
They're all at the same level of abstraction, and they all make sense to anyone familiar with banking.
But an `int`...? It seems like there's a concept missing from our domain model here. But what is it?
So we ask ourselves, "when we deposit an amount, or ask for the balance and receive an amount, what does the amount refer to...
in other words, it's an amount of what?"
It doesn't take us long to realise that our missing concept is *money*, so we decide to introduce a new class, `Money`.
Not only will this fix our interface to use a single level of abstraction, it will also enable us to model money with a different data type if we so choose,
e.g. one that supports non-integer amounts of money (e.g. dollars *and* cents, or pounds *and* pence).
Even better, any such change will be internal to our new class and hidden from client code, meaning we won't break any client code by introducing such a change.
It will also enable us to add other features of money, e.g. currency, if they become requirements.

We could go ahead and introduce the `Money` class and change all of our tests and code to use it.
But if we change everything at once we might easily miss something or make a mistake and end up staring at the debugger and pulling our hair out.
Luckily we understand the benefits of TDD, so we decide to introduce this new concept one failing test at a time.
But where to start? We currently use an `int` in three places: the `balance()` method; the `deposit()` method; and the private `balance` field.
The first two are externally visible on our interface, so we decide to pick one of those.
It doesn't really matter which one, so we choose the `balance()` method.
We know we'd like to work on one test at a time so we start by changing our initial test case to say that `balance()` should return a `Money`.

We create the `Money` class, then run all of our tests and watch our changed test fail.
We can see that it expected a `Money` but it actually received an `int` of `0`, which is the failure we expected.

Now we ask ourselves, what's the best way to make this test pass?
We could change the `balance()` method to return a `Money` object, but this would break all the other tests that call the method.
Instead, we decide to use a refactoring pattern called [Parallel Change](https://martinfowler.com/bliki/ParallelChange.html) (a.k.a. Expand and Contract),
wherein we make a change in three phases: expand the existing interface, migrate all usages, and then contract the interface again.
We start by changing the test to call a new version of the `balance()` method - we call this new method `balanceAsMoney()`
(it doesn't really matter what we call it, as we'll see later).

As our code doesn't compile, we implement this new method, returning a null.
We re-run our test and again watch it fail.
It still expected a `Money` but it now received `null`. This is the failure we expect, and a step closer towards our solution.

As before, the simplest way to make this test pass is to always return zero, in this case a `Money` object that represents zero, so we do that.
However, instead of passing we still have a failing test. We see something like the following:
```
Expected :org.example.bank.Money@5f8ed237
Actual   :org.example.bank.Money@57fa26b7
```
As seasoned Java developers, we immediately recognise the problem.
By default, Java uses *reference equality* to determine if two objects are equal.
So if two variables refer to the same underlying object then they're considered equal, and if they don't then they're not.
As we create one `Money` object in our test and another in our `balanceAsMoney()` method, our test fails as our objects aren't considered equal.
What we need instead is *value equality*, whereby two objects are considered equal if they have the same value.
(As it happens, bank notes provide a great example of reference vs value equality.
Two $10 bills are two different physical objects, so do not have reference equality.
But they both have a value of $10, so they do have value equality.)

Luckily Java provides an easy way to implement whatever equality mechanism we want, by overriding the default `Object.equals()` method.
We could just go ahead and implement this, and make our test pass.
But this is an account test and we'll actually be adding a behaviour to `Money`.
So we should really drive this behaviour out more specifically, directly on the `Money` class.
We begin by creating the `MoneyTest` and writing a simple test case for value equality, which fails, replicating the failure we see in the `AccountTest`.

We then create a field in `Money` to store the amount, and get our IDE to generate an `equals()` method which compares this amount, amongst other things.
(We also generate a `hashcode()` method as its [contract is heavily dependent](https://www.baeldung.com/java-equals-hashcode-contracts) on `equals()`.)
We could implement this ourselves but it's fairly mechanical and the IDE does a great job of it, so we save ourselves the effort and the opportunity for error.
We run our test and watch it pass.
We then run our `AccountTest` too, which now also passes.

Although we've generated the `equals` method and we're pretty sure it works, we should still test it.
Not only to prove that it works as we expect, but also to alert us if we break it in the future.
Looking at the implementation, we can see a few branches of logic we may want to test, e.g. unequal amounts, a null object, or a different class of object.
We add some tests to cover these branches, running each one as we add it and watching it pass.

Now we can return to the `AccountTest`.
We've migrated one usage of `balance()` to use `balanceAsMoney()` instead.
Now we move on to the next usage, changing it accordingly.
We run the test and watch it fail, expecting it to tell us that we wanted `10` and got `0`, but instead we get a familiar message:
```
Expected :org.example.bank.Money@29
Actual   :org.example.bank.Money@1f
```
What's going on here? Sure, we don't expect our two `Money` objects to be equal, but the error message isn't very helpful.
We realise it's because the failing test is printing out the result of the `toString()` method from each of our objects,
which by default is the fully qualified class name, suffixed with a hexadecimal representation of the hashcode.
We'd really like to see a more meaningful output here, so again, we switch to the `MoneyTest` to specify how `toString()` should behave.
We arbitrarily decide that it should return the amount prefixed with a `$` sign, for now at least.
We run the test and watch it fail.

We implement our `toString()` method accordingly.
Our test passes so we go back to our `AccountTest`.
We now see a more meaningful output:
```
Expected :$10
Actual   :$0
```

We change our new `balanceAsMoney()` method to return the account balance, wrapped as a `Money` object.
We run our test and watch it pass.

We now migrate our final usage of `balance()` to use `balanceAsMoney()` instead.
We run our test and watch it pass, as we expect, as the new behaviour is now equivalent to the old.

As the old `balance()` method is no longer used, we can safely delete it.
We do this, run our tests and watch them pass.

We rename the new `balanceAsMoney()` method to `balance()`, completing the migration of the balance behaviour to use the new `Money` class.
We run our tests and watch them pass.

We now take a step back and look at our recent changes to see if there's anything worth refactoring.
We decide to make our code a bit more fluent and readable by using the *factory method* pattern, as we did before.
This time we apply it to the `Money` constructor, which we replace with a new method called `amountOf()`.

Now we return to the interface for our `Account`.
We've migrated our `balance()` method to use `Money` and we'd like to do the same with the `deposit()` method.
Again, we'll work on one test at a time, using the *parallel change* refactoring pattern.
We start by changing our initial deposit test case to say that `deposit()` should take a `Money` parameter, rather than an `int`.
Unlike our `balance()` method, `deposit()` takes a parameter, so we can overload it (i.e. reuse the same name).

We add the new `deposit()` method with an empty body, run the test and watch it fail.

We can make the test pass simply by setting the balance to the incoming amount.
However, `amount` is a `Money` and `balance` is an `int`.
Luckily `Money` currently contains an `int` to represent its amount.
What if we temporarily reached inside and used that?
(We wouldn't normally expose the internal state of an object, but it can be useful *temporarily* whilst refactoring, without compromising long-term safety.)
We add an accessor method (getter)  for `Money.amount`, use it to set the balance, then run our test, which now passes!

Now we can move on to our next deposit test. We amend that to use the new `deposit()` method, run the test and watch it fail.

We change our new `deposit()` method to increment the balance by the incoming amount, run the test and watch it pass.

We can now safely delete the old `deposit()` method as it's no longer used.
We do this, run our tests and watch them pass.

We've now changed the public interface of our `Account` class to talk solely in domain terms.
But there's still that pesky `int` to represent the balance, and because of that we still have a nasty accessor method on `Money`.
We finish off the migration to `Money` by changing the `balance` field to use it.
As the public behaviour of our class shouldn't change, we don't need to change any tests.
We simply change the type of the `balance` field, and modify the `balance()` and `deposit()` methods accordingly.
We run our tests and watch them all pass.

Now the `deposit()` method [smells](https://martinfowler.com/bliki/CodeSmell.html) a little of [feature envy](https://refactoring.guru/smells/feature-envy).
It gets the amounts from two `Money` objects, adds them together and creates a new `Money` object from the sum.
It sounds like this behaviour should be on the `Money` class, and not `Account`.
We start by extracting the behaviour to a new method on `Account`, which we'll call `plus()`, as it's equivalent to the `+` operator.
After doing this we run our tests, which still pass.

Next, we move the new `plus()` method to the `Money` class, where it belongs.
We run our tests and they pass.
Now our `Account` class is a lot cleaner, and is written purely in domain terms.

What's more, now this behaviour is in `Money`, we can clean it up a bit, inlining the calls to `getAmount()`.
Remember, we only needed this method because the arithmetic was being performed outside of `Money`, and we needed access to the internal amount.
Once the arithmetic is where it belongs, we no longer need it, so we delete it accordingly.
Our `Money` class is now in much cleaner shape too.

As we've introduced a new method on `Money`, we may wish to define and test its behaviour more fully in the `MoneyTest`.
We'd then want to implement the rest of the account features from our requirements.
But we don't want to have all the fun, so we'll leave those as exercises for the reader... :)
