package org.suggs.katas.bankaccount;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Money {
    private double amount;

    private Money(final double anAmount) {
        this.amount = anAmount;
    }

    public static Money anAmountOf(final double anAmount) {
        return new Money(anAmount);
    }

    public void add(final Money anAmount) {
        amount = this.amount + anAmount.amount;
    }

    public void less(final Money anAmount) {
        amount = this.amount - anAmount.amount;
    }
}
