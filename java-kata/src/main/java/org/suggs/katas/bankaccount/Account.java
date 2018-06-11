package org.suggs.katas.bankaccount;

import lombok.EqualsAndHashCode;

import static org.suggs.katas.bankaccount.Money.anAmountOf;

@EqualsAndHashCode
public class Account {
    private Money balance;

    public static Account anAccountWith(Money amount) {
        return new Account(amount);
    }

    public static Account anEmptyAccount() {
        return new Account(anAmountOf(0.0d));
    }

    private Account(Money anAmount) {
        this.balance = anAmount;
    }

    public void deposit(final Money anAmount) {
        balance.add(anAmount);
    }

    public void withdraw(final Money anAmount) {
        balance.less(anAmount);
    }

    public void transfer(Money money, Account destinationAccount) {
        destinationAccount.deposit(money);
        this.withdraw(money);
    }
}
