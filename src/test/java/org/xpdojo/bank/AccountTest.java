package org.xpdojo.bank;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class AccountTest {


    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account();
    }

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        assertEquals(0, account.balance());
    }

    @Test
    public void depositAmountShouldIncreaseBalance() {
        account.deposit(10);
        assertThat(account.balance()).isEqualTo(10);
    }

    @Test
    public void depositmultipleAmountShouldIncreaseBalance() {
        account.deposit(10);
        account.deposit(3);
        assertThat(account.balance()).isEqualTo(13);
    }

    @Test
    public void withdrawInsufficientAmount() {
        assertThat(account.withdraw(5)).isFalse();
        assertThat(account.balance()).isEqualTo(0);
    }

    @Test
    public void withdrawAmount() {
        account.deposit(8);
        account.withdraw(5);
        assertThat(account.balance()).isEqualTo(3);
    }

    @Test
    public void transferMoneyBetweenAccounts() {
        Account accountSendTo =new Account();
        account.deposit(12);
        account.transfer(10, accountSendTo);
        assertThat(account.balance()).isEqualTo(2);
        assertThat(accountSendTo.balance()).isEqualTo(10);
    }
}
