package org.xpdojo.bank;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
	@Ignore
    public void depositAnAmountToIncreaseTheBalance() {
        Account account =new Account();
        assertEquals( 0, account.balance());
    }
}
