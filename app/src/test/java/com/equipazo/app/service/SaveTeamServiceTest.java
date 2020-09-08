package com.equipazo.app.service;

public class SaveTeamServiceTest {
//
//    private final LoadAccountPort loadAccountPort =
//            Mockito.mock(LoadAccountPort.class);
//
//    private final AccountLock accountLock =
//            Mockito.mock(AccountLock.class);
//
//    private final UpdateAccountStatePort updateAccountStatePort =
//            Mockito.mock(UpdateAccountStatePort.class);
//
//    private final SendMoneyService sendMoneyService =
//            new SendMoneyService(loadAccountPort, accountLock, updateAccountStatePort, moneyTransferProperties());
//
//
//    @Test
//    void transactionSucceeds() {
//
//        Account sourceAccount = givenSourceAccount();
//        Account targetAccount = givenTargetAccount();
//
//        givenWithdrawalWillSucceed(sourceAccount);
//        givenDepositWillSucceed(targetAccount);
//
//        Money money = Money.of(500L);
//
//        SendMoneyCommand command = new SendMoneyCommand(
//                sourceAccount.getId().get(),
//                targetAccount.getId().get(),
//                money);
//
//        boolean success = sendMoneyService.sendMoney(command);
//
//        assertThat(success).isTrue();
//
//        AccountId sourceAccountId = sourceAccount.getId().get();
//        AccountId targetAccountId = targetAccount.getId().get();
//
//        then(accountLock).should().lockAccount(eq(sourceAccountId));
//        then(sourceAccount).should().withdraw(eq(money), eq(targetAccountId));
//        then(accountLock).should().releaseAccount(eq(sourceAccountId));
//
//        then(accountLock).should().lockAccount(eq(targetAccountId));
//        then(targetAccount).should().deposit(eq(money), eq(sourceAccountId));
//        then(accountLock).should().releaseAccount(eq(targetAccountId));
//
//        thenAccountsHaveBeenUpdated(sourceAccountId, targetAccountId);
//    }
}
