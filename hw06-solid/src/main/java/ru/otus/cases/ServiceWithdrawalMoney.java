package ru.otus.cases;

import ru.otus.banknotes.BanknoteToNominalRuble;

public interface ServiceWithdrawalMoney {
    void loadCases(BanknoteToNominalRuble banknote);
    boolean cashOut(int count);
    int  issueRemainingMoney();
    void toStringBalanceATM();
}
