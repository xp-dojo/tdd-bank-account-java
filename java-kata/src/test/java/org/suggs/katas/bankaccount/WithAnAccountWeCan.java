package org.suggs.katas.bankaccount;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.suggs.katas.bankaccount.Account.anAccountWith;
import static org.suggs.katas.bankaccount.Account.anEmptyAccount;
import static org.suggs.katas.bankaccount.Money.anAmountOf;

public class WithAnAccountWeCan {

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        Account account = anAccountWith(anAmountOf(0.0d));
        account.deposit(anAmountOf(10.0d));
        assertThat(account).isEqualTo(anAccountWith(anAmountOf(10.0d)));
    }

    @Test
    public void withdrawAnAmountToDecreaseTheBalance() {
        Account account = anAccountWith(anAmountOf(20.0d));
        account.withdraw(anAmountOf(10.0d));
        assertThat(account).isEqualTo(anAccountWith(anAmountOf(10.0d)));
    }

    @Test
    public void transferMoneyFromOneAccountToAnother() {
        Account destinationAccount = anEmptyAccount();
        Account sourceAccount = anAccountWith(anAmountOf(50.0d));

        sourceAccount.transfer(anAmountOf(20.0d), destinationAccount);

        assertThat(sourceAccount).isEqualTo(anAccountWith(anAmountOf(30.0d)));
        assertThat(destinationAccount).isEqualTo(anAccountWith(anAmountOf(20.0d)));
    }

}
