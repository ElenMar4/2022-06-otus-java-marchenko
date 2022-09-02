package ru.otus.Cases;

public interface Case {

    void addBanknoteInCase();
    int takeMoneyOutCase (int count);
    int calculateBalanceOfCase ();
    int getQuantityBanknote();
    void toStringBalanceOfCase ();
}
