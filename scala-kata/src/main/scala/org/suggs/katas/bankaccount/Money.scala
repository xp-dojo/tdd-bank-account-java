package org.suggs.katas.bankaccount

object Money {
  def anAmountOf(amount: Double) = new Money(amount)
}

case class Money(amount: Double) {

  def less(subtraction: Money): Money = Money(amount - subtraction.amount)

  def add(addition: Money): Money = Money(amount + addition.amount)
}