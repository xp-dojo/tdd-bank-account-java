package org.suggs.katas.bankaccount;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.suggs.katas.bankaccount.Account.anAccountWithABalanceOf;
import static org.suggs.katas.bankaccount.Account.anEmptyAccount;
import static org.suggs.katas.bankaccount.Money.aMonetaryAmountOf;

public class WithAnAccountWeCan {

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        Account account = anAccountWithABalanceOf(aMonetaryAmountOf(0.0d));
        account.deposit(aMonetaryAmountOf(10.0d));
        assertThat(account).isEqualTo(anAccountWithABalanceOf(aMonetaryAmountOf(10.0d)));
    }

    @Test
    public void withdrawAnAmountToDecreaseTheBalance() {
        Account account = anAccountWithABalanceOf(aMonetaryAmountOf(20.0d));
        account.withdraw(aMonetaryAmountOf(10.0d));
        assertThat(account).isEqualTo(anAccountWithABalanceOf(aMonetaryAmountOf(10.0d)));
    }

    @Test
    public void transferMoneyFromOneAccountToAnother() {
        Account destinationAccount = anEmptyAccount();
        Account sourceAccount = anAccountWithABalanceOf(aMonetaryAmountOf(50.0d));

        sourceAccount.transfer(aMonetaryAmountOf(20.0d), destinationAccount);

        assertThat(sourceAccount).isEqualTo(anAccountWithABalanceOf(aMonetaryAmountOf(30.0d)));
        assertThat(destinationAccount).isEqualTo(anAccountWithABalanceOf(aMonetaryAmountOf(20.0d)));
    }

}
