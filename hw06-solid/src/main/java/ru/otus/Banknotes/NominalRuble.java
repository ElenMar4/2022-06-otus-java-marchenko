package ru.otus.Banknotes;

public enum NominalRuble implements Banknote{
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private final int value;

    NominalRuble (int value){
        this.value = value;
    }

    public int returnNominal (){
        return value;
    }
}
