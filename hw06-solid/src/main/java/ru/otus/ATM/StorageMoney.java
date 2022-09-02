package ru.otus.ATM;

import ru.otus.Banknotes.Banknote;

public interface StorageMoney {

    void loadATM (Banknote banknote);
    void getMoney(int count);
    int isBalance();
    void sheetBalanceOfATM ();
}
