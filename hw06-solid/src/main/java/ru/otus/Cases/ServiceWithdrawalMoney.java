package ru.otus.Cases;

import ru.otus.Banknotes.Banknote;

public interface ServiceWithdrawalMoney {
    void loadCases(Banknote banknote);
    boolean cashOut(int count);
    int  issueRemainingMoney();
    void toStringBalanceATM();
}
