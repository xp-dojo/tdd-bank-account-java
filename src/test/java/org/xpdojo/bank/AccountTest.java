package org.xpdojo.bank;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        Account account =new Account();
        assertEquals( 0, account.balance());
    }

    @Test
    public void depositAmountShouldIncreaseBalance() {
        Account account = new Account();
        account.deposit(10);
        assertThat(account.balance()).isEqualTo(10);
    }

    @Test
    public void depositmultipleAmountShouldIncreaseBalance() {
        Account account = new Account();
        account.deposit(10);
        account.deposit(3);
        assertThat(account.balance()).isEqualTo(13);
    }


}
