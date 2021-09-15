package org.xpdojo.bank;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    @Test
	//@Ignore
    public void depositAnAmountToIncreaseTheBalance() {
        //assertThat("your first test isn't implemented", true, is(false));
        //arrange
        Account account = new Account();

        //act (add some money)
        account.deposit(100);
        //assert
        assertThat(account.balance, is(100));
    }

    @Test
    public void startingBalanceIsZero() {
        Account account = new Account();
        assertThat(account.balance, is(0));
    }

    @Test
    public void depositingMultipleAmounts() {
        Account account = new Account();
        account.deposit(100);
        account.deposit(300);
        assertThat(account.balance, is(400));
    }

    @Test
    public void withdrawAnAmount() {
        Account account = new Account();
        account.withdraw(100);
        assertThat(account.balance, is(-100));
    }

    @Test
    public void depositAndWithdrawAnAmount() {
        Account account = new Account();
        account.deposit(400);
        account.withdraw(100);
        assertThat(account.balance, is(300));
    }

    @Test
    public void transferAnAmountFromOneAccountToAnother() {
        Account account1 = new Account(), account2 = new Account();
        account1.deposit(100);
        account1.transfer(100, account2);
        assertThat(account1.balance, is(0));
        assertThat(account2.balance, is(100));

    }
}
