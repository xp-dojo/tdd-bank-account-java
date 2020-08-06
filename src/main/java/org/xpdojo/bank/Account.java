package org.xpdojo.bank;

public class Account {

    private int balanceAmount;

    public Account() {
        this.balanceAmount = 0;
    }

    public int balance() {
        return balanceAmount;
    }

    public void deposit(int amount) {
        balanceAmount = balanceAmount+amount;
    }
}
