package org.suggs.katas.bankaccount

import org.scalatest._
import org.suggs.katas.bankaccount.Account.{anAccountWith, anEmptyAccount}
import org.suggs.katas.bankaccount.Money.anAmountOf

// account should be immutable
// accounts should contain collections of transactions

class BankSpec extends FlatSpec {

  "With an account we" can "deposit an amount to increase the balance" in {
    val account = anEmptyAccount()
    assert(account.deposit(anAmountOf(10.0d)) === anAccountWith(anAmountOf(10.0d)))
  }

  it can "withdraw an amount to decrease the balance" in {
    val account = anAccountWith(anAmountOf(50.0d))
    assert(account.withdraw(anAmountOf(20.0d)) === anAccountWith(anAmountOf(30.0d)))
  }
}
