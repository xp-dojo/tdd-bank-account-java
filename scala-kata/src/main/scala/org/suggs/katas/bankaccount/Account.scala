package org.suggs.katas.bankaccount

import org.suggs.katas.bankaccount.Money.anAmountOf

object Account {
  def anAccountWith(money: Money) = Account(money)

  def anEmptyAccount() = Account(anAmountOf(0.0d))

}

case class Account(amount: Money) {
  def withdraw(money: Money) = Account(amount.less(money))

  def deposit(money: Money) = Account(amount.add(money))

}
