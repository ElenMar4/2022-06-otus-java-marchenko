package ru.otus.ATM;

import ru.otus.banknotes.BanknoteToNominalRuble;

public interface StorageMoney {

    void loadATM (BanknoteToNominalRuble banknote);
    void getMoney(int count);
    int isBalance();
    void sheetBalanceOfATM ();
}
