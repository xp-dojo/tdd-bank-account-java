package org.suggs.katas.bankaccount;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Money {
    private final double amount;

    private Money(double anAmount) {
        this.amount = anAmount;
    }

    public static Money aMonetaryAmountOf(double anAmount) {
        return new Money(anAmount);
    }

    public Money add(Money anAmount) {
        return new Money(this.amount + anAmount.amount);
    }

    public Money less(Money anAmount) {
        return new Money(this.amount - anAmount.amount);
    }
}
